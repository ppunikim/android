package com.callor.todo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.callor.todo.databinding.FragmentFirstBinding;

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

            }
        });

        return view;
    }

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