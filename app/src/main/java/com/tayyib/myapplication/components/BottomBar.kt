package com.tayyib.myapplication.components


import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.BottomNavigation
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tayyib.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.tayyib.myapplication.screen.Screen
import com.tayyib.myapplication.ui.theme.bimserColor


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(Screen.Home, Screen.Profile)
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination
    BottomNavigation(
        modifier = Modifier
            .navigationBarsPadding()
            .navigationBarsPadding()
            .shadow(20.dp, shape = MaterialTheme.shapes.medium),
        backgroundColor = Color.White,
        contentColor = Color.Gray,
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen, currentDestination: NavDestination?, navController: NavHostController
) {
    BottomNavigationItem(label = { Text(text = screen.title) },
        icon = { Icon(imageVector = screen.icon!!, contentDescription = "Navigation Icon") },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        selectedContentColor = bimserColor,
        onClick = {
            navController.navigate(screen.route)
            { navController.popBackStack() }
        })
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val navController = rememberNavController()
    MyApplicationTheme {
        BottomBar(navController = navController)
    }

}