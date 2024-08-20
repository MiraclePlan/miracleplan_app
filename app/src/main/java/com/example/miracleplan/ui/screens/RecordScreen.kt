package com.example.miracleplan.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.customFont
import java.util.Calendar

@Composable
fun RecordPage(navController: NavHostController = rememberNavController()) {
    val calendar = remember { Calendar.getInstance() }
    val currentMonth = remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    val currentYear = remember { mutableStateOf(calendar.get(Calendar.YEAR)) }

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
            item {
                MonthSign(
                    currentMonth = currentMonth.value,
                    currentYear = currentYear.value,
                    onMonthChange = { newMonth, newYear ->
                        currentMonth.value = newMonth
                        currentYear.value = newYear
                    }
                )
            }
            item { CalendarTable(month = currentMonth.value, year = currentYear.value, navController = navController) }
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
fun MonthSign(currentMonth: Int, currentYear: Int, onMonthChange: (Int, Int) -> Unit) {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, currentMonth)
    calendar.set(Calendar.YEAR, currentYear)

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
            modifier = Modifier.clickable {
                calendar.add(Calendar.MONTH, -1)
                onMonthChange(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))
            }
        )
        Text(
            text = "${currentMonth + 1}월",
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
            modifier = Modifier.clickable {
                calendar.add(Calendar.MONTH, 1)
                onMonthChange(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR))
            }
        )
    }
}

@Composable
fun CalendarTable(month: Int, year: Int, navController: NavHostController) {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.DAY_OF_MONTH, 1)

    val startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) // 해당 달의 첫 날 요일
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) // 해당 달의 총 날짜 수
    val rows = ((startDayOfWeek - 1) + daysInMonth + 6) / 7 // 7일 단위로 나누어 필요한 행 수 계산
    val currentDate = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) // 현재 날짜

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
                    val day = row * 7 + col + 1 - (startDayOfWeek - 1)
                    WeekNumBox(
                        dateNum = if (day in 1..daysInMonth) day else 0,
                        currentDate = currentDate,
                        status = if (day % 2 == 0) "성공" else "실패",
                        onClick = {
                            if (day > 0) {
                                // Navigate to DetailRecord page
                                navController.navigate("DetailRecord")
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun RecordDateRow() {
    val dateLabels = listOf("일", "월", "화", "수", "목", "금", "토")

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
fun WeekNumBox(dateNum: Int, currentDate: Int, status: String, onClick: () -> Unit) {
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
            .padding(10.dp)
            .clickable(onClick = onClick),
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