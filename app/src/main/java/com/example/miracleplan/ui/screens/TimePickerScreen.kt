import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.miracleplan.R
import com.example.miracleplan.ui.screens.DefaultButton
import com.example.miracleplan.ui.screens.GoBackSign
import com.example.miracleplan.ui.screens.TopText


@Composable
fun TimePickerPage(navController: NavHostController = rememberNavController()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.white)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GoBackSign {
            navController.navigate("login")
        }
        TopText(text1 = "몇 시에 일어날까요?", text2 = "OO에서 기상 시간에 깨워드려요.")
        Spacer(modifier = Modifier.weight(0.7f))
        TimePicker()
        Spacer(modifier = Modifier.weight(1f))
        DefaultButton(onClick = {navController.navigate("todo")}, text = "다음")
    }
}

@Composable
fun TimePicker() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(vertical = 24.dp, horizontal = 16.dp)
    )
}



@Preview(showBackground = true)
@Composable
fun TimePickerPreview() {
    TimePickerPage()
}
