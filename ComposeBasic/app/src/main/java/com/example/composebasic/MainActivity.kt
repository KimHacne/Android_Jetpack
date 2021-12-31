package com.example.composebasic

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasic.ui.theme.ComposeBasicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ComposeBasicTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("현수입니다.")
                }
            }
        }
    }
}

// 뷰
@Composable
fun Greeting(name: String) {
    Scaffold(topBar = {
        TopAppBar(title = {Text("현수 연습 앱")}
            ,backgroundColor = Color(0xffBBDFC8))  }
            ,floatingActionButtonPosition = FabPosition.End
            ,floatingActionButton = {
                FloatingActionButton(onClick = {}){
                Text("클릭") }
            },
    ) {
        //Text(text = "안녕하세요? $name!")
        MyComposableView()
    }
}

@Composable
fun MyRowItem(){
    Log.d("TAG","MyRowItem: ")
    // horizontal Linear 와 비슷
    Row(
        Modifier
            .padding(10.dp)
            .background(Color(0xffeaffd0))
            .fillMaxWidth()  // 너비 화면 채움
        ,
        verticalAlignment = Alignment.CenterVertically  //gravity 와 유사
    ){
        Text(text= "안녕하세요?",Modifier.padding(all =10.dp).background(Color.Yellow))
        Spacer(modifier = Modifier.size(10.dp))
        Text(text= "안녕하세요?")
        Spacer(modifier = Modifier.size(10.dp))
        Text(text= "안녕하세요?")
    }
}

@Composable
fun MyComposableView(){
    Log.d("TAG","MyComposableView: ")

    // vertical Linear
    Column(  // 정적인 리스트
        Modifier
            .background(Color.Green)
            .verticalScroll(rememberScrollState())
    ){
        for (i in 0..30){
            MyRowItem()
        }
    }
}
// 미리보기
// 실행 시 나오는것은 아님, swiftUI와 비슷

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBasicTheme {
        Greeting("현수입니다")
    }
}