package com.example.miracleplan.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.customFont
import com.example.miracleplan.ui.theme.MiracleplanTheme
import java.util.Calendar

@Composable
fun SelectDatePage(navController: NavController = rememberNavController()) {
    val calendar = remember { Calendar.getInstance() }
    val currentMonth = remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    val currentYear = remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        GoBackSign {
            navController.navigate("login")
        }
        MonthSign(
            currentMonth = currentMonth.value,
            currentYear = currentYear.value,
            onMonthChange = { newMonth, newYear ->
                currentMonth.value = newMonth
                currentYear.value = newYear
            }
        )
        SCalendarTable(month = currentMonth.value, year = currentYear.value)
    }

}

@Composable
fun SWeekNumBox(dateNum: Int) {
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
        }
    }
}

@Composable
fun SCalendarTable(month: Int, year: Int) {
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
                    SWeekNumBox(
                        dateNum = if (day in 1..daysInMonth) day else 0
                    )
                }
            }
        }
    }
}








@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Preview() {
    MiracleplanTheme {
        SelectDatePage()
    }
}