package com.example.appdevtemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.example.appdevtemplate.ui.theme.AppDevTemplateTheme

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
fun Screen01(modifier : Modifier = Modifier){
    Canvas(
        modifier = Modifier.fillMaxSize()
    ){

        val randHeight = (0..size.height.toInt()).random().toFloat()
        val randWidth = (0..size.width.toInt()).random().toFloat()

        drawCircle(color= Color.Red, center = Offset(x= randWidth, y= randHeight), radius = size.minDimension/10f)
    }
}