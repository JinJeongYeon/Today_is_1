package org.ksj.today_is;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class group_edit extends AppCompatActivity {
    Integer fragment_num;
    Button add_button, delete_button, edit_button, okay_button;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_edit);

        /////DB
        DBHelper dbHelper = new DBHelper(group_edit.this, "today_is.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onCreate(db);
        /////DB

        /////findViewById
        add_button = (Button)findViewById(R.id.add_button);
        delete_button = (Button)findViewById(R.id.delete_button);
        edit_button = (Button)findViewById(R.id.edit_button);
        okay_button = (Button)findViewById(R.id.okay_button);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        /////findViewById

        //MainAcitivty.java -> group_edit.java framgent_num 받아옴
        Intent intent = getIntent();
        fragment_num = intent.getIntExtra("fragment_num",0);
        //MainAcitivty.java -> group_edit.java framgent_num 받아옴

        if (fragment_num == 1) {
            ArrayList<String> group_arrayList = new ArrayList<>();//DB에서 가져온 그룹명 저장할 ArrayList

            //저장된 그룹명 불러와 group_arrayList에 저장
            String group_select_sql = "SELECT DISTINCT Group_name FROM main_group_table";
            Cursor cursor = db.rawQuery(group_select_sql, null);

            while(cursor.moveToNext()){
                group_arrayList.add(cursor.getString(0));
            }//while

            for(String i : group_arrayList){
                System.out.println(i);
            }

            //저장된 그룹명 불러와 group_arrayList에 저장

            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dialog dialog = new Dialog(group_edit.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//제목 제거
                    dialog.setContentView(R.layout.add_group);

                    dialog.show();//그룹 추가 다이얼로그 표시

                    EditText dialog_editText;
                    Button dialog_save_button, dialog_cancel_button;
                    final String[] dialog_editText_string = new String[1];

                    /////Dialog findViewById
                    dialog_editText = dialog.findViewById(R.id.editText);
                    dialog_save_button = dialog.findViewById(R.id.save_button);
                    dialog_cancel_button = dialog.findViewById(R.id.cancel_button);
                    /////Dialog findViewById

                    dialog_save_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog_editText_string[0] = dialog_editText.getText().toString();
//                            System.out.println("가져온 값 == "+dialog_editText_string[0]);

                            if(dialog_editText_string[0].isEmpty()){
                                Toast.makeText(getApplicationContext(), "그룹명을 입력하세요.", Toast.LENGTH_SHORT).show();
                            }//값 입력하지 않은 경우
                            else{
                                Boolean check = false;//일치하는 그룹명 있음 == true, 없음 == false;

                                //이미 존재하는 그룹명인지 판별
                                for(String i : group_arrayList){
                                    if(dialog_editText_string[0].equals(i)){
                                        check=true;
                                        Toast.makeText(getApplicationContext(), "이미 존재하는 그룹명입니다.",Toast.LENGTH_SHORT).show();
                                        break;
                                    }//동일한 그룹명 있는 경우
                                }
                                //이미 존재하는 그룹명인지 판별


                                if(!check){
                                    String insert_sql = "INSERT INTO main_group_table(Group_name) VALUES ('"+dialog_editText_string[0]+"');";
                                    db.execSQL(insert_sql);
                                    dialog.dismiss();

                                    group_arrayList.add(dialog_editText_string[0]);

                                    //RecyclerView 갱신
                                    
                                    Toast.makeText(getApplicationContext(), "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                }//일치하는 그룹명 없는 경우
                            }//값 입력한 경우
                        }
                    });//dialog_save_button setOnClickListener

                    dialog_cancel_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });//dialog_cancel_button setOnClickListener
                }
            });//add_button setOnClickListener

            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });//delete_button setOnClickListener

            edit_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });//edit_button setOnClickListener

            okay_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();//액티비티 종료
                }
            });//okay_button setOnClickListener
        }//main_fragment에서 호출한 경우
        else if (fragment_num == 2) {

        }//memo_fragment에서 호출한 경우
    }//onCreate

    public void change_recyclerView(ArrayList<String> group_arrayList){
        
    }//RecyclerView 갱신

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("onStart 호출");
    }//onStart

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume 호출");
    }//onResume

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause 호출");
    }//onPuase

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop 호출");
    }//onStop

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy 호출");
    }//onDestroy
}