package com.example.SunPanel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class InfoAbout extends Fragment {
    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    TextView Evsr,ESTR,Xdolya,Plosha,Wi,KPD,Kz,Ws,KPDWithoutInvertor,NamePanel,KPDAkkum,KPDUstanov,KolovoPanel;
    long userId=0;
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.infoabout, container, false);
        NamePanel = (TextView) view.findViewById(R.id.NamePanel);
        KolovoPanel= (TextView) view.findViewById(R.id.Kolovopanel);
        Evsr = (TextView) view.findViewById(R.id.EVSR);
        ESTR= (TextView) view.findViewById(R.id.ESTR);
        Xdolya = (TextView) view.findViewById(R.id.Xdolya);
        Plosha= (TextView) view.findViewById(R.id.Plosha);
        Wi = (TextView) view.findViewById(R.id.Wi);
        KPD= (TextView) view.findViewById(R.id.KPD);
        Kz = (TextView) view.findViewById(R.id.Kz);
        Ws=(TextView) view.findViewById(R.id.Ws);
        KPDWithoutInvertor=(TextView) view.findViewById(R.id.KPDWithoutInvertor);
        KPDAkkum=(TextView) view.findViewById(R.id.KPDAkkum);
        KPDUstanov=(TextView) view.findViewById(R.id.KPDUstanov);
        Check   check = new Check();
        sqlHelper = new DataBaseHelper(getContext());

        db = sqlHelper.open();
        userId = Transletor.getId();
        if( userId > 0) {
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE + " where " +
                    DataBaseHelper.COLUMN_IDC + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            String ploshad = String.valueOf(Float.parseFloat((userCursor.getString(13))));
            String kolovo = userCursor.getString(2);
            String Ploshaall = String.valueOf(Float.parseFloat(ploshad) * Float.parseFloat(kolovo));
            String Potug = String.valueOf(Float.parseFloat(userCursor.getString(18)) / 10);
            String osvet= userCursor.getString(9);
            if((Integer.parseInt(String.valueOf(osvet))==1)&(Float.parseFloat(Potug)==0.0)) {
                NamePanel.setText("Назва панелі - " + userCursor.getString(1));
                KolovoPanel.setText("Кількість панелей - " + kolovo + " " + " шт");
                Evsr.setText("Освітленість - 0 Люкс");
                ESTR.setText("Щільність випромінювання - 0 Вт/м2");
                Xdolya.setText("Частка від потужності при харчуванні від початкового напруги - 0 ");
                Plosha.setText("Площа панелей - " + (Float.parseFloat(Ploshaall) * 100 + " ") + " м2");
                Wi.setText("Потужність падаючого випромінювання на сонячну батарею - 0 Вт");
                KPD.setText("КПД сонячної панелі - 0 %");
                Kz.setText("Коефіцієнт заповнення -0 ");
                Ws.setText("Потужність яку виробляє сонячні батареї- 0 Вт");
                KPDWithoutInvertor.setText("КПД без інвертора - 0 %");
                KPDAkkum.setText("КПД акамулятора - 0 %");
                KPDUstanov.setText("КПД  установки без сонячної батареї - 0 %");
            }else{
                NamePanel.setText("Назва панелі - " + userCursor.getString(1));
                KolovoPanel.setText("Кількість панелей - " + kolovo + " " + " шт");
                Evsr.setText("Освітленість - " + (userCursor.getString(10) + " ") + " Люкс");
                ESTR.setText("Щільність випромінювання - " + (userCursor.getString(11) + " ") + " Вт/м2");
                Xdolya.setText("Частка від потужності при харчуванні від початкового напруги - " + (userCursor.getString(12) + " "));
                Plosha.setText("Площа панелей - " + (Float.parseFloat(Ploshaall) * 100 + " ") + " м2");
                Wi.setText("Потужність падаючого випромінювання на сонячну батарею - " + (userCursor.getString(14) + " ") + " Вт");
                KPD.setText("КПД сонячної панелі - " + (userCursor.getString(15) + " ") + " %");
                Kz.setText("Коефіцієнт заповнення - " + (userCursor.getString(16) + " "));
                Ws.setText("Потужність яку виробляє сонячні батареї- " +(Potug + " ") + " Вт");
                KPDWithoutInvertor.setText("КПД без інвертора - " + (userCursor.getString(19) + " ") + " %");
                KPDAkkum.setText("КПД акамулятора - " + (userCursor.getString(20) + " ") + " %");
                KPDUstanov.setText("КПД  установки без сонячної батареї - " + (userCursor.getString(21) + " ") + " %");
            }
        }
        return view;
    }
}
