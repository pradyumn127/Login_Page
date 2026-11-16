package com.example.loginpage.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.loginpage.home.HomeScreen
import com.example.loginpage.login.LoginScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Login : Screen("login")
}

@Composable
fun NavGraph(splashViewModel: SplashViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val loginState by splashViewModel.isLoggedIn.collectAsStateWithLifecycle()
    val startDest = if (loginState == true) Screen.Home.route else Screen.Login.route

    NavHost(
        navController = navController, startDestination = startDest
    ) {
        composable(Screen.Home.route) {
            HomeScreen(onLoggedOut = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            })
        }
        composable(Screen.Login.route) {
            LoginScreen(onLoginSuccess = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(0) { inclusive = true }
                }
            })
        }
    }

}