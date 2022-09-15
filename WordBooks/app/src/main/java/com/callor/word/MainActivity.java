package com.callor.word;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.callor.word.model.WordVO;
import com.callor.word.model.WordViewModel;
import com.callor.word.model.WordViewModelFactory;

import java.util.Random;

/* Activity
   앱을 실행했을 때 보여지는 객체
   반드시 1개이상 존재해야 화면 구현됨.
 */
public class MainActivity extends AppCompatActivity {

    //원래 이미지 사이즈를 보관해줄 변수 선언
    private int imageWidth = 0;
    private int imageHeight = 0;

    private WordViewModel viewModel;

    // android 앱이 실행될 때 화면을 만들어주는 method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면을 그리는데 필요한 요소가 있다.
        setContentView(R.layout.activity_main);

        /* ViewModel 활용해
        tbl_words 데이터베이스로부터 SELECT ALL 수행하라.
         */
        viewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(WordViewModel.class);

        viewModel.selectAll().observe(this,

                //람다방식의 코드이다.
                wordList -> {
                 for(WordVO word : wordList) {
                     Log.d("MAIN",word.getWord());
                 }
                });



        //activity main.xml 에 설정된 btn_size 위젯을 사용하기 위한 객체로 설정
        Button btn_size = findViewById(R.id.btn_size);

        /* new View.OnClickListener()
        버튼을 클릭 또는 터치할 때 이벤트(할 일) 정하기.
        무명 클래스, 익명 클래스 방식으로 이벤트 핸들러 설정하기.
         */

        btn_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "이미지 변경할래?",
                        Toast.LENGTH_SHORT).show();

                String word = String.format("뿌니 %d", Math.floor(Math.random() * 100),0);
                WordVO wordVO = new WordVO(0,word);
                viewModel.insert(wordVO);

                /* 버튼 클릭되면 이미지 사이즈 변경하는 방법
                   1. 디바이스 화면 해상도 알아야 한다.
                   2. 표시된 이미지의 크기를 알아야 한다.
                 */

/*              1) 디바이스 화면의 해상도를 얻기 위한 객체 선언하기(이미지 꽉차게 만드는 것인데, 지금 작동 안한다.)
                DisplayMetrics metrics = new DisplayMetrics();
                WindowManager windowManager = (WindowManager)getApplicationContext().
                                              getSystemService(Context.WINDOW_SERVICE);
                windowManager.getDefaultDisplay().getMetrics(metrics);
*/

                ImageView imageView = findViewById(R.id.image);
                //ConstraintLayout 에 포함된 ImageView 의 layout 정보 가져와서
                ConstraintLayout.LayoutParams params =
                                        (ConstraintLayout.LayoutParams)imageView.getLayoutParams();

                if(imageWidth == 0) {
                    imageWidth = params.width;
                    imageHeight = params.height;
                }
                if(imageWidth == params.width) {
                // width 와 height 의 정보 변경하기
                params.width = 1000;
                params.height= 1400;
                //후에 작동 되도록 세팅해주기
                } else {
                    params.width = imageWidth;
                    params.height = imageHeight;
                }
                imageView.setLayoutParams(params);
            }
        });

    }
}