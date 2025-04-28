package com.example.appdevtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.input.pointer.pointerInput
import com.example.appdevtemplate.ui.theme.AppDevTemplateTheme

class Aufgabe02 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDevTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen02(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Screen02(modifier: Modifier = Modifier) {
    val path = remember { mutableStateListOf<Offset>() }
    val backlog = remember { mutableStateListOf<Stroke>() }
    var strokeWidth by remember { mutableFloatStateOf(3f) }

    Column (modifier = modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .background(Color.White)

        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectDragGestures(onDragEnd = {
                            backlog.add(Stroke(path.toList(), strokeWidth))
                            path.clear()
                        }) { change, dragAmount ->
                            path.add(change.position)
                        }
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                backlog.clear()
                            }
                        )
                    }
            ) {
                drawPoints(
                    points = path,
                    strokeWidth = strokeWidth,
                    pointMode = PointMode.Polygon,
                    color = Color.Black
                )
                for (stroke in backlog) {
                    drawPoints(
                        points = stroke.path,
                        strokeWidth = stroke.strokeWidth,
                        pointMode = PointMode.Polygon,
                        color = Color.Black
                    )
                }
            }
        }
        Text(
            text = String.format("Stroke Size: %.2f", strokeWidth*10) + "%"
        )
        Slider(
            value = strokeWidth,
            onValueChange = { strokeWidth = it },
            valueRange = 0f..10f
        )

    }
}