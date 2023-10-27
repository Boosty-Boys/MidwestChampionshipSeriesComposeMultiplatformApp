package com.boostyboys.mcs.designsystem.api.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse

interface McsColors {
    val primary: Color // Main branding color, used for important elements like buttons
    val onPrimary: Color // Text/icons on primary colored surface

    val secondary: Color // Second branding color, to contrast with primary
    val onSecondary: Color // Text/icons on secondary colored surface

    val background: Color // Main screen background
    val onBackground: Color // Text/icons on background color

    val surface: Color // Background for elements like cards, modals
    val onSurface: Color // Text/icons on a surface color

    val alert: Color // For alerts, error messages/buttons or icons
    val onAlert: Color // Text/icons on error color
}

internal val LocalColors: ProvidableCompositionLocal<McsColors> = staticCompositionLocalOf { LightColors }

object LightColors : McsColors {
    override val primary = Color(0xFF005CBA)
    override val onPrimary = Color(0xFFFFFFFF)

    override val secondary = Color(0xFFA3DFFF)
    override val onSecondary = Color(0xFF000000)

    override val background = Color(0xFFFFFFFF)
    override val onBackground = Color(0xFF000000)

    override val surface = Color(0xFFF2F2F2)
    override val onSurface = Color(0xFF000000)

    override val alert = Color(0xFFFF3B30)
    override val onAlert = Color(0xFFFFFFFF)
}

object DarkColors : McsColors {
    override val primary = Color(0xFF005CBA)
    override val onPrimary = Color(0xFFFFFFFF)

    override val secondary = Color(0xFF6B9AC4)
    override val onSecondary = Color(0xFFFFFFFF)

    override val background = Color(0xFF121212)
    override val onBackground = Color(0xFFFFFFFF)

    override val surface = Color(0xFF1F1F1F)
    override val onSurface = Color(0xFFFFFFFF)

    override val alert = Color(0xFFFF453A)
    override val onAlert = Color(0xFFFFFFFF)
}

@Composable
@ReadOnlyComposable
fun contentColorFor(backgroundColor: Color) =
    McsTheme.colors.contentColorFor(backgroundColor).takeOrElse { McsTheme.colors.onBackground }

fun McsColors.contentColorFor(backgroundColor: Color): Color {
    return when (backgroundColor) {
        primary -> onPrimary
        secondary -> onSecondary
        background -> onBackground
        surface -> onSurface
        alert -> onAlert
        else -> Color.Unspecified
    }
}
