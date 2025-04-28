package com.example.appdevtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.input.pointer.PointerId
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import com.example.appdevtemplate.ui.theme.AppDevTemplateTheme
import kotlin.random.Random

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
    val strokes = remember { mutableStateMapOf<PointerId, Stroke>() }
    val backlog = remember { mutableStateListOf<Stroke>() }
    var strokeWidth by remember { mutableFloatStateOf(3f) }

    Column (modifier = modifier.fillMaxSize()) {
        Text(
            text = "Number of Active Fingers: ${strokes.size}"
        )
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

                                for(change in changes){
                                    val id = change.id
                                    if(change.pressed){
                                        val stroke : Stroke

                                        if(strokes[id] == null){//before
                                            stroke = Stroke(listOf(), strokeWidth, randomColor())

                                        }else{//during
                                            stroke = strokes[id]?.copy()!!
                                        }
                                        stroke.add(change.position)
                                        strokes[id] = stroke
                                        change.consume()

                                    }else{//after
                                        if(strokes[id] != null) {
                                            backlog.add(strokes[id]!!)
                                            strokes.remove(id)
                                        }
                                    }
                                }
                            }
                        }
                    }
            ) {
                fun draw(stroke : Stroke) {
                    drawPoints(
                        points = stroke.path,
                        strokeWidth = stroke.strokeWidth,
                        pointMode = PointMode.Polygon,
                        color = stroke.color
                    )
                }
                for(stroke in strokes.values){
                    draw(stroke)
                    drawCircle(
                        color = stroke.color,
                        center = stroke.path[stroke.path.size-1],
                        radius = size.minDimension / 10f
                    )
                }
                for (stroke in backlog) {
                    draw(stroke)
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

fun randomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = 1f
    )
}
