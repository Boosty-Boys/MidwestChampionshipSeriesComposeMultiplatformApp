package com.boostyboys.mcs.ui.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.boostyboys.mcs.designsystem.api.theme.DarkColors
import com.boostyboys.mcs.designsystem.api.theme.LightColors
import com.boostyboys.mcs.designsystem.api.theme.McsColors
import com.boostyboys.mcs.designsystem.api.theme.Typography

class DemoScreen : Screen {

    @Composable
    override fun Content() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
        ) {
            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    text = "Light Colors",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            colorSection(LightColors)

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    text = "Dark Colors",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }

            colorSection(DarkColors)

            item { Spacer(modifier = Modifier.height(16.dp)) }

            item {
                TypographyRow(
                    textStyle = Typography.h1,
                    label = "This is the style of h1.",
                )
            }

            item {
                TypographyRow(
                    textStyle = Typography.h2,
                    label = "This is the style of h2.",
                )
            }

            item {
                TypographyRow(
                    textStyle = Typography.title,
                    label = "This is the style of title.",
                )
            }

            item {
                TypographyRow(
                    textStyle = Typography.subtitle,
                    label = "This is the style of subtitle.",
                )
            }

            item {
                TypographyRow(
                    textStyle = Typography.body,
                    label = "This is the style of body.",
                )
            }

            item {
                TypographyRow(
                    textStyle = Typography.caption,
                    label = "This is the style of caption.",
                )
            }

            item {
                TypographyRow(
                    textStyle = Typography.button,
                    label = "This is the style of button.",
                )
            }

            item { Spacer(modifier = Modifier.height(64.dp)) }
        }
    }

    private fun LazyListScope.colorSection(
        colors: McsColors,
    ) {
        item {
            ColorRow(
                color = colors.primary,
                onColor = colors.onPrimary,
                label = "onPrimary",
            )
        }

        item {
            ColorRow(
                color = colors.secondary,
                onColor = colors.onSecondary,
                label = "onSecondary",
            )
        }

        item {
            ColorRow(
                color = colors.background,
                onColor = colors.onBackground,
                label = "onBackground",
            )
        }

        item {
            ColorRow(
                color = colors.surface,
                onColor = colors.onSurface,
                label = "onSurface",
            )
        }

        item {
            ColorRow(
                color = colors.alert,
                onColor = colors.onAlert,
                label = "onAlert",
            )
        }
    }

    @Composable
    private fun ColorRow(
        color: Color,
        onColor: Color,
        label: String,
        modifier: Modifier = Modifier,
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(color = color),
            contentAlignment = Alignment.CenterStart,
            content = {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    text = label,
                    color = onColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            },
        )
    }

    @Composable
    private fun TypographyRow(
        textStyle: TextStyle,
        label: String,
        modifier: Modifier = Modifier,
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(80.dp),
            contentAlignment = Alignment.CenterStart,
            content = {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    text = label,
                    style = textStyle,
                )
            },
        )
    }
}
