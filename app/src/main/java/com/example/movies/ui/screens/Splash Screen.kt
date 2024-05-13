package com.example.movies.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.movies.R
import com.example.movies.domain.navigation.BottomScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(3000)
        navController.navigate(BottomScreens.Home.route)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(top = 140.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_anim_for_splash_1))
            val progress by animateLottieCompositionAsState(
                composition,
                iterations = LottieConstants.IterateForever,
                isPlaying = true
            )
            LottieAnimation(composition = composition, progress = { progress })
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(bottom = 150.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_anim_for_splash_2))
            val progress by animateLottieCompositionAsState(
                composition,
                iterations = LottieConstants.IterateForever,
                isPlaying = true
            )
            LottieAnimation(composition = composition, progress = { progress })
        }
    }
}