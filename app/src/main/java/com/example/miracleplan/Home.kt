package com.example.miracleplan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.miracleplan.ui.theme.MiracleplanTheme


val customFont = FontFamily(
    Font(R.font.pretendardvariable)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MainPage()
        }
    }
}

@Composable
fun MainPage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        TodaySign()
        ChallengeBox()
        BetweenLayer()
        Spacer(modifier = Modifier.weight(1f))
        CustomBottomNavigationBar()
    }
}

@Composable
fun TodaySign(modifier: Modifier = Modifier) {
    val month = 8
    val daysLeft = 8
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .background(color = colorResource(id = R.color.white))
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .width(361.dp)
                .height(28.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(modifier = Modifier.width(327.dp)) {
                Text(
                    text = "${month}월 (D-$daysLeft)",
                    fontFamily = customFont,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    letterSpacing = (-0.24).sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "알림",
                tint = colorResource(id = R.color.gray),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
fun ChallengeBox() {
    Column(
        modifier = Modifier
            .width(393.dp)
            .height(156.dp)
            .padding(top = 8.dp, bottom = 16.dp)
            .background(color = colorResource(id = R.color.white))
    ) {
        DateRow()
        SuccessfulStatusRow()
        DateNumRow()
    }
}
@Composable
fun DateRow() {
    Box(
        modifier = Modifier
            .width(393.dp)
            .height(40.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Row {
            val dates = listOf(
                "월", "화", "수", "목", "금", "토", "일"
            )
            Row {
                dates.forEach { date ->
                    DateBox(date)
                }
            }
        }
    }
}

@Composable
fun DateBox(date: String) {
    Box(
        modifier = Modifier
            .width(51.6.dp)
            .height(32.dp)
            .padding(vertical = 4.dp, horizontal = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date,
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.091.sp,
            color = colorResource(id = R.color.gray),
            fontWeight = FontWeight.Medium
        )

    }
}

@Composable
fun SuccessfulStatusRow() {
    Box(modifier = Modifier
        .height(44.dp)
        .width(393.dp)
        .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row {
            val successfulstatus = listOf(
                "성공", "성공", "실패", "도전", "예정", "예정", "예정"
            )
            successfulstatus.forEach { data ->
                SuccessfulStatusBox(data)
            }


        }
    }
}

@Composable
fun SuccessfulStatusBox(data: String) {
    val textColor = when (data) {
        "성공" -> Color(0xFF02C03B)
        "실패" -> Color(0xFFFF3333)
        "도전" -> Color(0xFF3377FF)
        "예정" -> colorResource(id = R.color.gray)
        else -> colorResource(id = R.color.gray)
    }
    Box(
        modifier = Modifier
            .height(28.dp)
            .width(51.6.dp)
            .padding(vertical = 4.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = data,
            fontFamily = customFont,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.203.sp,
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DateNumRow() {
    Box(
        modifier = Modifier
            .width(393.dp)
            .height(48.dp)
            .padding(vertical = 4.dp, horizontal = 16.dp)
    ) {
        Row {
            val nums = listOf(
                1, 2, 3, 4, 5, 6, 7
            )
            nums.forEach { num ->
                DateNumBox(num)
            }
        }
    }
}

@Composable
fun DateNumBox(num: Int) {
    Box(
        modifier = Modifier
            .width(51.6.dp)
            .height(40.dp)
            .padding(vertical = 8.dp, horizontal = 10.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = num.toString(),
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.091.sp,
            color = colorResource(id = R.color.gray),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun BetweenLayer() {
    Box(
        modifier = Modifier
            .width(393.dp)
            .height(8.dp)
            .background(color = colorResource(id = R.color.litegray))
    )

}

@Composable
fun CustomBottomNavigationBar() {
    Row(
        modifier = Modifier
            .width(393.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigationItem(
            iconResId = R.drawable.homestrock,
            label = "홈"
        )

        NavigationItem(
            iconResId = R.drawable.group,
            label = "그룹"
        )

        NavigationItem(
            iconResId = R.drawable.record,
            label = "기록"
        )
    }
}

@Composable
fun NavigationItem(iconResId: Int, label: String) {
    Column(
        modifier = Modifier
            .width(131.dp)
            .height(44.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            tint = colorResource(id = R.color.gray)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            fontFamily = customFont,
            text = label,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            color = colorResource(id = R.color.gray),
            fontWeight = FontWeight.Medium
        )
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    MiracleplanTheme {
        MainPage()
    }
}