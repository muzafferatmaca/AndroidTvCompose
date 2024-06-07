package com.muzafferatmaca.androidtvcompose.presentation.navigaiton

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.muzafferatmaca.androidtvcompose.presentation.screens.catalog.CatalogBrowser
import com.muzafferatmaca.androidtvcompose.presentation.screens.details.DetailsScreen

/**
 * Created by Muzaffer Atmaca on 8.06.2024 at 00:41
 */
@Composable
fun NavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "/") {
        composable("/") {
            CatalogBrowser(
                onMovieSelected = {
                    navController.navigate("/movie/${it.id}")
                }
            )
        }
        composable(
            route = "/movie/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) {
            DetailsScreen(
                backAction = {
                    navController.popBackStack()
                    navController.navigate("/")
                }
            )
        }
    }
}