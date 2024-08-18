package com.example.miracleplan.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.miracleplan.ui.theme.MiracleplanTheme

@Composable
fun RecordPage(navController: NavHostController = rememberNavController()) {
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
            item { RecordSign() }
            item { MonthSign() }
            item { Calendar() }
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
fun RecordSign() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .background(color = colorResource(id = R.color.white))
            .height(28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "기록",
            fontFamily = customFont,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = (-0.24).sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(id = R.drawable.bell),
            contentDescription = "알림",
            tint = colorResource(id = R.color.gray),
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun MonthSign() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp)
            .background(color = colorResource(id = R.color.white))
            .height(28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.leftaroow),
            contentDescription = "이전 달로 이동",
        )
        Text(
            text = "8월",
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.24).sp,
            modifier = Modifier.padding(horizontal = 48.dp)
        )
        Icon(
            painter = painterResource(id = R.drawable.rightarrow),
            contentDescription = "다음 달로 이동",
        )
    }
}

@Composable
fun Calendar() {
    val daysInMonth = 31 // 이 달의 총 날짜 수
    val startDay = 1
    val endDay = daysInMonth
    val rows = (endDay - startDay + 6) / 7 // 7일 단위로 나누어 필요한 행 수 계산
    val currentDate = java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH) // 현재 날짜

    Column {
        RecordDateRow()

        for (row in 0 until rows) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (col in 0 until 7) {
                    val day = row * 7 + col + 1
                    WeekNumBox(dateNum = if (day in startDay..endDay) day else 0, currentDate = currentDate, status = if (day % 2 == 0) "성공" else "실패")
                }
            }
        }
    }
}

@Composable
fun RecordDateRow() {
    val dateLabels = listOf("월", "화", "수", "목", "금", "토", "일")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(vertical = 4.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        dateLabels.forEach { day ->
            WeekBox(day = day)
        }
    }
}

@Composable
fun WeekBox(day: String) {
    Box(
        modifier = Modifier
            .width(44.dp)
            .height(51.6.dp)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Gray,
            letterSpacing = 0.091.sp
        )
    }
}

@Composable
fun WeekNumBox(dateNum: Int, currentDate: Int, status: String) {
    val statusColor = when {
        dateNum == 0 -> Color.Transparent
        dateNum > currentDate -> colorResource(id = R.color.gray)
        status == "성공" -> colorResource(id = R.color.green)
        else -> colorResource(id = R.color.red)
    }
    val statusText = when {
        dateNum == 0 -> ""
        dateNum > currentDate -> "예정"
        else -> status
    }

    Box(
        modifier = Modifier
            .width(51.6.dp)
            .height(62.dp)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (dateNum > 0) dateNum.toString() else "",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.091.sp,
                color = colorResource(id = R.color.gray)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = statusText,
                fontFamily = customFont,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Bold,
                color = statusColor
            )
        }
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