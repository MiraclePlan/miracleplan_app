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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
    ) {
        TodaySign()
        Spacer(modifier = Modifier.weight(1f))
        CustomBottomNavigationBar()
    }
}
@Composable
fun TodaySign(modifier: Modifier = Modifier) {
    val month: Int = 8
    val daysLeft: Int = 8
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
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
                    letterSpacing = -0.24.sp
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "알림",
                tint = Color.Gray,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}
@Composable
fun CustomBottomNavigationBar(modifier: Modifier = Modifier) {
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
            tint = Color.Gray
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            fontFamily = customFont,
            text = label,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            color = Color.Gray,
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