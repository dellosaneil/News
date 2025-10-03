package com.thelazybattley.news.navigation

//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thelazybattley.core.ui.AzureRadiance
import com.thelazybattley.core.ui.MulledWine
import com.thelazybattley.feature.home.ui.HomeScreen

@Composable
fun NewsNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = Destination.HOME
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                Destination.entries.forEachIndexed { index, destination ->
                    val isSelected = selectedDestination == index
                    val tint = if (isSelected) AzureRadiance else MulledWine
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(route = destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                painter = painterResource(id = destination.icon),
                                contentDescription = null,
                                tint = tint
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(id = destination.label),
                                color = tint
                            )
                        }
                    )
                }
            }
        }
    ) { contentPadding ->
        AppNavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(paddingValues = contentPadding)
        )
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.HOME -> HomeScreen()
                    Destination.EXPLORE -> Text("SEARCH")
                    Destination.BOOKMARK -> Text("BOOKMARK")
                }
            }
        }
    }
}
