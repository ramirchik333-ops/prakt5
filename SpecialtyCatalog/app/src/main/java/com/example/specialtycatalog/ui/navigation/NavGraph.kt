package com.example.specialtycatalog.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.specialtycatalog.ui.screens.DetailScreen
import com.example.specialtycatalog.ui.screens.ListScreen

/** Маршруты приложения. */
object Routes {
    const val LIST = "list"
    const val DETAIL = "detail/{id}"
}

/**
 * Граф навигации: главный экран -> экран деталей.
 * Состояние темы передаётся в оба экрана.
 */
@Composable
fun AppNavHost(
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LIST
    ) {
        composable(Routes.LIST) {
            ListScreen(
                navController = navController,
                darkTheme = darkTheme,
                onThemeChange = onThemeChange
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            DetailScreen(
                navController = navController,
                specialtyId = id,
                darkTheme = darkTheme
            )
        }
    }
}
