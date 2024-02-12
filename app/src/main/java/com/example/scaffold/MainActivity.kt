package com.example.scaffold

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffold.ui.theme.ScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(text = "My App")},
        actions = {
            IconButton(onClick = {
                expanded = !expanded
            }) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                DropdownMenuItem(onClick = { navController.navigate("info") }, text = {Text("Info")},
                    )
                DropdownMenuItem(onClick = { navController.navigate("settings") }, text = {Text("settings")},
                    )
            }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController) {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar("My App", navController) },
        content = {Text(text = "Content for Main screen")}
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Info", navController) },
        content = {Text(text = "Content for Info screen")}
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("Settings", navController) },
        content = {Text(text = "Content for Settings screen")}
    )
}
@Composable
fun ScaffoldApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") { MainScreen(navController) }
        composable(route = "info") { InfoScreen(navController) }
        composable(route = "settings") { SettingsScreen(navController) }
    }

}
