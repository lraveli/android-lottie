package com.example.lottieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val lottiecomposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.sample))
            var isPlaying by remember { mutableStateOf(true) }

            val lottieprogress by animateLottieCompositionAsState(
                composition = lottiecomposition,
                isPlaying = isPlaying,
                iterations = 5
            )

            LaunchedEffect(key1 = lottiecomposition) {
                if (lottieprogress == 0f) {
                    isPlaying = true
                }
                if (lottieprogress == 1f) {
                    isPlaying = false
                }
            }
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.align(Alignment.Center)) {
                    LottieAnimation(
                        composition = lottiecomposition,
                        modifier = Modifier
                            .size(300.dp),
                        progress = {
                            if (lottieprogress ==1f){ isPlaying = true }
                            lottieprogress
                        }
                    )
                    Button(onClick = { isPlaying = true}) {
                        Text(text = "Restart")
                    }
                }
            }
        }
    }
}
