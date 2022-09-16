package com.callor.word.persistance;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.callor.word.model.WordVO;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM tbl_words")
    public LiveData<List<WordVO>> selectAll();

    /* onConflict : Insert 명령을 수행할 때 만약, 충돌이 발생하면 어떻게 할 것인가
       OnConflictStrategy.ABOUT : 처리를 중단하라
       OnConflictStrategy.FAIL : 실패처리
       OnConflictStrategy.IGNORE : 충돌무시             -> 가장 많이 쓰이는 것이다.
       OnConflictStrategy.REPLACE : Update 수행
       OnConflictStrategy.ROLLBACK : 되돌리기
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(WordVO wordVO);

    @Query("DELETE FROM tbl_words")
    void deleteAll();

}
