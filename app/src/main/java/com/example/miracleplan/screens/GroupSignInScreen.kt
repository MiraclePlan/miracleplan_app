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
        GroupText()
        GroupNameBox()
        Spacer(modifier = Modifier.weight(1f))
        GroupGenerateButton(onClick = {})
    }
}

@Composable
fun GoBackSign(onClick: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.leftaroow),
            contentDescription = "돌아가기"
        )
    }
}

@Composable
fun GroupText() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(16.dp)
    ) {
        Text(
            text = "그룹 이름을 알려주세요!",
            fontFamily = customFont,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.552).sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "다른 사용자들과 함께 미라클모닝을 실천해요.",
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

@Composable
fun GroupGenerateButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .width(361.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.black), // 배경색 설정
            contentColor = colorResource(id = R.color.white)   // 텍스트 색상 설정
        ),
        shape = RoundedCornerShape(12.dp), // 버튼의 모서리를 둥글게 설정 (선택 사항)
    ) {
        Text(
            text = "그룹 생성하기",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun JoinedGroupPage(navController: NavHostController = rememberNavController()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
        ) {
            item { GroupSignWithSetting() }
            item { GroupMemberRow() }
            item { BetweenLayer() }
            item { GroupRank(navController, showIcon = false) }
        }
        CustomBottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            navController = navController
        )
    }
}

@Composable
fun GroupSignWithSetting() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .background(color = colorResource(id = R.color.white))
            .height(28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "그룹",
            fontFamily = customFont,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = (-0.24).sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(id = R.drawable.setting),
            contentDescription = "설정",
            tint = colorResource(id = R.color.gray)
        )
    }
}

@Composable
fun GroupMemberRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(16.dp)
    ) {
        items(members) { member ->
            GroupMemberBox(member)
        }
    }
}


@Composable
fun GroupMemberBox(member: Member) {
    Row(
        modifier = Modifier
            .width(96.dp)
            .height(128.dp)
    ) {
        Column(
            modifier = Modifier
                .width(72.dp)
                .height(128.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = member.profileImageResId),
                contentDescription = "${member.name} 프로필 사진"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = member.name,
                fontFamily = customFont,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.091.sp
            )
            Text(
                text = member.status,
                color = when (member.status) {
                    "성공" -> colorResource(id = R.color.green)
                    "실패" -> colorResource(id = R.color.red)
                    "도전 중" -> colorResource(id = R.color.gray)
                    else -> colorResource(id = R.color.gray)
                },
                fontFamily = customFont,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.091.sp
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
    }
}



@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun Preview() {
    MiracleplanTheme {
        JoinedGroupPage()
    }
}