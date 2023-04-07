import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NotesServiceTest {
    @Before
    fun clearBeforeTest() {
        NotesService.clear()
    }

    @Test
    fun add() {
        NotesService.add(note = Notes())
        NotesService.add(note = Notes())
        assertEquals(2, NotesService.listNotes.size)
    }

    @Test
    fun createComment() {
        NotesService.add(note = Notes())
        NotesService.createComment(comment = Comments(noteId =  1, message = "First comment"))
        val result = NotesService.createComment(comment = Comments(noteId =  1, message = "Second comment"))

        assertEquals(2, NotesService.getById(1).commentList.size)
        assertEquals(true, result)
    }

    @Test
    fun delete() {
        NotesService.add(note = Notes())
        NotesService.add(note = Notes())

        val result = NotesService.delete(1)

        assertEquals(true, result)
    }

    @Test
    fun deleteComment() {
        NotesService.add(note = Notes())
        NotesService.add(note = Notes())

        NotesService.createComment(comment = Comments(noteId =  2, message = "First comment"))
        NotesService.createComment(comment = Comments(noteId =  2, message = "Second comment"))

        val result = NotesService.deleteComment(2,2)

        assertEquals(true, result)
    }

    @Test
    fun edit() {
        NotesService.add(note = Notes())
        NotesService.add(note = Notes())

        val result = NotesService.edit(editNote = Notes(id = 2, text = "edit"))

        assertEquals(true, result)
    }

    @Test
    fun editComment() {
        NotesService.add(note = Notes())
        NotesService.add(note = Notes())

        NotesService.createComment(comment = Comments(noteId =  2, message = "First comment"))
        NotesService.createComment(comment = Comments(noteId =  2, message = "Second comment"))

        val result = NotesService.editComment(editComment = Comments(2,2, message = "edit"))

        assertEquals(true, result)
    }

    @Test
    fun get() {
        NotesService.add(note = Notes(text = "First"))
        NotesService.add(note = Notes(text = "Second"))

        val result = NotesService.get()

        assertEquals(NotesService.listNotes, result)
    }

    @Test
    fun getById() {
        NotesService.add(note = Notes(text = "First"))
        NotesService.add(note = Notes(text = "Second"))

        val result = NotesService.getById(2)

        assertEquals(2, result.id)
    }

    @Test
    fun getComments() {
        NotesService.add(note = Notes())

        NotesService.createComment(comment = Comments(noteId =  1, message = "First comment"))
        NotesService.createComment(comment = Comments(noteId =  1, message = "Second comment"))

        val result = NotesService.getComments(1)

        assertEquals(NotesService.getById(1).commentList, result)
    }

    @Test
    fun restoreComment() {
        NotesService.add(note = Notes())

        NotesService.createComment(comment = Comments(noteId =  1, message = "First comment"))
        NotesService.createComment(comment = Comments(noteId =  1, message = "Second comment"))
        NotesService.createComment(comment = Comments(noteId =  1, message = "Third comment"))

        NotesService.deleteComment(1,3)

        val result = NotesService.restoreComment(1,3)

        assertEquals(true, result)
    }
}