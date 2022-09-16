package com.callor.word.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.callor.word.persistance.WordRepository;

import org.jetbrains.annotations.NonNls;

import java.util.List;

/* wordVO : 데이터를 담고 table 을 생성하는 용도의 클래스
   wordViewModel : viewModel 클래스로 데이터를 Observer 방식으로 핸들링하기 위한 도구.
                   (observer,관찰자 : 객체에 등록해 상태변화 있을 때마다 메서드 등을 변화시킴)
*/
public class WordViewModel extends AndroidViewModel {

    private WordRepository wordRepository;
    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
    }

    public LiveData<List<WordVO>> selectAll(){
        return wordRepository.selectAll();
    }
    public void insert(WordVO wordVO){
        wordRepository.insert(wordVO);
    }


}
