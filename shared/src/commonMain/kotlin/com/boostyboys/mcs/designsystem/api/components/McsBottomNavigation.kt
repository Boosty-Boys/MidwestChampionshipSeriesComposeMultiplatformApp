package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import com.boostyboys.mcs.designsystem.api.theme.McsTheme
import com.boostyboys.mcs.designsystem.api.theme.contentColorFor
import com.boostyboys.mcs.navigation.ScheduleTab
import com.boostyboys.mcs.navigation.StandingsTab

// import androidx.compose.runtime.Composable
// TODO
// @Composable expect fun McsBottomNavigation()
@Composable
fun McsBottomNavigation(
    modifier: Modifier = Modifier,
    backgroundColor: Color = McsTheme.colors.background,
) {
    BottomNavigation(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = backgroundColor,
        contentColor = contentColorFor(backgroundColor),
    ) {
        TabNavigationItem(ScheduleTab)
        TabNavigationItem(StandingsTab)
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    val isSelected = remember(tabNavigator.current) {
        tabNavigator.current == tab
    }

    val color = if (isSelected) {
        McsTheme.colors.onBackground
    } else {
        McsTheme.colors.onBackground.copy(alpha = 0.5f)
    }

    BottomNavigationItem(
        selected = isSelected,
        onClick = { tabNavigator.current = tab },
        label = {
            CaptionText(
                text = tab.options.title,
                color = color,
                fontWeight = if (isSelected) {
                    FontWeight.Medium
                } else {
                    null
                },
            )
        },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = it,
                    contentDescription = tab.options.title,
                    tint = color,
                )
            }
        },
    )
}
