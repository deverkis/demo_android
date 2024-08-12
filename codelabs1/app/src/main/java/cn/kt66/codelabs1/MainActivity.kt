package cn.kt66.codelabs1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.kt66.codelabs1.ui.RaceTrackerApp
import cn.kt66.codelabs1.ui.theme.Codelabs1Theme
import kotlinx.coroutines.Deferred
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
                    RaceTrackerApp()
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
    val temperature = async{
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Codelabs1Theme {
        //WellnessScreen()
        RaceTrackerApp()
    }
}