package org.ksj.today_is;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class main_group_choice_fragment extends Fragment {
    Button group_button;
    LinearLayout group_linearLayout;
    Boolean set_group = false;//group_linearLayout 보임 == true, 안보임 == false

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_group_choice_fragment, container, false);

        /////findViewById
        group_button = rootView.findViewById(R.id.group_button);
        group_linearLayout = rootView.findViewById(R.id.group_linearLayout);
        /////findViewById

        /////앱 화면 처음 띄운 경우 초기화
        if(!set_group){
            group_linearLayout.setVisibility(View.INVISIBLE);
        }
        /////앱 화면 처음 띄운 경우 초기화

        /////group_button
        group_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(set_group) {
                    set_group = false;
                    group_linearLayout.setVisibility(View.INVISIBLE);//그룹 목록 보이게
                }//false
                else{
                    set_group = true;
                    group_linearLayout.setVisibility(View.VISIBLE);//그룹 목록 보이게
                }//false
            }
        });//group_button setOnClickListener
        /////group_button

        return rootView;
    }
}