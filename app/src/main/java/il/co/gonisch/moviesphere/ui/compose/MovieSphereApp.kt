package il.co.gonisch.moviesphere.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import il.co.gonisch.moviesphere.ui.compose.screen.Screen
import il.co.gonisch.moviesphere.ui.compose.screen.genres.GenresScreen


@Composable
fun MovieSphereApp() {
    val navController = rememberNavController() // Create the NavController

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            MovieSphereTopAppBar(modifier = Modifier)
        },
        bottomBar = { MovieSphereBottomNavigation(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home", // Define initial screen
            modifier = Modifier
                .padding(paddingValues)
        ) {
            composable("home") { /*TODO*/ }
            composable("genres") {
                GenresScreen()
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieSphereTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Movie Sphere",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier,
        actions = {
            IconButton(
                { /*TODO*/ }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            }
        },
    )
}

@Composable
fun MovieSphereBottomNavigation(navController: NavController) {
    val screens = listOf(
        Screen.Home,
        Screen.Genres,
    )

    NavigationBar {
        // this is a reactive way to store the navigation stack tray as a state that
        // will change automatically when navigation event occurs.
        val currentBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry.value?.destination?.route
        screens.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.label
                    )
                },
                label = { Text(screen.label) },
                selected = currentDestination == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                    }
                }
            )
        }
    }
}