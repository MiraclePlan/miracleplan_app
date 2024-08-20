package com.example.miracleplan.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.miracleplan.ui.theme.MiracleplanTheme

@Composable
fun AddTodoListPage(navController: NavHostController = rememberNavController()) {
    val todoList = remember { mutableStateListOf<String>() }
    val isButtonEnabled = todoList.any { it.isNotBlank() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        GoBackSign {
            navController.navigate("SelectDate")
        }
        TopText("주영님의 계획을 알려주세요!", "주영님만의 미라클 투두리스트를 만들어보세요.")
        LazyColumn {
            items(todoList.size) { index ->
                TodoAddBox(
                    text = todoList[index],
                    onTextChange = { newText -> todoList[index] = newText }
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
            item {
                AddButton {
                    todoList.add("") // Add an empty item to the list
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        CantClickButton(
            onClick = {
                if (isButtonEnabled) navController.navigate("SignInComplete")
            },
            isEnabled = isButtonEnabled
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoAddBox(text: String, onTextChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.uncheck),
            contentDescription = "그냥 아이콘"
        )
        androidx.compose.material3.TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = {
                Text(
                    text = "입력해 주세요.",
                    color = colorResource(id = R.color.gray),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.091.sp,
                    fontFamily = customFont
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 0.091.sp,
                color = colorResource(id = R.color.gray)
            ),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = 1.5.dp,
                    shape = RoundedCornerShape(8.dp),
                    color = colorResource(id = R.color.outlinecolor)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.white),
                contentColor = colorResource(id = R.color.gray)
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Text(
                text = "추가하기",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontFamily = customFont,
                letterSpacing = 0.091.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun CantClickButton(onClick: () -> Unit, modifier: Modifier = Modifier, isEnabled: Boolean) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(361.dp)
            .height(56.dp)
            .clickable(onClick = onClick),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) colorResource(id = R.color.black) else colorResource(id = R.color.outlinecolor),
            contentColor = if (isEnabled) colorResource(id = R.color.white) else colorResource(id = R.color.gray)
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        Text(
            text = "다음",
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = customFont,
            fontWeight = FontWeight.Bold
        )
    }
}

//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//fun Preview() {
//    MiracleplanTheme {
//        AddTodoListPage()
//    }
//}
