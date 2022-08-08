package com.example.SunPanel;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class Swipe extends AppCompatActivity implements View.OnClickListener {
    private ViewPager pager;
    private PagerAdapter adapter;
    private Button mail;
    String Email;
    String subject;
    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    TextView KPDObsh;
    String otvet;
    long userId=0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainraschet);


        List<Fragment> list=new ArrayList<>();
        list.add(new InfoAbout());

        list.add(new Result());
        list.add(new graphik());
        list.add(new graphik2());

        pager=findViewById(R.id.pager);
        adapter=new Slider(getSupportFragmentManager(),list);
        pager.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {

        // переход к главной activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    public void  Zvit(View v){
        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.prompt, null);
        //Создаем AlertDialog
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
        //Настраиваем prompt.xml для нашего AlertDialog:
        mDialogBuilder.setView(promptsView);
        //Настраиваем отображение поля для ввода текста в открытом диалоге:
        final EditText userInput = (EditText) promptsView.findViewById(R.id.input_email);
        //Настраиваем сообщение в диалоговом окне:
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int id) {
                                Email = String.valueOf(userInput.getText());
                                SendMail();
                            }
                        })
                .setNegativeButton("Отмена",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        //Создаем AlertDialog:
        AlertDialog alertDialog = mDialogBuilder.create();
        //и отображаем его:
        alertDialog.show();
    }
    public  void SendMail() {
        Check check = new Check();
        sqlHelper = new DataBaseHelper(this);

        db = sqlHelper.open();
        userId = Transletor.getId();
        if( userId > 0) {
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE + " where " +
                    DataBaseHelper.COLUMN_IDC + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            String ploshad= String.valueOf(Float.parseFloat((userCursor.getString(13))));
            String KPD=Regu(userCursor.getString(23));
            String Potug= String.valueOf(Float.parseFloat(userCursor.getString(18))/10);
            otvet=("Назва панелі - "+userCursor.getString(1)+"\n" +
                    "Кількість панелей - "+userCursor.getString(2) + " "+ " шт\n"+
                    "Освітленість - "+(userCursor.getString(10) + " ")+ " Люкс \n"+
                    "Щільність випромінювання - -"+(userCursor.getString(11) + " ")+ " Вт/м2 \n"+
                    "Частка від потужності при харчуванні від початкового напруги - - "+(userCursor.getString(12) + " ")+ "  \n"+
                    "Площа панелей - "+(Float.parseFloat(ploshad)*100 + " ") + " "+ " м2 Вт \n"+
                    "Потужність падаючого випромінювання на сонячну батарею  - "+(userCursor.getString(14) + " ")+ " "+ " Вт \n"+
                    "КПД сонячної панелі - "+Regu(userCursor.getString(15) + " ") + " "+ " % \n"+
                    "Коефіцієнт заповнення - "+Regu(userCursor.getString(16) + " ")+ "  \n"+
                    "Потужність яку виробляє сонячні батареї - "+(Potug + " ") + " "+ " Вт \n"+
                    "КПД без інвертора - "+(userCursor.getString(19) + " ") + " "+ " % \n"+
                    "КПД акамулятора - "+(userCursor.getString(20) + " ") + " "+ " % \n"+
                    "КПД  установки без сонячної батареї - "+(userCursor.getString(21) + " ") + " "+ " % \n"+
                    "Загальний ККД всього циклу виробництва електроенергії становить близько - "
                    +Float.parseFloat(Regu(KPD ))+ " відсотків. З одного боку цей показник невисокий," +
                    " проте, принцип дії автономної сонячної електростанції зводиться до того, " +
                    "що вона протягом усього світлового дня накопичує енергію " +
                    "(близько 10-12 годин) і віддає енергію протягом двох-трьох годин в темний час доби." +
                    " Тому реальна ефективність даного циклу виробництва електроенергії вище, ніж "+Float.parseFloat(Regu(KPD) )+" відсотків");
        }
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,
                new String[]{Email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, otvet);
        startActivity(emailIntent);
    }
    public String Regu(String in){
        String out;
        int index = in.indexOf('.');
        out = in.substring(0, index+3);
        return out;
    }
}
