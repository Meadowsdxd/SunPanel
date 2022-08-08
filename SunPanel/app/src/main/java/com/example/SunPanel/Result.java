package com.example.SunPanel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Result extends Fragment {

    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    TextView KPDObsh , kpd,WT;
    ImageView imageView;
    long userId=0;


    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_result, container, false);
        KPDObsh = (TextView) view.findViewById(R.id.KPDObsh);
        imageView = view.findViewById(R.id.sun); Animat();
        kpd=(TextView) view.findViewById(R.id.textView3);
        WT=(TextView) view.findViewById(R.id.WT);
        Check check = new Check();
        sqlHelper = new DataBaseHelper(getContext());

        db = sqlHelper.open();
        userId = Transletor.getId();
        if( userId > 0) {
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE + " where " +
                    DataBaseHelper.COLUMN_IDC + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            String KPD = (userCursor.getString(23));
            String Potug= String.valueOf(Float.parseFloat(userCursor.getString(18))/10);

            if (Float.parseFloat(Potug) ==0) {
                WT.setText("0 Вт");
                kpd.setText("ККД 0 %");
                KPDObsh.setText("У ночі сонячні батареї не створюють електроєнергію, тому ККД становить - 0 %");

            }else
            if (Float.parseFloat(KPD) < 0) {
                WT.setText("0 Вт");
                kpd.setText("ККД 0 %");
                KPDObsh.setText("У ночі сонячні батареї не створюють електроєнергію, тому ККД становить - 0 %");

            } else {
                KPDObsh.setText("Загальний ККД всього циклу виробництва електроенергії становить близько - " + Float.parseFloat(KPD) + " відсотків. З одного боку цей показник невисокий, проте, принцип дії автономної сонячної електростанції зводиться до того, що вона протягом усього світлового дня накопичує енергію (близько 10-12 годин) і віддає енергію протягом двох-трьох годин в темний час доби. Тому реальна ефективність даного циклу виробництва електроенергії вище, ніж " + Float.parseFloat(KPD) + " відсотків");
                WT.setText(Regu(Potug+"Вт")+"Вт");
                kpd.setText("ККД " +Regu (KPD )+ " %");

            }
            //Инициализируем элементы:
        }
        return view;
    }
    public void Animat(){
        final Animation rotate = AnimationUtils.loadAnimation((Context) getHost(), R.anim.center);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setRepeatMode(Animation.ABSOLUTE);
        rotate.setStartTime(2000);
        rotate.setDuration(4000L);
        imageView.startAnimation(rotate);
    }
    public String Regu(String in){
        String out;
        int index = in.indexOf('.');
        out = in.substring(0, index+3);
        return out;
    }
    }


