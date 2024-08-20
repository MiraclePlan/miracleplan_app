package com.example.miracleplan.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.miracleplan.ui.theme.MiracleplanTheme
import java.util.Calendar

@Composable
fun SelectDatePage(navController: NavController = rememberNavController()) {
    val calendar = remember { Calendar.getInstance() }
    val currentMonth = remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    val currentYear = remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var firstSelectedDate by remember { mutableStateOf<Int?>(null) }
    var secondSelectedDate by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GoBackSign {
            navController.navigate("login")
        }
        TopText(
            text1 = "계획을 언제까지 도전하시나요?",
            text2 = "주영님에게 맞는 도전 캘린더를 제작해 드려요."
        )
        MonthSign(
            currentMonth = currentMonth.value,
            currentYear = currentYear.value,
            onMonthChange = { newMonth, newYear ->
                currentMonth.value = newMonth
                currentYear.value = newYear
            }
        )
        SCalendarTable(
            month = currentMonth.value,
            year = currentYear.value,
            onDateSelected = { first, second ->
                firstSelectedDate = first
                secondSelectedDate = second
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        CantClickButton(
            onClick = {
                if (firstSelectedDate != null && secondSelectedDate != null) {
                    navController.navigate("todo")
                }
            },
            isEnabled = firstSelectedDate != null && secondSelectedDate != null
        )
        Spacer(modifier = Modifier.height(12.dp))

    }
}

@Composable
fun SWeekNumBox(
    dateNum: Int,
    isSelected: Boolean,
    isBetweenSelected: Boolean,
    isRowStart: Boolean,
    isRowEnd: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(51.6.dp)
            .height(44.dp)
            .background(
                color = if (isSelected) colorResource(id = R.color.black) else Color.Transparent,
                shape = if (isSelected) RoundedCornerShape(8.dp) else RoundedCornerShape(0.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = when {
                        isSelected -> Color.Transparent
                        isBetweenSelected -> colorResource(id = R.color.outlinecolor)
                        else -> Color.Transparent
                    },
                    shape = when {
                        isSelected -> RoundedCornerShape(8.dp)
                        isBetweenSelected && isRowStart -> RoundedCornerShape(
                            topStart = 8.dp,
                            bottomStart = 8.dp
                        )

                        isBetweenSelected && isRowEnd -> RoundedCornerShape(
                            topEnd = 8.dp,
                            bottomEnd = 8.dp
                        )

                        else -> RoundedCornerShape(0.dp)
                    }
                )
        ) {
            Text(
                text = if (dateNum > 0) dateNum.toString() else "",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.091.sp,
                color = when {
                    isSelected -> colorResource(id = R.color.white)
                    isBetweenSelected -> colorResource(id = R.color.gray)
                    else -> colorResource(id = R.color.gray)
                },
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun SCalendarTable(
    month: Int,
    year: Int,
    onDateSelected: (Int?, Int?) -> Unit
) {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.YEAR, year)
    calendar.set(Calendar.MONTH, month)
    calendar.set(Calendar.DAY_OF_MONTH, 1)

    val startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val rows = ((startDayOfWeek - 1) + daysInMonth + 6) / 7

    var firstSelectedDate by remember { mutableStateOf<Int?>(null) }
    var secondSelectedDate by remember { mutableStateOf<Int?>(null) }

    Column {
        RecordDateRow()

        for (row in 0 until rows) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (col in 0 until 7) {
                    val day = row * 7 + col + 1 - (startDayOfWeek - 1)

                    if (day in 1..daysInMonth) {
                        val startDate = minOf(firstSelectedDate ?: 0, secondSelectedDate ?: 0)
                        val endDate = maxOf(firstSelectedDate ?: 0, secondSelectedDate ?: 0)
                        val isBetweenSelected = day in (startDate + 1) until endDate
                        val isFirstOrSecondSelected = day == firstSelectedDate || day == secondSelectedDate
                        val isRowStart = col == 0
                        val isRowEnd = col == 6

                        SWeekNumBox(
                            dateNum = day,
                            isSelected = isFirstOrSecondSelected,
                            isBetweenSelected = isBetweenSelected,
                            isRowStart = isRowStart,
                            isRowEnd = isRowEnd,
                            onClick = {
                                if (firstSelectedDate == null) {
                                    firstSelectedDate = day
                                } else if (secondSelectedDate == null) {
                                    secondSelectedDate = day
                                } else {
                                    firstSelectedDate = day
                                    secondSelectedDate = null
                                }
                                onDateSelected(firstSelectedDate, secondSelectedDate)
                            }
                        )
                    } else {
                        SWeekNumBox(
                            dateNum = 0,
                            isSelected = false,
                            isBetweenSelected = false,
                            isRowStart = false,
                            isRowEnd = false,
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
}




//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//fun Preview() {
//    MiracleplanTheme {
//        SelectDatePage()
//    }
//}