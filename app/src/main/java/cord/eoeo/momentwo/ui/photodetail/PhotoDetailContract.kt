package cord.eoeo.momentwo.ui.photodetail

import cord.eoeo.momentwo.ui.UiEffect
import cord.eoeo.momentwo.ui.UiEvent
import cord.eoeo.momentwo.ui.UiState

class PhotoDetailContract {
    data class State(
        val albumId: Int = -1,
        val subAlbumId: Int = -1,
        val photoId: Int = -1,
        val photoUrl: String = "",
        val isMenuVisible: Boolean = true,
        val isLiked: Boolean = false,
        val isLoading: Boolean = false,
        val isSuccess: Boolean = false,
        val isError: Boolean = false,
    ) : UiState

    sealed interface Event : UiEvent {
        data object OnToggleIsMenuVisible : Event
        data object OnToggleIsLiked : Event
        data object OnBack : Event
        data class OnError(val errorMessage: String) : Event
    }

    sealed interface Effect : UiEffect {
        data object PopBackStack : Effect
        data class ShowSnackbar(val message: String) : Effect
    }
}
