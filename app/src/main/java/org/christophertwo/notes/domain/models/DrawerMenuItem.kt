package org.christophertwo.notes.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerMenuItem(
    val id: String,
    val title: String,
    val icon: ImageVector,
    val badgeCount: Int? = null // Optional badge count
)