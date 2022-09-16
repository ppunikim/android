package com.callor.word.persistance;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.callor.word.model.WordVO;

import java.util.List;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<WordVO>> wordList;

    public WordRepository(Application application) {
        WordDatabase db = WordDatabase.getInstance(application);
        wordDao = db.wordDao();
        wordList = wordDao.selectAll();
    }

    public LiveData<List<WordVO>> selectAll() {
        return wordDao.selectAll();
    }

    public void insert(WordVO wordVO) {

        //insert 를 수행할 때 background 에서 수행하라.
        WordDatabase.databaseExcutor.execute(
                //randa 코드로 변경(1.8이상부터 가능)
                /*
                execute() method 에게 wordDao.insert(데이터) method 를 보내
                execute() 가 wordDao.insert() method 를 실행하도록 지시

                ()->wordDao.insert(wordVO)를 자신이 직접 실행하는 것이 아니라
                databaseExecutor 에게 대신 실행하도록 지시하는 것이 람다 함수이다.
                 */
                ()->wordDao.insert(wordVO));
    }


}
