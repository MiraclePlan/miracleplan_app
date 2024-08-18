package com.example.miracleplan.screens

import android.annotation.SuppressLint
import android.os.Build
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.customFont
import com.example.miracleplan.data.Member
import com.example.miracleplan.data.members
import com.example.miracleplan.ui.theme.MiracleplanTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun GeneratedGroupPage(navController: NavHostController = rememberNavController()) {
    val sheetState = rememberModalBottomSheetState(

    )
    val scope = rememberCoroutineScope()

    Scaffold {
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
                item {
                    GroupSignWithSetting(
                        onSettingClick = {
                            // Coroutine을 사용하여 Bottom Sheet를 표시
                            scope.launch {
                                sheetState.show()
                            }
                        }
                    )
                }
                item { GroupMemberRow() }
                item { BetweenLayer() }
                item { GroupRank(navController, showIcon = false) }
            }
            CustomBottomNavigationBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                navController = navController
            )
        }
    }
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            // Bottom Sheet를 닫을 때 호출됨
            scope.launch {
                sheetState.hide()
            }
        }
    ) {
        BottomSheet(
            onInviteClick = { /* 초대하기 로직 추가 */ },
            onLeaveClick = { /* 그룹 나가기 로직 추가 */ },
        )
    }
}

@Composable
fun BottomSheet(
    onInviteClick: () -> Unit,
    onLeaveClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.white))
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Text(
            text = "그룹 설정",
            fontSize = 20.sp,
            fontFamily = customFont,
            lineHeight = 28.sp,
            letterSpacing = (-0.24).sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black),
            modifier = Modifier.padding(vertical = 12.dp)
        )
        BottomSheetContent(
            iconResName = "useradd",
            text = "초대하기",
            onClick = onInviteClick
        )
        BottomSheetContent(
            iconResName = "exitgroup",
            text = "그룹 나가기",
            onClick = onLeaveClick
        )
    }
}

@Composable
fun BottomSheetContent(iconResName: String, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 12.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val (iconResId, iconTintResId) = getIconResource(iconResName)

        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = "아이콘",
            tint = colorResource(id = iconTintResId) // 색상 적용
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontFamily = customFont,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.091.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

fun getIconResource(iconResName: String): Pair<Int, Int> {
    return when (iconResName) {
        "useradd" -> Pair(R.drawable.useradd, R.color.black)
        "exitgroup" -> Pair(R.drawable.exitgroup, R.color.red)
        else -> throw IllegalArgumentException("Unknown icon name: $iconResName")
    }
}

@Composable
fun GroupSignWithSetting(onSettingClick: () -> Unit) {
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
            painter = painterResource(id = R.drawable.setting),
            contentDescription = "설정",
            tint = colorResource(id = R.color.gray),
            modifier = Modifier.clickable { onSettingClick() }
        )
    }
}

@Composable
fun GroupMemberRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(16.dp)
    ) {
        items(members) { member ->
            GroupMemberBox(member)
        }
    }
}

@Composable
fun GroupMemberBox(member: Member) {
    Row(
        modifier = Modifier
            .width(96.dp)
            .height(128.dp)
    ) {
        Column(
            modifier = Modifier
                .width(72.dp)
                .height(128.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = member.profileImageResId),
                contentDescription = "${member.name} 프로필 사진"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = member.name,
                fontFamily = customFont,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                letterSpacing = 0.091.sp
            )
            Text(
                text = member.status,
                color = when (member.status) {
                    "성공" -> colorResource(id = R.color.green)
                    "실패" -> colorResource(id = R.color.red)
                    "도전 중" -> colorResource(id = R.color.gray)
                    else -> colorResource(id = R.color.gray)
                },
                fontFamily = customFont,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.091.sp
            )
        }
        Spacer(modifier = Modifier.width(24.dp))
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun Preview() {
    MiracleplanTheme {
        GeneratedGroupPage()
    }
}
