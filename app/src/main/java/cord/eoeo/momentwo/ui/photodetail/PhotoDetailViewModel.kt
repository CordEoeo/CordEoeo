package cord.eoeo.momentwo.ui.photodetail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import cord.eoeo.momentwo.ui.BaseViewModel
import cord.eoeo.momentwo.ui.MomentwoDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<PhotoDetailContract.State, PhotoDetailContract.Event, PhotoDetailContract.Effect>() {
    init {
        val (albumId, subAlbumId, photoId, photoUrl) = savedStateHandle.toRoute<MomentwoDestination.PhotoDetail>()

        setState(
            uiState.value.copy(
                albumId = albumId,
                subAlbumId = subAlbumId,
                photoId = photoId,
                photoUrl = photoUrl,
            )
        )
    }

    override fun createInitialState(): PhotoDetailContract.State = PhotoDetailContract.State()

    override fun handleEvent(newEvent: PhotoDetailContract.Event) {
        when (newEvent) {
            is PhotoDetailContract.Event.OnToggleIsMenuVisible -> {
                with (uiState.value) { setState(copy(isMenuVisible = isMenuVisible.not())) }
            }

            is PhotoDetailContract.Event.OnToggleIsLiked -> {
                with (uiState.value) { setState(copy(isLiked = isLiked.not())) }
                // TODO: 1초 딜레이 후 좋아요 상태 변경 요청 추가
            }

            is PhotoDetailContract.Event.OnBack -> {
                setEffect { PhotoDetailContract.Effect.PopBackStack }
            }

            is PhotoDetailContract.Event.OnError -> {
                setEffect { PhotoDetailContract.Effect.ShowSnackbar(newEvent.errorMessage) }
            }
        }
    }
}
