package cord.eoeo.momentwo.ui.photodetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import cord.eoeo.momentwo.ui.SIDE_EFFECTS_KEY
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun PhotoDetailScreen(
    coroutineScope: CoroutineScope,
    imageLoader: ImageLoader,
    uiState: () -> PhotoDetailContract.State,
    effectFlow: () -> Flow<PhotoDetailContract.Effect>,
    onEvent: (event: PhotoDetailContract.Event) -> Unit,
    snackbarHostState: () -> SnackbarHostState,
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow()
            .onEach { effect ->
                when (effect) {
                    is PhotoDetailContract.Effect.PopBackStack -> {
                        popBackStack()
                    }

                    is PhotoDetailContract.Effect.ShowSnackbar -> {
                        snackbarHostState().showSnackbar(
                            message = effect.message,
                        )
                    }
                }
            }.collect()
    }

    Scaffold(
        topBar = {
            if (uiState().isMenuVisible) {
                PhotoDetailTopAppBar(
                    onBack = { onEvent(PhotoDetailContract.Event.OnBack) }
                )
            }
        },
        bottomBar = {
            if (uiState().isMenuVisible) {
                PhotoDetailBottomBar(
                    isLiked = { uiState().isLiked },
                    onClickDescription = { /* TODO: Show Description */ },
                    onClickLike = { onEvent(PhotoDetailContract.Event.OnToggleIsLiked) },
                    onClickComment = { /* TODO: Show Comments */ },
                    onClickDownload = { /* TODO: Download Photo */ },
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        AsyncImage(
            model = uiState().photoUrl,
            contentDescription = "사진",
            imageLoader = imageLoader,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { onEvent(PhotoDetailContract.Event.OnToggleIsMenuVisible) }
                ),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailTopAppBar(
    onBack: () -> Unit,
) {
    TopAppBar(
        title = { /* TODO: 사진 날짜 or 찍은 곳 */ },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, "")
            }
        },
    )
}

@Composable
fun PhotoDetailBottomBar(
    isLiked: () -> Boolean,
    onClickDescription: () -> Unit,
    onClickLike: () -> Unit,
    onClickComment: () -> Unit,
    onClickDownload: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(8.dp),
    ) {
        IconButton(onClick = onClickDescription) {
            Icon(Icons.AutoMirrored.Outlined.Article, "사진 설명")
        }

        IconButton(onClick = onClickLike) {
            Icon(if (isLiked()) Icons.Default.Favorite else Icons.Default.FavoriteBorder, "좋아요")
        }

        IconButton(onClick = onClickComment) {
            Icon(Icons.Outlined.ModeComment, "사진 댓글")
        }

        IconButton(onClick = onClickDownload) {
            Icon(Icons.Default.Download, "사진 다운로드")
        }
    }
}
