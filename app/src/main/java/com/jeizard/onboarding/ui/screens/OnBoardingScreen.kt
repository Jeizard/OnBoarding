package com.jeizard.onboarding.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.jeizard.onboarding.model.OnBoardingItem
import com.jeizard.onboarding.navigation.NavigationItem
import com.jeizard.onboarding.ui.theme.OnBoardingTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()

    val items = listOf(
        OnBoardingItem.First,
        OnBoardingItem.Second,
        OnBoardingItem.Third,
        OnBoardingItem.Fourth
    )
    val state = rememberPagerState(pageCount = { items.size })

    val animatedColor = animateColorAsState(
        targetValue = items[state.currentPage].backgroundColor,
        animationSpec = tween(durationMillis = 300, delayMillis = 0),
        label = "ColorAnimation")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedColor.value)
    ) {
        HorizontalPager(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.85f)
        ) {
            page -> OnBoardingItem(item = items[page])
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(0.15f)
        ){
            BottomBar(
                size = items.size,
                selectedIndex = state.currentPage,
                backgroundColor = items[state.currentPage].backgroundColor,
                onNextClicked = {
                    if(state.currentPage < (items.size - 1)){
                        scope.launch {
                            state.animateScrollToPage(
                                page = state.currentPage + 1,
                                animationSpec = tween(durationMillis = 300, delayMillis = 0))
                        }
                    } else{
                        navController.popBackStack()
                        navController.navigate(NavigationItem.Home.route)
                    }
                },
                onSkipClicked = {
                    navController.popBackStack()
                    navController.navigate(NavigationItem.Home.route)
                }
            )
        }
    }
}

@Composable
fun OnBoardingItem(item: OnBoardingItem) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp, 24.dp, 24.dp, 38.dp,)
        ) {
            Text(
                text = stringResource(id = item.title),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 12.dp)
            )
            Text(
                text = stringResource(id = item.body),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary.copy(0.9f),
                fontSize = 18.sp
            )
        }
        Image(
            painter = painterResource(id = item.image),
            contentDescription = "OnBoardingImage",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun BottomBar(
    size: Int,
    selectedIndex: Int,
    backgroundColor: Color,
    onNextClicked: () -> Unit,
    onSkipClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Indicators(size = size, selectedIndex = selectedIndex)

        Text(
            text = "Skip",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary.copy(0.7f),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .clickable {
                    onSkipClicked()
                }
        )

        CircularProgressButton(
            size = size,
            selectedIndex = selectedIndex,
            backgroundColor = backgroundColor,
            onNextClicked = onNextClicked
        )
    }
}

@Composable
fun BoxScope.CircularProgressButton(
    size: Int,
    selectedIndex: Int,
    backgroundColor: Color,
    onNextClicked: () -> Unit
) {
    val animatedProgress = animateFloatAsState(
        targetValue = ((selectedIndex + 1).toFloat() / size.toFloat()),
        animationSpec = tween(durationMillis = 300, delayMillis = 0),
        label = "ProgressAnimation")
    val animatedColor = animateColorAsState(
        targetValue = backgroundColor,
        animationSpec = tween(durationMillis = 300, delayMillis = 0),
        label = "ColorAnimation")

    Box(
        modifier = Modifier
            .size(58.dp)
            .align(Alignment.CenterEnd)
    ) {
        CircularProgressIndicator(
            progress = animatedProgress.value,
            modifier = Modifier
                .size(58.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.primary.copy(0.4f),
            strokeWidth = 3.dp
        )

        FloatingActionButton(
            onClick = { onNextClicked() },
            modifier = Modifier
                .size(42.dp)
                .align(Alignment.Center),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = animatedColor.value,
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = "ArrowRightIcon"
            )
        }
    }
}

@Composable
fun BoxScope.Indicators(size: Int, selectedIndex: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.align(Alignment.TopStart)
    ) {
        repeat(size){
            Indicator(isSelected = it == selectedIndex)
        }
    }
}
@Composable
fun Indicator(isSelected: Boolean) {
    val animatedWidth = animateDpAsState(
        targetValue = if (isSelected) 24.dp else 8.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "IndicatorWidthAnimation"
    )

    Box(
        modifier = Modifier
            .height(8.dp)
            .width(animatedWidth.value)
            .clip(CircleShape)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
            )
    )
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    OnBoardingTheme {
        Box(modifier = Modifier.background(OnBoardingItem.First.backgroundColor)) {
            OnBoardingItem(item = OnBoardingItem.First)
        }
    }
}
@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview() {
    OnBoardingTheme {
        Box(modifier = Modifier.background(OnBoardingItem.Second.backgroundColor)) {
            OnBoardingItem(item = OnBoardingItem.Second)
        }
    }
}
@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview() {
    OnBoardingTheme {
        Box(modifier = Modifier.background(OnBoardingItem.Third.backgroundColor)) {
            OnBoardingItem(item = OnBoardingItem.Third)
        }
    }
}
@Composable
@Preview(showBackground = true)
fun FourthOnBoardingScreenPreview() {
    OnBoardingTheme {
        Box(modifier = Modifier.background(OnBoardingItem.Fourth.backgroundColor)) {
            OnBoardingItem(item = OnBoardingItem.Fourth)
        }
    }
}