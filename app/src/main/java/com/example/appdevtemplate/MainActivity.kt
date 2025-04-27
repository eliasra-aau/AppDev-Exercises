package com.example.appdevtemplate

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.appdevtemplate.ui.theme.AppDevTemplateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDevTemplateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StartScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun StartScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        //Aufgabe01
        Button(onClick = {
            context.startActivity(Intent(context, Aufgabe01::class.java))
        }) {
            Text(text = "Aufgabe01")
        }
        //Aufgabe02
        Button(onClick = {
            context.startActivity(Intent(context, Aufgabe02::class.java))
        }) {
            Text(text = "Aufgabe02")
        }
        //Aufgabe03
        Button(onClick = {
            context.startActivity(Intent(context, Aufgabe03::class.java))
        }) {
            Text(text = "Aufgabe03")
        }
        //Aufgabe04
        Button(onClick = {
            context.startActivity(Intent(context, Aufgabe04::class.java))
        }) {
            Text(text = "Aufgabe04")
        }
    }
}