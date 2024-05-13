package com.example.usptu_map.WebParsing

data class Lesson(
    val name: String,
    val startTime: String,
    val endTime: String,
    val pairNumber: Int,
    val teacher: String?,
    val building: String?,
    val room: String?
)
