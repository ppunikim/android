package com.callor.word.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.callor.word.R;
import com.callor.word.model.WordVO;

/* 순서 헷갈리면 안된다.
1. ListAdopter 를 선언하고
2. 내부 클래스로 ListHolder 를 선언
3. ListHolder 는 RecyclerView.ViewHolder 를 상속받는다.
4. WordListAdapter 는 ListAdopter 를 상속받는다.
5. ListAdapter 에 VO 클래스와 ListHolder 클래스를 Generic 으로 선언한다.
 */
public class WordListAdopter extends ListAdapter<WordVO, WordListAdopter.WordListHolder> {

    public WordListAdopter(@NonNull DiffUtil.ItemCallback<WordVO> diffCallback) {
        super(diffCallback);
    }
    protected WordListAdopter(@NonNull AsyncDifferConfig<WordVO> config) {
        super(config);
    }

    @NonNull
    @Override
    public WordListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //item 을 표현하기 위한 word_item.xml 을 Holder 에게 보내기 위한 준비
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.word_item,parent,false);

        WordListHolder holder = new WordListHolder(view);
        return holder;
    }//홀더 만들고
    @Override
    public void onBindViewHolder(@NonNull WordListHolder holder, int position) {
        holder.bind(getItem(position));
    }//홀더 연결시키는 구조.

    public class WordListHolder extends RecyclerView.ViewHolder {
        TextView txt_word_item;

        //itemView 의 역할 : word_item.xml 을 대신하는 객체
        public WordListHolder(@NonNull View itemView) {
            super(itemView);
            //word_item.xml 에 선언된 textView 를 객체로 선언한 것
            txt_word_item = itemView.findViewById(R.id.txt_word_item);
        }
        //word 문자열 데이터를 받아와 TextView 에 세팅하기
        public void bind(WordVO wordVO) {
            txt_word_item.setText(wordVO.getWord());
        }

    }//end inner class
}
