package com.example.miracleplan.screens

import GroupMemberRow
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.customFont

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun GroupDetailsPage(navController: NavHostController = rememberNavController()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DetailRow{ navController.navigate("group") }
            GroupMemberRow()
            BetweenLayer()
            GroupRank(navController, showIcon = false)
            Spacer(modifier = Modifier.weight(1f))
            GroupParticipateButton(onClick = {navController.navigate("generatedGroup")})
        }
    }
}

@Composable
fun DetailRow(onClick: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.leftaroow),
            contentDescription = "그룹으로 돌아가기",
            modifier = Modifier.clickable(onClick = onClick),
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "상세 보기",
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.24).sp,
            fontFamily = customFont
        )
    }
}

@Composable
fun GroupParticipateButton(onClick: () -> Unit) {
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
            text = "참여하기",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold
        )
    }
}
