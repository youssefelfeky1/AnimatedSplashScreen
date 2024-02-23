package com.example.animatedsplashscreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color.DarkGray, modifier = Modifier.fillMaxSize()) {
                Navigation()
            }

        }
    }
}



@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"){
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }
        composable("main_screen"){
            MainScreen()
        }
    }
}

@SuppressLint("RememberReturnType")
@Composable
fun SplashScreen(navController: NavController){
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.5f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
            )
        delay(3000L)
        navController.navigate("main_screen")
    }

    Box(
        modifier =Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
        ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription ="logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}


@Composable
fun MainScreen(){
    Box(
        modifier =Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Main Screen", color = Color.White)
    }
}