package com.callor.word.model

/* dataClass 는 Kotlin 의 독특한 class type 의 한가지로
   VO(DTO) 클래스를 선언하는 명령문

   class 클래스( 기본 생성자 )
*/
@Entity(tableName  = "tbl_words")
data class Word(
    val val_seq : Int,
    val word : String
)