package com.example.composelist

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.composelist.ui.theme.ComposeListTheme
import com.example.composelist.utils.DummyDataProvider
import com.example.composelist.utils.RandomUser

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeListTheme {
                ContentView()
            }
        }
    }
}

@Composable
fun ContentView(){
    Surface(color = MaterialTheme.colors.background){
        Scaffold(backgroundColor = androidx.compose.ui.graphics.Color.White,
            topBar = { MyAppBar()}
        ){
            RandomUserListView(randomUsers = DummyDataProvider.userList)
        }
    }
}

@Composable
fun MyAppBar(){
    TopAppBar(elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.height(58.dp)
        ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            fontSize = 18.sp,
            fontWeight = FontWeight.Black

        )
    }
}

@Composable
fun RandomUserListView(randomUsers: List<RandomUser>){
    // 메모리 관리 LazyColumn
    // 리사이클러뷰와 비슷
    LazyColumn(){
//        items(randomUsers){ User ->//데이터 목록 갯수만큼 반복문 돌리는 늬앙스
//            RandomUserView(User)
//        }
        items(randomUsers){ RandomUserView(it) }
    }
}

@Composable
fun RandomUserView(randomUser: RandomUser){
    val typography =MaterialTheme.typography
    Card(
        modifier = Modifier
            .padding(10.dp) //패딩
            .fillMaxWidth() // 가로 채우기
            .padding(10.dp), // 가로 패딩
        elevation = 10.dp, // 공중에 띄우는 효과
        shape = RoundedCornerShape(12.dp) // 코너 둥글기
    ){
        Row(modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically, // gravity 를 중앙으로
            horizontalArrangement = Arrangement.spacedBy(10.dp) // 각 박스별로 10dp 씩 떨어게
        ) {
//            Box(modifier =
//                Modifier
//                    .size(width = 60.dp, height = 60.dp)
//                    .clip(CircleShape)
//                    .background(androidx.compose.ui.graphics.Color.Red)
//            )
            ProfileImage(imgUrl = randomUser.profileImage)
            Column() {
                Text(text = randomUser.name,
                        style = typography.subtitle1
                    )
                Text(text = randomUser.description,
                        style = typography.body1
                    )
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ProfileImage(imgUrl : String , modifier: Modifier = Modifier){
    // 이미지 비트맵
    val bitmap : MutableState<Bitmap?> = mutableStateOf(null)

    // 이미지 모디파이어
    val imageModifier = modifier
        .size(50.dp, 50.dp)
        .clip(CircleShape)

    Glide.with(LocalContext.current)
        .asBitmap()
        .load(imgUrl)
        .into(object : CustomTarget<Bitmap>(){
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmap.value = resource
            }
            override fun onLoadCleared(placeholder: Drawable?) {}
        })
    // 비트맵이 있다면
    bitmap.value?.asImageBitmap()?.let{ fb ->
        Image(bitmap = fb,
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
    } ?: Image(painter = painterResource(id = R.drawable.ic_baseline_empty_img),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = imageModifier
    )
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeListTheme {
        ContentView()
    }
}