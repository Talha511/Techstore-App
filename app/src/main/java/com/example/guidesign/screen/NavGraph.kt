package com.example.guidesign.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.guidesign.ModelClasses.Discount

@Composable
fun Nav(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "SplashScreen") {
        composable("SplashScreen") {
            SplashScreen(
                onclickPro = { navController.navigate("ProductScreen") },
                onclickAuth = { navController.navigate("LoginScreen") }
            )
        }
        composable("LoginScreen") {
            LoginScreen(navController)

        }
        composable("ProductScreen") {
            ProductScreen(
                onClickItem = { discount ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("discount", discount)
                    navController.navigate("Details")
                }, navController
            )
        }
        composable("Details") { backStackEntry ->
            val discount =
                navController.previousBackStackEntry?.savedStateHandle?.get<Discount>("discount")

            discount?.let {
                ProductDetailScreenLayout(discount,
                    onCLickNav = {
                    navController.popBackStack(
                        route = "ProductScreen",
                        inclusive = false
                    )
                })
            }
        }
    }
}