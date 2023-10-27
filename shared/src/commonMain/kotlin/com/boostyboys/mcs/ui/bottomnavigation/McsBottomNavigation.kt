package com.boostyboys.mcs.ui.bottomnavigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.boostyboys.mcs.designsystem.api.components.ButtonText
import com.boostyboys.mcs.designsystem.api.theme.McsTheme

// TODO add to design system with expect/actual
@Composable
internal fun McsBottomNavigation() {
    BottomNavigation(
        backgroundColor = McsTheme.colors.surface.copy(alpha = 0.75f),
        contentColor = McsTheme.colors.onSurface.copy(alpha = 0.75f),
    ) {
        TabNavigationItem(ScheduleTab)
        TabNavigationItem(TeamTab)
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    val isSelected = remember(tabNavigator.current) {
        tabNavigator.current == tab
    }

    BottomNavigationItem(
        selected = isSelected,
        onClick = { tabNavigator.current = tab },
        label = {
            ButtonText(
                text = tab.options.title,
                color = if (isSelected) {
                    McsTheme.colors.secondary
                } else {
                    McsTheme.colors.onSurface.copy(alpha = 0.75f)
                },
            )
        },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title,
                    tint = if (isSelected) {
                        McsTheme.colors.secondary
                    } else {
                        McsTheme.colors.onSurface.copy(alpha = 0.75f)
                    },
                )
            }
        },
    )
}
