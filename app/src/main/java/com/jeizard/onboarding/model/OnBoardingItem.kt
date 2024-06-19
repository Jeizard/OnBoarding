package com.jeizard.onboarding.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.jeizard.onboarding.R

sealed class OnBoardingItem(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val body: Int
) {
    data object First : OnBoardingItem(
        image = R.drawable.onboarding_image_1,
        title = R.string.onboarding_title_1,
        body = R.string.onboarding_body_1
    )
}