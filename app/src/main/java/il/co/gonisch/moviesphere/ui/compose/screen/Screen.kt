package il.co.gonisch.moviesphere.ui.compose.screen

import il.co.gonisch.moviesphere.R

sealed class Screen(val route: String, val label: String, val icon: Int) {
    data object Home : Screen("home", "Home", R.drawable.baseline_home_24)
    data object Genres : Screen("genres", "Genres", R.drawable.baseline_category_24)
}