package com.example.composelist.utils

data class RandomUser(
    val name : String = "핸스핸스 김핸스",
    val description : String = "컴포즈 부수기 대작전",
    val profileImage : String = "https://randomuser.me/api/portraits/men/78.jpg"
)

object DummyDataProvider {

    val userList = List<RandomUser>(200){RandomUser()}

}