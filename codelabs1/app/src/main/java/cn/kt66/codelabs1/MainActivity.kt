package cn.kt66.codelabs1

import Unit3Test3
import android.os.Bundle
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
                    Unit3Test3()
                }
            }
        }
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
        Unit3Test3()
    }
}