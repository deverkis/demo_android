/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kt66.codelabs1.ui.screens

import NetworkMarsPhotosRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val photos: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class MarsViewModel : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
        //uri realestate
        //[{"price":450000,"id":"424905","type":"buy","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631300503690E01_DXXX.jpg"},
        //{"price":8000000,"id":"424906","type":"rent","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631300305227E03_DXXX.jpg"}
        //,{"price":11000000,"id":"424907","type":"rent","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631290503689E01_DXXX.jpg"},{"price":8000000,"id":"424908","type":"rent","img_src":"http://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0044631290305226E03_DXXX.jpg"},
        // photos
//         [
//   {
//     "id": "424905",
//     "img_src": "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0044631300503690E01_DXXX.jpg"
//   },
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getMarsPhotos() {
        viewModelScope.launch {
            try {
                //将“val listResult = MarsApi.retrofitService.getPhotos()”行替换为以下代码：
                //val listResult = MarsApi.retrofitService.getPhotos()
                //仓库将提供数据，而不是由 ViewModel 直接发出关于数据的网络请求。ViewModel 不再直接引用 MarsApi 代码。
                val marsPhotoRespository = NetworkMarsPhotosRepository(restrofitService)
                val listResult = marsPhotoRespository.getMarsPhotos()
                // marsUiState = MarsUiState.Success(listResult)
                marsUiState =
                    MarsUiState.Success("Success:${listResult.size} Mars photos retrieved")
            } catch (e: IOException) {
                marsUiState = MarsUiState.Error
            }
        }
        //marsUiState = "Set the Mars API status response here!"
    }
}
