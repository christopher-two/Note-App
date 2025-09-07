package org.christophertwo.notes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.christophertwo.notes.presentation.screens.note.NoteRoot
import org.christophertwo.notes.presentation.screens.notes.NotesRoot
import org.christophertwo.notes.utils.NavigationRoutes
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String = NavigationRoutes.Notes.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationRoutes.Notes.route) {
            NotesRoot(
                viewModel = koinViewModel(),
                navController = navController
            )
        }
        composable(
            route = NavigationRoutes.Note.route + "/{id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
            })
        ) {
            NoteRoot(viewModel = koinViewModel(), navController = navController)
        }
    }
}