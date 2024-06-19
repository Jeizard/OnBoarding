package com.jeizard.onboarding.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.jeizard.onboarding.R
import com.jeizard.onboarding.ui.theme.Blue
import com.jeizard.onboarding.ui.theme.Peach
import com.jeizard.onboarding.ui.theme.Purple
import com.jeizard.onboarding.ui.theme.Yellow

sealed class OnBoardingItem(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val body: Int,
    val backgroundColor: Color,
) {
    data object First : OnBoardingItem(
        image = R.drawable.onboarding_image_1,
        title = R.string.onboarding_title_1,
        body = R.string.onboarding_body_1,
        backgroundColor = Yellow
    )
    data object Second : OnBoardingItem(
        image = R.drawable.onboarding_image_2,
        title = R.string.onboarding_title_2,
        body = R.string.onboarding_body_2,
        backgroundColor = Purple
    )
    data object Third : OnBoardingItem(
        image = R.drawable.onboarding_image_3,
        title = R.string.onboarding_title_3,
        body = R.string.onboarding_body_3,
        backgroundColor = Peach
    )
    data object Fourth : OnBoardingItem(
        image = R.drawable.onboarding_image_4,
        title = R.string.onboarding_title_4,
        body = R.string.onboarding_body_4,
        backgroundColor = Blue
    )
}