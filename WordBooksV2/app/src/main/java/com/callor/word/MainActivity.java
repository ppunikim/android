package com.callor.word;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.callor.word.helper.WordDiffUtil;
import com.callor.word.helper.WordListAdopter;
import com.callor.word.model.WordVO;
import com.callor.word.model.WordViewModel;
import com.callor.word.model.WordViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Random;

/* Activity
   앱을 실행했을 때 보여지는 객체
   반드시 1개이상 존재해야 화면 구현됨.
 */
public class MainActivity extends AppCompatActivity {

    //원래 이미지 사이즈를 보관해줄 변수 선언
    private int imageWidth = 0;
    private int imageHeight = 0;

    private TextInputEditText txt_word;
    private WordViewModel viewModel;
    private RecyclerView wordListView;

    // android 앱이 실행될 때 화면을 만들어주는 method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //화면을 그리는데 필요한 요소가 있다.
        setContentView(R.layout.activity_main);

        /*
        lifeCycle.ViewModelProvider 를 사용해 
        WordViewModel 클래스를 객체로 생성해 viewModel 에 세팅하기
         */
        viewModel = new ViewModelProvider
                .AndroidViewModelFactory(this.getApplication())
                .create(WordViewModel.class);
        
        /* ViewModel 활용해
        tbl_words 데이터베이스로부터 SELECT ALL 수행하라.
        insert 된가 동시에 바로 update 하라는 코드가 observer 이다.
         */



        //activity main.xml 에 설정된 btn_save 위젯을 사용하기 위한 객체로 설정
        Button btn_save = findViewById(R.id.btn_save);
        txt_word = findViewById(R.id.txt_word);
        /* new View.OnClickListener()
        버튼을 클릭 또는 터치할 때 이벤트(할 일) 정하기.
        무명 클래스, 익명 클래스 방식으로 이벤트 핸들러 설정하기.
         */
        wordListView = findViewById(R.id.word_list);

        WordListAdopter adopter = new WordListAdopter(new WordDiffUtil());
        wordListView.setAdapter(adopter);
        wordListView.setLayoutManager(new LinearLayoutManager
                (this,RecyclerView.VERTICAL,false));
        viewModel.selectAll().observe(this,

                //람다방식의 코드이다.
                wordList ->
                   adopter.submitList(wordList)
/*
                 for(WordVO word : wordList) {
                     Log.d("MAIN",word.getWord());
                 }
*/
                );


        List<WordVO> wordList = viewModel.selectAll().getValue();

        //adopter 와 데이터 연결
        adopter.submitList(wordList);


        btn_save.setOnClickListener(new View.OnClickListener() {
            //입력한 내용을 출력하기 위한 코드


            @Override
            public void onClick(View view) {
                String strWord = txt_word.getText().toString();
                Toast.makeText(view.getContext(),
                        strWord,
                        Toast.LENGTH_SHORT).show();

                viewModel.insert(new WordVO(0,strWord));
            }
        });

    }
}