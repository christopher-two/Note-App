package org.christophertwo.notes.presentation.screens.note

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditorDefaults

@Composable
fun NoteRoot(
    viewModel: NoteViewModel = viewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NoteScreen(
        state = state,
        navController = navController,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteScreen(
    state: NoteState,
    navController: NavController,
    onAction: (NoteAction) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        value = state.title,
                        placeholder = {
                            Text(
                                text = "Title",
                                style = typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        },
                        singleLine = true,
                        onValueChange = { onAction(NoteAction.ChangeTitle(it)) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledContainerColor = colorScheme.surface,
                        ),
                        textStyle = typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        },
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = null
                            )
                        }
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            onAction(NoteAction.SaveNote)
                        },
                        content = {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorScheme.surfaceContainer,
                    scrolledContainerColor = colorScheme.onSurface,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                containerColor = colorScheme.primaryContainer,
                contentColor = colorScheme.onPrimaryContainer,
                content = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        },
        modifier = Modifier
            .fillMaxSize(),
        content = { padding ->
            NoteContent(
                padding = padding,
                onAction = onAction,
                state = state
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteContent(
    padding: PaddingValues,
    state: NoteState,
    onAction: (NoteAction) -> Unit
) {
    val richTextState = rememberRichTextState()

    // LaunchedEffect to load content into the editor when state.content changes from the ViewModel
    LaunchedEffect(state.content) {
        if (richTextState.toMarkdown() != state.content) {
            richTextState.setMarkdown(state.content)
        }
    }

    // LaunchedEffect to send content to ViewModel when a save is triggered
    LaunchedEffect(state.save) {
        if (state.save) {
            Log.d("NoteContent", "Saving note with content: ${richTextState.toMarkdown()}")
            onAction(NoteAction.ChangeContent(richTextState.toMarkdown()))
        }
    }

    RichTextEditor(
        state = richTextState,
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        colors = RichTextEditorDefaults.richTextEditorColors(
            textColor = colorScheme.onBackground,
            cursorColor = colorScheme.primary,
            containerColor = colorScheme.background
        )
    )
}
