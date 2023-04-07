data class Notes(
    var id: Long = 0,
    val title: String = "",
    var text: String ="",
    val date: Long = 0,
    val comments: Int = 0,
    val readComments: Int = 0,
    val view_url: String = "",
    val privacyView: String = "",
    val canComment: Boolean = true,
    val textWiki: String = "",
    var isDeleted: Boolean = false,
    val commentList: MutableList<Comments> = mutableListOf()
) {
    override fun toString(): String {
        return "Заметка № $id, заголовок: $title, текст: $text, комментарии ${commentList.size}"
    }
}
