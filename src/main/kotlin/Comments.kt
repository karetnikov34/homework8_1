data class Comments(
    var commentId: Int =0,
    val noteId: Long,
    val replyTo: Int = 0,
    var message: String,
    var date: Long = 0,
    var isDeleted: Boolean = false
) {
    override fun toString(): String {
        return "Комментарий № $commentId текст: $message"
    }
}
