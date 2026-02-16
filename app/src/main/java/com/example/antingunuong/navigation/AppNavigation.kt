package com.example.antingunuong.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.antingunuong.ui.screen.MainScreen
import com.example.antingunuong.ui.screen.AlarmScreen
import com.example.antingunuong.ui.screen.SettingScreen
import com.example.antingunuong.ui.screen.StopwatchScreen
import com.example.antingunuong.ui.screen.TimerScreen
import com.example.antingunuong.ui.screen.WorldClockScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.MAIN
    ) {

        composable(Routes.MAIN) {
            MainScreen(navController)
        }

        composable(Routes.ALARM) {
            AlarmScreen(navController)
        }

        composable(Routes.WORLD_CLOCK) {
            WorldClockScreen(navController)
        }

        composable(Routes.STOPWATCH) {
            StopwatchScreen(navController)
        }

        composable(Routes.TIMER) {
            TimerScreen(navController)
        }

        composable(
            route = Routes.SETTING,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(300)
                )
            },

            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(300)
                )
            },

            // 👇 Khi bấm back
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(300)
                )
            },

            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(300)
                )
            }
        ) {
            SettingScreen(navController)
        }
    }
}