
package com.example.SunPanel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static android.text.TextUtils.isEmpty;

public class Order extends AppCompatActivity {

    String nameClient,SecondnameClient,Patronymic,PlaceClient,PhoneClient,MailClient;
    String namePanel,pricePanel,typePanel,sizePanel,kKDPanel,specificPanel,idPanel,password;
    int pricePanelperemen,PriceDopperemen,priceUslugperemen;
    int allprice;
    String kolovopanelperemen;
    int KOLOVODOpperemen;
    int allkolovo;
    String Akkum,Invertor,Conrtroller,KOLOVODOp,idKomplect;
    String nameService,staff,addressService,phoneService,mailService;
    String typeUslug,timeUslug,idService,typeGarant,timeGarant;
Button nextButton;
ImageView imageView;
TextView zakaz,zakaz2,zakaz3,zakaz4,zakaz5;
    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;
    int Pos=0;
    private String zakazsend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zakaz);}
        @Override
        public void onResume() {
            super.onResume();
userId=Transletor.getId();
            zakaz = (TextView) findViewById(R.id.Zakaz);
            zakaz2 = (TextView) findViewById(R.id.Zakaz2);
            zakaz3 = (TextView) findViewById(R.id.Zakaz3);
            zakaz4 = (TextView) findViewById(R.id.Zakaz4);
            zakaz5 = (TextView) findViewById(R.id.Zakaz5);
            sqlHelper = new DataBaseHelper(this);
            //sqlHelper.create_db();
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
            //-----------------------------------------------------------
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_PANEL + " where " +
                        DataBaseHelper.COLUMN_IDPANEL + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                namePanel = (String.valueOf(userCursor.getString(1)));
                pricePanel = (String.valueOf(userCursor.getString(2)));
                pricePanelperemen = Integer.parseInt(String.valueOf(userCursor.getString(2)));
                sizePanel = (String.valueOf(userCursor.getString(3)));
                typePanel = (String.valueOf(userCursor.getString(4)));
                kKDPanel = (String.valueOf(userCursor.getString(5)));
                specificPanel = (String.valueOf(userCursor.getString(6)));
                idPanel = (String.valueOf(userCursor.getString(7)));
                kolovopanelperemen =(String.valueOf(userCursor.getString(8)));
                userCursor.close();
            }
            //-----------------------------------------------------------
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_DOP + " where " +
                        DataBaseHelper.COLUMN_IDDOP + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                Akkum = (String.valueOf(userCursor.getString(1)));
                Conrtroller = (String.valueOf(userCursor.getString(2)));
                Invertor = (String.valueOf(userCursor.getString(3)));

                PriceDopperemen = Integer.parseInt(String.valueOf(userCursor.getString(4)));
                KOLOVODOp = (String.valueOf(userCursor.getString(5)));
                idKomplect = (String.valueOf(userCursor.getString(6)));
                userCursor.close();
            }
            //-----------------------------------------------------------
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_SERVICE + " where " +
                        DataBaseHelper.COLUMN_IDSERVICE + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                nameService = (userCursor.getString(1));
                staff = (String.valueOf(userCursor.getString(2)));
                addressService = (String.valueOf(userCursor.getString(3)));
                phoneService = (String.valueOf(userCursor.getString(4)));
                mailService = (String.valueOf(userCursor.getString(5)));

                userCursor.close();
            }
            //-----------------------------------------------------------
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_OBSLUG + " where " +
                        DataBaseHelper.COLUMN_IDOBSLUG + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                typeUslug = (userCursor.getString(1));
                priceUslugperemen = Integer.parseInt(String.valueOf(userCursor.getString(2)));
                timeUslug = (String.valueOf(userCursor.getString(3)));
                idService = (String.valueOf(userCursor.getString(4)));

                userCursor.close();
            }
            //-----------------------------------------------------------
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_GARANT + " where " +
                        DataBaseHelper.COLUMN_IDGARANT + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                typeGarant = (userCursor.getString(1));
                timeGarant = (String.valueOf(userCursor.getString(2)));
                userCursor.close();
            }
            //allkolovo=kolovopanelperemen+KOLOVODOpperemen;
            allprice = (Integer.parseInt(String.valueOf(pricePanelperemen)) + (Integer.parseInt(String.valueOf(priceUslugperemen))) + (Integer.parseInt(String.valueOf(PriceDopperemen))));
            if((isEmpty(namePanel))&&(isEmpty(Akkum))&&(isEmpty(Akkum))){
                zakaz.setText ("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  " + "\n" +
                        "| ID Клієнта - " + userId + "\n" +
                        "| ПІБ Клієнта - " + SecondnameClient + " " + nameClient + " " + Patronymic + "\n" +
                        "| Номер телефона - " + PhoneClient + "\n" +
                        "| Mail клієнта - " + MailClient + " \n"+
                        "| Поверніться до форми заповнення і введіть дані.Дякую!!!");
                zakaz2.setVisibility(View.GONE);
                zakaz3.setVisibility(View.GONE);
                zakaz4.setVisibility(View.GONE);}else {zakaz.setText ("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " + "\n" +
                    "| ID Клієнта - " + userId + "\n" +
                    "| ПІБ Клієнта - " + SecondnameClient + " " + nameClient + " " + Patronymic + "\n" +
                    "| Номер телефона - " + PhoneClient + "\n" +
                    "| Mail клієнта - " + MailClient + " \n"+
                    "| Пароль клієнта - " + password + " \n");}
            if(isEmpty(namePanel)){zakaz2.setVisibility(View.GONE);}else {zakaz2.setVisibility(View.VISIBLE);
           zakaz2.setText (   "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _" + " \n" +
                        "| ID панелі - " + idPanel + "\n" +
                        "| Назва панелі  " + namePanel + "\n" +
                        "| ККД панелей - " + kKDPanel + "\n" +
                        "| Ціна панелей - " + pricePanelperemen + " грн. " + "Колово панелей - " + kolovopanelperemen + "\n" +
                        "| Дата замовлення - " + timeGarant + "\n");}
            if(isEmpty(Akkum)){zakaz3.setVisibility(View.GONE);}else {zakaz3.setVisibility(View.VISIBLE);
            zakaz3.setText (  "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  _ _ _ _ _ _ _" + " \n" +
                        "| ID додаткових комплектуючих - " + idKomplect + "\n" +
                        "| Назва акумулятора - " + Akkum + "\n" +
                        "| Назва контролера - " + Conrtroller + "\n" +
                        "| Ціна додаткових комплектуючих- " + PriceDopperemen + " грн. " + "\n" +
                        "| Дата замовлення - " + timeGarant + "\n" );}
            if(isEmpty(typeUslug)){zakaz4.setVisibility(View.GONE);}else {zakaz4.setVisibility(View.VISIBLE);
            zakaz4.setText ("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _" + " \n" +
                        "| ID  - " + userId +" Тип послуги - "+typeUslug+"\n"+
                        "| Дата замовлення " + timeGarant + " \n"+
                        "| Ціна послуги - " + priceUslugperemen + " грн. \n"+
                        "| Час виконання послуги - " + timeUslug + " \n"+
                        "| Id сервісу, який виконує роботу - " + idService + " \n" );}
            zakaz5.setVisibility(View.VISIBLE);
                zakaz5.setText ("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _" + " \n" +
                        "| ID замовлення - " + userId +" Тип послуги - "+typeUslug+"\n"+
                        "| Дата замовлення " + timeGarant + " \n"+
                        "| Ціна до сплати - " + allprice + " грн. \n"+
                        "| Час виконання послуги - " + timeUslug + " \n"+
                        "| Id сервісу, який виконує роботу - " + idService + " \n" );


        }
    String Email;
    String subject;

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
        //-----------------------------------------------------------
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_PANEL + " where " +
                    DataBaseHelper.COLUMN_IDPANEL + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            namePanel = (String.valueOf(userCursor.getString(1)));
            pricePanel = (String.valueOf(userCursor.getString(2)));
            pricePanelperemen = Integer.parseInt(String.valueOf(userCursor.getString(2)));
            sizePanel = (String.valueOf(userCursor.getString(3)));
            typePanel = (String.valueOf(userCursor.getString(4)));
            kKDPanel = (String.valueOf(userCursor.getString(5)));
            specificPanel = (String.valueOf(userCursor.getString(6)));
            idPanel = (String.valueOf(userCursor.getString(7)));
            kolovopanelperemen = (String.valueOf(userCursor.getString(8)));
            userCursor.close();
        }
        //-----------------------------------------------------------
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_DOP + " where " +
                    DataBaseHelper.COLUMN_IDDOP + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            Akkum = (String.valueOf(userCursor.getString(1)));
            Conrtroller = (String.valueOf(userCursor.getString(2)));
            Invertor = (String.valueOf(userCursor.getString(3)));

            PriceDopperemen = Integer.parseInt(String.valueOf(userCursor.getString(4)));
            KOLOVODOp = (String.valueOf(userCursor.getString(5)));
            idKomplect = (String.valueOf(userCursor.getString(6)));
            userCursor.close();
        }
        //-----------------------------------------------------------
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_SERVICE + " where " +
                    DataBaseHelper.COLUMN_IDSERVICE + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            nameService = (userCursor.getString(1));
            staff = (String.valueOf(userCursor.getString(2)));
            addressService = (String.valueOf(userCursor.getString(3)));
            phoneService = (String.valueOf(userCursor.getString(4)));
            mailService = (String.valueOf(userCursor.getString(5)));

            userCursor.close();
        }
        //-----------------------------------------------------------
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_OBSLUG + " where " +
                    DataBaseHelper.COLUMN_IDOBSLUG + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            typeUslug = (userCursor.getString(1));
            priceUslugperemen = Integer.parseInt(String.valueOf(userCursor.getString(2)));
            timeUslug = (String.valueOf(userCursor.getString(3)));
            idService = (String.valueOf(userCursor.getString(4)));

            userCursor.close();
        }
        //-----------------------------------------------------------
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_GARANT + " where " +
                    DataBaseHelper.COLUMN_IDGARANT + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            typeGarant = (userCursor.getString(1));
            timeGarant = (String.valueOf(userCursor.getString(2)));
            userCursor.close();
        }

        zakazsend= (
                        "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ " + "\n" +
                        "| ID Клієнта - " + userId + "\n" +
                        "| ПІБ Клієнта - " + SecondnameClient + " " + nameClient + " " + Patronymic + "\n" +
                        "| Номер телефона - " + PhoneClient + "\n" +
                        "| Mail клієнта - " + MailClient + " \n"+
                        "| Пароль клієнта - " + password + " \n"+
                        "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _" + " \n" +
                        "| ID замовлення - " + userId +" Тип послуги - "+typeUslug+"\n"+
                        "| Дата замовлення " + timeGarant + " \n"+
                        "| Ціна до сплати - " + allprice + " грн. \n"+
                        "| Час виконання послуги - " + timeUslug + " \n"+
                        "| Id сервісу, який виконує роботу - " + idService + " \n"+
                        "| _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _" + " \n"
        );
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,
                new String[]{Email});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, zakazsend);

        startActivity(emailIntent);
    }
}







