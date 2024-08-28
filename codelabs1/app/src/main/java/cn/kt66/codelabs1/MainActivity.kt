package cn.kt66.codelabs1

import Unit3Test3
import Unit3Test35
import Unit4Test1
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cn.kt66.codelabs1.ui.theme.Codelabs1Theme

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //日志消息的优先级，即消息的重要性。在本示例中，Log.v() 会记录详细消息。Log.d() 方法会写入调试消息。Log 类中的其他方法包括 Log.i()（表示信息性消息）、Log.w()（表示警告）和Log.e()（表示错误消息）。
        Log.d(TAG,"onCreate Called")
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
                    Unit4Test15()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Codelabs1Theme(darkTheme = true) {
        //WellnessScreen()
        //RaceTrackerApp()
        //MarsPhotosApp()
        //Unit2Test3()
        //Unit3Test1()
        Unit4Test15()
    }
}