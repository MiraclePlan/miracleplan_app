import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TimePicker() {
    val hours = (1..12).toList()
    val minutes = (0..59 step 5).toList()
    val periods = listOf("오전", "오후")

    var selectedHour by remember { mutableIntStateOf(7) }
    var selectedMinute by remember { mutableIntStateOf(30) }
    var selectedPeriod by remember { mutableStateOf("오후") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TimePickerColumn(
            items = hours,
            selectedItem = selectedHour,
            onItemSelected = { selectedHour = it }
        )
        Text(":", fontSize = 24.sp)
        TimePickerColumn(
            items = minutes,
            selectedItem = selectedMinute,
            onItemSelected = { selectedMinute = it }
        )
        Text(":", fontSize = 24.sp)
        TimePickerColumn(
            items = periods,
            selectedItem = selectedPeriod,
            onItemSelected = { selectedPeriod = it }
        )
    }
}

@Composable
fun <T> TimePickerColumn(
    items: List<T>,
    selectedItem: T,
    onItemSelected: (T) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .height(150.dp)
            .width(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(items) { index, item ->
            val isSelected = item == selectedItem
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(if (isSelected) Color(0xFFE0E0E0) else Color.Transparent)
                    .clickable { onItemSelected(item) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.toString(),
                    fontSize = if (isSelected) 24.sp else 20.sp,
                    color = if (isSelected) Color.Black else Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TimePickerPreview() {
    TimePicker()
}
