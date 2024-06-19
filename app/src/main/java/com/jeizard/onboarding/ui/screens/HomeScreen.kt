package com.jeizard.onboarding.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jeizard.onboarding.R
import androidx.compose.material3.Text
import com.jeizard.onboarding.ui.theme.Blue
import com.jeizard.onboarding.ui.theme.OnBoardingTheme

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.home_message),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    OnBoardingTheme {
        HomeScreen()
    }
}