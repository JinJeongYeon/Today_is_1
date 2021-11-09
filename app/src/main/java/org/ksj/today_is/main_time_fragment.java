package org.ksj.today_is;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.text.method.DialerKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

public class main_time_fragment extends Fragment {
    TextView onOff_textView, time_textView;
    Boolean set_time = false;//시간 설정 Off == false, On == true
    Integer hour, minute;
    String temp_hour = "09", temp_minute = "00";//9시 5분 -> 09시 05분 변환 위한 문자열

    private main_time_fragment.OnTimePickerSetListener onTimePickerSetListener;

    public interface OnTimePickerSetListener{
        void onTimePickerSet_notice(String hour, String minute, Boolean check);
    }//check true == 알림 On, false == 알림 Off

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof main_time_fragment.OnTimePickerSetListener){
            onTimePickerSetListener = (main_time_fragment.OnTimePickerSetListener) context;
        }
        else{
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onTimePickerSetListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_time_fragment, container, false);

        /////findViewById
        onOff_textView = rootView.findViewById(R.id.onOff_textView);
        time_textView = rootView.findViewById(R.id.time_textView);
        /////findViewById

        /////화면 처음 띄운 경우 초기화
        if(!set_time){
            time_textView.setVisibility(View.INVISIBLE);
        }
        /////화면 처음 띄운 경우 초기화

        /////onOff_textView
        onOff_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(set_time){
                    set_time = false;
                    time_textView.setVisibility(View.INVISIBLE);
                    onOff_textView.setText("알림 Off");
                }//true
                else{
                    set_time = true;
                    time_textView.setVisibility(View.VISIBLE);
                    onOff_textView.setText("알림 On");

                    /////time_textView
                    time_textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            show_time_dialog();
                            onTimePickerSetListener.onTimePickerSet_notice(temp_hour, temp_minute, set_time);
                        }
                    });//time_textView setOnClickListener
                    /////time_textView
                }//false

                onTimePickerSetListener.onTimePickerSet_notice(temp_hour, temp_minute, set_time);
            }
        });//onOff_textView setOnClickListener
        /////onOff_textView

        return rootView;
    }

    public void show_time_dialog(){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_main_time_fragment_dialog);
        dialog.show();



        /////activity_main_time_fragment_dialog
        NumberPicker hour_numberPicker, minute_numberPicker;
        Button okay_button, cancel_button;

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

                time_textView.setText(temp_hour+"시 "+temp_minute+"분");
                dialog.dismiss();

                onTimePickerSetListener.onTimePickerSet_notice(temp_hour, temp_minute, set_time);
            }
        });//okay_button setOnClickListener

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });//cancel_button setOnClickListener
        /////activity_main_time_fragment_dialog
    }//시간 설정 다이얼로그 표시
}