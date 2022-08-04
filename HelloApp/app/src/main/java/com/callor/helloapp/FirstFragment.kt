package com.callor.helloapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.callor.helloapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    // FragmentFirstBinding 는 fragment를 연결하는 도구이다.
    /*
    코틀린 객체 변수들은 원칙적으로 null 값 저장이 불가능하다.
    변수의 타입뒤에 ? 를 붙여주면 제한적으로 null값을 허용해 저장 가능하다
     */
    private var _binding: FragmentFirstBinding? = null


    // This property is only valid between onCreateView and onDestroyView.
    //전역변수 만드는 방법이다.
    private val binding get() = _binding!!

    /* onCreateView
     * fragment를 확장해 객체로 만드는 함수이다.
     * return type이 view? 이고
     * 3개의 매개변수를 가지는 구조이다.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }
    
    /* onViewCreated
    fragment 함수를 화면에 그리는 함수
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* firstfragment에 있는 buttonFirst를 클릭하면
        어딘가에 설정된 nav controller를 찾아서 navigate() 함수를 실행하여
        화면을 전환하는 역할을 한다.
         */
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    /* binding의 값이 null로 바뀌었으므로,
    이것은 화면을 지우는 함수이다.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}