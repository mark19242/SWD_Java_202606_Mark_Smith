import { useEffect, useState } from 'react'
import { useAuth } from '../context/AuthContext'
import './NotesPage.css'

export function NotesPage() {
  const { username, authedFetch } = useAuth()
  const [greeting, setGreeting] = useState('')
  const [notes, setNotes] = useState([])
  const [title, setTitle] = useState('')
  const [content, setContent] = useState('')
  const [error, setError] = useState(null)
  const [submitting, setSubmitting] = useState(false)

  useEffect(() => {
    authedFetch('/greet').then(setGreeting).catch(() => {})
    loadNotes()
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [])

  async function loadNotes() {
    try {
      const data = await authedFetch('/notes')
      setNotes(data)
    } catch {
      setError('Could not load notes.')
    }
  }

  async function handleCreate(event) {
    event.preventDefault()
    setError(null)
    setSubmitting(true)
    try {
      const created = await authedFetch('/notes', {
        method: 'POST',
        body: { title, content },
      })
      setNotes((current) => [...current, created])
      setTitle('')
      setContent('')
    } catch {
      setError('Could not create note.')
    } finally {
      setSubmitting(false)
    }
  }

  async function handleDelete(id) {
    try {
      await authedFetch(`/notes/${id}`, { method: 'DELETE' })
      setNotes((current) => current.filter((note) => note.id !== id))
    } catch {
      setError('Could not delete note.')
    }
  }

  return (
    <div className="notes-page">
      <p className="notes-greeting">{greeting || `Hello, ${username}`}</p>
      {error && <p className="notes-error">{error}</p>}

      <form className="notes-form" onSubmit={handleCreate}>
        <input
          placeholder="Title"
          value={title}
          onChange={(event) => setTitle(event.target.value)}
          required
        />
        <textarea
          placeholder="What's on your mind?"
          value={content}
          onChange={(event) => setContent(event.target.value)}
          required
        />
        <button type="submit" disabled={submitting}>
          {submitting ? 'Saving…' : 'Add note'}
        </button>
      </form>

      <div className="notes-list">
        {notes.length === 0 && <p className="notes-empty">No notes yet.</p>}
        {notes.map((note) => (
          <div className="note-card" key={note.id}>
            <h3>{note.title}</h3>
            <p>{note.content}</p>
            <button type="button" className="note-delete" onClick={() => handleDelete(note.id)}>
              Delete
            </button>
          </div>
        ))}
      </div>
    </div>
  )
}
