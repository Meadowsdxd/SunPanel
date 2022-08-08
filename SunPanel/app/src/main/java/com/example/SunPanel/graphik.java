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


public class graphik extends Fragment {
    private ViewPager pager;
    private PagerAdapter adapter;
    DataBaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    String os;
    long userId=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView=(ViewGroup)inflater.inflate(R.layout.pager_4,container,false);
        GraphView graph = (GraphView) rootView.findViewById(R.id.graph);
        sqlHelper = new DataBaseHelper(getContext());
        db = sqlHelper.open();
        userId = Transletor.getId();
        if( userId > 0) {
            userCursor = db.rawQuery("select * from " + DataBaseHelper.TABLE + " where " +
                    DataBaseHelper.COLUMN_IDC + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            os = userCursor.getString(9);
        }
        // activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);

// activate horizontal scrolling
        graph.getViewport().setScrollable(true);



// activate vertical scrolling
        graph.getViewport().setScrollableY(true);

// set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(4);
        graph.getViewport().setMaxX(24);
// set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(10000);
if(Integer.parseInt(os)!=1){
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(4,  0),
                new DataPoint(5,  Integer.parseInt(os)-2100),
                new DataPoint(6,  Integer.parseInt(os)-2000),
                new DataPoint(7,  Integer.parseInt(os)-1900),
                new DataPoint(8,  Integer.parseInt(os)-1600),
                new DataPoint(9,  Integer.parseInt(os)-1200),
                new DataPoint(10,  Integer.parseInt(os)-600),
                new DataPoint(11,  Integer.parseInt(os)),
                new DataPoint(12,  Integer.parseInt(os)),
                new DataPoint(13,  Integer.parseInt(os)),
                new DataPoint(14,  Integer.parseInt(os)),
                new DataPoint(15,  Integer.parseInt(os)-200),
                new DataPoint(16,  Integer.parseInt(os)-300),
                new DataPoint(17,  Integer.parseInt(os)-800),
                new DataPoint(18,  Integer.parseInt(os)-1300),
                new DataPoint(19,  Integer.parseInt(os)-1500),
                new DataPoint(20,  Integer.parseInt(os)-3300),
                new DataPoint(21,  Integer.parseInt(os)-4000),
                new DataPoint(22,  0)

        });
    series.setColor(Color.GREEN);
    graph.addSeries(series);}
else{ LineGraphSeries<DataPoint> series2 = new LineGraphSeries<DataPoint>(new DataPoint[] {
        new DataPoint(4,  0),
        new DataPoint(22,  0)
       }); series2.setColor(Color.GREEN);
    graph.addSeries(series2);}


        return rootView;
    }
}
