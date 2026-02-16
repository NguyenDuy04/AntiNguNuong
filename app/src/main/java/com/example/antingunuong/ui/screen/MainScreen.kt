package com.example.antingunuong.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.example.antingunuong.R

import com.example.antingunuong.navigation.Routes
import com.example.antingunuong.ui.theme.MintPrimary

@Composable
fun MainScreen(rootNavController: NavController) {
    val bottomNavController = rememberNavController()

    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentTitle = bottomNavItems
        .find { it.route == currentRoute }
        ?.title ?: ""

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = currentTitle,
                onSettingClick = {
                    rootNavController.navigate(Routes.SETTING)
                }
            )
        },
        bottomBar = {
            MyBottomNavigationBar(bottomNavController)
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = Routes.ALARM,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.ALARM) {
                AlarmScreen(rootNavController)
            }
            composable(Routes.WORLD_CLOCK) {
                WorldClockScreen(rootNavController)
            }
            composable(Routes.STOPWATCH) {
                StopwatchScreen(rootNavController)
            }
            composable(Routes.TIMER) {
                TimerScreen(rootNavController)
            }
        }
    }
}

data class BottomNavItem(
    val route: String,
    val title: String,
    val icon: Int
)
val bottomNavItems = listOf(
    BottomNavItem(Routes.ALARM, "Alarm", R.drawable.ic_alarm),
    BottomNavItem(Routes.WORLD_CLOCK, "World", R.drawable.ic_worldclock),
    BottomNavItem(Routes.STOPWATCH, "Stopwatch", R.drawable.ic_stopwatch),
    BottomNavItem(Routes.TIMER, "Timer", R.drawable.ic_timer)
)
@Composable
fun MyBottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(item.title)
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
fun MyTopAppBar(
    title: String,
    onSettingClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
    ) {
        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton(
            onClick = onSettingClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_setting),
                contentDescription = "Setting",
                tint = Color.White
            )
        }
    }
}