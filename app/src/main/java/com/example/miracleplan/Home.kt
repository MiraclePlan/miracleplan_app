package com.example.miracleplan

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.miracleplan.ui.theme.MiracleplanTheme


val customFont = FontFamily(
    Font(R.font.pretendardvariable)
)

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MainPage()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp) // 바텀 네비게이션 바의 높이만큼 패딩 추가
        ) {
            TodaySign()
            ChallengeBox()
            BetweenLayer()
            AchievementList()
            BetweenLayer()
            GroupRank()
            // Spacer 추가 (유동적인 여백 추가)
            Spacer(modifier = Modifier.weight(1f))
        }

        CustomBottomNavigationBar(
            modifier = Modifier
                .align(Alignment.BottomCenter) // 하단 고정
                .fillMaxWidth()
                .height(60.dp) // 바텀 네비게이션 바의 높이
        )
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
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 4.dp, horizontal = 16.dp)
    ) {
        val nums = listOf(1, 2, 3, 4, 5, 6, 7)
        nums.forEachIndexed { index, num ->
            DateNumBox(num, isCenter = index == nums.size / 2)
        }
    }
}


@Composable
fun DateNumBox(num: Int, isCenter: Boolean) {
    Box(
        modifier = Modifier
            .width(51.6.dp)
            .height(40.dp)
            .background(
                color = if (isCenter) colorResource(id = R.color.black) else Color.Transparent, // 가운데 박스는 검은색 배경
                shape = RoundedCornerShape(8.dp)
            )
            .then(
                if (isCenter) {
                    Modifier
                } else {
                    Modifier.padding(vertical = 8.dp, horizontal = 10.dp) // 다른 박스에는 padding을 적용
                }
            )
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = num.toString(),
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.091.sp,
            color = if (isCenter) Color.White else colorResource(id = R.color.gray), // 가운데 박스의 텍스트 색상을 흰색으로 변경
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
fun CustomBottomNavigationBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
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
//achievements: List<String>
@Composable
fun AchievementList() {
    val dummyAchievements = listOf(
        "아침 운동하기",
        "독서 30분",
        "코딩 공부 1시간",
        "청소하기",
//        "저녁 산책",
//        "명상하기",
//        "가족과 시간 보내기"
    )
    Column (
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .width(393.dp)
                .height(52.dp)
                .padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "오늘 달성 계획",
                fontFamily = customFont,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.24).sp,
            )
        }
        LazyColumn {
            items(dummyAchievements) { achievement ->
                AchievementItem(achievement.toString())
            }
        }
    }
}

@Composable
fun AchievementItem(achievement: String) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(vertical = 12.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.check),
            contentDescription = "체크박스",
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
            letterSpacing = 0.091.sp
        )
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun GroupRank() {
    val people = listOf(
        Person("기모띠이정우", 50, R.drawable.profile1),
        Person("카와이이주영", 80, R.drawable.profile2),
        Person("타카이김태경", 60, R.drawable.profile3),
        Person("무주카시이나제준", 60, R.drawable.profile4)
    )
    val sortedPeople = people.sortedByDescending { it.points }

    val rankList = mutableListOf<Pair<Person, Int>>()
    var currentRank = 1
    var previousPoints: Int? = null
    var previousRank = 1

    for ((index, person) in sortedPeople.withIndex()) {
        if (person.points != previousPoints) {
            currentRank = index + 1
        } else {
            currentRank = previousRank
        }
        rankList.add(person to currentRank)
        previousPoints = person.points
        previousRank = currentRank
    }

    Column (
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(28.dp)
            ) {
                Text(
                    text = "그룹 랭킹",
                    fontFamily = customFont,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    color = colorResource(id = R.color.black),
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.24).sp
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "그룹으로 이동",
                tint = colorResource(id = R.color.gray),
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        LazyColumn {
            items(rankList) { (person, rank) ->
                PersonRow(person = person, rank = rank)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PersonRow(person: com.example.miracleplan.Person, rank: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 20.dp),
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
            painter = painterResource(id = person.profileImageResId),
            contentDescription = "${person.name} 프로필",
            modifier = Modifier
                .height(28.dp)
                .width(28.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = person.name,
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.091.sp,
            color = colorResource(id = R.color.black),
            modifier = Modifier.weight(1f) // 이름과 포인트 사이의 공간 조절
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "${person.points}P",
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 0.091.sp,
            color = colorResource(id = R.color.black)
        )
    }
}

data class Person(
    val name: String,
    val points: Int,
    val profileImageResId: Int
)



@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun Preview() {
    MiracleplanTheme {
        MainPage()
    }
}