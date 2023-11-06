package com.boostyboys.mcs.ui.demo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.boostyboys.mcs.designsystem.api.components.BodyText
import com.boostyboys.mcs.designsystem.api.components.CaptionText
import com.boostyboys.mcs.designsystem.api.components.McsAlertButton
import com.boostyboys.mcs.designsystem.api.components.McsCard
import com.boostyboys.mcs.designsystem.api.components.McsCircularLoader
import com.boostyboys.mcs.designsystem.api.components.McsPrimaryButton
import com.boostyboys.mcs.designsystem.api.components.McsSecondaryButton
import com.boostyboys.mcs.designsystem.api.components.TitleText
import com.boostyboys.mcs.designsystem.api.theme.McsTheme
import com.boostyboys.mcs.designsystem.api.theme.Typography

class DemoScreen : Screen {

    @Composable
    override fun Content() {
        McsTheme {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(McsTheme.colors.background),
            ) {
                colorSection()
                typographySection()
                buttonSection()
                cardSection()
                loaderSection()

                item { Spacer(modifier = Modifier.height(64.dp)) }
            }
        }
    }

    @Composable
    private fun SectionHeader(text: String) {
        TitleText(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 32.dp, bottom = 8.dp),
            text = text,
            color = McsTheme.colors.onBackground,
            textAlign = TextAlign.Center,
        )
    }

    private fun LazyListScope.colorSection() {
        item {
            SectionHeader(text = "Colors:")
        }

        item {
            ColorRow(
                color = McsTheme.colors.primary,
                onColor = McsTheme.colors.onPrimary,
                label = "onPrimary",
            )
        }

        item {
            ColorRow(
                color = McsTheme.colors.secondary,
                onColor = McsTheme.colors.onSecondary,
                label = "onSecondary",
            )
        }

        item {
            ColorRow(
                color = McsTheme.colors.background,
                onColor = McsTheme.colors.onBackground,
                label = "onBackground",
            )
        }

        item {
            ColorRow(
                color = McsTheme.colors.surface,
                onColor = McsTheme.colors.onSurface,
                label = "onSurface",
            )
        }

        item {
            ColorRow(
                color = McsTheme.colors.alert,
                onColor = McsTheme.colors.onAlert,
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
                .height(60.dp)
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

    private fun LazyListScope.typographySection() {
        item {
            SectionHeader(text = "Typography:")
        }

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
    }

    @Composable
    private fun TypographyRow(
        textStyle: TextStyle,
        label: String,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.CenterStart,
            content = {
                Text(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                    text = label,
                    style = textStyle,
                    color = McsTheme.colors.onBackground,
                )
            },
        )
    }

    private fun LazyListScope.buttonSection() {
        item {
            SectionHeader(text = "Buttons:")
        }

        // primary enabled
        item {
            McsPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                text = "Primary Button",
                onClick = {},
            )
        }

        // primary disabled
        item {
            McsPrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                text = "Primary Disabled",
                enabled = false,
                onClick = {},
            )
        }

        // secondary enabled
        item {
            McsSecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                text = "Secondary Button",
                onClick = {},
            )
        }

        // secondary disabled
        item {
            McsSecondaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                text = "Secondary Disabled",
                enabled = false,
                onClick = {},
            )
        }

        // alert enabled
        item {
            McsAlertButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                text = "Alert Button",
                onClick = {},
            )
        }

        // alert disabled
        item {
            McsAlertButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                text = "Alert Disabled",
                enabled = false,
                onClick = {},
            )
        }
    }

    private fun LazyListScope.cardSection() {
        item {
            SectionHeader(text = "Cards:")
        }

        item {
            McsCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                content = {
                    Column(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        BodyText(text = "This is a card.")
                        Spacer(modifier = Modifier.height(4.dp))
                        CaptionText(text = "You can't click me!")
                    }
                },
            )
        }

        item {
            McsCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                onClick = {},
                content = {
                    Column(
                        modifier = Modifier.padding(16.dp),
                    ) {
                        BodyText(text = "This is a clickable card.")
                        Spacer(modifier = Modifier.height(4.dp))
                        CaptionText(text = "You can click all up on me ;)")
                    }
                },
            )
        }
    }

    private fun LazyListScope.loaderSection() {
        item {
            SectionHeader(text = "Circular Loader:")
        }

        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                McsCircularLoader(
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}
