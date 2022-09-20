package com.callor.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.callor.todo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* setContentView(R.layout.activity_main);
         기존의 setContentView 코드를 viewBinding 방식으로 변경

         그래서 기존에는 TextView txtView = findViewById(R.id.txt);
                       txtView.setText("뿌니");
         이렇게 적어야 하는데, 더 간단해졌다.

         */
        //위와 같이 사용해야 할 것을 activity_main.xml 을 확장해 객체로 생성하기
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        //mainBinding.txt.setText("뿌니");  //위 두줄을 이 한줄로 줄일 수 있다.
    }
}