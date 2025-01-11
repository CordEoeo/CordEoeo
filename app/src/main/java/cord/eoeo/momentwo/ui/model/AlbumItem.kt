package cord.eoeo.momentwo.ui.model

import cord.eoeo.momentwo.ui.MomentwoDestination

data class AlbumItem(
    val id: Int,
    val title: String,
    val subTitle: String,
    val imageUrl: String,
    val subAlbumCount: Int,
) {
    companion object {
        fun newInstance(albumDetail: MomentwoDestination.AlbumDetail): AlbumItem =
            AlbumItem(
                id = albumDetail.id,
                title = albumDetail.title,
                subTitle = albumDetail.subTitle,
                imageUrl = albumDetail.imageUrl,
                subAlbumCount = 0, // TODO
            )
    }
}
