package cn.kt66.codelabs1

import StatefulCounter
import WaterCounter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WellnessScreen(modifier:Modifier=Modifier){
    //WaterCounter(modifier)
    StatefulCounter(modifier)
}