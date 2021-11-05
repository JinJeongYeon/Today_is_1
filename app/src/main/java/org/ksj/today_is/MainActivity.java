package org.ksj.today_is;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    //////activity_main.xml button
    Button main_button, memo_button, diary_button, chart_button, settings_button, share_button;
    Button group_button, add_button, date_button;
    //////activity_main.xml button

    //////activity_date_dialog.xml button
    Button okay, cancel;
    NumberPicker year_numberPicker, month_numberPicker;
    //////activity_date_dialog.xml button

    /////MainAcitivty.java에서 필요한 변수
    int year, month, day;
    main_fragment mainFragment;
    memo_fragment memoFragment;
    diary_fragment diaryFragment;
    chart_fragment chartFragment;
    settings_fragment settingsFragment;
    Integer fragment_num = 1;
    /*
     * Fragment별 add_button setOnClickListener 다르게 하기 위해
     * 1 == main, 2 == memo, 3 == diary, 4 == chart, 5 == settings
     */

    int fragment1 = 1, fragment2 = 2, fragment3 = 3, fragment4 = 4, fragment5 = 5;//각각 메인, 메모, 일기, 통계, 설정
//    FragmentTransaction fragmentTransaction;
    /////MainAcitivty.java에서 필요한 변수

//    Button button[] = new Button[30];
//    Integer[] r_Button = {
//      R.id.main_button, R.id.memo_button, R.id.diary_button, R.id.chart_button, R.id.settings_button,
//            R.id.share_button, R.id.group_button, R.id.add_button, R.id.date_button
//    };//findViewById 하기 위한 배열

    TextView date_textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////////findViewById
//        for(int i=0; i<9; i++){
//            button[i]=(Button) findViewById(r_Button[i]);
//            Log.d("ID",r_Button[i].toString());
//        }//findViewById 한꺼번에 하는 for문

        //////activity_main.xml findViewById
        main_button = (Button) findViewById(R.id.main_button);
        memo_button = (Button) findViewById(R.id.memo_button);
        diary_button = (Button) findViewById(R.id.diary_button);
        chart_button = (Button) findViewById(R.id.chart_button);
        settings_button = (Button) findViewById(R.id.settings_button);
        share_button = (Button) findViewById(R.id.share_button);
        group_button = (Button) findViewById(R.id.group_button);
        add_button = (Button) findViewById(R.id.add_button);
        date_button = (Button) findViewById(R.id.date_button);
        date_textView = (TextView) findViewById(R.id.date_textView);
        //////activity_main.xml findViewById

        /////MainActivity.java
