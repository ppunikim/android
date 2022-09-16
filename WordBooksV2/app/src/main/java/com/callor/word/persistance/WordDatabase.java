package com.callor.word.persistance;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.callor.word.model.WordVO;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* 이클래스에서 abstract 용도
    RoomDatabase 를 상속받을때 반드시 재정의해야할 method 가 있는데,
    그 설정을 무시하고 필요한 method 만 재정의하겠다는 의미.
 */
@Database(entities = {WordVO.class},version = 1, exportSchema = false)
public abstract class WordDatabase extends RoomDatabase{

    //이 method 에서 abstract 용도 : 이 클래스를 상속받는 곳에서는 이 method 꼭 재정의하라는 의미.
    public abstract WordDao wordDao();

    /* volatile 이란
       원래 프로그램이 작동되는 과정에서 사용된 변수를 저장하는 메모리는
       운영체제, JVM 등에서 최적화를 수행하며 직접 관리를 한다.

       하지만 volatile 로 저장된 메모리는 최적화에서 제외하고,
       성능에 우선을 두는 메모리 관리를 수행하라는 의미.

       Java 에서 Volatile 선언은 JVM 메모리가 아닌 안드로이드 메모리로 관리하라고 하는 것이다.
     */
    private static volatile WordDatabase DB_CONN;

    //이 변수 선언문 이후의 method 는 thread 환경에서 background 로 실행하라.
    public static final ExecutorService databaseExcutor =
            Executors.newFixedThreadPool(3);

    
    /* SingleTone 객체 선언
       프로젝트가 실행되는 과정에서 한번만 객체를 만들고 그 객체를 재활용하는 방식
     */
    public static WordDatabase getInstance(final Context context) {
        //연결할 데이터베이스가 없으면 만들어라는 이야기임.
        if(DB_CONN == null) {
            synchronized (WordDatabase.class) {
                if(DB_CONN == null) {
                    DB_CONN = Room.databaseBuilder(context.getApplicationContext(),
                            WordDatabase.class,"wordDB").
                            fallbackToDestructiveMigration().
                           //샘플데이터 추가해 데이터가 잘 들어오는지 확인하는 과정이다.

//                            addCallback(roomCallBack).
                            build();
                }
            }
        }
        return DB_CONN;
    }//end getInstance

    //인터페이스로 직접 내부객체 생성하기
    private static final RoomDatabase.Callback roomCallBack
                                = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseExcutor.execute(
                    //ramda
                    ()->{
                        WordDao wordDao = DB_CONN.wordDao();
                        wordDao.deleteAll();

                        WordVO wordVO = new WordVO(0,"ppuni");
                        wordDao.insert(wordVO);

                        wordDao.insert(new WordVO(0,"cute"));
                        wordDao.insert(new WordVO(0,"pretty"));
                    }
            );
        }
    };



}
