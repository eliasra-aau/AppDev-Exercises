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
    val backlog = remember { mutableStateListOf<List<Offset>>() }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectDragGestures(onDragEnd = {
                    backlog.add(path.toList())
                    path.clear()
                }) { change, dragAmount ->
                    path.add(change.position)
                }
            }
    ) {
        drawPoints(
            points = path,
            strokeWidth = 3f,
            pointMode = PointMode.Polygon,
            color = Color.Black
        )
        for(list in backlog) {
            drawPoints(
                points = list,
                strokeWidth = 3f,
                pointMode = PointMode.Polygon,
                color = Color.Black
            )
        }
    }
}

