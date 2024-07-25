import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cn.kt66.codelabs1.WellnessTaskItem

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier) {
    Column {
        if (count > 0) {
            Text(text = "You've had $count glasses.", modifier = modifier.padding(16.dp))
        }
        Button(onClick = onIncrement, enabled = count < 10) {
            Text("Add one")
        }
    }
}

/**
 * 状态提升
 */
@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var waterCount by rememberSaveable {
        mutableStateOf(0)
    }
    /*    var juiceCount   by rememberSaveable {
            mutableStateOf(0)
        }*/
    StatelessCounter(count = waterCount, { waterCount++ }, modifier)
    //StatelessCounter(count = juiceCount, { juiceCount++ }, modifier)
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by rememberSaveable { mutableStateOf(0) }
        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    taskName = "Have you taken your 15 minute walk today?",
                    checked = false,
                    onCheckedChange = {},
                    onClose = { showTask = false }
                )
            }
            Text(text = "You've had $count glasses.", modifier = modifier.padding(16.dp))
        }
        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text("Add one")
            }
            Button(onClick = { count = 0 }, Modifier.padding(start = 8.dp)) {
                Text("Clear water count")
            }
        }

    }
}

