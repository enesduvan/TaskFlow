package com.enesduvan.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.enesduvan.taskflow.presentation.DetailsScreen
import com.enesduvan.taskflow.presentation.HomeScreen
import com.enesduvan.taskflow.presentation.LoginScreen
import com.enesduvan.taskflow.ui.theme.TaskFlowTheme
import com.enesduvan.taskflow.viewmodel.HomeViewModel
import com.enesduvan.taskflow.viewmodel.TaskModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val homeViewModel: HomeViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = "login_screen"
            ) {

                composable("login_screen") {
                    LoginScreen(
                        navController = navController
                    )
                }

                composable("home_screen") {
                    HomeScreen(
                        viewModel = homeViewModel,
                        navController = navController
                    )
                }

                composable("details_screen/{taskId}") { backStackEntry ->

                    val taskId = backStackEntry.arguments?.getString("taskId")

                    val task = homeViewModel.taskList.find {
                        it.Id == taskId
                    }

                    if (task != null) {
                        DetailsScreen(
                            task = task,
                            viewModel = homeViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskFlowTheme {
        Greeting("Android")
    }
}