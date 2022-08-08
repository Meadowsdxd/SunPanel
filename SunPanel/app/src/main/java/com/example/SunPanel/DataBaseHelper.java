package com.example.SunPanel;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH; // полный путь к базе данных
    private static String DB_NAME = "NewBaseDate.db";
    private static final int SCHEMA = 1; // версия базы данных
    static final String TABLE_CLIENT = "client"; // название таблицы в бд
    // названия столбцов
    static final String COLUMN_IDCLIENT = "_id";
    static final String COLUMN_NAME = "NameClient";
    static final String COLUMN_SECONDNAMECLIENT= "SecondNameClient";
    static final String COLUMN_PATRONYMICCLIENT="PatronymicClient";
    static final String COLUMN_PHONECLIENT="PhoneClient";
    static final String COLUMN_PLACELEAVING="AddressClient";
    static final String COLUMN_MAILCLIENT="MailClient";
    static final String COLUMN_PASSWORD="Password";
    //-----------------------------------------------------------
    static final String TABLE_PANEL = "panel"; // название таблицы в бд
    static final String COLUMN_IDPANEL = "_id";
    static final String COLUMN_NAMEPANEL="NamePanel";
    static final String COLUMN_PRICEPANEL="PricePanel";
    static final String COLUMN_SIZEPANEL="SizePanel";
    static final String COLUMN_TYPEPANEL="TypePanel";
    static final String COLUMN_KKDPANEL="KKDPanel";
    static final String COLUMN_SPECIFICPANEL="SpecificPanel";
    static final String COLUMN_KOLOVOPANEL="KolovoPanel";
    //-----------------------------------------------------------
    static final String TABLE_DOP = "dop"; // название таблицы в бд
    static final String COLUMN_IDDOP = "_id";
    static final String COLUMN_AKKUM="Akkum";
    static final String COLUMN_CONTROLLER="Controller";
    static final String COLUMN_INVERTOR="Invertor";
    static final String COLUMN_PRICEDOP="Price";
    static final String COLUMN_KOLOVODOP="KolovoDop";
    static final String COLUMN_IIDKOMPLECT="idkomplect";
    static final String COLUMN_IDPANELL="IdPanel";
    //-----------------------------------------------------------
    static final String TABLE_SERVICE = "service"; // название таблицы в бд
    static final String COLUMN_IDSERVICE = "_id";
    static final String COLUMN_NAMESERVICE="NameService";
    static final String COLUMN_STAFFSERVICE="StaffService";
    static final String COLUMN_ADDRESSSERVICE="AddressService";
    static final String COLUMN_PHONESERVICE="PhoneService";
    static final String COLUMN_MAILSERVICE="MailService";
    //-----------------------------------------------------------
    static final String TABLE_OBSLUG = "poslug"; // название таблицы в бд
    static final String COLUMN_IDOBSLUG = "_id";
    static final String COLUMN_TYPEOBSLUG="TypePoslug";
    static final String COLUMN_PRICEOBSLUG="PricePoslug";
    static final String COLUMN_TIMEOBSLUG="TimeWork";
    static final String COLUMN_IDSERVICES="idServices";
    //-----------------------------------------------------------
    static final String TABLE_GARANT = "garant"; // название таблицы в бд
    static final String COLUMN_IDGARANT = "_id";
    static final String COLUMN_TYPEGARANT="TypeGarant";
    static final String COLUMN_TIMEGARANT="TimeGarant";
    //-----------------------------------------------------------
    static final String TABLE = "gener2";
    static final String COLUMN_IDC = "_id";
    static final String COLUMN_NAMEC = "name";
    static final String COLUMN_KOLOVO = "kolovo";
    static final String COLUMN_STARTTEMP = "starttemp";
    static final String COLUMN_TEMPEND= "endtemp";
    static final String COLUMN_DLINA = "dlina";
    static final String COLUMN_SHIRINA = "shirina";
    static final String COLUMN_HOLOSTHOD = "holosthod";
    static final String COLUMN_WOLTAG = "woltag";
    static final String COLUMN_OSVET= "osvet";
    static final String COLUMN_EVSR= "EVsr";
    static final String COLUMN_ETSR="Etsr";
    static final String COLUMN_XDOLYA="Xdolya";
    static final String COLUMN_PLOSHA="Plosha";
    static final String COLUMN_WI="Wi";
    static final String COLUMN_KPD="KPD";
    static final String COLUMN_KZ="Kz";
    static final String COLUMN_UMAX="Umax";
    static final String COLUMN_WS="Ws";
    static final String COLUMN_KPDWITHOUTINVERTOR="KPDWithoutInvertor";
    static final String COLUMN_KPDREGULAR="KPDRegular";
    static final String COLUMN_KPDAKKUM="KPDAkkum";
    static final String COLUMN_KPDUSTANOV="KPDUstanov";
    static final String COLUMN_KPDOBSH="KPDObsh";


    private Context myContext;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        DB_PATH =context.getFilesDir().getPath() + DB_NAME;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {
    }
    public void create_db(){
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){
            Log.d("DatabaseHelper", ex.getMessage());
        }
    }
    public SQLiteDatabase open()throws SQLException {
        return SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
