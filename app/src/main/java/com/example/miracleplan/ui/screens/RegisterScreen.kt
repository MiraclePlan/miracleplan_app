package com.example.miracleplan.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.customFont

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RegisterPage(navController: NavController = rememberNavController()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RegisterTitle()
        InputBox()
        Spacer(modifier = Modifier.weight(1f))
        RegisterBox(navController)
    }
}

@Composable
fun RegisterTitle() {
    Text(
        text = "미라클플랜에\n오신 걸 환영해요!",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 32.sp,
        letterSpacing = (-0.552).sp,
        fontFamily = customFont,
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp)
            .padding(vertical = 24.dp, horizontal = 16.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(218.dp)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "아이디",
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.091.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray)
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextField()
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "비밀번호",
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.091.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.gray)
        )
        Spacer(modifier = Modifier.height(4.dp))
        PwTextField()
    }
}

@Composable
fun RegisterBox(navController: NavController) {
    RegisterButton(onClick = {
        navController.navigate("login")
    })
}

@Composable
fun RegisterButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(361.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.black),
            contentColor = colorResource(id = R.color.white)
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(
            text = "회원가입",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = customFont,
            letterSpacing = 0.091.sp,
            fontWeight = FontWeight.Bold
        )
    }
}