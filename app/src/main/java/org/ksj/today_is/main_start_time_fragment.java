package org.ksj.today_is;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class main_start_time_fragment extends Fragment {
    NumberPicker hour_numberPicker, minute_numberPicker;
    Button okay_button, cancel_button;
    TextView start_time_textView;
    Integer hour, minute;
    String temp_hour, temp_minute; //9시 5분 -> 09시 5분

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_start_time_fragment,container,false);

        /////findViewById
        start_time_textView = rootView.findViewById(R.id.start_time_textView);
        /////findViewById

        /////start_time_textView
        start_time_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /////activity_main_time_fragment_dialog
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.activity_main_time_fragment_dialog);
                dialog.show();

                /////findViewById
                hour_numberPicker = dialog.findViewById(R.id.hour_numberPicker);
                minute_numberPicker = dialog.findViewById(R.id.minute_numberPicker);
                okay_button = dialog.findViewById(R.id.okay_button);
                cancel_button = dialog.findViewById(R.id.cancel_button);
                /////findViewById

                /////화면 처음 띄운 경우 초기화
                hour_numberPicker.setMinValue(0);
                hour_numberPicker.setMaxValue(23);
                minute_numberPicker.setMinValue(0);
                minute_numberPicker.setMaxValue(59);
                hour_numberPicker.setValue(9);
                /////화면 처음 띄운 경우 초기화

                okay_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        hour = hour_numberPicker.getValue();
                        minute = minute_numberPicker.getValue();

                        if(hour.toString().length() == 1){
                            temp_hour = "0"+hour.toString();
                        }//0~9시
                        else{
                            temp_hour = hour.toString();
                        }//10~23시

                        if(minute.toString().length() == 1){
                            temp_minute = "0"+minute.toString();
                        }//0~9분
                        else{
                            temp_minute = minute.toString();
                        }//10~59분

                        start_time_textView.setText(temp_hour+"시 "+temp_minute+"분");
                        dialog.dismiss();
                    }
                });//okay_button setOnClickListener

                cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });//cancel_button setOnClickListener
                /////activity_main_time_fragment_dialog
            }
        });//start_time_textView setOnClickListener
        /////start_time_textView

        return rootView;
    }
}