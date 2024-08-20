package com.example.miracleplan.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.customFont
import com.example.miracleplan.ui.theme.MiracleplanTheme

@Composable
fun DetailRecordPage(navController: NavHostController = rememberNavController()) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white))
                .padding(bottom = 60.dp)
        ) {
            DetailRow({ navController.navigate("record") }, "기록")
            RecordColumn()
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
fun RecordColumn() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "2024.08.30",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 28.sp,
                fontFamily = customFont,
                letterSpacing = (-0.24).sp,
                color = colorResource(id = R.color.black)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "성공",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 22.sp,
                fontFamily = customFont,
                letterSpacing = 0.091.sp,
                color = colorResource(id = R.color.green)
            )
        }
        AchievementItem(achievement = "이정우")
        AchievementItem(achievement = "이정우")
        AchievementItem(achievement = "이정우")
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Preview() {
    MiracleplanTheme {
        DetailRecordPage()
    }
}