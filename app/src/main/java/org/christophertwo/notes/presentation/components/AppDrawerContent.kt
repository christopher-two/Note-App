package org.christophertwo.notes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Brush
import compose.icons.fontawesomeicons.solid.Moon
import org.christophertwo.notes.domain.models.DrawerMenuItem
import org.christophertwo.notes.utils.RoutesDrawer


@Composable
fun AppDrawerContent(
    modifier: Modifier = Modifier,
    selectedRoute: String?,
    onItemSelected: (route: String) -> Unit,
    onCloseDrawer: () -> Unit
) {
    val uiItems = listOf(
        DrawerMenuItem(RoutesDrawer.ThemeOptions.route, "Appearance", FontAwesomeIcons.Solid.Moon),
        DrawerMenuItem(RoutesDrawer.ColorTheme.route, "Color Theme", FontAwesomeIcons.Solid.Brush)
    )

    ModalDrawerSheet(modifier = modifier) {
        Column(modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)) {
            DrawerSectionLabel("UI Settings")
            uiItems.forEach { item ->
                DrawerNavigationItem(
                    item = item,
                    isSelected = item.id == selectedRoute,
                    onClick = {
                        onItemSelected(item.id)
                        onCloseDrawer()
                    }
                )
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun DrawerSectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun DrawerNavigationItem(
    item: DrawerMenuItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    NavigationDrawerItem(
        icon = {
            Icon(
                item.icon,
                contentDescription = item.title,
                modifier = Modifier.size(24.dp)
            )
        },
        label = { Text(item.title, fontSize = 14.sp) },
        selected = isSelected,
        onClick = onClick,
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        badge = {
            item.badgeCount?.let {
                Text(it.toString())
            }
        }
    )
}