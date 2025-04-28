package com.example.appdevtemplate

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

class Stroke(
    var path : List<Offset>,
    val strokeWidth : Float = 3f,
    val color : Color = Color.Black
    ){
    fun copy() : Stroke{
        return Stroke(path, strokeWidth, color)
    }
    fun add(offset: Offset){
        val tmp = path.toMutableList()
        tmp.add(offset)
        path = tmp.toList()
    }
}