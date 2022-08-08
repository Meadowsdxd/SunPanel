package com.example.SunPanel;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


public class graphik2 extends Fragment {
    private ViewPager pager;
    private PagerAdapter adapter;
    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    String Potug;
    long userId=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.fragment_graphik2,container,false);
        GraphView graph = (GraphView) rootView.findViewById(R.id.graph2);
        sqlHelper = new DataBaseHelper(getContext());
        db = sqlHelper.open();
        userId = Transletor.getId();
        if( userId > 0) {
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE + " where " +
                    DataBaseHelper.COLUMN_IDC + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
        Potug= String.valueOf(Float.parseFloat(userCursor.getString(18))/10);
        }
        // activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);

// activate horizontal scrolling
        graph.getViewport().setScrollable(true);
// activate vertical scrolling
        graph.getViewport().setScrollableY(true);
// set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(24);
// set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(Float.parseFloat(Potug)+10);
double pg1= Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.1);
        double pg2=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.13);
        double pg3=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.15);
        double pg4=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.18);
        double pg5=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.2);
        double pg6=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.24);
        double pg7=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.6);
        double pg8=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.4);
        double pg9=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.2);
        double pg10=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.097);
        double pg11=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.087);
        double pg12=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.067);
        double pg13=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.047);
        double pr1=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.0097);
        double pr2=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.0087);
        double pr3=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.0067);
        double pr4=Float.parseFloat(Potug)-(Float.parseFloat(Potug)*0.0047);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(4,  0),
                new DataPoint(5,  pg6 ),
                new DataPoint(6,   pg5),
                new DataPoint(7,   pg4),
                new DataPoint(8,   pg3),
                new DataPoint(9,   pg2),
                new DataPoint(10,  pr1),
                new DataPoint(11,  pr3),
                new DataPoint(12,  pr4),
                new DataPoint(13,  pr3),
                new DataPoint(14,  pr2),
                new DataPoint(15,  pg13),
                new DataPoint(16,  pg12),
                new DataPoint(17,  pg11),
                new DataPoint(18,  pg10),
                new DataPoint(19,  pg9),
                new DataPoint(20,  pg8),
                new DataPoint(21,  pg7),
                new DataPoint(22, 0)
        });
        series.setColor(Color.GREEN);
        graph.addSeries(series);

        return rootView;
    }
}
