package org.ksj.today_is;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class main_place_fragment extends Fragment {
    Button place_button;
    TextView textView;
    Boolean set_place = false;//false == 장소 추가 안함, true == 장소 추가 함

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main_place_fragment, container, false);

        /////findViewById
        place_button = rootView.findViewById(R.id.place_button);
        textView = rootView.findViewById(R.id.textView);
        /////findViewById

        /////화면 처음 띄운 경우 초기화
        if(!set_place){
            textView.setVisibility(View.INVISIBLE);
        }
        /////화면 처음 띄운 경우 초기화

        /////place_button
        place_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(set_place){
                    set_place = false;
                    textView.setVisibility(View.INVISIBLE);//안보이게
                }//true
                else{
                    set_place = true;
                    textView.setVisibility(View.VISIBLE);//보이게
                }//false
            }
        });//place_button setOnClickListener
        /////place_button

        return rootView;
    }
}