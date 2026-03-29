package com.example.studysnap.presentation.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.example.studysnap.presentation.navigation.RootNavGraph
import com.example.studysnap.presentation.navigation.Routes
import com.example.studysnap.presentation.theme.StudySnapTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StudySnapTheme {
                val navController = rememberNavController()
                var startDestination by remember { mutableStateOf(Routes.HomeScreenDestination.route) }

                RootNavGraph(
                    navController = navController,
                    startDestination = startDestination
                )
            }
        }
    }
}