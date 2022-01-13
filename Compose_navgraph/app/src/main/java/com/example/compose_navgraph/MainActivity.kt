package com.example.compose_navgraph

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_navgraph.ui.theme.Compose_navgraphTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_navgraphTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NavGraph()
                }
            }
        }
    }
}

// 네비게이션 라우트 enum (값을 가지는 enum)
enum class NAV_ROUTE(val routeName : String, val description : String, val btnColor : Color){
    MAIN("MAIN","메인 화면",Color(0xFF2E48DA)),
    LOGIN("LOGIN","로그인 화면",Color(0xFF43B5E9)),
    REGISTER("REGISTER","회원가입 화면",Color(0xFFFFBF00)),
    USER_PROFILE("USER_PROFILE","유저 프로필 화면",Color(0xFF35F83D)),
    SETTING("SETTING","설정 화면",Color(0xFFFF4105)),
}

// 네비게이션 라우트 액션
class RouteAction(navHostController: NavHostController){

    // 특정 라우트로 이동
    val navTo: (NAV_ROUTE) -> Unit = { route ->
        navHostController.navigate(route.routeName)
    }

    // 뒤로가기
    val goBack: () -> Unit = {
        navHostController.navigateUp()
    }

}

@Composable
fun NavGraph(starting : NAV_ROUTE = NAV_ROUTE.MAIN){

    // 네비게이션 컨트롤러
    val navController = rememberNavController()

    // 네비게이션 라우트 액션
    val routeAction = remember(navController) { RouteAction(navController)}

    // NavHost로 네비게이션 연결
    // 네이게이션 연결할 녀석들을 설정한다
    NavHost(navController, starting.routeName){

        // 라우트 이름 = 화면의 키
        composable(NAV_ROUTE.MAIN.routeName){
            // 화면이 들어가는 부분
            MainScreen(routeAction = routeAction)
        }
        // 라우트 이름 = 화면의 키
        composable(NAV_ROUTE.LOGIN.routeName){
            // 화면이 들어가는 부분
            LoginScreen(routeAction = routeAction)
        }
        // 라우트 이름 = 화면의 키
        composable(NAV_ROUTE.USER_PROFILE.routeName){
            // 화면이 들어가는 부분
            ProfileScreen(routeAction = routeAction)
        }
        // 라우트 이름 = 화면의 키
        composable(NAV_ROUTE.REGISTER.routeName){
            // 화면이 들어가는 부분
            RegisterScreen(routeAction = routeAction)
        }
        // 라우트 이름 = 화면의 키
        composable(NAV_ROUTE.SETTING.routeName){
            // 화면이 들어가는 부분
            SettingScreen(routeAction = routeAction)
        }


    }
}
// 로그인 화면
@Composable
fun LoginScreen(routeAction: RouteAction){
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.padding(8.dp),
            Alignment.Center
        ){
            Text(text = "로그인 화면",
                style= TextStyle(Color.Black,22.sp,FontWeight.Medium)
            )
            // 뒤로가기 버튼
            Button(onClick = routeAction.goBack,
                    modifier = Modifier.padding(16.dp)
                        .offset(y = 100.dp)
            ){
                Text("뒤로가기")
            }
        }
    }
}
// 회원가입 화면
@Composable
fun RegisterScreen(routeAction: RouteAction){
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.padding(8.dp),
            Alignment.Center
        ){
            Text(text = "회원가입 화면",
                style= TextStyle(Color.Black,22.sp,FontWeight.Medium)
            )
            // 뒤로가기 버튼
            Button(onClick = routeAction.goBack,
                modifier = Modifier.padding(16.dp)
                    .offset(y = 100.dp)
            ){
                Text("뒤로가기")
            }
        }
    }
}
// 로그인 화면
@Composable
fun ProfileScreen(routeAction: RouteAction){
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.padding(8.dp),
            Alignment.Center
        ){
            Text(text = "프로필 화면",
                style= TextStyle(Color.Black,22.sp,FontWeight.Medium)
            )
            // 뒤로가기 버튼
            Button(onClick = routeAction.goBack,
                modifier = Modifier.padding(16.dp)
                    .offset(y = 100.dp)
            ){
                Text("뒤로가기")
            }
        }
    }
}
// 로그인 화면
@Composable
fun SettingScreen(routeAction: RouteAction){
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.padding(8.dp),
            Alignment.Center
        ){
            Text(text = "설정 화면",
                style= TextStyle(Color.Black,22.sp,FontWeight.Medium)
            )
            // 뒤로가기 버튼
            Button(onClick = routeAction.goBack,
                modifier = Modifier.padding(16.dp)
                    .offset(y = 100.dp)
            ){
                Text("뒤로가기")
            }
        }
    }
}

@Composable
fun MainScreen(routeAction: RouteAction){
    Surface(modifier = Modifier.fillMaxSize()
    ){
        Column(Modifier.padding(16.dp)){
            NavBotton(route = NAV_ROUTE.REGISTER, routeAction = routeAction)
            NavBotton(route = NAV_ROUTE.USER_PROFILE, routeAction = routeAction)
            NavBotton(route = NAV_ROUTE.LOGIN, routeAction = routeAction)
            NavBotton(route = NAV_ROUTE.SETTING, routeAction = routeAction)
        }
    }
}

// Column 에 들어있는 네비게이션 버튼
@Composable
fun ColumnScope.NavBotton(route: NAV_ROUTE, routeAction: RouteAction){
    Button(onClick = {
        routeAction.navTo(route)
    },colors = ButtonDefaults.buttonColors(backgroundColor = route.btnColor),
        modifier = Modifier
            .weight(1f)
            .padding(8.dp)
            .fillMaxSize()
    ) {
        Text(text = route.description,
            style= TextStyle(Color.White,22.sp,FontWeight.Medium)
        )
    }
}

//@Preview(showBackgound = true)
//@Composable
//fun DefaultPreview() {
//    Compose_navgraphTheme {
//
//    }
//}