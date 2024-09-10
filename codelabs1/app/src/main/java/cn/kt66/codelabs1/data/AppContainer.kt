package cn.kt66.codelabs1.data

import MarsPhotoRespository
import NetworkMarsPhotosRepository
import cn.kt66.codelabs1.network.MarsApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val marsPhotosRepository: MarsPhotoRespository
}

class DefaultAppContainer() : AppContainer {

    override val marsPhotosRepository: MarsPhotoRespository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }
    private val BASE_URL = "https://"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(("application/json".toMediaType())))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}