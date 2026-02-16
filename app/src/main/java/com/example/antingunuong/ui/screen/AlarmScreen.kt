package com.example.antingunuong.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.antingunuong.R
import com.example.antingunuong.ui.theme.MintPrimary

data class Alarm(
    val id: Int,
    val time: String,
    val repeat: String,
    val isEnabled: Boolean
)

@Composable
fun AlarmScreen(navController: NavController) {
    var alarmList by remember {
        mutableStateOf(
            listOf(
                Alarm(1, "07:00", "T2, T3, T4", true),
                Alarm(2, "08:30", "T5, T6", false),
                Alarm(3, "06:15", "Everyday", true)
            )
        )
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MintPrimary,
                shape = CircleShape,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 12.dp,
                    pressedElevation = 16.dp
                ),
                modifier = Modifier.border(
                    width = 1.dp,
                    color = Color(0xFF2DD4BF),
                    shape = CircleShape
                ),
                onClick = {
                    val newAlarm = Alarm(
                        id = alarmList.size + 1,
                        time = "09:00",
                        repeat = "Once",
                        isEnabled = true
                    )
                    alarmList = alarmList + newAlarm
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = "Add Alarm",
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(0.dp)
                .border(1.dp, Color.White)
        ) {
            items(alarmList) { alarm ->
                AlarmItem(
                    alarm = alarm,
                    onToggle = { isChecked ->
                        alarmList = alarmList.map {
                            if (it.id == alarm.id)
                                it.copy(isEnabled = isChecked)
                            else it
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AlarmItem(
    alarm: Alarm,
    onToggle: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = alarm.time,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = alarm.repeat,
                    fontSize = 14.sp
                )
            }
            Switch(
                checked = alarm.isEnabled,
                onCheckedChange = onToggle
            )
        }
    }
}