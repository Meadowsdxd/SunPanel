package com.example.SunPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginIn extends AppCompatActivity {
    String nameClient,SecondnameClient,Patronymic,PlaceClient,PhoneClient,MailClient,password;
    EditText mail,passLog;
    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    ImageView sunnyanims;
    long userId=0;
    int Pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_in);
        mail=(EditText)findViewById(R.id.emailLog);
        passLog=(EditText)findViewById(R.id.passLog);
        sunnyanims=(ImageView)findViewById(R.id.sunnyanim);
        Animat();
        sqlHelper = new DataBaseHelper(this);


        db = sqlHelper.open();
        userId = Transletor.getId();
        Pos = Transletor.getPosition();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("id");
        }
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_CLIENT + " where " +
                    DataBaseHelper.COLUMN_IDCLIENT + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            nameClient = (userCursor.getString(1));
            SecondnameClient = (String.valueOf(userCursor.getString(2)));
            Patronymic = (String.valueOf(userCursor.getString(3)));
            PlaceClient = (String.valueOf(userCursor.getString(5)));
            PhoneClient = (String.valueOf(userCursor.getString(4)));
            MailClient = (String.valueOf(userCursor.getString(6)));
            password= (String.valueOf(userCursor.getString(7)));
            userCursor.close();
        }
        mail.setText(MailClient);
        Transletor transletor=new Transletor();
        transletor.setId(userId);
    }

    public void nextpage(View view){
        String passl;
        passl= String.valueOf(passLog.getText());
        if(passl.equals(password)){
        Intent intent = new Intent(this, Order.class);
        startActivity(intent);}else{ Toast.makeText(getApplicationContext(),"Пароль не вірний, спробуйте знову",Toast.LENGTH_SHORT).show();}
    }
    public void Animat(){
        final Animation rotate = AnimationUtils.loadAnimation( this, R.anim.center);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setRepeatMode(Animation.ABSOLUTE);
        rotate.setStartTime(2000);
        rotate.setDuration(4000L);
        sunnyanims.startAnimation(rotate);
    }
}