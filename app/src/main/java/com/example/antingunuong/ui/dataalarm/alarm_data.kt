package com.example.antingunuong.ui.dataalarm

data class Alarm(
    val id: Int,
    val time: String,
    val amPm: String,
    val habitTitle: String,
    var isActive: Boolean = true,
    val repeatDays: String,
)