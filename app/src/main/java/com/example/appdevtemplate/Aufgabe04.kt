package com.example.appdevtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.appdevtemplate.ui.theme.AppDevTemplateTheme

class Aufgabe04 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDevTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Screen04(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Screen04(modifier: Modifier) {
    var scale by remember { mutableFloatStateOf(1f) }
    var angle by remember { mutableFloatStateOf(0f) }
    var offset by remember { mutableStateOf(Offset(0f,0f)) }

    val state = rememberTransformableState { scaleChange, offsetChange, rotationChange ->
        scale *= scaleChange
        angle += rotationChange
        offset += offsetChange
    }

    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        scale = 1f
                        angle = 0f
                        offset = Offset.Zero
                    }
                )
            }
    ) {
        Box(
            Modifier
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    rotationZ = angle,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .transformable(state = state)
                .background(Color.Black)
                .size(100.dp)
        )
    }
}
