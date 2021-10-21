package org.ksj.today_is;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button main_button, memo_button, diary_button, chart_button, settings_button, share_button;
    Button group_button, add_button, date_spinner_button;

    Button button[] = new Button[9];
    Integer[] r_Button = {
      R.id.main_button, R.id.memo_button, R.id.diary_button, R.id.chart_button, R.id.settings_button,
            R.id.share_button, R.id.group_button, R.id.add_button, R.id.date_spinner_button
    };//findViewById 하기 위한 배열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0; i<9; i++){
            button[i]=(Button)findViewById(r_Button[i]);
        }//findViewById 한꺼번에 하는 for문
    }//onCreate
}