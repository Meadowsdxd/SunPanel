package com.example.SunPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Transletor transletor = new Transletor();
    EditText nameClient,SecondnameClient,Patronymic,PlaceClient,PhoneClient,MailClient,timeGarant,typeGarant,idPanel,nameService,staff,addressService,phoneService,mailService,typeUslug,idService,priceUslug,timeUslug;
    TextView nameServiceView,staffView,addressServiceView,phoneServiceView,mailServiceView,typeUslugView,priceUslugView,idServiceView,timeUslugView;

        int pricePanelperemen,PriceDopperemen,priceUslugperemen;
    EditText namePanel,pricePanel,sizePanel,typePanel,kKDPanel,specificPanel,Akkum,Invertor,Conrtroller,KOLOVODOp,PriceDop,idKomplect,password,kolovoPanel;
    TextView kolovoPanelView,passwordView,idPanelView,namePanelView,timeGarantView,typeGarantView,pricePanelView,sizePanelView,typePanelView,kKDPanelView,specificPanelView,AkkumView,InvertorView,ConrtrollerView,KOLOVODOpView,PriceDopView,idKomplectView,nameClientView,SecondnameClientView,PatronymicView,PlaceClientView,PhoneClientView,MailClientView;
        DataBaseHelper sqlHelper;

        SQLiteDatabase db;
        Cursor userCursor;
        long userId=0;
        int Pos;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_popitka);
            //-----------------------------------------------------------
            idPanelView =(TextView) findViewById(R.id.IdPanelView);

            nameClientView = (TextView) findViewById(R.id.NameClientView);
            SecondnameClientView = (TextView) findViewById(R.id.SecondNameClientView);
            PatronymicView = (TextView) findViewById(R.id.PatronymicView);
            PlaceClientView = (TextView) findViewById(R.id.PlaceClientView);
            PhoneClientView = (TextView) findViewById(R.id.PhoneClientView);
            MailClientView = (TextView) findViewById(R.id.MailClientView);
            passwordView= (TextView) findViewById(R.id.PasswordView);
            //-----------------------------------------------------------
            namePanelView = (TextView) findViewById(R.id.NamePanelView);
            pricePanelView = (TextView) findViewById(R.id.PricePanelView);
            sizePanelView = (TextView) findViewById(R.id.SizePanelView);
            typePanelView = (TextView) findViewById(R.id.TypePanelView);
            kKDPanelView = (TextView) findViewById(R.id.KKDPanelView);
            specificPanelView = (TextView) findViewById(R.id.SpecificPanelView);
            kolovoPanelView= (TextView) findViewById(R.id.KolovoPanelView);
            //-----------------------------------------------------------
            AkkumView = (TextView) findViewById(R.id.AkkumView);
            InvertorView = (TextView) findViewById(R.id.InvertorView);
            ConrtrollerView = (TextView) findViewById(R.id.ConrtrollerView);
            KOLOVODOpView = (TextView) findViewById(R.id.KOLOVODOpView);
            PriceDopView = (TextView) findViewById(R.id.PriceDopView);
            idKomplectView = (TextView) findViewById(R.id.idKomplectView);
            timeGarantView=(TextView) findViewById(R.id.TimeGarantView);
            typeGarantView=(TextView) findViewById(R.id.TypeGarantView);
            //-----------------------------------------------------------
            nameServiceView = (TextView) findViewById(R.id.NameServiceView);
            staffView = (TextView) findViewById(R.id.StaffView);
            addressServiceView = (TextView) findViewById(R.id.AddressServiceView);
            phoneServiceView = (TextView) findViewById(R.id.PhoneServiceView);
            mailServiceView = (TextView) findViewById(R.id.MailServiceView);
            //-----------------------------------------------------------
            typeUslugView= (TextView) findViewById(R.id.TypeUslugView);
            priceUslugView= (TextView) findViewById(R.id.PriceUslugView);
            timeUslugView= (TextView) findViewById(R.id.TimeUslugView);
            idServiceView= (TextView) findViewById(R.id.IdServiceView);
            //-----------------------------------------------------------
            timeGarantView=(TextView) findViewById(R.id.TimeGarantView);
            typeGarantView=(TextView) findViewById(R.id.TypeGarantView);
            //-----------------------------------------------------------
            nameClient = (EditText) findViewById(R.id.NameClient);
            SecondnameClient = (EditText) findViewById(R.id.SecondNameClient);
            Patronymic = (EditText) findViewById(R.id.Patronymic);
            PlaceClient = (EditText) findViewById(R.id.Address);
            PhoneClient = (EditText) findViewById(R.id.PhoneClient);
            MailClient = (EditText) findViewById(R.id.MailClient);
            password= (EditText) findViewById(R.id.Password);
            //-----------------------------------------------------------
            idPanel= (EditText) findViewById(R.id.IdPanel);
            kolovoPanel=(EditText) findViewById(R.id.KolovoPanel);
            namePanel = (EditText) findViewById(R.id.NamePanel);
            pricePanel = (EditText) findViewById(R.id.PricePanel);
            sizePanel = (EditText) findViewById(R.id.SizePanel);
            typePanel = (EditText) findViewById(R.id.TypePanel);
            kKDPanel = (EditText) findViewById(R.id.KKDPanel);
            specificPanel = (EditText) findViewById(R.id.SpecificPanel);

            //-----------------------------------------------------------
            Akkum = (EditText) findViewById(R.id.Akkum);
            Invertor = (EditText) findViewById(R.id.Invertor);
            Conrtroller = (EditText) findViewById(R.id.Conrtroller);
            KOLOVODOp = (EditText) findViewById(R.id.KOLOVODOp);
            PriceDop = (EditText) findViewById(R.id.PriceDop);
            idKomplect = (EditText) findViewById(R.id.idKomplect);
            //-----------------------------------------------------------
            nameService = (EditText) findViewById(R.id.NameService);
            staff = (EditText) findViewById(R.id.Staff);
            addressService = (EditText) findViewById(R.id.AddressService);
            phoneService= (EditText) findViewById(R.id.PhoneService);
            mailService = (EditText) findViewById(R.id.MailService);
            //-----------------------------------------------------------
            typeUslug= (EditText) findViewById(R.id.TypeUslug);
            priceUslug= (EditText) findViewById(R.id.PriceUslug);
            timeUslug= (EditText) findViewById(R.id.TimeUslug);
            idService= (EditText) findViewById(R.id.IdService);
            //-----------------------------------------------------------
            timeGarant=(EditText) findViewById(R.id.TimeGarant);
            typeGarant=(EditText) findViewById(R.id.TypeGarant);
            //-----------------------------------------------------------

            sqlHelper = new DataBaseHelper(this);
           db = sqlHelper.open();
            //sqlHelper.create_db();
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                userId = extras.getLong("id");
            }
            // если 0, то добавление
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_CLIENT + " where " +
                        DataBaseHelper.COLUMN_IDCLIENT + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                nameClient.setText(userCursor.getString(1));
                SecondnameClient.setText(String.valueOf(userCursor.getString(2)));
                Patronymic.setText(String.valueOf(userCursor.getString(3)));
                PlaceClient.setText(String.valueOf(userCursor.getString(5)));
                PhoneClient.setText(String.valueOf(userCursor.getString(4)));
                MailClient.setText(String.valueOf(userCursor.getString(6)));
                password.setText(String.valueOf(userCursor.getString(7)));
                userCursor.close();
            }
            //-----------------------------------------------------------
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_PANEL + " where " +
                        DataBaseHelper.COLUMN_IDPANEL + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                 namePanel.setText(String.valueOf(userCursor.getString(1)));
                 pricePanel.setText(String.valueOf(userCursor.getString(2)));
                 pricePanelperemen=Integer.parseInt(String.valueOf(userCursor.getString(2)));
                 sizePanel.setText(String.valueOf(userCursor.getString(3)));
                 typePanel.setText(String.valueOf(userCursor.getString(4)));
                 kKDPanel.setText(String.valueOf(userCursor.getString(5)));
                 specificPanel.setText(String.valueOf(userCursor.getString(6)));
                  idPanel.setText(String.valueOf(userCursor.getString(7)));
                kolovoPanel.setText(String.valueOf(userCursor.getString(8)));
                userCursor.close();}
            //-----------------------------------------------------------
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_DOP + " where " +
                        DataBaseHelper.COLUMN_IDDOP + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                 Akkum.setText(String.valueOf(userCursor.getString(1)));
               Conrtroller.setText(String.valueOf(userCursor.getString(2)));
               Invertor.setText(String.valueOf(userCursor.getString(3)));
               PriceDop.setText(String.valueOf(userCursor.getString(4)));
               PriceDopperemen=Integer.parseInt(String.valueOf(userCursor.getString(4)));
               KOLOVODOp.setText(String.valueOf(userCursor.getString(5)));
               // KOLOVODOpperemen=Integer.parseInt(String.valueOf(userCursor.getString(5)));
               idKomplect.setText(String.valueOf(userCursor.getString(6)));
                userCursor.close();}
            //-----------------------------------------------------------
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_SERVICE + " where " +
                        DataBaseHelper.COLUMN_IDSERVICE + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                nameService.setText(userCursor.getString(1));
                staff.setText(String.valueOf(userCursor.getString(2)));
                addressService.setText(String.valueOf(userCursor.getString(3)));
                phoneService.setText(String.valueOf(userCursor.getString(4)));
                mailService.setText(String.valueOf(userCursor.getString(5)));

                userCursor.close();
            }
            //-----------------------------------------------------------
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_OBSLUG + " where " +
                        DataBaseHelper.COLUMN_IDOBSLUG + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                typeUslug.setText(userCursor.getString(1));
                priceUslug.setText(String.valueOf(userCursor.getString(2)));
                priceUslugperemen=Integer.parseInt(String.valueOf(userCursor.getString(2)));
                timeUslug.setText(String.valueOf(userCursor.getString(3)));
                idService.setText(String.valueOf(userCursor.getString(4)));

                userCursor.close();
            }
            //-----------------------------------------------------------
            if (userId > 0) {
                // получаем элемент по id из бд
                userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_GARANT + " where " +
                        DataBaseHelper.COLUMN_IDGARANT + "=?", new String[]{String.valueOf(userId)});
                userCursor.moveToFirst();
                typeGarant.setText(userCursor.getString(1));
                timeGarant.setText(String.valueOf(userCursor.getString(2)));
                userCursor.close();
            }





            transletor.setId(userId);
            Hide();
        }



    public void save(View view){
            Check check = new Check();
            if (check.ISNull(String.valueOf(nameClient.getText()), String.valueOf(SecondnameClient.getText()),
                    String.valueOf(Patronymic.getText()), String.valueOf(PhoneClient.getText()),
                    String.valueOf(PlaceClient.getText()),String.valueOf(MailClient.getText()),
                    String.valueOf(nameService.getText()), String.valueOf(staff.getText()),
                    String.valueOf(addressService.getText()), String.valueOf(phoneService.getText()), String.valueOf(mailService.getText()),
                    String.valueOf(typeGarant.getText()),String.valueOf(timeGarant.getText()), this)){
            try{
                ContentValues cv_client = new ContentValues();
                ContentValues cv_panel = new ContentValues();
                ContentValues cv_dop = new ContentValues();
                ContentValues cv_service = new ContentValues();
                ContentValues cv_poslug = new ContentValues();
                ContentValues cv_garant = new ContentValues();

                //-----------------------------------------------------------
                cv_client.put(DataBaseHelper.COLUMN_NAME, nameClient.getText().toString());
                cv_client.put(DataBaseHelper.COLUMN_SECONDNAMECLIENT,SecondnameClient.getText().toString());
                cv_client.put(DataBaseHelper.COLUMN_PATRONYMICCLIENT, Patronymic.getText().toString());
                cv_client.put(DataBaseHelper.COLUMN_PHONECLIENT, PhoneClient.getText().toString());
                cv_client.put(DataBaseHelper.COLUMN_PLACELEAVING, PlaceClient.getText().toString());
                cv_client.put(DataBaseHelper.COLUMN_MAILCLIENT, MailClient.getText().toString());
                cv_client.put(DataBaseHelper.COLUMN_PASSWORD, Integer.parseInt(String.valueOf(password.getText())));
                //-----------------------------------------------------------
                cv_panel.put(DataBaseHelper.COLUMN_NAMEPANEL, namePanel.getText().toString());
                cv_panel.put(DataBaseHelper.COLUMN_PRICEPANEL,pricePanel.getText().toString());
                cv_panel.put(DataBaseHelper.COLUMN_SIZEPANEL, sizePanel.getText().toString());
                cv_panel.put(DataBaseHelper.COLUMN_TYPEPANEL, typePanel.getText().toString());
                cv_panel.put(DataBaseHelper.COLUMN_KKDPANEL, kKDPanel.getText().toString());
                cv_panel.put(DataBaseHelper.COLUMN_SPECIFICPANEL, specificPanel.getText().toString());
                cv_panel.put(DataBaseHelper.COLUMN_IDPANELL, idPanel.getText().toString());
                cv_panel.put(DataBaseHelper.COLUMN_KOLOVOPANEL, kolovoPanel.getText().toString());
                //-----------------------------------------------------------
                cv_dop.put(DataBaseHelper.COLUMN_AKKUM, Akkum.getText().toString());
                cv_dop.put(DataBaseHelper.COLUMN_CONTROLLER, Conrtroller.getText().toString());
                cv_dop.put(DataBaseHelper.COLUMN_INVERTOR, Invertor.getText().toString());
                cv_dop.put(DataBaseHelper.COLUMN_PRICEDOP, PriceDop.getText().toString());
                cv_dop.put(DataBaseHelper.COLUMN_KOLOVODOP, KOLOVODOp.getText().toString());
                cv_dop.put(DataBaseHelper.COLUMN_IIDKOMPLECT, idKomplect.getText().toString());
                //-----------------------------------------------------------
                cv_service.put(DataBaseHelper.COLUMN_NAMESERVICE, nameService.getText().toString());
                cv_service.put(DataBaseHelper.COLUMN_STAFFSERVICE, staff.getText().toString());
                cv_service.put(DataBaseHelper.COLUMN_ADDRESSSERVICE, addressService.getText().toString());
                cv_service.put(DataBaseHelper.COLUMN_PHONESERVICE, phoneService.getText().toString());
                cv_service.put(DataBaseHelper.COLUMN_MAILSERVICE, mailService.getText().toString());
                //-----------------------------------------------------------
                cv_poslug.put(DataBaseHelper.COLUMN_TYPEOBSLUG, typeUslug.getText().toString());
                cv_poslug.put(DataBaseHelper.COLUMN_PRICEOBSLUG, priceUslug.getText().toString());
                cv_poslug.put(DataBaseHelper.COLUMN_TIMEOBSLUG, timeUslug.getText().toString());
                cv_poslug.put(DataBaseHelper.COLUMN_IDSERVICES, idService.getText().toString());
                //-----------------------------------------------------------
                cv_garant.put(DataBaseHelper.COLUMN_TYPEGARANT, typeGarant.getText().toString());
                cv_garant.put(DataBaseHelper.COLUMN_TIMEGARANT, timeGarant.getText().toString());
                //-----------------------------------------------------------



            if (userId > 0) {
                db.update(DataBaseHelper.TABLE_CLIENT, cv_client, DataBaseHelper.COLUMN_IDCLIENT + "=" + userId, null);
                db.update(DataBaseHelper.TABLE_PANEL, cv_panel, DataBaseHelper.COLUMN_IDPANEL + "=" + userId, null);
                db.update(DataBaseHelper.TABLE_DOP, cv_dop, DataBaseHelper.COLUMN_IDDOP + "=" + userId, null);
                db.update(DataBaseHelper.TABLE_SERVICE, cv_service, DataBaseHelper.COLUMN_IDSERVICE + "=" + userId, null);
                db.update(DataBaseHelper.TABLE_OBSLUG, cv_poslug, DataBaseHelper.COLUMN_IDOBSLUG + "=" + userId, null);
                db.update(DataBaseHelper.TABLE_GARANT, cv_garant, DataBaseHelper.COLUMN_IDGARANT + "=" + userId, null);
            } else {
                db.insert(DataBaseHelper.TABLE_CLIENT, null, cv_client);
                db.insert(DataBaseHelper.TABLE_PANEL, null, cv_panel);
                db.insert(DataBaseHelper.TABLE_DOP, null, cv_dop);
                db.insert(DataBaseHelper.TABLE_SERVICE, null, cv_service);
                db.insert(DataBaseHelper.TABLE_OBSLUG, null, cv_poslug);
                db.insert(DataBaseHelper.TABLE_GARANT, null, cv_garant);
              // db.insert(DataBaseHelper.TABLE_ZAKAZ, null, cv_zakaz);
            }}catch (NumberFormatException e){e.printStackTrace();
                Toast.makeText(this, "\tERROR\nCan`t put in DataBase", Toast.LENGTH_LONG).show();}
            goHome();
        }}
        public void delete(View view){
            db.delete(DataBaseHelper.TABLE_CLIENT, "_id = ?", new String[]{String.valueOf(userId)});
            db.delete(DataBaseHelper.TABLE_PANEL, "_id = ?", new String[]{String.valueOf(userId)});
            db.delete(DataBaseHelper.TABLE_DOP, "_id = ?", new String[]{String.valueOf(userId)});
            db.delete(DataBaseHelper.TABLE_SERVICE, "_id = ?", new String[]{String.valueOf(userId)});
            db.delete(DataBaseHelper.TABLE_OBSLUG, "_id = ?", new String[]{String.valueOf(userId)});
            db.delete(DataBaseHelper.TABLE_GARANT, "_id = ?", new String[]{String.valueOf(userId)});
            goHome();
        }
        private void goHome(){
            // закрываем подключение
            db.close();
            // переход к главной activity
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }


    String[] checks = {"Введіть дані про клієнта","Введіть дані про панель", "Введіть дані про додаткове обладнення","Введіть дані про сервіс","Введіть дані про обслуговування","Введіть дані про гарантію"};
        void Hide(){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, checks);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            Spinner spinner = (Spinner) findViewById(R.id.Listing);
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
                    if (position==0){
                        nameClient.setVisibility(View.VISIBLE);
                        SecondnameClient.setVisibility(View.VISIBLE);
                        Patronymic.setVisibility(View.VISIBLE);
                        PhoneClient.setVisibility(View.VISIBLE);
                        PlaceClient.setVisibility(View.VISIBLE);
                        MailClient.setVisibility(View.VISIBLE);
                        nameClientView.setVisibility(View.VISIBLE);
                        SecondnameClientView.setVisibility(View.VISIBLE);
                        PatronymicView.setVisibility(View.VISIBLE);
                        PhoneClientView.setVisibility(View.VISIBLE);
                        PlaceClientView.setVisibility(View.VISIBLE);
                        MailClientView.setVisibility(View.VISIBLE);
                        passwordView.setVisibility(View.VISIBLE);
                        password.setVisibility(View.VISIBLE);
                        specificPanelView.setVisibility(View.VISIBLE);
                        namePanel.setVisibility(View.GONE);
                        pricePanel.setVisibility(View.GONE);
                        sizePanel.setVisibility(View.GONE);
                        typePanel.setVisibility(View.GONE);
                        kKDPanel.setVisibility(View.GONE);
                        kolovoPanelView.setVisibility(View.GONE);
                        kolovoPanel.setVisibility(View.GONE);
                        specificPanel.setVisibility(View.GONE);
                        namePanelView.setVisibility(View.GONE);
                        pricePanelView.setVisibility(View.GONE);
                        sizePanelView.setVisibility(View.GONE);
                        typePanelView.setVisibility(View.GONE);
                        kKDPanelView.setVisibility(View.GONE);
                        specificPanelView.setVisibility(View.GONE);
                        Akkum.setVisibility(View.GONE);
                        idKomplect.setVisibility(View.GONE);
                        Conrtroller.setVisibility(View.GONE);
                        Invertor.setVisibility(View.GONE);
                        KOLOVODOp.setVisibility(View.GONE);
                        PriceDopView.setVisibility(View.GONE);
                        PriceDop.setVisibility(View.GONE);
                        AkkumView.setVisibility(View.GONE);
                        idKomplectView.setVisibility(View.GONE);
                        ConrtrollerView.setVisibility(View.GONE);
                        InvertorView.setVisibility(View.GONE);
                        KOLOVODOpView.setVisibility(View.GONE);
                        PriceDop.setVisibility(View.GONE);
                        timeGarantView.setVisibility(View.GONE);
                        typeGarant.setVisibility(View.GONE);
                        typeGarantView.setVisibility(View.GONE);
                        timeGarant.setVisibility(View.GONE);
                        nameService .setVisibility(View.GONE);
                        staff.setVisibility(View.GONE);
                        addressService.setVisibility(View.GONE);
                        phoneService.setVisibility(View.GONE);
                        mailService.setVisibility(View.GONE);
                        nameServiceView .setVisibility(View.GONE);
                        staffView.setVisibility(View.GONE);
                        addressServiceView.setVisibility(View.GONE);
                        phoneServiceView.setVisibility(View.GONE);
                        mailServiceView.setVisibility(View.GONE);
                        idPanelView.setVisibility(View.GONE);
                        idPanel.setVisibility(View.GONE);
                        typeUslugView.setVisibility(View.GONE);
                        priceUslugView.setVisibility(View.GONE);
                        timeUslugView.setVisibility(View.GONE);
                        idServiceView.setVisibility(View.GONE);
                        typeUslug.setVisibility(View.GONE);
                        priceUslug.setVisibility(View.GONE);
                        timeUslug.setVisibility(View.GONE);
                        idService.setVisibility(View.GONE);
                    }else if (position==1){
                        kolovoPanelView.setVisibility(View.VISIBLE);
                        kolovoPanel.setVisibility(View.VISIBLE);
                        idPanel.setVisibility(View.VISIBLE);
                        idPanelView.setVisibility(View.VISIBLE);
                        nameClient.setVisibility(View.GONE);
                        SecondnameClient.setVisibility(View.GONE);
                        Patronymic.setVisibility(View.GONE);
                        PhoneClient.setVisibility(View.GONE);
                        PlaceClient.setVisibility(View.GONE);
                        MailClient.setVisibility(View.GONE);
                        nameClientView.setVisibility(View.GONE);
                        SecondnameClientView.setVisibility(View.GONE);
                        PatronymicView.setVisibility(View.GONE);
                        PhoneClientView.setVisibility(View.GONE);
                        PlaceClientView.setVisibility(View.GONE);
                        MailClientView.setVisibility(View.GONE);
                        specificPanelView.setVisibility(View.GONE);
                        namePanel.setVisibility(View.VISIBLE);
                        pricePanel.setVisibility(View.VISIBLE);
                        sizePanel.setVisibility(View.VISIBLE);
                        typePanel.setVisibility(View.VISIBLE);
                        kKDPanel.setVisibility(View.VISIBLE);
                        specificPanel.setVisibility(View.VISIBLE);
                        namePanelView.setVisibility(View.VISIBLE);
                        pricePanelView.setVisibility(View.VISIBLE);
                        sizePanelView.setVisibility(View.VISIBLE);
                        typePanelView.setVisibility(View.VISIBLE);
                        kKDPanelView.setVisibility(View.VISIBLE);
                        specificPanelView.setVisibility(View.VISIBLE);
                        Akkum.setVisibility(View.GONE);
                        idKomplect.setVisibility(View.GONE);
                        Conrtroller.setVisibility(View.GONE);
                        Invertor.setVisibility(View.GONE);
                        KOLOVODOp.setVisibility(View.GONE);
                        PriceDopView.setVisibility(View.GONE);
                        PriceDop.setVisibility(View.GONE);
                        AkkumView.setVisibility(View.GONE);
                        idKomplectView.setVisibility(View.GONE);
                        ConrtrollerView.setVisibility(View.GONE);
                        InvertorView.setVisibility(View.GONE);
                        KOLOVODOpView.setVisibility(View.GONE);
                        PriceDop.setVisibility(View.GONE);
                        timeGarantView.setVisibility(View.GONE);
                        typeGarant.setVisibility(View.GONE);
                        typeGarantView.setVisibility(View.GONE);
                        timeGarant.setVisibility(View.GONE);
                        nameService .setVisibility(View.GONE);
                        staff.setVisibility(View.GONE);
                        addressService.setVisibility(View.GONE);
                        phoneService.setVisibility(View.GONE);
                        mailService.setVisibility(View.GONE);
                        nameServiceView .setVisibility(View.GONE);
                        staffView.setVisibility(View.GONE);
                        addressServiceView.setVisibility(View.GONE);
                        phoneServiceView.setVisibility(View.GONE);
                        mailServiceView.setVisibility(View.GONE);
                        typeUslugView.setVisibility(View.GONE);
                        priceUslugView.setVisibility(View.GONE);
                        timeUslugView.setVisibility(View.GONE);
                        idServiceView.setVisibility(View.GONE);
                        typeUslug.setVisibility(View.GONE);
                        priceUslug.setVisibility(View.GONE);
                        timeUslug.setVisibility(View.GONE);
                        idService.setVisibility(View.GONE);
                        password.setVisibility(View.GONE);
                        passwordView.setVisibility(View.GONE);
                    }
                    else if (position==2){
                        kolovoPanelView.setVisibility(View.GONE);
                        kolovoPanel.setVisibility(View.GONE);
                        nameClient.setVisibility(View.GONE);
                        SecondnameClient.setVisibility(View.GONE);
                        Patronymic.setVisibility(View.GONE);
                        PhoneClient.setVisibility(View.GONE);
                        PlaceClient.setVisibility(View.GONE);
                        MailClient.setVisibility(View.GONE);
                        nameClientView.setVisibility(View.GONE);
                        SecondnameClientView.setVisibility(View.GONE);
                        PatronymicView.setVisibility(View.GONE);
                        PhoneClientView.setVisibility(View.GONE);
                        PlaceClientView.setVisibility(View.GONE);
                        MailClientView.setVisibility(View.GONE);
                        namePanelView.setVisibility(View.GONE);
                        pricePanelView.setVisibility(View.GONE);
                        sizePanelView.setVisibility(View.GONE);
                        typePanelView.setVisibility(View.GONE);
                        kKDPanelView.setVisibility(View.GONE);
                        namePanel.setVisibility(View.GONE);
                        pricePanel.setVisibility(View.GONE);
                        sizePanel.setVisibility(View.GONE);
                        typePanel.setVisibility(View.GONE);
                        kKDPanel.setVisibility(View.GONE);
                        specificPanel.setVisibility(View.GONE);
                        specificPanelView.setVisibility(View.GONE);
                        Akkum.setVisibility(View.VISIBLE);
                        idKomplect.setVisibility(View.VISIBLE);
                        Conrtroller.setVisibility(View.VISIBLE);
                        Invertor.setVisibility(View.VISIBLE);
                        KOLOVODOp.setVisibility(View.VISIBLE);
                        PriceDopView.setVisibility(View.VISIBLE);
                        PriceDop.setVisibility(View.VISIBLE);
                        AkkumView.setVisibility(View.VISIBLE);
                        idKomplectView.setVisibility(View.VISIBLE);
                        ConrtrollerView.setVisibility(View.VISIBLE);
                        InvertorView.setVisibility(View.VISIBLE);
                        KOLOVODOpView.setVisibility(View.VISIBLE);
                        PriceDop.setVisibility(View.VISIBLE);
                        timeGarantView.setVisibility(View.GONE);
                        typeGarant.setVisibility(View.GONE);
                        typeGarantView.setVisibility(View.GONE);
                        timeGarant.setVisibility(View.GONE);
                        nameService .setVisibility(View.GONE);
                        staff.setVisibility(View.GONE);
                        addressService.setVisibility(View.GONE);
                        phoneService.setVisibility(View.GONE);
                        mailService.setVisibility(View.GONE);
                        nameServiceView .setVisibility(View.GONE);
                        staffView.setVisibility(View.GONE);
                        addressServiceView.setVisibility(View.GONE);
                        phoneServiceView.setVisibility(View.GONE);
                        mailServiceView.setVisibility(View.GONE);
                        typeUslugView.setVisibility(View.GONE);
                        priceUslugView.setVisibility(View.GONE);
                        timeUslugView.setVisibility(View.GONE);
                        idServiceView.setVisibility(View.GONE);
                        typeUslug.setVisibility(View.GONE);
                        priceUslug.setVisibility(View.GONE);
                        timeUslug.setVisibility(View.GONE);
                        idService.setVisibility(View.GONE);
                        password.setVisibility(View.GONE);
                        passwordView.setVisibility(View.GONE);
                        AkkumView.setVisibility(View.GONE);
                    }else
                        if (position==3){
                            kolovoPanelView.setVisibility(View.GONE);
                            kolovoPanel.setVisibility(View.GONE);
                        nameClient.setVisibility(View.GONE);
                        SecondnameClient.setVisibility(View.GONE);
                        Patronymic.setVisibility(View.GONE);
                        PhoneClient.setVisibility(View.GONE);
                        PlaceClient.setVisibility(View.GONE);
                        MailClient.setVisibility(View.GONE);
                        nameClientView.setVisibility(View.GONE);
                        SecondnameClientView.setVisibility(View.GONE);
                        PatronymicView.setVisibility(View.GONE);
                        PhoneClientView.setVisibility(View.GONE);
                        PlaceClientView.setVisibility(View.GONE);
                        MailClientView.setVisibility(View.GONE);
                        namePanelView.setVisibility(View.GONE);
                        pricePanelView.setVisibility(View.GONE);
                        sizePanelView.setVisibility(View.GONE);
                        typePanelView.setVisibility(View.GONE);
                        kKDPanelView.setVisibility(View.GONE);
                        namePanel.setVisibility(View.GONE);
                        pricePanel.setVisibility(View.GONE);
                        sizePanel.setVisibility(View.GONE);
                        typePanel.setVisibility(View.GONE);
                        kKDPanel.setVisibility(View.GONE);
                        specificPanel.setVisibility(View.GONE);
                        specificPanelView.setVisibility(View.GONE);
                        Akkum.setVisibility(View.GONE);
                        idKomplect.setVisibility(View.GONE);
                        Conrtroller.setVisibility(View.GONE);
                        Invertor.setVisibility(View.GONE);
                        KOLOVODOp.setVisibility(View.GONE);
                        PriceDopView.setVisibility(View.GONE);
                        PriceDop.setVisibility(View.GONE);
                        AkkumView.setVisibility(View.GONE);
                        idKomplectView.setVisibility(View.GONE);
                        ConrtrollerView.setVisibility(View.GONE);
                        InvertorView.setVisibility(View.GONE);
                        KOLOVODOpView.setVisibility(View.GONE);
                        PriceDop.setVisibility(View.GONE);

                        nameService .setVisibility(View.VISIBLE);
                        staff.setVisibility(View.VISIBLE);
                        addressService.setVisibility(View.VISIBLE);
                        phoneService.setVisibility(View.VISIBLE);
                        mailService.setVisibility(View.VISIBLE);
                        nameServiceView .setVisibility(View.VISIBLE);
                        staffView.setVisibility(View.VISIBLE);
                        addressServiceView.setVisibility(View.VISIBLE);
                        phoneServiceView.setVisibility(View.VISIBLE);
                        mailServiceView.setVisibility(View.VISIBLE);

                        timeGarantView.setVisibility(View.GONE);
                        typeGarant.setVisibility(View.GONE);
                        typeGarantView.setVisibility(View.GONE);
                        timeGarant.setVisibility(View.GONE);

                            typeUslugView.setVisibility(View.GONE);
                            priceUslugView.setVisibility(View.GONE);
                            timeUslugView.setVisibility(View.GONE);
                            idServiceView.setVisibility(View.GONE);
                            typeUslug.setVisibility(View.GONE);
                            priceUslug.setVisibility(View.GONE);
                            timeUslug.setVisibility(View.GONE);
                            idService.setVisibility(View.GONE);
                            password.setVisibility(View.GONE);
                            passwordView.setVisibility(View.GONE);
                    }else
                        if (position==4){
                            kolovoPanelView.setVisibility(View.GONE);
                            kolovoPanel.setVisibility(View.GONE);
                            typeUslugView.setVisibility(View.VISIBLE);
                            priceUslugView.setVisibility(View.VISIBLE);
                            timeUslugView.setVisibility(View.VISIBLE);
                            idServiceView.setVisibility(View.VISIBLE);
                            typeUslug.setVisibility(View.VISIBLE);
                            priceUslug.setVisibility(View.VISIBLE);
                            timeUslug.setVisibility(View.VISIBLE);
                            idService.setVisibility(View.VISIBLE);
                        nameClient.setVisibility(View.GONE);
                        SecondnameClient.setVisibility(View.GONE);
                        Patronymic.setVisibility(View.GONE);
                        PhoneClient.setVisibility(View.GONE);
                        PlaceClient.setVisibility(View.GONE);
                        MailClient.setVisibility(View.GONE);
                        nameClientView.setVisibility(View.GONE);
                        SecondnameClientView.setVisibility(View.GONE);
                        PatronymicView.setVisibility(View.GONE);
                        PhoneClientView.setVisibility(View.GONE);
                        PlaceClientView.setVisibility(View.GONE);
                        MailClientView.setVisibility(View.GONE);
                        specificPanelView.setVisibility(View.GONE);
                        namePanel.setVisibility(View.GONE);
                        pricePanel.setVisibility(View.GONE);
                        sizePanel.setVisibility(View.GONE);
                        typePanel.setVisibility(View.GONE);
                        kKDPanel.setVisibility(View.GONE);
                        specificPanel.setVisibility(View.GONE);
                        namePanelView.setVisibility(View.GONE);
                        pricePanelView.setVisibility(View.GONE);
                        sizePanelView.setVisibility(View.GONE);
                        typePanelView.setVisibility(View.GONE);
                        kKDPanelView.setVisibility(View.GONE);
                        specificPanelView.setVisibility(View.GONE);
                        Akkum.setVisibility(View.GONE);
                        idKomplect.setVisibility(View.GONE);
                        Conrtroller.setVisibility(View.GONE);
                        Invertor.setVisibility(View.GONE);
                        KOLOVODOp.setVisibility(View.GONE);
                        PriceDopView.setVisibility(View.GONE);
                        PriceDop.setVisibility(View.GONE);
                        AkkumView.setVisibility(View.GONE);
                        idKomplectView.setVisibility(View.GONE);
                        ConrtrollerView.setVisibility(View.GONE);
                        InvertorView.setVisibility(View.GONE);
                        KOLOVODOpView.setVisibility(View.GONE);
                        PriceDop.setVisibility(View.GONE);
                        timeGarantView.setVisibility(View.GONE);
                        typeGarant.setVisibility(View.GONE);
                        typeGarantView.setVisibility(View.GONE);
                        timeGarant.setVisibility(View.GONE);
                        nameService .setVisibility(View.GONE);
                        staff.setVisibility(View.GONE);
                        addressService.setVisibility(View.GONE);
                        phoneService.setVisibility(View.GONE);
                        mailService.setVisibility(View.GONE);
                        nameServiceView .setVisibility(View.GONE);
                        staffView.setVisibility(View.GONE);
                        addressServiceView.setVisibility(View.GONE);
                        phoneServiceView.setVisibility(View.GONE);
                        mailServiceView.setVisibility(View.GONE);
                        idPanelView.setVisibility(View.GONE);
                        idPanel.setVisibility(View.GONE);
                        password.setVisibility(View.GONE);
                            passwordView.setVisibility(View.GONE);
                    }else
                        if (position==5){
                            kolovoPanelView.setVisibility(View.GONE);
                            kolovoPanel.setVisibility(View.GONE);
                            typeGarant.setVisibility(View.VISIBLE);
                            typeGarantView.setVisibility(View.VISIBLE);
                            timeGarantView.setVisibility(View.VISIBLE);
                            timeGarant.setVisibility(View.VISIBLE);
                            typeUslugView.setVisibility(View.GONE);
                            priceUslugView.setVisibility(View.GONE);
                            timeUslugView.setVisibility(View.GONE);
                            idServiceView.setVisibility(View.GONE);
                            typeUslug.setVisibility(View.GONE);
                            priceUslug.setVisibility(View.GONE);
                            timeUslug.setVisibility(View.GONE);
                            idService.setVisibility(View.GONE);
                            nameClient.setVisibility(View.GONE);
                            SecondnameClient.setVisibility(View.GONE);
                            Patronymic.setVisibility(View.GONE);
                            PhoneClient.setVisibility(View.GONE);
                            PlaceClient.setVisibility(View.GONE);
                            MailClient.setVisibility(View.GONE);
                            nameClientView.setVisibility(View.GONE);
                            SecondnameClientView.setVisibility(View.GONE);
                            PatronymicView.setVisibility(View.GONE);
                            PhoneClientView.setVisibility(View.GONE);
                            PlaceClientView.setVisibility(View.GONE);
                            MailClientView.setVisibility(View.GONE);
                            specificPanelView.setVisibility(View.GONE);
                            namePanel.setVisibility(View.GONE);
                            pricePanel.setVisibility(View.GONE);
                            sizePanel.setVisibility(View.GONE);
                            typePanel.setVisibility(View.GONE);
                            kKDPanel.setVisibility(View.GONE);
                            specificPanel.setVisibility(View.GONE);
                            namePanelView.setVisibility(View.GONE);
                            pricePanelView.setVisibility(View.GONE);
                            sizePanelView.setVisibility(View.GONE);
                            typePanelView.setVisibility(View.GONE);
                            kKDPanelView.setVisibility(View.GONE);
                            specificPanelView.setVisibility(View.GONE);
                            Akkum.setVisibility(View.GONE);
                            idKomplect.setVisibility(View.GONE);
                            Conrtroller.setVisibility(View.GONE);
                            Invertor.setVisibility(View.GONE);
                            KOLOVODOp.setVisibility(View.GONE);
                            PriceDopView.setVisibility(View.GONE);
                            PriceDop.setVisibility(View.GONE);
                            AkkumView.setVisibility(View.GONE);
                            idKomplectView.setVisibility(View.GONE);
                            ConrtrollerView.setVisibility(View.GONE);
                            InvertorView.setVisibility(View.GONE);
                            KOLOVODOpView.setVisibility(View.GONE);
                            PriceDop.setVisibility(View.GONE);

                            nameService .setVisibility(View.GONE);
                            staff.setVisibility(View.GONE);
                            addressService.setVisibility(View.GONE);
                            phoneService.setVisibility(View.GONE);
                            mailService.setVisibility(View.GONE);
                            nameServiceView .setVisibility(View.GONE);
                            staffView.setVisibility(View.GONE);
                            addressServiceView.setVisibility(View.GONE);
                            phoneServiceView.setVisibility(View.GONE);
                            mailServiceView.setVisibility(View.GONE);
                            idPanelView.setVisibility(View.GONE);
                            idPanel.setVisibility(View.GONE);
                            password.setVisibility(View.GONE);
                            passwordView.setVisibility(View.GONE);
        }

                        if((namePanel.getText().toString())==""){

                            transletor.setPosition(1);
                        }else
                        if((typeUslug.getText().toString())==""){
                            transletor.setPosition(2);
                        }else
                        if((Akkum.getText().toString())==""){
                        transletor.setPosition(3);
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });}}