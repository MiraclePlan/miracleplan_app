package com.example.miracleplan.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.customFont
import com.example.miracleplan.data.Member
import com.example.miracleplan.data.members
import com.example.miracleplan.ui.theme.MiracleplanTheme
import java.time.LocalDate

private val ItemHeight = 52.dp
internal val PaddingHorizontal = 16.dp
internal val PaddingVertical = 12.dp
private val BoxSize = 28.dp
private val DateBoxWidth = 51.6.dp
private val DateBoxHeight = 32.dp

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainPage(navController: NavHostController = rememberNavController()) {
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
            item { TodaySign() }
            item { ChallengeBox() }
            item { BetweenLayer() }
            item { AchievementList() }
            item { BetweenLayer() }
            item { GroupRank(navController, showIcon = true) }
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
fun TodaySign(modifier: Modifier = Modifier) {
    val month = 8
    val daysLeft = 8

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = PaddingVertical, horizontal = PaddingHorizontal)
            .background(color = colorResource(id = R.color.white))
            .height(28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${month}월 (D-$daysLeft)",
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ChallengeBox() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(156.dp)
            .padding(top = 8.dp, bottom = 16.dp)
            .background(color = colorResource(id = R.color.white))
    ) {
        DateRow()
        SuccessfulStatusRow()
        DateNumRow()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateNumRow() {
    // 현재 날짜 가져오기
    val today = remember { LocalDate.now() }
    // 현재 날짜에서 -3일부터 +3일까지의 날짜 리스트 생성
    val dates = (0..6).map { today.minusDays(3).plusDays(it.toLong()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 4.dp, horizontal = PaddingHorizontal)
    ) {
        dates.forEachIndexed { index, date ->
            DateNumBox(date.dayOfMonth, isCenter = index == 3)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateRow() {
    // 현재 날짜 가져오기
    val today = remember { LocalDate.now() }
    // 현재 날짜에서 -3일부터 +3일까지의 요일 리스트 생성
    val weekdays = (0..6).map { today.minusDays(3).plusDays(it.toLong()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(horizontal = PaddingHorizontal, vertical = 4.dp)
    ) {
        weekdays.forEach { date ->
            val koreanWeekday = getKoreanDayOfWeek(date.dayOfWeek)
            DateBox(koreanWeekday)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getKoreanDayOfWeek(dayOfWeek: java.time.DayOfWeek): String {
    return when (dayOfWeek) {
        java.time.DayOfWeek.MONDAY -> "월"
        java.time.DayOfWeek.TUESDAY -> "화"
        java.time.DayOfWeek.WEDNESDAY -> "수"
        java.time.DayOfWeek.THURSDAY -> "목"
        java.time.DayOfWeek.FRIDAY -> "금"
        java.time.DayOfWeek.SATURDAY -> "토"
        java.time.DayOfWeek.SUNDAY -> "일"
    }
}


@Composable
fun DateBox(date: String) {
    Box(
        modifier = Modifier
            .width(DateBoxWidth)
            .height(DateBoxHeight)
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
    Box(
        modifier = Modifier
            .height(44.dp)
            .fillMaxWidth()
            .padding(horizontal = PaddingHorizontal, vertical = 8.dp)
    ) {
        Row {
            val successfulStatus = listOf("성공", "성공", "실패", "도전", "예정", "예정", "예정")
            successfulStatus.forEach { status ->
                SuccessfulStatusBox(status)
            }
        }
    }
}

@Composable
fun SuccessfulStatusBox(status: String) {
    val textColor = when (status) {
        "성공" -> Color(0xFF02C03B)
        "실패" -> Color(0xFFFF3333)
        "도전" -> Color(0xFF3377FF)
        "예정" -> colorResource(id = R.color.gray)
        else -> colorResource(id = R.color.gray)
    }
    Box(
        modifier = Modifier
            .height(28.dp)
            .width(DateBoxWidth)
            .padding(vertical = 4.dp)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = status,
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
fun DateNumBox(num: Int, isCenter: Boolean) {
    Box(
        modifier = Modifier
            .width(DateBoxWidth)
            .height(40.dp)
            .background(
                color = if (isCenter) colorResource(id = R.color.black) else Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(
                vertical = if (isCenter) 0.dp else 8.dp,
                horizontal = if (isCenter) 0.dp else 10.dp
            )
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = num.toString(),
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.091.sp,
            color = if (isCenter) Color.White else colorResource(id = R.color.gray),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun BetweenLayer() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(color = colorResource(id = R.color.litegray))
    )
}

@Composable
fun CustomBottomNavigationBar(modifier: Modifier = Modifier, navController: NavHostController) {
    val currentRoute = currentRoute(navController)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigationItem(
            iconResId = if (currentRoute == "home") R.drawable.clickhome else R.drawable.homestrock,
            label = "홈",
            isSelected = currentRoute == "home",
            onClick = { navController.navigate("home") }
        )
        NavigationItem(
            iconResId = if (currentRoute == "group") R.drawable.clickgroup else R.drawable.group,
            label = "그룹",
            isSelected = currentRoute == "group",
            onClick = { navController.navigate("group") }
        )
        NavigationItem(
            iconResId = if (currentRoute == "record") R.drawable.clickrecord else R.drawable.record,
            label = "기록",
            isSelected = currentRoute == "record",
            onClick = { navController.navigate("record") }
        )
    }
}

@Composable
fun NavigationItem(iconResId: Int, label: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(131.dp)
            .height(44.dp)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = label,
            tint = if (isSelected) colorResource(id = R.color.black) else colorResource(id = R.color.gray)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            fontFamily = customFont,
            text = label,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            color = if (isSelected) colorResource(id = R.color.black) else colorResource(id = R.color.gray),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun AchievementList() {
    val dummyAchievements = listOf(
        "아침 운동하기",
        "독서 30분",
        "코딩 공부 1시간",
    )

    Column(
        modifier = Modifier
            .padding(vertical = PaddingVertical)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(ItemHeight)
                .padding(vertical = PaddingVertical, horizontal = PaddingHorizontal)
        ) {
            Text(
                text = "오늘 달성 계획",
                fontFamily = customFont,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.24).sp
            )
        }
        dummyAchievements.forEach { achievement ->
            AchievementItem(achievement)
        }
    }
}

@Composable
fun AchievementItem(achievement: String) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(ItemHeight)
            .padding(vertical = PaddingVertical, horizontal = PaddingHorizontal)
            .clickable { isChecked = !isChecked },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(
                id = if (isChecked) R.drawable.check else R.drawable.uncheck
            ),
            contentDescription = if (isChecked) "체크됨" else "체크안됨",
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = achievement,
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            color = colorResource(id = R.color.gray),
            fontWeight = FontWeight.Medium,
            fontStyle = FontStyle.Normal,
            letterSpacing = 0.091.sp,
            textDecoration = if (isChecked) TextDecoration.LineThrough else TextDecoration.None
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun GroupRank(navController: NavController, showIcon: Boolean = true) {
    val sortedMembers = members.sortedByDescending { it.points }
    val rankList = calculateRankList(sortedMembers)

    Column(
        modifier = Modifier
            .padding(vertical = PaddingVertical)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(ItemHeight)
                .padding(vertical = PaddingVertical, horizontal = PaddingHorizontal)
                .clickable { navController.navigate("group") },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "그룹 랭킹",
                fontFamily = customFont,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.24).sp,
                modifier = Modifier.weight(1f)
            )
            if (showIcon) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "그룹으로 이동",
                    tint = colorResource(id = R.color.gray),
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
            }
        }
        rankList.forEach { (member, rank) ->
            PersonRow(member = member, rank = rank)
        }
    }
}


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PersonRow(member: Member, rank: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = PaddingVertical, horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = rank.toString(),
            fontFamily = customFont,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = member.profileImageResId),
            contentDescription = "${member.name} 프로필",
            modifier = Modifier
                .size(BoxSize)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = member.name,
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.091.sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "${member.points}P",
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.091.sp,
            color = colorResource(id = R.color.black)
        )
    }
}


fun calculateRankList(members: List<Member>): List<Pair<Member, Int>> {
    val rankList = mutableListOf<Pair<Member, Int>>()
    var previousPoints: Int? = null
    var previousRank = 1

    for ((index, member) in members.sortedByDescending { it.points }.withIndex()) {
        val currentRank = if (member.points != previousPoints) index + 1 else previousRank
        rankList.add(member to currentRank)
        previousPoints = member.points
        previousRank = currentRank
    }

    return rankList
}

//@RequiresApi(Build.VERSION_CODES.P)
//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    MiracleplanTheme {
//        MainPage()
//    }
//}

