package com.example.appdevtemplate

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.pointerInput
import com.example.appdevtemplate.ui.theme.AppDevTemplateTheme

class Aufgabe03 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDevTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen03(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Screen03(modifier: Modifier = Modifier) {
    val paths = remember { mutableStateMapOf<PointerId, MutableList<Offset>>() }
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
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent()
                                val changes = event.changes

                                changes.forEach { change ->
                                    val id = change.id
                                    if(change.pressed){
                                        if (paths[id] == null) {
                                            paths[id] = mutableListOf()
                                        }
                                        paths[id]?.add(change.position)
                                    }else{
                                        if(!paths[id].isNullOrEmpty()) {
                                            backlog.add(
                                                Stroke(
                                                    paths[id]?.toList() ?: listOf(),
                                                    strokeWidth
                                                )
                                            )
                                            paths.remove(id)
                                        }
                                    }
                                }
                            }
                        }
                    }
            ) {
                for(path in paths.values) {
                    drawPoints(
                        points = path,
                        strokeWidth = strokeWidth,
                        pointMode = PointMode.Polygon,
                        color = Color.Black
                    )
                }
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