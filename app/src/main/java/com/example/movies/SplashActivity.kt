package com.example.movies

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.movies.ui.theme.MoviesTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                SplashScreen()
            }
        }
    }

    @Composable
    fun SplashScreen() {
        LaunchedEffect(key1 = true) {
            delay(3000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(modifier = Modifier.fillMaxWidth()) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_anim_for_splash_1))
                val progress by animateLottieCompositionAsState(
                    composition,
                    iterations = 1,
                    isPlaying = true
                )
                LottieAnimation(composition = composition, progress = { progress })
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_anim_for_splash_2))
                val progress by animateLottieCompositionAsState(
                    composition,
                    iterations = 1,
                    isPlaying = true
                )
                LottieAnimation(composition = composition, progress = { progress })
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_anim_for_splash_3))
                val progress by animateLottieCompositionAsState(
                    composition,
                    iterations = 1,
                    isPlaying = true
                )
                LottieAnimation(composition = composition, progress = { progress })
            }
        }
    }
}