import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.miracleplan.screens.BetweenLayer
import com.example.miracleplan.screens.CustomBottomNavigationBar
import com.example.miracleplan.screens.GroupRank
import com.example.miracleplan.ui.theme.MiracleplanTheme
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun GeneratedGroupPage(navController: NavHostController = rememberNavController()) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var isDialogVisible by remember { mutableStateOf(false) }

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
                            // 설정 아이콘 클릭 시 바텀시트 표시
                            showBottomSheet = true
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

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {
                // 바텀시트 내용
                BottomSheet(
                    onInviteClick = { /* 초대하기 로직 추가 */ },
                    onLeaveClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                        isDialogVisible = true
                    }
                )
            }
        }
    }

    if (isDialogVisible) {
        AlertDialog(
            onDismissRequest = { isDialogVisible = false },
            title = { Text(text = "정말로 나가시겠습니까?") },
            text = { Text("그룹에서 나가면 다시 초대를 받아야 합니다.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        isDialogVisible = false
                        // 그룹 나가기 로직 추가
                    }
                ) {
                    Text("나가기")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { isDialogVisible = false }
                ) {
                    Text("취소")
                }
            }
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
