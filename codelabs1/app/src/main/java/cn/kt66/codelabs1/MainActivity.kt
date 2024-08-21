package cn.kt66.codelabs1

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.kt66.codelabs1.ui.theme.Codelabs1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import kotlin.system.measureTimeMillis

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Codelabs1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //WellnessScreen()
                    //MyApp()
                    //RaceTrackerApp()
                    //MarsPhotosApp()
                    // unit2Test2()
                    UnitTest3()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MyApp() {
    Text("Myapp")
    val time = measureTimeMillis {
        runBlocking {
            // 异步代码
            println("Weather forecast")
            println(getWeatherReport())
            println("Have a good day!")
        }
    }
    println("Execution time: ${time / 1000.0} seconds")

    runBlocking {
        println("${Thread.currentThread().name} - runBlocking function")
        launch {
            println("${Thread.currentThread().name} - launch function")
            withContext(Dispatchers.Default) {
                println("${Thread.currentThread().name} - withContext function")
                delay(1000)
                println("10 results found.")
            }
            println("${Thread.currentThread().name} - end of launch function")
        }
        println("Loading...")
    }

}

/**
 * coroutineScope{} 用于为此天气预报任务创建局部作用域
 * coroutineScope 只有在所有工作完成后才会返回。
 */
suspend fun getWeatherReport() = coroutineScope {
    //launch() 和 async() 是 CoroutineScope 的扩展函数。
    val forecast = async { getForecast() }
    val temperature = async {
        getTemperature()
    }
    // 天气预报只包含天气预报信息 Sunny，但不包含温度，因为相应协程已取消。
    temperature.cancel()
    "${forecast.await()}"

    // "${forecast.await()} ${temperature.await()}"
}

suspend fun getForecast(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature(): String {
    delay(500)
    throw AssertionError("Temperature is invalid")
    return "30\u00b0C"
}


@Composable
fun unit2Test2() {
    //可组合函数可以使用 remember 可组合函数将对象存储在内存中。
    //var result = 1
    var result by remember { mutableStateOf(1) }
    Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        val imageResource = when (result) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = imageResource.toString()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { result = (1..6).random() }) {
            Text(stringResource(id = R.string.roll))
        }
    }

}

@Composable
fun UnitTest3() {
    var roundUp by remember {
        mutableStateOf(false)
    }
    var amountInput by remember { mutableStateOf("") }
    var tipInput by remember { mutableStateOf("") }
    val tipPercent = tipInput.toDoubleOrNull() ?: 0.0
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount, tipPercent, roundUp)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        EditNumberField(
            label = R.string.bill_amount,
            leadingIcon=R.drawable.money,
            value = amountInput,
            onValueChange = { amountInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )
        EditNumberField(
            label = R.string.how_was_the_service,
            leadingIcon = R.drawable.percent,
            value = tipInput,
            onValueChange = { tipInput = it }, modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )

        )
        RoundTheTipRow(
            roundUp = roundUp,
            onRoundUpChanged = { roundUp = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    @StringRes label: Int,
    @DrawableRes leadingIcon:Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        leadingIcon = {Icon(painter= painterResource(id = leadingIcon),null)},
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(stringResource(id = label)) },
        modifier = modifier,
        //将键盘类型设置为数字键盘即可输入数字
        //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun RoundTheTipRow(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .size(48.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(id = R.string.round_up_tip))
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberFieldOld(modifier: Modifier = Modifier) {
    //val amountInput="0"
    //您需要依赖名为“重组”的进程来更新应用的组合。
    //而 MutableState 类型是可变的。可以使用 mutableStateOf() 函数来创建可观察的 MutableState。
    //var amountInput:MutableState<String> =  mutableStateOf("0")
    //可组合函数可以使用 remember 跨重组存储对象。
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amount)
    //在 lambda 表达式中，it 变量包含新值。
    //singleLine 这样可以将文本框从多行压缩成可水平滚动的单行。
    TextField(
        value = amountInput,
        onValueChange = { amountInput = it },
        label = { Text(stringResource(id = R.string.bill_amount)) },
        modifier = modifier,
        singleLine = true,
        //将键盘类型设置为数字键盘即可输入数字
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun TipTimeLayout() {
    Column {
        Text(
            text = stringResource(id = R.string.tip_amount, "$0.00"),
            style = MaterialTheme.typography.displaySmall
        )
    }
}

private fun calculateTip(
    amount: Double,
    tipPercent: Double = 15.0,
    roundUp: Boolean = false
): String {
    var tip = tipPercent / 100 * amount
    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }
    Log.e("roundUp",roundUp.toString())
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Codelabs1Theme {
        //WellnessScreen()
        //RaceTrackerApp()
        //MarsPhotosApp()
        UnitTest3()
    }
}