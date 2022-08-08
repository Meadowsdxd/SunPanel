package com.example.SunPanel;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ADDPanel extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
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
        EditText holosthod;
        EditText osvet;
        EditText woltag;
        long userId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_d_d_panel);

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
                countButton= (Button) findViewById(R.id.countButton);
                userCursor.close();
            } else {
                // скрываем кнопку удаления
                delButton.setVisibility(View.GONE);
           //     countButton.setVisibility(View.GONE);
            }
            final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

            progressW = (TextView) findViewById(R.id.progressW);

            seekBar.setProgress(220);
            //progressW.setText("220");

            seekBar.setOnSeekBarChangeListener(this);

            final SeekBar seekBar2 = (SeekBar) findViewById(R.id.seekBar2);

            progressOS = (TextView) findViewById(R.id.progressOS);
            seekBar2.setProgress(0);
            // progressOS.setText("0");
            seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar2, int progress2, boolean fromUser) {
                    progressOS.setText("" + progress2);
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
            ContentValues cv = new ContentValues();
            cv.put(DataBaseHelper.COLUMN_NAMEC, nameBox.getText().toString());
            cv.put(DataBaseHelper.COLUMN_KOLOVO, kolovoBox.getText().toString());
            cv.put(DataBaseHelper.COLUMN_STARTTEMP, starttempBox.getText().toString());
            cv.put(DataBaseHelper.COLUMN_TEMPEND, tempEnd.getText().toString());
            cv.put(DataBaseHelper.COLUMN_DLINA, a.getText().toString());
            cv.put(DataBaseHelper.COLUMN_SHIRINA, b.getText().toString());
            cv.put(DataBaseHelper.COLUMN_HOLOSTHOD, holosthod.getText().toString());
            cv.put(DataBaseHelper.COLUMN_OSVET, osvet.getText().toString());
            cv.put(DataBaseHelper.COLUMN_WOLTAG, woltag.getText().toString());
            if (userId > 0) {
                db.update(DataBaseHelper.TABLE, cv, DataBaseHelper.COLUMN_IDC + "=" + String.valueOf(userId), null);
            } else {
                db.insert(DataBaseHelper.TABLE, null, cv);
            }
            goHome();
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
        public void add(View view){
            ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COLUMN_NAMEC, nameBox.getText().toString());
            cv.put(DataBaseHelper.COLUMN_KOLOVO, Integer.parseInt(kolovoBox.getText().toString()));
            cv.put(DataBaseHelper.COLUMN_DLINA, Integer.parseInt(a.getText().toString()));
            cv.put(DataBaseHelper.COLUMN_SHIRINA, Integer.parseInt(b.getText().toString()));
          cv.put(DataBaseHelper.COLUMN_STARTTEMP, Integer.parseInt(starttempBox.getText().toString()));
            cv.put(DataBaseHelper.COLUMN_TEMPEND, Integer.parseInt(tempEnd.getText().toString()));
            cv.put(DataBaseHelper.COLUMN_HOLOSTHOD, Integer.parseInt(holosthod.getText().toString()));
            cv.put(DataBaseHelper.COLUMN_WOLTAG, Integer.parseInt(progressW.getText().toString()));
            cv.put(DataBaseHelper.COLUMN_OSVET, Integer.parseInt(progressOS.getText().toString()));

            if (userId > 0) {
                db.update(DataBaseHelper.TABLE, cv, DataBaseHelper.COLUMN_IDC + "=" + String.valueOf(userId), null);
            } else {
                db.insert(DataBaseHelper.TABLE, null, cv);
            }
            Intent intent = new Intent(this, Swipe.class);
            startActivity(intent);
        }


    }