//        mainfragment = new main_fragment();
//        mainfragment = (main_fragment)getSupportFragmentManager().findFragmentById(R.id.main_Fragment);
//        memoFragment = new memo_fragment();
        mainFragment = new main_fragment();
        memoFragment = new memo_fragment();
        diaryFragment = new diary_fragment();
        chartFragment = new chart_fragment();
        settingsFragment = new settings_fragment();
        /////MainActivity.java

        /////////////////DB
        DBHelper dbHelper = new DBHelper(MainActivity.this, "today_is_db.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        /////////////////DB

        //////////////////date_textView
        long now = System.currentTimeMillis();//현재 날짜 가져옴
        Date date= new Date(now);
        //Log.d("Date",date.toString());

//        year = date.getYear()+1900;
//        month = date.getMonth()+1;
//        day = date.getDate();

        date_textView.setText(year+"년 "+month+"월"); //앱 처음 시작될 때 띄울 디폴트값(현재 년/월) 지정
        Bundle bundle = new Bundle();
        bundle.putInt("year",year);
        bundle.putInt("month",month);

//        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        mainfragment.setArguments(bundle);
//        fragmentTransaction.replace(R.id.main_Fragment,mainfragment);
//        fragmentTransaction.detach(mainfragment).attach(mainfragment).commit();
        //////////////////date_textView

        //////////////////date_button
        date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //날짜 선택 NumberPicker 다이얼로그 구현
                Dialog dialog = new Dialog(MainActivity.this);

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//제목 없앰
                dialog.getWindow().setGravity(Gravity.BOTTOM);//위치 아래로
                dialog.setContentView(R.layout.activity_date_dialog);

                Point device_size = get_Device_size();//디바이스 크기 구하는 함수 호출

                Window window = dialog.getWindow();//다이얼로그 화면 들고옴
                int x = device_size.x;
                int y = (int) Math.round(device_size.y * 0.3);
                window.setLayout(x,y);//다이얼로그 크기 변경(가로 -> 디바이스 가로와 동일, 세로 -> 디바이스 세로 * 0.3)

                dialog.show();

                /////activity_date_dialog findViewById
                okay = dialog.findViewById(R.id.okay);
                cancel = dialog.findViewById(R.id.cancel);
                year_numberPicker = dialog.findViewById(R.id.year_numberPicker);
                month_numberPicker = dialog.findViewById(R.id.month_numberPicker);
                /////activity_date_dialog findViewById

                /////year_numberPicker
                year_numberPicker.setMinValue(1990);
                year_numberPicker.setMaxValue(2100);
                year_numberPicker.setValue(year);//초기값 현재 년도
                /////year_numberPicker

                /////month_numberPicker
                month_numberPicker.setMinValue(1);
                month_numberPicker.setMaxValue(12);
                month_numberPicker.setValue(month);//초기값 현재 월
                /////month_numberPicker

                /////okay button
                okay.setOnClickListener(new View.OnClickListener() {
                    private Object main_fragment;

                    @Override
                    public void onClick(View view) {
                        year = year_numberPicker.getValue();//사용자가 선택한 년도
                        month = month_numberPicker.getValue();//사용자가 선택한 월
                        Log.d("바뀐 날짜",year+"년 "+month+"월");

                        date_textView.setText(year+"년 "+month+"월");

                        /////MainAcitivty -> main_fragment 값 전달

//                        Bundle bundle = new Bundle(2);//파라미터 == 전달하려는 값 갯수
//                        bundle.putInt("year",year);
//                        bundle.putInt("month",month);
//                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                        mainfragment.setArguments(bundle);
//                        fragmentTransaction.replace(R.id.main_Fragment,mainfragment);
//                        fragmentTransaction.detach(mainfragment).attach(mainfragment).commit();
//                        /////MainAcitivty -> main_fragment 값 전달

                        dialog.dismiss();
                    }
                });
                /////okay button

                /////cancel button
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                /////cancel button
            }
        });
        //////////////////date_button

        //////////////////main_button
        main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(1);
            }
        });
        //////////////////main_button

        //////////////////memo_button
        memo_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(2);
            }
        });
        //////////////////memo_button

        //////////////////diary_button
        diary_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(3);
            }
        });
        //////////////////diary_button

        //////////////////chart_button
        chart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(4);
            }
        });
        //////////////////chart_button

        //////////////////settings_button
        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_fragment(5);
            }
        });
        //////////////////settings_button

        //////////////////share_button
        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //////////////////settings_button

        //////////////////group_button
        group_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //////////////////group_button

        //////////////////add_button
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_fragment test = (main_fragment) getSupportFragmentManager().findFragmentById(R.id.main_Fragment);
                year = test.year;
                month = test.month;
                day = test.day;
                
//                System.out.println("프래그먼트에서 선택한 날짜     "+year+"년 "+month+"월 "+day+"일");
                Intent intent = new Intent(getApplicationContext(), add_todo.class);
                intent.putExtra("year",year);
                intent.putExtra("month",month);
                intent.putExtra("day",day);
                startActivityForResult(intent,101);
            }
        });
        //////////////////add_button
    }//onCreate

    @Override
    protected void onStart() {
        super.onStart();
    }//onStart

    @Override
    protected void onResume() {
        super.onResume();
    }//onResume

    @Override
    protected void onPause() {
        super.onPause();
    }//onPause

    @Override
    protected void onStop() {
        super.onStop();
    }//onStop

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }//onDestroy

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101){
            if(resultCode == RESULT_OK){
              //저장이 완료되었습니다 Toast
                Toast.makeText(getApplicationContext(),"저장이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Point get_Device_size(){
        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        Log.d("size",size+"");

        return size;
    }//디바이스 크기 구하는 함수

    private void change_fragment(int fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        switch (fragment){
            case 1:
                fragment_num = 1;
                transaction.replace(R.id.main_Fragment, mainFragment);
                transaction.commit();

                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent(getApplicationContext(), add_todo.class);
//                        intent.putExtra("year",year);
//                        intent.putExtra("month",month);
//                        intent.putExtra("day",day);
//                        startActivityForResult(intent,101);
                    }
                });//ToDo 추가 화면으로 이동
                break;
            case 2:
                fragment_num = 2;
                transaction.replace(R.id.main_Fragment, memoFragment);
                transaction.commit();

                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });//메모 추가 화면으로 이동
                break;
            case 3:
                fragment_num = 3;
                transaction.replace(R.id.main_Fragment, diaryFragment);
                transaction.commit();

                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });//일기 추가 화면으로 이동
                break;
            case 4:
                fragment_num = 4;
                transaction.replace(R.id.main_Fragment, chartFragment);
                transaction.commit();

                add_button.setVisibility(View.INVISIBLE);
                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
            case 5:
                fragment_num = 5;
                transaction.replace(R.id.main_Fragment, settingsFragment);
                transaction.commit();

                add_button.setVisibility(View.INVISIBLE);
                add_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
                break;
        }
    }//프래그먼트 변경하는 함수
}