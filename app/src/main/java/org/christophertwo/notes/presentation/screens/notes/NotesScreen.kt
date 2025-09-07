package org.christophertwo.notes.presentation.screens.notes

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Bars
import compose.icons.fontawesomeicons.solid.Plus
import kotlinx.coroutines.launch
import org.christophertwo.notes.presentation.components.AppDrawerContent
import org.christophertwo.notes.presentation.components.CardNote
import org.christophertwo.notes.presentation.theme.NotesTheme
import org.christophertwo.notes.utils.NavigationRoutes
import org.christophertwo.notes.utils.RoutesDrawer

@Composable
fun NotesRoot(
    viewModel: NotesViewModel,
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NotesScreen(
        state = state,
        navController = navController,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotesScreen(
    state: NotesState,
    navController: NavHostController,
    onAction: (NotesAction) -> Unit,
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { sheetValue ->
            if (sheetValue == SheetValue.Hidden) {
                //onAction(NotesAction.DismissSheet)
            }
            true
        }
    )

    LaunchedEffect(state.currentBottomSheet, sheetState) {
        scope.launch {
            if (state.currentBottomSheet != null) {
                sheetState.show()
            } else {
                if (sheetState.isVisible) {
                    sheetState.hide()
                }
            }
        }
    }

    ModalNavigationDrawer(
        drawerContent = {
            AppDrawerContent(
                modifier = Modifier,
                selectedRoute = state.currentBottomSheet?.route,
                onItemSelected = { routeId ->
                    scope.launch { drawerState.close() } // Close drawer first
                    val selectedRoute: RoutesDrawer? = when (routeId) {
                        RoutesDrawer.ThemeOptions.route -> RoutesDrawer.ThemeOptions
                        RoutesDrawer.ColorTheme.route -> RoutesDrawer.ColorTheme
                        else -> null
                    }
                    selectedRoute?.let {
                        onAction(NotesAction.ShowSheet(it))
                    }
                },
                onCloseDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        },
        drawerState = drawerState,
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = state.titleAppBar,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            },
                            content = {
                                Icon(
                                    imageVector = FontAwesomeIcons.Solid.Bars,
                                    modifier = Modifier.size(24.dp),
                                    contentDescription = null
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
                        navController.navigate(NavigationRoutes.Note.route + "/-1")
                    },
                    containerColor = colorScheme.primaryContainer,
                    contentColor = colorScheme.onPrimaryContainer,
                    content = {
                        Icon(
                            imageVector = FontAwesomeIcons.Solid.Plus,
                            modifier = Modifier.size(24.dp),
                            contentDescription = null
                        )
                    }
                )
            },
            content = { padding ->
                NotesContent(
                    state = state,
                    padding = padding,
                    navController = navController,
                    onAction = onAction
                )
            },
            modifier = Modifier.fillMaxSize()
        )
    }
    if (state.currentBottomSheet != null) {
        ModalBottomSheet(
            onDismissRequest = { onAction(NotesAction.DismissSheet) },
            sheetState = sheetState,
            shape = MaterialTheme.shapes.large,
            containerColor = colorScheme.surfaceContainerLowest,
            dragHandle = { BottomSheetDefaults.DragHandle() }
        ) {
            Box(modifier = Modifier.padding(16.dp)) { // Consider adjusting Modifier.fillMaxSize() if not needed
                when (state.currentBottomSheet) {
                    RoutesDrawer.ThemeOptions -> {

                    }

                    RoutesDrawer.ColorTheme -> {

                    }
                }
            }
        }
    }
}

@Composable
fun NotesContent(
    state: NotesState,
    padding: PaddingValues,
    navController: NavController,
    onAction: (NotesAction) -> Unit
) {
    if (state.notes.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center,
            content = {
                Text(
                    text = "No notes",
                    color = colorScheme.onBackground,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 20.sp
                    )
                )
            }
        )
    } else {
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            content = {
                items(state.notes) { note ->
                    CardNote(
                        title = note.title,
                        content = note.content,
                        date = note.date.toString(),
                        onDeleted = {
                            onAction(NotesAction.DeleteNote(note.id))
                        },
                        onClick = {
                            Log.d("NotesScreen", "Clicked on note with ID: ${note.id}")
                            navController.navigate(NavigationRoutes.Note.route + "/${note.id}")
                        }
                    )
                }
                item { Spacer(modifier = Modifier.size(20.dp)) }
            }
        )
    }
}

@Preview
@Composable
private fun Preview() {
    NotesTheme {
        NotesScreen(
            state = NotesState(),
            navController = NavHostController(LocalContext.current),
            onAction = {}
        )
    }
}