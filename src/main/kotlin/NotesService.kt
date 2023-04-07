object NotesService {
     var listNotes = mutableListOf<Notes>()
     var notesIdCount = 0
     var commentsIdCount = 0

    fun add(note: Notes) {
        notesIdCount++
        note.id = notesIdCount.toLong()
        listNotes.add(note)
    }

    fun createComment(comment: Comments): Boolean {
        for (note in listNotes) {
            if (note.id == comment.noteId && !note.isDeleted) {
                commentsIdCount++
                comment.commentId = commentsIdCount
                note.commentList.add(comment)
                return true
            }
        }
        throw NoteNotFoundException("Заметка не найдена!")
    }

    fun delete(noteId: Long): Boolean {
        for (note in listNotes) {
            if (note.id == noteId && !note.isDeleted) {
                note.isDeleted = true
                return true
            }
        }
        throw NoteNotFoundException("Заметка не найдена!")
    }

    fun deleteComment(noteId: Long, commentId: Int):Boolean {
        for (note in listNotes) {
            if (note.id == noteId && !note.isDeleted) {
                for (comment in note.commentList) {
                    if (comment.commentId == commentId && !comment.isDeleted) {
                        comment.isDeleted = true
                        return true
                    }
                }
            }
        }
        throw NoteNotFoundException("Комментарий не найден!")
    }

    fun edit(editNote: Notes): Boolean {
        for (note in listNotes) {
            if (editNote.id == note.id && !note.isDeleted) {
                note.text = editNote.text
                return true
            }
        }
        throw NoteNotFoundException("Заметка не найдена!")
    }

    fun editComment(editComment: Comments) : Boolean {
        for (note in listNotes) {
            if (editComment.noteId == note.id && !note.isDeleted) {
                for (comment in note.commentList) {
                    if (editComment.commentId == comment.commentId && !comment.isDeleted) {
                        comment.message = editComment.message
                        return true
                    }
                }
            }
        }
        throw NoteNotFoundException("Комментарий не найден!")
    }

    fun get(): MutableList<Notes> {
        return listNotes
    }


    fun getById(noteId: Long) : Notes {
        for (note in listNotes) {
            if (noteId == note.id && !note.isDeleted) {
                return note
            }
        }
        throw NoteNotFoundException("Заметка не найдена!")
    }

    fun getComments(noteId: Long): MutableList<Comments> {
        for (note in listNotes) {
            if (noteId == note.id && !note.isDeleted) {
                return note.commentList
            }
        }
        throw NoteNotFoundException("Заметка не найдена!")
    }

    fun restoreComment (noteId: Long, commentId: Int): Boolean {
        for (note in listNotes) {
            if (noteId == note.id && !note.isDeleted) {
                for (comment in note.commentList) {
                    if (commentId == comment.commentId && comment.isDeleted) {
                        comment.isDeleted = false
                        return true
                    }
                }
            }
        }
        throw NoteNotFoundException("Комментарий не найден!")
    }

    fun clear() {
        listNotes = mutableListOf<Notes>()
        notesIdCount = 0
        commentsIdCount = 0
    }
}