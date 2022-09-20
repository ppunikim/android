package com.callor.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callor.todo.databinding.FragmentFirstBinding;

import java.util.Arrays;
import java.util.List;

import javax.xml.transform.Result;

/* findViewById 에 비해 viewBinding 방식 사용 장점
    1. Null PointException 에서 비교적 안전함.
    2. xml 을 직접 핸들링하기 않기 때문에 시스템에서 여유로움.
 */
public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    public FirstFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        /* OnClickListener
           1. imageView 에 src 를 설정하는데 src 에 설정된 drawable 파일에는 2개의 icon 을 설정한다.
           2. 2개 중, icon state_pressed 를 true 로 설정하기
           3. imageView 에 click event 를 설정하면 버튼 터치했을 때 바뀌는 것을 볼 수 있다.
         */
        binding.heartImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigation
//                        .findNavController(view)
//                        .navigate(R.id.action_firstFragment_to_secondFragment);

                //폰에 내장된 음성인식 기능 사용하기 위한 준비
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                
                //한글 등 언어에 관계없이 인식해달라는 말.
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                
                //STT(말하는 마이크 역할) 가 시작 됐을 때 이것을 보여달라는 말
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"말해봐");

                //이제 시작하라.
                activityResult.launch(intent);

            }
        });

        return view;
    }//end onCreateView


    private ActivityResultLauncher<Intent> activityResult
            = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {

                // 외부 Activity 를 호출하고, 그 Activity 가 보내주는 데이터를 수신하는 method
                @Override
                public void onActivityResult(ActivityResult result) {

                    //정상적으로 음성인식 돼 text 가 return 되면,
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        List<String> strText = result
                                .getData()
                                .getStringArrayListExtra(
                                        RecognizerIntent.EXTRA_RESULTS
                                );
                                binding.txt.setText(strText.get(0));
                    }
                }
            }
    );







    /* 주의사항!(onDestroyView 사용이유)
       viewBinding 방식으로 Fragment 를 사용할 때
       반드시 onDestroyView() 를 정의하고
       이 메서드에서 binding 객체 변수를 null 로 초기화 해서
       메모리 누수(lick)를 방지해야 한다.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}//end class