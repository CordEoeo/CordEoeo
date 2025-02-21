package cord.eoeo.momentwo.ui.friend.friendrequest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LifecycleStartEffect
import coil.ImageLoader
import cord.eoeo.momentwo.ui.RESUME_EFFECTS_KEY
import cord.eoeo.momentwo.ui.START_EFFECTS_KEY
import cord.eoeo.momentwo.ui.model.FriendRequestItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FriendRequestScreen(
    coroutineScope: CoroutineScope,
    imageLoader: ImageLoader,
    receivedRequests: () -> List<FriendRequestItem>,
    sentRequests: () -> List<FriendRequestItem>,
    isReceivedListChanged: () -> Boolean,
    isSentListChanged: () -> Boolean,
    getReceivedRequests: () -> Unit,
    getSentRequests: () -> Unit,
    onClickAccept: (Int, String) -> Unit,
    onClickReject: (Int, String) -> Unit,
    onClickCancel: (Int, Int) -> Unit,
    pagerState: () -> PagerState,
) {
    LifecycleStartEffect(START_EFFECTS_KEY) {
        getReceivedRequests()
        getSentRequests()
        onStopOrDispose { }
    }

    val tabs = listOf("받은 요청", "보낸 요청")

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TabRow(
            selectedTabIndex = pagerState().currentPage,
            modifier = Modifier.fillMaxWidth(),
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = index == pagerState().currentPage,
                    onClick = { coroutineScope.launch { pagerState().animateScrollToPage(index) } },
                    text = { Text(text = title) },
                )
            }
        }

        HorizontalPager(
            state = pagerState(),
            beyondViewportPageCount = 1,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
        ) { page ->
            when (page) {
                0 -> {
                    ReceivedRequestScreen(
                        imageLoader = imageLoader,
                        receivedRequests = receivedRequests,
                        isReceivedListChanged = isReceivedListChanged,
                        getReceivedRequests = getReceivedRequests,
                        onClickAccept = onClickAccept,
                        onClickReject = onClickReject,
                    )
                }

                1 -> {
                    SentRequestScreen(
                        imageLoader = imageLoader,
                        sentRequests = sentRequests,
                        isSentListChanged = isSentListChanged,
                        getSentRequests = getSentRequests,
                        onClickCancel = onClickCancel,
                    )
                }
            }
        }
    }
}

@Composable
fun ReceivedRequestScreen(
    imageLoader: ImageLoader,
    receivedRequests: () -> List<FriendRequestItem>,
    isReceivedListChanged: () -> Boolean,
    getReceivedRequests: () -> Unit,
    onClickAccept: (Int, String) -> Unit,
    onClickReject: (Int, String) -> Unit,
) {
    LifecycleResumeEffect(RESUME_EFFECTS_KEY) {
        getReceivedRequests()
        onPauseOrDispose { }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        itemsIndexed(items = receivedRequests(), key = { _, item -> item.nickname }) { index, friendRequestItem ->
            ReceivedFriendRequestItem(
                imageLoader = imageLoader,
                item = { friendRequestItem },
                onClickAccept = { onClickAccept(index, friendRequestItem.nickname) },
                onClickReject = { onClickReject(index, friendRequestItem.nickname) },
            )
        }
    }
}

@Composable
fun SentRequestScreen(
    imageLoader: ImageLoader,
    sentRequests: () -> List<FriendRequestItem>,
    isSentListChanged: () -> Boolean,
    getSentRequests: () -> Unit,
    onClickCancel: (Int, Int) -> Unit,
) {
    LifecycleResumeEffect(RESUME_EFFECTS_KEY) {
        getSentRequests()
        onPauseOrDispose { }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        itemsIndexed(items = sentRequests(), key = { _, item -> item.nickname }) { index, friendRequestItem ->
            SentFriendRequestItem(
                imageLoader = imageLoader,
                item = { friendRequestItem },
                onClickCancel = { onClickCancel(index, friendRequestItem.userId) },
            )
        }
    }
}
