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

import MarsPhotoRespository
import NetworkMarsPhotosRepository
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import cn.kt66.codelabs1.MarsPhotosApplication
import cn.kt66.codelabs1.network.MarsPhoto
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface MarsUiState {
    //data class Success(val photos: MarsPhoto) : MarsUiState
    data class Success(val photos:List<MarsPhoto>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}


class MarsViewModel(private val marsPhotosRepository: MarsPhotoRespository) : ViewModel() {
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
            viewModelScope.launch {
                marsUiState = MarsUiState.Loading
                marsUiState = try {
                    val listResult = marsPhotosRepository.getMarsPhotos()
                    val result = marsPhotosRepository.getMarsPhotos()[0]

                    //MarsUiState.Success(
                   //     "Success: ${listResult.size} Mars photos retrieved"
                    //)
                    //MarsUiState.Success("First Mars image url:${result.img_src}")
                    MarsUiState.Success(listResult)
                } catch (e: IOException) {
                    MarsUiState.Error
                } catch (e: HttpException) {
                    MarsUiState.Error
                }
            }
        }
        //marsUiState = "Set the Mars API status response here!"
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}
