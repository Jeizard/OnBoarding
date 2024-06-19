package com.jeizard.onboarding.ui.screens

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeizard.onboarding.ui.theme.Yellow

@Composable
@Preview
fun OnBoardingScreen() {
}

@Composable
@Preview
fun CircularProgressButton() {
    val size = 4
    val selectedIndex = 1
    val backgroundColor = Yellow

    Box(
        modifier = Modifier
            .size(58.dp)
    ) {
        CircularProgressIndicator(
            progress = ((selectedIndex + 1).toFloat() / size.toFloat()),
            modifier = Modifier
                .size(58.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.primary.copy(0.4f),
            strokeWidth = 3.dp
        )

        FloatingActionButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.Center),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = backgroundColor,
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight,
                contentDescription = "ArrowRightIcon"
            )
        }
    }
}

@Composable
@Preview
fun Indicators() {
    val size = 4
    val selectedIndex = 1

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(size){
            Indicator(isSelected = it == selectedIndex)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean) {
    val width = animateDpAsState(
        targetValue = if (isSelected) 24.dp else 8.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "IndicatorWidthAnimation"
    )

    Box(
        modifier = Modifier
            .height(8.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
            )
    )
}