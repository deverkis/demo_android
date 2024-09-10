package cn.kt66.codelabs1

import android.app.Application
import cn.kt66.codelabs1.data.AppContainer
import cn.kt66.codelabs1.data.DefaultAppContainer

class MarsPhotosApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}