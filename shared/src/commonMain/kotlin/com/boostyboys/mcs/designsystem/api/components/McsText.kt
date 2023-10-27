package com.boostyboys.mcs.designsystem.api.components

import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.boostyboys.mcs.designsystem.api.theme.LocalTypography

@Composable
fun H1Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTypography.current.h1,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style.merge(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
        ),
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun H2Text(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTypography.current.h2,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style.merge(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
        ),
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTypography.current.title,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style.merge(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
        ),
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun SubtitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTypography.current.subtitle,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style.merge(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
        ),
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTypography.current.body,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style.merge(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
        ),
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun CaptionText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTypography.current.caption,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style.merge(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
        ),
        overflow = overflow,
        maxLines = maxLines,
    )
}

@Composable
fun ButtonText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = LocalContentColor.current,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = LocalTypography.current.button,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style.merge(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
        ),
        overflow = overflow,
        maxLines = maxLines,
    )
}
