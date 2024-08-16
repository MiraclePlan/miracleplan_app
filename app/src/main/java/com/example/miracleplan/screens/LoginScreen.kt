import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start

        ) {
            Spacer(modifier = Modifier.height(50.dp))  // 화면 위에 공간을 추가하여 텍스트를 위로 이동

            Text(
                text = "미라클플랜에\n오신 걸 환영해요!",
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)  // 텍스트와 입력 칸 사이 간격을 조정
            )
            Text(
                text = "아이디",
                fontSize = 16.sp
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("입력해 주세요") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "비밀번호",
                fontSize = 16.sp
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("입력해 주세요") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 300.dp)
            )

            Spacer(modifier = Modifier.height(60.dp))  // 로그인 버튼을 아래로 이동시키기 위한 추가 공간

            Button(
                onClick = { /* TODO: 로그인 처리 */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text(text = "로그인")
            }


        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start

        ){
            Spacer(modifier = Modifier.height(16.dp))

            ClickableText(
                text = AnnotatedString("계정이 없다면? 회원가입"),
                onClick = { /* TODO: 회원가입 페이지로 이동 */ },
                modifier = Modifier.padding(top = 0.dp)
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}
