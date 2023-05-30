package com.example.githubtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.githubtest.ui.theme.GithubtestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubtestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("多指觸控Compose實例")
                }
            }
        }
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var X = remember { mutableStateOf(0f) }
    var Y = remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier.fillMaxSize()
            .pointerInteropFilter { event ->
                X.value = event.getX(0)
                Y.value = event.getY(0)
                true
            }

    ){
        Canvas(modifier = Modifier){
            drawCircle(Color.Yellow, 100f, Offset(X.value, Y.value))
        }
    }


    Column {
        Row {
            Text(
                text = " $name!",
                modifier = modifier
            )
            Image(
                painter = painterResource(id = R.drawable.robot),
                contentDescription = "手掌圖片",
                alpha = 0.7f,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Blue)
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        var count = 0
        Text(
            text = count.toString(),
            fontSize = 50.sp)
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubtestTheme {
        Greeting("多指觸控Compose實例")
    }
}