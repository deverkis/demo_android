import cn.kt66.codelabs1.network.MarsApiService
import cn.kt66.codelabs1.network.MarsPhoto

interface MarsPhotoRespository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository(private val marsApiService: MarsApiService) :
    MarsPhotoRespository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return marsApiService.getPhotos()
    }
}