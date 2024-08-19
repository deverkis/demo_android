package cn.kt66.codelabs1.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarsPhoto(
    val id: String,
    @SerialName(value="img_src")
    val img_src:String
) {
}

