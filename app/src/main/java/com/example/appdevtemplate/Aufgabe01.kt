package com.example.appdevtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.appdevtemplate.ui.theme.AppDevTemplateTheme
import kotlin.math.absoluteValue
import kotlin.math.sqrt

class Aufgabe01 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDevTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen01(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Screen01(modifier: Modifier = Modifier) {
    var bluePosX by remember { mutableStateOf(-1f) }
    var bluePosY by remember { mutableStateOf(-1f) }
    var redPosX by remember { mutableStateOf(-1f) }
    var redPosY by remember { mutableStateOf(-1f) }

    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxSize()
        ) {
            Canvas(
                modifier = Modifier
                    .background(color = Color.White)
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = { offset ->
                                bluePosX = offset.x
                                bluePosY = offset.y
                            },
                            onDoubleTap = {
                                redPosX = (0..size.width.toInt()).random().toFloat()
                                redPosY = (0..size.height.toInt()).random().toFloat()
                            },
                            onLongPress = { offset ->
                                redPosX = offset.x
                                redPosY = offset.y
                            }

                        )
                    }
            ) {
                if (redPosX == -1f && redPosY == -1f) {
                    redPosX = (0..size.width.toInt()).random().toFloat()
                    redPosY = (0..size.height.toInt()).random().toFloat()
                }
                drawCircle(
                    color = Color.Red,
                    center = Offset(redPosX, redPosY),
                    radius = size.minDimension / 10f
                )

                if (bluePosX != -1f && bluePosY != -1f) {
                    drawCircle(
                        color = Color.Blue,
                        center = Offset(bluePosX, bluePosY),
                        radius = size.minDimension / 10f
                    )
                    drawLine(
                        start = Offset(bluePosX, bluePosY),
                        end = Offset(redPosX, redPosY),
                        color = Color.Black,
                        strokeWidth = 3f
                    )
                }
            }
        }
        if (bluePosX != -1f && bluePosY != -1f && redPosX != -1f && redPosY != -1f) {
            val dist = calcDist(bluePosX, bluePosY, redPosX, redPosY)
            Text(
                text = "Distance: ${dist.toInt()} px",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

fun calcDist(x1 : Float, y1 : Float, x2 : Float, y2 : Float) : Float{
    val xDist = (x1 - x2).absoluteValue
    val yDist = (y1 - y2).absoluteValue
    var tmp = xDist * xDist + yDist * yDist
    return sqrt(tmp)
}