package org.christophertwo.notes.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.materialkolor.DynamicMaterialTheme
import com.materialkolor.PaletteStyle

@Composable
fun NotesTheme(
    seedColor: Color = Color(0xFF769CDF),
    content: @Composable () -> Unit
) {
    DynamicMaterialTheme(
        seedColor = seedColor,
        isAmoled = true,
        style = PaletteStyle.Fidelity,
        content = content,
        typography = Typography,
    )
}