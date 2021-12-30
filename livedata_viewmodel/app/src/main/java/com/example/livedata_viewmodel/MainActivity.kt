package com.example.livedata_viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val TAG:String = "로그"
    }

    lateinit var myViewModelNumber: ViewModelNumber

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰 모델 프로바이더를 통해 뷰 모델 가져오기
        // 라이프사이클을 가지고 있는 녀석을 넣어줌 (자신 즉, 여기선 MainActivity)
        // 가져오고 싶은 뷰모델 클래스를 넣어서 뷰모델을 가져옴
        myViewModelNumber = ViewModelProvider(this).get(ViewModelNumber::class.java)

        // 뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터 옵저빙
        // 데이값이 변경될 때마다 실행됨
        myViewModelNumber.currentValue.observe(this, Observer {
            Log.d(TAG,"MainActivity - myViewModelNumber - currentValue 라이브 데이터 값 변경 : $it")
            number_textview.text = it.toString()
        })

        //리스너 연결
        plus_btn.setOnClickListener(this)
        minus_btn.setOnClickListener(this)
        multiply_btn.setOnClickListener(this)
        divide_btn.setOnClickListener(this)
    }

    // 클릭
    override fun onClick(view: View?) {
        val userInput = userinput_editextview.text.toString().toInt()

        // 뷰모델에 라이브데이터 값을 변경하는 메소드 실행
        when(view){
            plus_btn ->
                myViewModelNumber.updateValue(actionType = ActionType.PLUS, userInput)
            minus_btn ->
                myViewModelNumber.updateValue(actionType = ActionType.MINUS, userInput)
            multiply_btn ->
                myViewModelNumber.updateValue(actionType = ActionType.MULTIPLY, userInput)
            divide_btn ->
                myViewModelNumber.updateValue(actionType = ActionType.DIVIDE, userInput)
        }
    }
}