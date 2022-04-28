package com.vashishth.diskschedulingalgos

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vashishth.diskschedulingalgos.navigation.AlgoNavigation
import com.vashishth.diskschedulingalgos.ui.theme.DiskSchedulingAlgosTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                AlgoNavigation()
            }
        }
    }
}
@Composable
fun MyApp(content: @Composable () -> Unit){
    DiskSchedulingAlgosTheme {
        content()
    }
}

@Composable
fun DiskImg(){
    Surface(modifier = Modifier.width(300.dp).height(250.dp),
        shape = RoundedCornerShape(corner = CornerSize(5.dp)),
        elevation = 5.dp,
        color = colorResource(id = R.color.white)) {
        Image(
            painter = painterResource(id = R.drawable.diskimg),
            contentDescription = "Disk Image"
        )
    }
}

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(targetValue = 1f,
                        animationSpec = tween(
                            durationMillis = 800,
                            easing = {
                                OvershootInterpolator(2f).getInterpolation(it)
                            }
                        )
            )
        delay(1100L)
        navController.navigate("home_screen")
    }
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().background(color = Color.Black)){
        Image(painter = painterResource(id = R.drawable.diskjetwhite),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }
}







