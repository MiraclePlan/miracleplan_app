package com.example.miracleplan.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.customFont
import com.example.miracleplan.ui.theme.MiracleplanTheme

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun GroupPage(navController: NavHostController = rememberNavController()) {
    val groupInfos = listOf(
        GroupInfo(groupName = "이주영팸", wakeUpTime = 730, peopleCount = 4),
        GroupInfo(groupName = "선린일짱", wakeUpTime = 800, peopleCount = 3),
        GroupInfo(groupName = "2학년 5반", wakeUpTime = 920, peopleCount = 15),
        GroupInfo(groupName = "이주영팸", wakeUpTime = 700, peopleCount = 4),
        GroupInfo(groupName = "선린일짱", wakeUpTime = 830, peopleCount = 3),
        GroupInfo(groupName = "2학년 5반", wakeUpTime = 920, peopleCount = 15),
        GroupInfo(groupName = "2학년 5반", wakeUpTime = 920, peopleCount = 15)

    )

    val isTransparent = groupInfos.size >= 6


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
            item { GroupSign() }
            items(groupInfos) { groupInfo ->
                GroupBox(groupInfo = groupInfo, onClick = {
                    navController.navigate("groupDetails")
                })
            }
        }

        GroupPlus(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 84.dp, end = 24.dp),
            isTransparent = isTransparent,
            onClick = {
                navController.navigate("groupSignIn") // 페이지 이동
            }
        )

        CustomBottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            navController = navController
        )
    }
}

@Composable
fun GroupSign() {
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
            painter = painterResource(id = R.drawable.bell),
            contentDescription = "알림",
            tint = colorResource(id = R.color.gray)
        )
    }
}

data class GroupInfo(
    val groupName: String,
    val wakeUpTime: Int,
    val peopleCount: Int
)

@Composable
fun GroupBox(groupInfo: GroupInfo, onClick: () -> Unit) {
    val hours = groupInfo.wakeUpTime / 100
    val minutes = groupInfo.wakeUpTime % 100

    val profileImages = listOf(
        R.drawable.profile1,
        R.drawable.profile2,
        R.drawable.profile3,
        R.drawable.profile4,
        R.drawable.profile5,
        R.drawable.profile6,
        R.drawable.profile7,
        R.drawable.profile8
    )

    val imageSize = 24.dp
    val overlapOffset = 12.dp
    val displayCount = minOf(groupInfo.peopleCount, 5)
    val selectedImages = profileImages.shuffled().take(displayCount)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clickable(onClick = onClick)
    ) {
        GroupHeader(groupInfo.groupName)
        WakeUpTime(hours, minutes)
        MemberRow(groupInfo.peopleCount, selectedImages, imageSize, overlapOffset)
    }
}

@Composable
fun GroupHeader(groupName: String) {
    Text(
        text = groupName,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = customFont,
        letterSpacing = (-0.24).sp,
        modifier = Modifier.padding(vertical = 4.dp)
    )
}

@Composable
fun WakeUpTime(hours: Int, minutes: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "기상",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = customFont,
            letterSpacing = 0.091.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = if (minutes == 0) {
                "${hours}시"
            } else {
                "${hours}시 ${minutes}분"
            },
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = customFont,
            letterSpacing = 0.091.sp
        )
    }
}

@Composable
fun MemberRow(peopleCount: Int, selectedImages: List<Int>, imageSize: Dp, overlapOffset: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "인원",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = customFont,
            letterSpacing = 0.091.sp,
            modifier = Modifier.weight(1f)
        )
        ProfileImages(selectedImages, imageSize, overlapOffset)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "${peopleCount}명",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = customFont,
            letterSpacing = 0.091.sp
        )
    }
}

@Composable
fun ProfileImages(selectedImages: List<Int>, imageSize: Dp, overlapOffset: Dp) {
    Box(
        modifier = Modifier
            .wrapContentWidth(Alignment.Start)
    ) {
        selectedImages.forEachIndexed { index, imageResId ->
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Person $index",
                modifier = Modifier
                    .size(imageSize)
                    .zIndex(index.toFloat())
                    .offset(x = index * -overlapOffset)
            )
        }
    }
}

@Composable
fun GroupPlus(modifier: Modifier = Modifier, isTransparent: Boolean, onClick: () -> Unit) {
    val alpha = if (isTransparent) 0.7f else 1.0f
    Box(
        modifier = modifier
            .width(56.dp)
            .height(56.dp)
            .background(
                color = colorResource(id = R.color.black).copy(alpha = alpha),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "그룹 추가",
            tint = colorResource(id = R.color.white).copy(alpha = alpha)
        )
    }
}




//@RequiresApi(Build.VERSION_CODES.P)
//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    MiracleplanTheme {
//        GeneratedGroupPage()
//    }
//}
