package com.example.miracleplan

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.ui.theme.MiracleplanTheme


val customFont = FontFamily(
    Font(R.font.pretendardvariable)
)

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiracleplanTheme {
                // 네비게이션 설정
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { MainPage(navController) } // Home 화면
                    composable("group") { GroupPage(navController) } // Group 화면
                    composable("record") { RecordPage() } // Record 화면
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun MainPage(navController: NavHostController = rememberNavController()) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        // Add verticalScroll modifier to Column
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
            item { GroupRank(navController = navController) }
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
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .background(color = colorResource(id = R.color.white))
            .height(28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Row 내부에서 요소 간의 간격을 자동으로 조정
    ) {
        Text(
            text = "${month}월 (D-$daysLeft)",
            fontFamily = customFont,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = (-0.24).sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f) // 텍스트가 가능한 한 넓게 차지하도록 설정
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
fun CustomBottomNavigationBar(modifier: Modifier = Modifier, navController: NavHostController) {
    Row(
        modifier = modifier
            .width(393.dp)
            .height(60.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        NavigationItem(
            iconResId = R.drawable.homestrock,
            label = "홈",
            onClick = { navController.navigate("home") }
        )

        NavigationItem(
            iconResId = R.drawable.group,
            label = "그룹",
            onClick = { navController.navigate("group") }
        )

        NavigationItem(
            iconResId = R.drawable.record,
            label = "기록",
            onClick = { navController.navigate("record") }
        )
    }
}

@Composable
fun NavigationItem(iconResId: Int, label: String, onClick: () -> Unit) {
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
        "저녁 산책",
        "명상하기",
        "가족과 시간 보내기"
    ) // 더미 데이터 나중에 서버에서 받을거임
    Column(
        modifier = Modifier
            .padding(vertical = 12.dp)
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
        dummyAchievements.forEach { achievement ->
            AchievementItem(achievement)
        }
    }
}

@Composable
fun AchievementItem(achievement: String) {
    // 체크 상태를 기억하는 변수
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .clickable {
                // 클릭 시 체크 상태를 토글
                isChecked = !isChecked
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(
                id = if (isChecked) R.drawable.check else R.drawable.uncheck),
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
fun GroupRank(navController: NavController) {
    val people = listOf(
        Person("기모띠이정우", 50, R.drawable.profile1),
        Person("카와이이주영", 80, R.drawable.profile2),
        Person("타카이김태경", 60, R.drawable.profile3),
        Person("무주카시이나제준", 60, R.drawable.profile4)
    )
    val sortedPeople = people.sortedByDescending { it.points }

    val rankList = mutableListOf<Pair<Person, Int>>()
    var currentRank: Int
    var previousPoints: Int? = null
    var previousRank = 1

    for ((index, person) in sortedPeople.withIndex()) {
        currentRank = if (person.points != previousPoints) {
            index + 1
        } else {
            previousRank
        }
        rankList.add(person to currentRank)
        previousPoints = person.points
        previousRank = currentRank
    }

    Column(
        modifier = Modifier
            .padding(vertical = 12.dp)
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
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clickable {
                        navController.navigate("group") // 페이지 이동
                    }
            )
        }
        rankList.forEach { (person, rank) ->
            PersonRow(person = person, rank = rank)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PersonRow(person: Person, rank: Int) {
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
@Composable
fun GroupPage(navController: NavHostController = rememberNavController()) {
    val groupInfos = listOf(
        GroupInfo(groupName = "이주영팸", wakeUpTime = 730, peopleCount = 4),
        GroupInfo(groupName = "선린일짱", wakeUpTime = 800, peopleCount = 3),
        GroupInfo(groupName = "2학년 5반", wakeUpTime = 920, peopleCount = 20)
    )

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
                GroupBox(groupInfo = groupInfo)
            }
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
fun GroupSign(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .background(color = colorResource(id = R.color.white))
            .height(28.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween // Row 내부에서 요소 간의 간격을 자동으로 조정
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
            tint = colorResource(id = R.color.gray),
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

data class GroupInfo(
    val groupName: String,
    val wakeUpTime: Int,
    val peopleCount: Int
)


@Composable
fun GroupBox(groupInfo: GroupInfo) {
    val hours = groupInfo.wakeUpTime / 100
    val minutes = groupInfo.wakeUpTime % 100

    // 사용할 프로필 이미지 리소스 아이디를 리스트로 정의
    val profileImages = listOf(
        R.drawable.profile1,
        R.drawable.profile2,
        R.drawable.profile3,
        R.drawable.profile4
    )

    val imageSize = 24.dp
    val overlapOffset = 12.dp
    val imageCount = profileImages.size

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(124.dp)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        // 그룹 이름
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .padding(vertical = 4.dp)
        ) {
            Text(
                text = groupInfo.groupName,
                fontSize = 20.sp,
                lineHeight = 28.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                letterSpacing = (-0.24).sp
            )
        }
        // 기상 시간
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .padding(vertical = 4.dp)
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
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "${hours}시 ${minutes}분",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                letterSpacing = 0.091.sp
            )
        }
        // 인원과 프로필 이미지
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
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
            Spacer(modifier = Modifier.width(12.dp))

            // 프로필 이미지를 겹쳐서 표시
            Box(
                modifier = Modifier
                    .wrapContentWidth(Alignment.Start)
            ) {
                // Display profile images
                val displayCount = minOf(groupInfo.peopleCount, imageCount)
                repeat(displayCount) { index ->
                    val reverseIndex = displayCount - 1 - index
                    Image(
                        painter = painterResource(id = profileImages[reverseIndex % imageCount]),
                        contentDescription = "Person $index",
                        modifier = Modifier
                            .size(imageSize)
                            .zIndex(index.toFloat()) // 겹침 순서 조정
                            .offset(x = index * -overlapOffset)
                    )
                }


            }

            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "${groupInfo.peopleCount}명",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                letterSpacing = 0.091.sp
            )
        }
    }
}


























@Composable
fun RecordPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "기록 페이지",
                fontFamily = customFont,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.black)
            )
        }
    }
}




@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun Preview() {
    MiracleplanTheme {
//        val sampleGroupInfo = GroupInfo(
//            groupName = "7시반 미라클 모닝 팟 구함",
//            wakeUpTime = 730,
//            peopleCount = 8
//        )
//        GroupBox(groupInfo = sampleGroupInfo)
        GroupPage()
    }
}