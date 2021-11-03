package org.ksj.today_is;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main_fragment extends Fragment {
    CalendarView main_calendarView;
    RecyclerView main_recyclerView;
    Date calendarView_date;//캘린더뷰에 선택된 날짜

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_fragment,container,false);

        /////findViewById
        main_calendarView = rootView.findViewById(R.id.main_calendarView);
//        main_recyclerView = rootView.findViewById(R.id.main_recyclerView);
        /////findViewById
        
        /////화면 처음 표시된 경우 초기화
        calendarView_date = new Date(main_calendarView.getDate());//캘린더 뷰에서 날짜 가져옴

        /////해당 날짜에 저장된 ToDo 불러옴
        DBHelper dbHelper = new DBHelper(getContext(), "today_is.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);

//        String sql = "SELECT * from main_table where 날짜;";
        /////해당 날짜에 저장된 ToDo 불러옴
        /////화면 처음 표시된 경우 초기화

        /////main_calendarView
        main_calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                calendarView_date = new Date(main_calendarView.getDate());//캘린더 뷰에서 바뀐 날짜 가져옴
            }
        });/////main_calendarView setOnDateChangeListener
        /////main_calendarView

        return rootView;
    }
}