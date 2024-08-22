package cn.kt66.codelabs1

import Unit3Test4
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                    //Unit2Test3()
                    //Unit3Test1()
                    Unit3Test4()
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Codelabs1Theme {
        //WellnessScreen()
        //RaceTrackerApp()
        //MarsPhotosApp()
        //Unit2Test3()
        //Unit3Test1()
        Unit3Test4()
    }
}