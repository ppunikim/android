package com.callor.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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

        /* 화면의 상단 AppVar 에 현재 열린 fragment 정보를 표현하기
           todo_navigation.xml 에 설정된 두 개의 fragment ID 정보를 가져와
           AppBarConfiguration 에 설정해준다.
           AppBar 의 제목에 각각 fragment 에 설정된 label 값을 가져와 표현해준다.
         */
        AppBarConfiguration appBarConfiguration
                = new AppBarConfiguration.Builder(
                R.id.firstFragment,R.id.secondFragment).build();

        NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = hostFragment.getNavController();

        //NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);

        /* fragment navigation 과 AppBar title 을 서로 연동하는 코드이다.*/
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);

    }
}