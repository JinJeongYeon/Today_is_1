package org.ksj.today_is;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.Date;

public class date_dialog extends AppCompatActivity {
    NumberPicker year_numberPicker, month_numberPicker;
    Button okay, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_dialog);

        Date date = new Date();
        long now = System.currentTimeMillis();
        int year = date.getYear()+1900;
        int month = date.getMonth()+1;

        year_numberPicker = (NumberPicker) findViewById(R.id.year_numberPicker);
        month_numberPicker = (NumberPicker) findViewById(R.id.month_numberPicker);
        okay = (Button) findViewById(R.id.okay);
        cancel = (Button) findViewById(R.id.cancel);

        Log.d("year/month",year+"년"+month+"월");
        
        /////////////year_numberPicker
        year_numberPicker.setMinValue(1950);
        year_numberPicker.setMaxValue(2100);//1950~2100년
        year_numberPicker.setValue(year);
        /////////////year_numberPicker

        /////////////month_numberPicker
        month_numberPicker.setMinValue(1);
        month_numberPicker.setMaxValue(12);//1~12월
        month_numberPicker.setValue(month);
        /////////////month_numberPicker
    }//onCreate
}