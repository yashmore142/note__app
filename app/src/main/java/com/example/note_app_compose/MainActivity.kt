package com.example.note_app_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.authui.LoginScreen
import com.example.firebaseauthapp.ui.screens.SignupScreen
import com.example.note_app_compose.constant.NavRoutes
import com.example.note_app_compose.screen.AddNoteScreen
import com.example.note_app_compose.screen.NoteListScreen
import com.example.note_app_compose.screen.UpdateNoteScreen
import com.example.note_app_compose.sessionmanager.SessionManager
import com.example.note_app_compose.ui.theme.Note_App_ComposeTheme
import com.example.note_app_compose.viewmodel.NoteViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    private var mViewModel: NoteViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        val sessionManager = SessionManager(this)
        FirebaseApp.initializeApp(this)

        setContent {
            Note_App_ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navigation(mViewModel!!,sessionManager =sessionManager)
                }
            }
        }
    }
}


@Composable
fun navigation(mViewModel: NoteViewModel, sessionManager: SessionManager) {
    val navController = rememberNavController()

    val isLoggedIn = sessionManager.getUserId() != null
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) NavRoutes.NOTE_LIST else NavRoutes.LOGIN
    ) {

        composable(NavRoutes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(NavRoutes.NOTE_LIST) {
                        popUpTo(NavRoutes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToSignup = {
                    navController.navigate(NavRoutes.SIGN_UP)
                }
            )
        }

        composable(NavRoutes.SIGN_UP) {
            SignupScreen(
                onSignupSuccess = {
                    navController.navigate(NavRoutes.NOTE_LIST) {
                        popUpTo(NavRoutes.SIGN_UP) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }

        composable("note_list") {
            NoteListScreen(
                mViewModel,
                navController
            )
        }
        composable("add_note") {
            AddNoteScreen(
                mViewModel,
                navController
            )
        }
        composable("update_note/{note_id}", arguments = listOf(navArgument("note_id") {
            type = NavType.IntType
        })) {
            val noteId = it.arguments?.getInt("note_id")
            if (noteId != null) {
                UpdateNoteScreen(noteId, mViewModel, navController)


            }
        }
    }
}

