package com.example.codelab

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codelab.ui.theme.CodelabTheme
import com.example.codelab.ui.theme.Green150
import com.example.codelab.ui.theme.Teal200
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.White) {
                    MyApp()
                }
            }
        }
    }
}

/*@Composable
private fun Greeting(name: String) {
    Surface(color = MaterialTheme.colors.primary) {
        Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }
}*/
/*
@Composable
fun HelloWorld(modifier : Modifier){
    Surface(color = Green150, modifier = modifier){
        Text(
            text = "Hello World!",
            color = Color.White,
        modifier = Modifier.padding(24.dp)
        )
    }
}
*/


@Composable
private fun MyApp() {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {
           Log.d("TAG","My APP 온보딩 클릭")
            shouldShowOnboarding = !shouldShowOnboarding
        })
    } else {
//        MyColumn(modifier= Modifier.padding(20.dp))
        MyLazyColumn(modifier= Modifier.padding(20.dp))
    }
//   Surface(color = Color.Yellow) {
////        HelloWorld(modifier = Modifier.padding(10.dp))
//       MyColumn(modifier = Modifier.padding(10.dp))
//   }

}


@Composable
fun MyColumn(names: List<String> = listOf("김현수", "현수킴","김핸스"), modifier : Modifier) {
    Column(modifier = modifier)
    {
        for (name in names) {
            MyName(name)
        }
    }
}

@Composable
fun MyName(name : String){

    val expanded = rememberSaveable { mutableStateOf(false)}

    var isOpen by remember { mutableStateOf(false)}

    val (shouldOpen,setShouldOpen) = remember { mutableStateOf(false)}

//    val extraPadding = if (expanded.value) 48.dp else 0.dp

    val extraPadding by animateDpAsState(
        if (expanded.value) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(color = Green150
        ,modifier = Modifier
            .clickable { Log.d("TAG", "My Name : $name") }
            .padding(bottom = extraPadding.coerceAtLeast(0.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ){
            Text(
                text = name,
                modifier = Modifier
                    .padding(10.dp)
                    .background(Teal200)
                    .weight(1f),
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            OutlinedButton(onClick =
            {
                Log.d("TAG","My Name 클릭됨 $name")
                expanded.value = !expanded.value  // 1번째 방식
                //isOpen = !isOpen  // 2번째 방식
                //setShouldOpen(!shouldOpen)  // 3번째 방식
            }
            ) {
                Text(text=if(expanded.value)"열림" else " 닫힘")
            }
        }

    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    // TODO: This state should be hoisted
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Composable
fun MyLazyColumn(names: List<String> = List(1000) {"number : $it"}, modifier : Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names){ name ->
            MyName(name = name)
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_NO,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    CodelabTheme {
        MyApp()
    }
}
