package com.example.SunPanel;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.SunPanel.RozSunPanel.KPDPanel;

import java.util.Arrays;

import java.util.Random;



public class UserActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{
    TextView progressW;
    TextView  progressOS;
    Button delButton;
    Button saveButton;
    Button countButton;
    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    EditText nameBox;
    EditText kolovoBox;
    EditText tempEnd;
    EditText starttempBox;
    EditText a ;
    EditText b;
    String time;
    EditText holosthod,TimeStart,TimeEnd;
    EditText osvet;
    EditText woltag;
    String Umax;
    String Potug,EVsr,Etsr,Woltag,Kz,Plosha,KPD,Wi,Xdolya,Ws,KPDWithoutInvertor,Wpoter,KPDregular,KPDObsh,KPDUstanov,KPDAkkum,KPDOBSHTIME,KPDObshh;
    float Amax,Wmax,  minhod3;
    float maxhod3;;
    float minhod1;
    float maxhod1;
    float minhod2,maxhod5,minhod5;
    float maxhod2,maxhod4,minhod4;

    long userId=0;
    private int td,ts;
    String[] cities = {"Off","5-10", "10-14", "14-17", "17-19", "19-21", "5-21","21-5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nameBox = (EditText) findViewById(R.id.NamePanel);
        kolovoBox = (EditText) findViewById(R.id.KolovoBox);
        starttempBox = (EditText) findViewById(R.id.tempStart);
        tempEnd = (EditText) findViewById(R.id.tempEnd);
        a = (EditText) findViewById(R.id.a);
        b = (EditText) findViewById(R.id.b);
        holosthod = (EditText) findViewById(R.id.holosthod);
        woltag=(EditText) findViewById(R.id.progressW);
        osvet=(EditText) findViewById(R.id.progressOS);
        delButton = (Button) findViewById(R.id.deleteButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        countButton= (Button) findViewById(R.id.countButton);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.cities);
        spinner.setAdapter(adapter);
        // заголовок
        spinner.setPrompt("Title");
        // выделяем элемент
        spinner.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();

                if(position==0){

                    KPDOBSHTIME= String.valueOf(0);
                    time= String.valueOf(1);
                }else
                if(position==1){
                    KPDOBSHTIME= String.valueOf(2000);
                    time= String.valueOf(300);
                }else
                if(position==2){
                    KPDOBSHTIME= String.valueOf(0);
                    time= String.valueOf(240);
                }else
                if(position==3){
                    KPDOBSHTIME= String.valueOf(500);
                    time= String.valueOf(180);
                }else
                if(position==4){
                    KPDOBSHTIME= String.valueOf(2300);
                    time= String.valueOf(120);
                }else
                if(position==5){
                    KPDOBSHTIME= String.valueOf(4000);
                    time= String.valueOf(120);
                } else if(position==6){
                    KPDOBSHTIME= String.valueOf(1760);
                    time= String.valueOf(960);
                }else if(position==7){
                    KPDOBSHTIME= String.valueOf(Integer.parseInt(String.valueOf(osvet.getText()))-1);
                    time= String.valueOf(0);
                }


            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        sqlHelper = new DataBaseHelper(this);
        db = sqlHelper.open();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
        // если 0, то добавление
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE + " where " +
                    DataBaseHelper.COLUMN_IDC + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            nameBox.setText(userCursor.getString(1));
            kolovoBox.setText(userCursor.getString(2));
            starttempBox.setText(userCursor.getString(3));
            tempEnd.setText(userCursor.getString(4));
            a.setText(userCursor.getString(5));
            b.setText(userCursor.getString(6));
            holosthod.setText(userCursor.getString(7));
            osvet.setText(userCursor.getString(9));
            woltag.setText(userCursor.getString(8));

            userCursor.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
        Transletor transletor = new Transletor();
        transletor.setId(userId);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        try {
            progressW = (TextView) findViewById(R.id.progressW);
        } catch (Exception e) {
            progressW.setText(0);
        }
        seekBar.setProgress( Integer.parseInt(String.valueOf(woltag.getText())));
        //progressW.setText("220");

        seekBar.setOnSeekBarChangeListener(this);

        final SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);

