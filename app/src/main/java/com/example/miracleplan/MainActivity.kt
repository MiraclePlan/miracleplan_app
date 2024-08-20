package com.example.miracleplan

import TimePickerPage
import GeneratedGroupPage
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.ui.screens.AddTodoListPage
import com.example.miracleplan.ui.screens.GroupDetailsPage
import com.example.miracleplan.ui.screens.GroupPage
import com.example.miracleplan.ui.screens.GroupSign
import com.example.miracleplan.ui.screens.GroupSignInPage
import com.example.miracleplan.ui.screens.LoginPage
import com.example.miracleplan.ui.screens.MainPage
import com.example.miracleplan.ui.screens.RecordPage
import com.example.miracleplan.ui.screens.RegisterPage
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
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "login") {
                    composable("home") { MainPage(navController) } // Home 화면
                    composable("group") { GroupPage(navController) } // Group 화면
                    composable("record") { RecordPage(navController) } // Record 화면
                    composable("groupSignIn") { GroupSignInPage(navController) }
                    composable("groupDetails") { GroupDetailsPage(navController) }
                    composable("generatedGroup") { GeneratedGroupPage(navController) }
                    composable("login") { LoginPage(navController) }
                    composable("register") { RegisterPage(navController) }
                    composable("todo") { AddTodoListPage(navController) }
                    composable("timePicker") { TimePickerPage(navController) }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun Preview() {
    MiracleplanTheme {
        GroupSign()
    }
}
