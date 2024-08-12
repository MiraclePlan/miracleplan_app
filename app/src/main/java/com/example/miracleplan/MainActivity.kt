package com.example.miracleplan

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.screens.GroupPage
import com.example.miracleplan.screens.GroupSign
import com.example.miracleplan.screens.GroupSignInPage
import com.example.miracleplan.screens.MainPage
import com.example.miracleplan.screens.RecordPage
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
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") { MainPage(navController) } // Home 화면
                    composable("group") { GroupPage(navController) } // Group 화면
                    composable("record") { RecordPage(navController) } // Record 화면
                    composable("groupSignIn") { GroupSignInPage(navController) }
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
