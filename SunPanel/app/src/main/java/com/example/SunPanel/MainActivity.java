package com.example.SunPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
        DataBaseHelper sqlHelper;
        SQLiteDatabase db;
        Cursor userCursor;
        SimpleCursorAdapter userAdapter;
        ListView userList;
        EditText userFilter;
        Spinner spinner;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userList = (ListView)findViewById(R.id.userList);
        userFilter = (EditText)findViewById(R.id.userFilter);
        search();
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
        intent.putExtra("id", id);
        startActivity(intent);
        }
        });
        sqlHelper = new DataBaseHelper(getApplicationContext());
        // создаем базу данных
        sqlHelper.create_db();

        }
        String idd;
    String[] citiess = {"Назва","Кількість", "Температура"};
    @Override
    public void onResume() {
            super.onResume();
        try {
        db = sqlHelper.open();
        userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE_CLIENT, null);
        String[] headers = new String[]{DataBaseHelper.COLUMN_NAME, DataBaseHelper.COLUMN_SECONDNAMECLIENT};
        userAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
        userCursor, headers, new int[]{android.R.id.text1, android.R.id.text2}, 0);
        // если в текстовом поле есть текст, выполняем фильтрацию
        // данная проверка нужна при переходе от одной ориентации экрана к другой
        if(!userFilter.getText().toString().isEmpty())
        userAdapter.getFilter().filter(userFilter.getText().toString());

        // установка слушателя изменения текста
        userFilter.addTextChangedListener(new TextWatcher() {
        public void afterTextChanged(Editable s) { }
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        // при изменении текста выполняем фильтрацию
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        userAdapter.getFilter().filter(s.toString()); }

        });

        // устанавливаем провайдер фильтрации
        userAdapter.setFilterQueryProvider(new FilterQueryProvider() {
        @Override
        public Cursor runQuery(CharSequence constraint) {
        if (constraint == null || constraint.length() == 0) {
        return db.rawQuery("select * from " + DataBaseHelper.TABLE_CLIENT, null); }
        else {
        return  db.rawQuery("select * from " + DataBaseHelper.TABLE_CLIENT + " where " +
        idd + " like ?", new String[]{"%" + constraint.toString() + "%"});

        }
        }
        });

        userList.setAdapter(userAdapter);
        }
        catch (SQLException ex){}
        }
        // по нажатию на кнопку запускаем UserActivity для добавления данных


        @Override
        public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
        }
        public void add(View view){
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
        }
       /* public void Helper(View view){
        Intent intent = new Intent(this, Helper.class);
        startActivity(intent);
        }*/
        public void search(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citiess);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.citiess);
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
        idd=DataBaseHelper.COLUMN_NAME;

        }else if (position==1){
        idd=DataBaseHelper.COLUMN_SECONDNAMECLIENT;

        }else if (position==2){
      //  idd=DataBaseHelper.COLUMN_PATRONYMICCLIENT;

        }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
        });
        }}