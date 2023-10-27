package com.boostyboys.mcs.designsystem.api.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

interface McsTypography {
    val h1: TextStyle // For main headings
    val h2: TextStyle // For subheadings
    val title: TextStyle // For card titles, toolbars, etc.
    val subtitle: TextStyle // For any sub-information under titles
    val body: TextStyle // Main content text
    val caption: TextStyle // Small text for things like footnotes, photo captions
    val button: TextStyle // Text on buttons
}

internal val LocalTypography: ProvidableCompositionLocal<McsTypography> = staticCompositionLocalOf { Typography }

object Typography : McsTypography {
    override val h1 = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.15.sp,
    )
    override val h2 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.1.sp,
    )
    override val title = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.15.sp,
    )
    override val subtitle = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.1.sp,
    )
    override val body = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.25.sp,
    )
    override val caption = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Light,
        letterSpacing = 0.4.sp,
    )
    override val button = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 1.25.sp,
    )
}
