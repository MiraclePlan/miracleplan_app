package com.example.miracleplan.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.customFont
import com.example.miracleplan.data.Member
import com.example.miracleplan.data.members
import com.example.miracleplan.ui.theme.MiracleplanTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun GroupSignInPage(navController: NavHostController = rememberNavController()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GoBackSign {
            navController.navigate("group") // 페이지 이동
        }
        TopText("그룹 이름을 알려주세요!", "다른 사용자들과 함께 미라클모닝을 실천해요.")
        GroupNameBox()
        Spacer(modifier = Modifier.weight(1f))
        DefaultButton(onClick = {navController.navigate("generatedGroup")}, "그룹 생성하기")
    }
}

@Composable
fun GoBackSign(onClick: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.leftaroow),
            contentDescription = "돌아가기",
            modifier = Modifier.clickable(onClick = onClick),
        )
    }
}

@Composable
fun TopText(text1 : String, text2 : String) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(16.dp)
    ) {
        Text(
            text = text1,
            fontFamily = customFont,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.552).sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = text2,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = customFont,
            letterSpacing = 0.091.sp
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupNameBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "그룹 이름",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 24.sp,
            letterSpacing = 0.091.sp,
            fontFamily = customFont,
            color = colorResource(id = R.color.gray),
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextField()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField() {
    var text by remember { mutableStateOf("") }
    androidx.compose.material3.TextField(
        value = text,
        onValueChange = { newText -> text = newText },
        placeholder = {
            Text(
                text = "입력해 주세요",
                color = colorResource(id = R.color.gray),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.091.sp,
                fontFamily = customFont
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.oulinecolor),
                shape = RoundedCornerShape(12.dp)
            ),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.091.sp,
            color = colorResource(id = R.color.gray)
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,  // 배경 투명
            focusedIndicatorColor = Color.Transparent,  // 포커스 시 하단의 기본 색 제거
            unfocusedIndicatorColor = Color.Transparent  // 비포커스 시 하단의 기본 색 제거
        )
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PwTextField() {
    val password = remember { mutableStateOf("") }
    val isPasswordVisible = remember { mutableStateOf(false) }

    androidx.compose.material3.TextField(
        value = password.value,
        onValueChange = { password.value = it },
        placeholder = {
            Text(
                text = "입력해 주세요",
                color = colorResource(id = R.color.gray),
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.091.sp,
                fontFamily = customFont
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.oulinecolor),
                shape = RoundedCornerShape(12.dp)
            ),
        visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        textStyle = androidx.compose.ui.text.TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.091.sp,
            color = colorResource(id = R.color.gray),


        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,  // 배경 투명
            focusedIndicatorColor = Color.Transparent,  // 포커스 시 하단의 기본 색 제거
            unfocusedIndicatorColor = Color.Transparent  // 비포커스 시 하단의 기본 색 제거
        )
    )
}

@Composable
fun DefaultButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(361.dp)
            .height(56.dp)
            .clickable(onClick = onClick),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.black), // 배경색 설정
            contentColor = colorResource(id = R.color.white)   // 텍스트 색상 설정
        ),
        shape = RoundedCornerShape(12.dp), // 버튼의 모서리를 둥글게 설정 (선택 사항)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold
        )
    }
}


//@RequiresApi(Build.VERSION_CODES.P)
//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    MiracleplanTheme {
//        MainPage()
//    }
//}