        progressOS = (TextView) findViewById(R.id.progressOS);


        seekBar2.setProgress(Integer.parseInt(String.valueOf(osvet.getText())));
        // progressOS.setText("0");
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                if(Integer.parseInt(String.valueOf(progressOS.getText()))<0){ progressOS.setText(0);}else {
                    progressOS.setText("" + progress2);}
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void save(View view){


            MyBd();

         goHome();
    }

    public void  MyBd(){
        ContentValues cv = new ContentValues();
        try {
            cv.put(DataBaseHelper.COLUMN_NAMEC, nameBox.getText().toString());
            cv.put(DataBaseHelper.COLUMN_KOLOVO, kolovoBox.getText().toString());
            cv.put(DataBaseHelper.COLUMN_STARTTEMP, starttempBox.getText().toString());
            cv.put(DataBaseHelper.COLUMN_TEMPEND, tempEnd.getText().toString());
            cv.put(DataBaseHelper.COLUMN_DLINA, a.getText().toString());
            cv.put(DataBaseHelper.COLUMN_SHIRINA, b.getText().toString());
            cv.put(DataBaseHelper.COLUMN_HOLOSTHOD, holosthod.getText().toString());
            cv.put(DataBaseHelper.COLUMN_OSVET, osvet.getText().toString());
            cv.put(DataBaseHelper.COLUMN_WOLTAG, woltag.getText().toString());
            cv.put(DataBaseHelper.COLUMN_EVSR, EVsr);
            cv.put(DataBaseHelper.COLUMN_ETSR, Etsr);
            cv.put(DataBaseHelper.COLUMN_XDOLYA, Xdolya);
            cv.put(DataBaseHelper.COLUMN_PLOSHA, Plosha);
            cv.put(DataBaseHelper.COLUMN_WI, Wi);
            cv.put(DataBaseHelper.COLUMN_KPD, KPD);
            cv.put(DataBaseHelper.COLUMN_KZ, Kz);
            cv.put(DataBaseHelper.COLUMN_UMAX, Umax);
            cv.put(DataBaseHelper.COLUMN_WS, Ws);
            cv.put(DataBaseHelper.COLUMN_KPDWITHOUTINVERTOR, KPDWithoutInvertor);
            cv.put(DataBaseHelper.COLUMN_KPDREGULAR, KPDregular);
            cv.put(DataBaseHelper.COLUMN_KPDAKKUM, KPDAkkum);
            cv.put(DataBaseHelper.COLUMN_KPDUSTANOV, KPDUstanov);
            cv.put(DataBaseHelper.COLUMN_KPDOBSH, KPDObsh);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "\tERROR\nCan`t put in DataBase", Toast.LENGTH_LONG).show();
        }
        try {
            if (userId > 0) {
                db.update(DataBaseHelper.TABLE, cv, DataBaseHelper.COLUMN_IDC + "=" + String.valueOf(userId), null);
            } else {
                db.insert(DataBaseHelper.TABLE, null, cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "\tERROR\nCan`t put in DataBase", Toast.LENGTH_LONG).show();
        }

    }
    public void delete(View view){
        db.delete(DataBaseHelper.TABLE, "_id = ?", new String[]{String.valueOf(userId)});
        goHome();
    }
    private void goHome(){
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, MainActivity2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressW.setText("" + progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    public void GOTONEXT(View view){
        calculate();


            ContentValues cv = new ContentValues();
            cv.put(DataBaseHelper.COLUMN_NAMEC, nameBox.getText().toString());
            cv.put(DataBaseHelper.COLUMN_KOLOVO, kolovoBox.getText().toString());
            cv.put(DataBaseHelper.COLUMN_STARTTEMP, starttempBox.getText().toString());
            cv.put(DataBaseHelper.COLUMN_TEMPEND, tempEnd.getText().toString());
            cv.put(DataBaseHelper.COLUMN_DLINA, a.getText().toString());
            cv.put(DataBaseHelper.COLUMN_SHIRINA, b.getText().toString());
            cv.put(DataBaseHelper.COLUMN_HOLOSTHOD, holosthod.getText().toString());
            cv.put(DataBaseHelper.COLUMN_OSVET, KPDObshh);
            cv.put(DataBaseHelper.COLUMN_WOLTAG, woltag.getText().toString());
            cv.put(DataBaseHelper.COLUMN_EVSR, EVsr);
            cv.put(DataBaseHelper.COLUMN_ETSR, Etsr);
            cv.put(DataBaseHelper.COLUMN_XDOLYA, Xdolya);
            cv.put(DataBaseHelper.COLUMN_PLOSHA, Plosha);
            cv.put(DataBaseHelper.COLUMN_WI,    Wi );
            cv.put(DataBaseHelper.COLUMN_KPD, KPD);
            cv.put(DataBaseHelper.COLUMN_KZ, Kz);
            cv.put(DataBaseHelper.COLUMN_UMAX, Umax);
            cv.put(DataBaseHelper.COLUMN_WS, Potug);
            cv.put(DataBaseHelper.COLUMN_KPDWITHOUTINVERTOR, KPDWithoutInvertor);
            cv.put(DataBaseHelper.COLUMN_KPDREGULAR, KPDregular);
            cv.put(DataBaseHelper.COLUMN_KPDAKKUM, KPDAkkum);
            cv.put(DataBaseHelper.COLUMN_KPDUSTANOV, KPDUstanov);
            cv.put(DataBaseHelper.COLUMN_KPDOBSH, KPDObsh);

            if (userId > 0) {
                db.update(DataBaseHelper.TABLE, cv, DataBaseHelper.COLUMN_IDC + "=" + String.valueOf(userId), null);
            } else {
                db.insert(DataBaseHelper.TABLE, null, cv);
            }
            Intent intent = new Intent(this, Swipe.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }



    public void calculate(){
        minhod1=0;
        maxhod1=20;

        float[] array;
        array = new float[37];
        for (int i = 0; i < array.length; i++) {
            array[i] =  minhod1 + new Random().nextFloat() * (maxhod1 - minhod1);

        }
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            //  Log.d(String.valueOf(array[i])," Napryagenie "+i+"");
        }
        Log.d(String.valueOf(array[35])," maxhodNapryagenie");
        minhod2=0;
        maxhod2=Float.parseFloat(String.valueOf(woltag.getText()))-80;

        float[] array2;
        array2 = new float[37];
        for (int i = 0; i < array2.length; i++) {
            array2[i] =  minhod2 + new Random().nextFloat() * (maxhod2 - minhod2);
        }
        Arrays.sort(array2);
        for (int i = 0; i < array2.length; i++) {
            //   Log.d(String.valueOf(array2[i])," ampertag "+i+"");
        }
        Log.d(String.valueOf(array2[35])," maxhodAmpertag");
        minhod3=0;
        maxhod3=2;

        float[] array3;
        array3 = new float[37];
        for (int i = 0; i < array3.length; i++) {
            array3[i] =  minhod3 + new Random().nextFloat() * (maxhod3 - minhod3);
        }
        Arrays.sort(array3);
        for (int i = 0; i < array3.length; i++) {
            Log.d(String.valueOf(array3[i])," woltag "+i+"");
        }
        Log.d(String.valueOf(array3[35])," maxhodWoltag");
        minhod4=2;
        maxhod4= (float) 2.5;
        float[] array4;
        array4 = new float[9];
        for (int i = 0; i < array4.length; i++) {
            array4[i] =  minhod4 + new Random().nextFloat() * (maxhod4 - minhod4);
        }
        Arrays.sort(array4);
        for (int i = 0; i < array4.length; i++) {
            //    Log.d(String.valueOf(array4[i])," TimeWT "+i+"");
        }
        float sum = 0;
        for (float value : array4) {
            sum += value;
        }
        float sredznach=sum/array4.length;
        Log.d(String.valueOf(sredznach)," SredTimeWT ");
        minhod4=2;
        maxhod4= (float) 3;
        float[] array5;
        array5 = new float[11];
        for (int i = 0; i < array5.length; i++) {
            array5[i] =  minhod4 + new Random().nextFloat() * (maxhod4 - minhod4);
        }
        Arrays.sort(array5);
        for (int i = 0; i < array5.length; i++) {
            //    Log.d(String.valueOf(array4[i])," TimeWT "+i+"");
        }
        float sum2 = 0;
        for (float value2 : array5) {
            sum2 += value2;
        }
        float sredznach2=sum2/array4.length;
        Log.d(String.valueOf(sredznach2)," SredTimeWT ");
        KPDPanel kp=new KPDPanel();
        String k=(tempEnd.getText()).toString();
        Amax=array3[35];
        Wmax=array2[35];
        Umax= String.valueOf(array[35]);
        KPDObshh= String.valueOf(Integer.parseInt(String.valueOf(osvet.getText()))-Integer.parseInt(KPDOBSHTIME));
        EVsr=String.valueOf(kp.EVsr(Integer.parseInt(KPDObshh), Integer.parseInt(String.valueOf(kolovoBox.getText()))));
        Etsr=String.valueOf(kp.Etsr(Double.parseDouble(EVsr), Integer.parseInt(k)));
        Xdolya=String.valueOf(kp.Xdolya(Double.parseDouble(String.valueOf(woltag.getText()))));
        Plosha=String.valueOf(kp.Plosha(Double.parseDouble(String.valueOf(a.getText())), Double.parseDouble(String.valueOf(b.getText()))));
        Wi=String.valueOf(kp.Wi(Double.parseDouble(Plosha), Double.parseDouble(Etsr)));
        KPD=String.valueOf(kp.KPD(Double.parseDouble(String.valueOf(Amax)), Double.parseDouble(Wi)));
        Kz=String.valueOf(kp.Kz(Double.parseDouble(String.valueOf(Wmax)),Double.parseDouble(String.valueOf(Umax)), Double.parseDouble(String.valueOf(Amax))));
        Ws=String.valueOf(kp.Ws(Double.parseDouble(Wi), Double.parseDouble(Xdolya)));
        KPDWithoutInvertor=String.valueOf(kp.KPDWithoutInvertor(Double.parseDouble(String.valueOf(sredznach2)), Double.parseDouble(String.valueOf(holosthod.getText()))));
        KPDregular=String.valueOf(kp.KPDregular(Double.parseDouble(String.valueOf(sredznach))));
        KPDAkkum= String.valueOf(kp.KPDAkkum(Double.parseDouble(String.valueOf(sredznach)), Double.parseDouble(String.valueOf(holosthod.getText()))));
        KPDUstanov=String.valueOf(kp.KPDUstanov(Double.parseDouble(KPDregular),Double.parseDouble(String.valueOf(KPDAkkum)), Double.parseDouble(String.valueOf(KPDWithoutInvertor))));
        KPDObsh=String.valueOf(kp.KPDObsh(Double.parseDouble(String.valueOf(KPD)), Double.parseDouble(String.valueOf(KPDUstanov))));
        Potug= String.valueOf(((Integer.parseInt(time)*Float.parseFloat(Ws))*Integer.parseInt(String.valueOf(kolovoBox.getText()))*(Float.parseFloat(KPDObsh))/100));


        Log.d(String.valueOf(EVsr),"EVsr");
        Log.d(String.valueOf(Etsr),"Etsr");
        Log.d(String.valueOf(Woltag),"Woltag");
        Log.d(String.valueOf(Plosha),"Plosha");
        Log.d(String.valueOf(Wi),"Wi");
        Log.d(String.valueOf(KPD),"KPD");
        Log.d(String.valueOf(Kz),"Kz");
        Log.d(String.valueOf(Xdolya),"Xdolya");
        Log.d(String.valueOf(Ws),"Ws");
        Log.d(String.valueOf(KPDregular),"KPDregular");
        Log.d(String.valueOf(KPDWithoutInvertor),"KPDWithoutInvertor");
        Log.d(String.valueOf(KPDAkkum),"KPDAkkum");
        Log.d(String.valueOf(KPDUstanov),"KPDUstanov");
        Log.d(String.valueOf(KPDObsh),"KPDObsh");
        Log.d(String.valueOf(Potug),"Potug");
    }
}