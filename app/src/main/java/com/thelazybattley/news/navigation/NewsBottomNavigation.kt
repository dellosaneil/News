package com.thelazybattley.news.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.thelazybattley.core.ui.theme.LocalNewsColors
import com.thelazybattley.core.ui.theme.LocalNewsTypography
import com.thelazybattley.core.util.AppDestinations
import com.thelazybattley.feature.home.ui.HomeScreen
import com.thelazybattley.feature.seeall.ui.SeeAllScreen

@Composable
fun NewsBottomNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val startDestination = BottomNavigationDestination.HOME
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }
    val routes = BottomNavigationDestination.entries.map { it.route }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
        containerColor = LocalNewsColors.current.white,
        bottomBar = {
            if(!routes.contains(element = currentRoute)) {
                return@Scaffold
            }
            val colors = LocalNewsColors.current
            NavigationBar(
                windowInsets = NavigationBarDefaults.windowInsets,
                containerColor = LocalNewsColors.current.white,
                modifier = Modifier.shadow(elevation = 8.dp)
            ) {
                BottomNavigationDestination.entries.forEachIndexed { index, destination ->
                    val isSelected = selectedDestination == index
                    val tint = if (isSelected) colors.primary else colors.grayScale
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            navController.navigate(route = destination.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
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
                                color = tint,
                                style = LocalNewsTypography.current.smallText
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = colors.transparent
                        )
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
    startDestination: BottomNavigationDestination,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route
    ) {
        BottomNavigationDestination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    BottomNavigationDestination.HOME -> HomeScreen { destination ->
                        navController.navigate(route = destination.route)
                    }
                    BottomNavigationDestination.EXPLORE -> Text("SEARCH")
                    BottomNavigationDestination.BOOKMARK -> Text("BOOKMARK")
                }
            }
        }
        AppDestinations.entries.forEach { destination ->
            composable(route = destination.route) {
                when (destination) {
                    AppDestinations.SEE_ALL -> SeeAllScreen()
                }
            }
        }
    }
}
