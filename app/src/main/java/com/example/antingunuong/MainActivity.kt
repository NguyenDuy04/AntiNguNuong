package com.example.antingunuong

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.antingunuong.ui.theme.AntiNguNuongSmartAlarmTheme
import com.example.antingunuong.ui.theme.MintPrimary

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AntiNguNuongSmartAlarmTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AntiNguNuongApp()
                }
            }
        }
    }
}

@Composable
fun AlarmScreen() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

    }
}

@Composable
fun WorldClockScreen() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("WorldClockScreen")
    }
}

@Composable
fun StopwatchScreen() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("StopwatchScreen")
    }
}

@Composable
fun TimerScreen() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("TimerScreen")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(currentScreen: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = currentScreen,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            when (currentScreen) {
                "Alarm" -> {
                    IconButton(onClick = { /* Thêm báo thức */ }) {
                        Icon(painterResource(R.drawable.ic_setting), contentDescription = null)
                    }
                }
                "WorldClock" -> {
                    IconButton(onClick = { /* Thêm báo thức */ }) {
                        Icon(painterResource(R.drawable.ic_setting), contentDescription = null)
                    }
                }
                "Stopwatch" -> {
                    IconButton(onClick = { /* Thêm báo thức */ }) {
                        Icon(painterResource(R.drawable.ic_setting), contentDescription = null)
                    }
                }
                "Timer" -> {
                    IconButton(onClick = { /* Thêm báo thức */ }) {
                        Icon(painterResource(R.drawable.ic_setting), contentDescription = null)
                    }
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        )
    )
}

@Composable
fun FABAlarm(onFABClick: () -> Unit) {
    FloatingActionButton(
        onClick = onFABClick,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MintPrimary,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 12.dp,
            pressedElevation = 16.dp
        ),
        modifier = Modifier.border(
            width = 1.dp,
            color = Color(0xFF2DD4BF), // hoặc Color.Gray nếu không muốn mint
            shape = CircleShape
        ),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = "Thêm báo thức",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun MyBottomNavigationBar(selectedItem: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf("Alarm", "WorldClock", "Stopwatch", "Timer")
    val icons = listOf(
        R.drawable.ic_alarm,
        R.drawable.ic_worldclock,
        R.drawable.ic_stopwatch,
        R.drawable.ic_timer
    )

    NavigationBar (
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {Icon(painter = painterResource(id = icons[index]), contentDescription = item, modifier = Modifier.size(24.dp))},
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { onItemSelected(index)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MintPrimary,
                    selectedTextColor = MintPrimary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    indicatorColor = MaterialTheme.colorScheme.surface
                ),
            )
        }
    }
}

@Composable
fun AntiNguNuongApp() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val screens = listOf("Alarm", "WorldClock", "Stopwatch", "Timer")
    Scaffold(
        topBar = {
            MyTopAppBar(currentScreen = screens[selectedItem])
        },
        bottomBar = {
            MyBottomNavigationBar(
                selectedItem = selectedItem,
                onItemSelected = { selectedItem = it }
            )
        },
        floatingActionButton = {
            if (selectedItem == 0) {
                FABAlarm (onFABClick = {
                    println("Đã bấm nút thêm báo thức!")
                })
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedItem) {
                0 -> AlarmScreen()
                1 -> WorldClockScreen()
                2 -> StopwatchScreen()
                3 -> TimerScreen()
            }
        }
    }
}
