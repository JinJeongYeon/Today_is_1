package org.ksj.today_is;

import android.content.Context;
import android.content.Intent;
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
//    CalendarView main_calendarView;
    RecyclerView main_recyclerView;
    Date calendarView_date;//캘린더뷰에 선택된 날짜
    int year, month, day;

    com.prolificinteractive.materialcalendarview.MaterialCalendarView calendarView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_fragment,container,false);

        /////findViewById
//        main_calendarView = rootView.findViewById(R.id.main_calendarView);
//        main_recyclerView = rootView.findViewById(R.id.main_recyclerView);
        calendarView = rootView.findViewById(R.id.material_calendarView);
        /////findViewById
        
        /////화면 처음 표시된 경우 초기화
//        calendarView_date = new Date(main_calendarView.getDate());//캘린더 뷰에서 날짜 가져옴
//        year = calendarView_date.getYear()+1900;
//        month = calendarView_date.getMonth()+1;
//        day = calendarView_date.getDay();
//        main_fragment_to_MainActivity(year, month, day);
        calendarView_date = calendarView.getCurrentDate().getDate();
        System.out.println("선택한 날짜 == "+calendarView_date);

//        System.out.println("선택된 날짜   "+year+"년 "+month+"월 "+day+"일");

        /////해당 날짜에 저장된 ToDo 불러옴
        DBHelper dbHelper = new DBHelper(getContext(), "today_is.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);

//        String sql = "SELECT * from main_table where 날짜;";
        /////해당 날짜에 저장된 ToDo 불러옴
        /////화면 처음 표시된 경우 초기화

        /////main_calendarView
//        main_calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView calendarView, int calendarView_year, int calendarView_month, int calendarView_day) {
//                year = calendarView_year;
//                month = calendarView_month+1;
//                day = calendarView_day;//선택한 날짜값 갱신
//                System.out.println("가져온 데이터 출력   "+year+"년 "+month+"월 "+day+"일");
////                main_fragment_to_MainActivity(year, month, day);
//            }
//        });/////main_calendarView setOnDateChangeListener
        /////main_calendarView



        return rootView;
    }//onCreateView

//    public void main_fragment_to_MainActivity(int year, int month, int day){
//
//    }//main_fragment -> MainActivity 날짜 값 전달
}