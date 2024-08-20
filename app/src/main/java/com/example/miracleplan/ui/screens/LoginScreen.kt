package com.example.miracleplan.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.customFont
import com.example.miracleplan.ui.theme.MiracleplanTheme
import com.example.miracleplan.ui.viewmodel.AuthViewModel
import java.time.format.TextStyle


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LoginPage(navController: NavHostController = rememberNavController()) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MiracleplanSign()
        IdPwInputBox()
        Spacer(modifier = Modifier.weight(1f))
        LoginOrSignInBox(navController)
    }
}

@Composable
fun MiracleplanSign() {
    Spacer(modifier = Modifier.height(60.dp))
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
fun IdPwInputBox() {
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
fun LoginOrSignInBox(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(116.dp)
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginButton(onClick = {
            navController.navigate("todo")
        })
        Spacer(modifier = Modifier.height(12.dp))
        SignInText(navController)
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.black),
            contentColor = colorResource(id = R.color.white)
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(
            text = "로그인",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = customFont,
            letterSpacing = 0.091.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SignInText(navController: NavHostController) {
    Text(
        text = "계정이 없다면? 회원가입",
        fontFamily = customFont,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.091.sp,
        fontWeight = FontWeight.Bold,
        textDecoration = TextDecoration.Underline,
        color = colorResource(id = R.color.gray),
        modifier = Modifier.clickable {
            navController.navigate("register")
        }
    )
}


//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//fun Preview() {
//    MiracleplanTheme {
//        LoginPage()
//    }
//}