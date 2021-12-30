package com.example.livedata_viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

// 데이터 변경
// 뷰모델은 데이터 변경사항을 알려주는 라이브 데이터를 가지고 있음

enum class ActionType{
    PLUS, MINUS, MULTIPLY, DIVIDE
}

class ViewModelNumber : ViewModel() {

    companion object {
        const val TAG: String = "로그"
    }

    // mutable 라이브 데이터 - 수정 가능
    // 라이브 데이터 - 읽기 전용

    // 내부에서 설정하는 자유로은 mutable
    // 변경 가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    // 변경되지 않는 데이터를 가져올 때 '_'없이 설정
    // 공개적으로 가져오는 변수는 private 이 아닌 public 으로 외부에서도 접근가능하도록 설정
    // 하지만 값을 직접 라이브데이터에 접근하지 않고 뷰모델을 통해 가져올 수 있도록 설정

    val currentValue: LiveData<Int>
        get() = _currentValue

    // 초기값 설정
    init{
        Log.d(TAG, "ViewModelNumber - 생성자 호출")
        _currentValue.value = 0  // mutable 이기 때문에 변경 가능
    }

    // 뷰모델이 가지고 있는 값을 변경하는 메소드
    fun updateValue(actionType: ActionType, input: Int){
        when(actionType){
            ActionType.PLUS ->
                _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS ->
                _currentValue.value = _currentValue.value?.minus(input)
            ActionType.MULTIPLY ->
                _currentValue.value = _currentValue.value?.times(input)
            ActionType.DIVIDE ->
                _currentValue.value = _currentValue.value?.div(input)
        }
    }
}