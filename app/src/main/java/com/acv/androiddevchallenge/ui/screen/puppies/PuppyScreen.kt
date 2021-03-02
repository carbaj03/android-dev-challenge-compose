package com.acv.androiddevchallenge.ui.screen.puppies

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
//import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import com.acv.androiddevchallenge.ui.model.Id
import com.acv.androiddevchallenge.ui.screen.detail.PuppyDetailScreen
import com.acv.androiddevchallenge.ui.screen.detail.PuppyDetailViewModel
import com.acv.androiddevchallenge.ui.theme.PuppyShapes
import com.acv.androiddevchallenge.ui.theme.PuppyTheme

sealed class PuppyScreen(val route: String) {
    object Puppies : PuppyScreen("puppyList")
    data class PuppyDetail(val id: Id) : PuppyScreen(route) {
        companion object {
            const val route = "puppyDetail/{puppyId}}"
        }
    }
}

fun NavGraphBuilder.main(
    navController: NavHostController,
    puppyViewModel: PuppyViewModel,
    puppyDetailViewModel: PuppyDetailViewModel,
    theme: PuppyTheme,
    shape: PuppyShapes,
    onThemeChange: (PuppyTheme) -> Unit,
    onShapeChange: (PuppyShapes) -> Unit,
) {
    val main = MainNavigatorComponent(navController = navController)
    composable(PuppyScreen.Puppies.route) {
        PuppiesScreen(main, puppyViewModel, theme, shape, onThemeChange, onShapeChange)
    }
    composable(
        route = PuppyScreen.PuppyDetail.route,
//        arguments = listOf(navArgument("puppyId") { defaultValue = "me" }),
    ) {
        PuppyDetailScreen(
            mainNavigator = main,
            id = Id(1),
            puppyDetailViewModel = puppyDetailViewModel,
        )
    }
}

class MainNavigatorComponent(
    private val navController: NavHostController
) : MainNavigator {
    override fun goBack() {
        navController.popBackStack()
    }

    override fun goMain() {
        navController.navigate(PuppyScreen.Puppies.route)
    }

    override fun goDetail() {
        navController.navigate(PuppyScreen.PuppyDetail.route)
    }
}

interface MainNavigator {
    fun goBack()
    fun goMain()
    fun goDetail()
}