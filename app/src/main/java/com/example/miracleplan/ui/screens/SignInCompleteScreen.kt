package com.example.miracleplan.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.miracleplan.R
import com.example.miracleplan.customFont
import com.example.miracleplan.ui.theme.MiracleplanTheme

@Composable
fun SignInCompletePage(navController: NavController = rememberNavController()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ) {
            val composition by rememberLottieComposition(
                LottieCompositionSpec.RawRes(R.raw.animation),
            )
            val lottieAnimatable = rememberLottieAnimatable()
            LaunchedEffect(composition) {
                lottieAnimatable.animate(
                    composition = composition,
                    iterations = LottieConstants.IterateForever
                )
            }
            LottieAnimation(
                composition = composition,
                progress = { lottieAnimatable.progress },
            )
            Spacer(modifier = Modifier.height(5.dp))
            Column(
                modifier = Modifier
                    .height(84.dp)
                    .width(307.dp)
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "가입이 완료되었어요!",
                    fontSize = 24.sp,
                    fontFamily = customFont,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 32.sp,
                    letterSpacing = (-0.552).sp
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "미라클 플랜과 함께 미라클 모닝을 함께해요.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 24.sp,
                    letterSpacing = 0.091.sp,
                    fontFamily = customFont
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        DefaultButton(onClick = { navController.navigate("home")}, text = "완료")
        Spacer(modifier = Modifier.height(12.dp))
    }
}








@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Preview() {
    MiracleplanTheme {
        SignInCompletePage()
    }
}