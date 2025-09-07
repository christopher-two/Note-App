package org.christophertwo.notes.utils

sealed class NavigationRoutes(val route: String) {
    object Notes : NavigationRoutes("notes")
    object Note : NavigationRoutes("note")
}