package org.christophertwo.notes.utils

sealed class RoutesDrawer(val route: String) {
    object ThemeOptions : RoutesDrawer("theme_options")
    object ColorTheme : RoutesDrawer("color_theme")
}