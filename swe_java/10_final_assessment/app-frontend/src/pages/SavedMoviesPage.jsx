import { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import { useAuth } from "../context/AuthContext"

const POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

export function SavedMoviesPage() {
  const { authedFetch } = useAuth()

  const [savedMovies, setSavedMovies] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const [editingMovieId, setEditingMovieId] = useState(null)

  const [editForm, setEditForm] = useState({
    watchStatus: "WANT_TO_WATCH",
    personalRating: "",
    notes: "",
  })

  const [updatingMovieId, setUpdatingMovieId] = useState(null)
  const [deletingMovieId, setDeletingMovieId] = useState(null)
  const [actionError, setActionError] = useState(null)

  useEffect(() => {
    async function loadSavedMovies() {
      try {
        setError(null)

        const data = await authedFetch("/saved-movies/details")

        setSavedMovies(data)
      } catch {
        setError("ReelVibe couldn't load your saved movies.")
      } finally {
        setLoading(false)
      }
    }

    loadSavedMovies()
  }, [authedFetch])

  function handleManageMovie(savedMovie) {
    setActionError(null)

    if (editingMovieId === savedMovie.savedMovieId) {
      setEditingMovieId(null)
      return
    }

    setEditingMovieId(savedMovie.savedMovieId)

    setEditForm({
      watchStatus: savedMovie.watchStatus ?? "WANT_TO_WATCH",

      personalRating: savedMovie.personalRating ?? "",

      notes: savedMovie.notes ?? "",
    })
  }

  async function handleSaveChanges(savedMovieId) {
    setActionError(null)
    setUpdatingMovieId(savedMovieId)

    try {
      const updatedSavedMovie = await authedFetch(
        `/saved-movies/${savedMovieId}`,
        {
          method: "PUT",
          body: {
            watchStatus: editForm.watchStatus,

            personalRating:
              editForm.personalRating === ""
                ? null
                : Number(editForm.personalRating),

            notes: editForm.notes.trim() === "" ? null : editForm.notes.trim(),
          },
        },
      )

      setSavedMovies((current) =>
        current.map((item) =>
          item.savedMovie.savedMovieId === savedMovieId
            ? {
                ...item,
                savedMovie: updatedSavedMovie,
              }
            : item,
        ),
      )

      setEditingMovieId(null)
    } catch {
      setActionError("ReelVibe couldn't update this movie.")
    } finally {
      setUpdatingMovieId(null)
    }
  }

  async function handleRemoveMovie(savedMovieId) {
    setActionError(null)

    const confirmed = window.confirm(
      "Remove this movie from your saved collection?",
    )

    if (!confirmed) {
      return
    }

    setDeletingMovieId(savedMovieId)

    try {
      await authedFetch(`/saved-movies/${savedMovieId}`, {
        method: "DELETE",
      })

      setSavedMovies((current) =>
        current.filter((item) => item.savedMovie.savedMovieId !== savedMovieId),
      )

      setEditingMovieId(null)
    } catch {
      setActionError("ReelVibe couldn't remove this movie.")
    } finally {
      setDeletingMovieId(null)
    }
  }

  if (loading) {
    return (
      <main className="saved-movies-page">
        <section className="saved-movies-empty">
          <p className="saved-movies-eyebrow">Your Collection</p>

          <h1 className="display-font">Loading your movies...</h1>
        </section>
      </main>
    )
  }

  if (error) {
    return (
      <main className="saved-movies-page">
        <section className="saved-movies-empty">
          <p className="auth-error">{error}</p>

          <Link to="/questionnaire" className="btn btn-primary">
            Find My Vibe
          </Link>
        </section>
      </main>
    )
  }

  if (savedMovies.length === 0) {
    return (
      <main className="saved-movies-page">
        <section className="saved-movies-empty">
          <p className="saved-movies-eyebrow">Your Collection</p>

          <h1 className="display-font">Nothing saved yet.</h1>

          <p>Find your ReelVibe and save movies that catch your eye.</p>

          <Link to="/questionnaire" className="btn btn-primary">
            Find My Vibe
          </Link>
        </section>
      </main>
    )
  }

  return (
    <main className="saved-movies-page">
      <header className="saved-movies-header">
        <div>
          <p className="saved-movies-eyebrow">Your Collection</p>

          <h1 className="saved-movies-title display-font">Saved Movies</h1>

          <p className="saved-movies-subtitle">
            Movies you wanted to keep around.
          </p>
        </div>

        <Link to="/questionnaire" className="saved-movies-find">
          + Find Another Movie
        </Link>
      </header>
      {actionError && (
        <p className="saved-movies-action-error">{actionError}</p>
      )}
      <section className="saved-movie-grid">
        {savedMovies.map((item) => {
          const { savedMovie, movie } = item

          const posterUrl = movie.poster_path
            ? `${POSTER_BASE_URL}${movie.poster_path}`
            : null

          return (
            <article className="saved-movie-card" key={savedMovie.savedMovieId}>
              <div className="saved-movie-poster-wrap">
                {posterUrl ? (
                  <img
                    className="saved-movie-poster"
                    src={posterUrl}
                    alt={`${movie.title} poster`}
                  />
                ) : (
                  <div className="movie-poster-placeholder">🎬</div>
                )}

                <span className="saved-status-badge">
                  {formatWatchStatus(savedMovie.watchStatus)}
                </span>
              </div>

              <div className="saved-movie-content">
                <div className="movie-card-heading">
                  <h2>{movie.title}</h2>

                  {movie.vote_average != null && (
                    <span className="movie-rating">
                      ★ {movie.vote_average.toFixed(1)}
                    </span>
                  )}
                </div>

                {movie.release_date && (
                  <p className="movie-release">
                    {movie.release_date.slice(0, 4)}
                  </p>
                )}

                <p className="movie-overview">
                  {movie.overview || "No overview available."}
                </p>

                <div className="saved-movie-meta">
                  <div>
                    <span className="score-label">Watch Status</span>

                    <strong>{formatWatchStatus(savedMovie.watchStatus)}</strong>
                  </div>

                  <div>
                    <span className="score-label">My Rating</span>

                    <strong>
                      {savedMovie.personalRating
                        ? `${savedMovie.personalRating}/5`
                        : "Not rated"}
                    </strong>
                  </div>
                </div>

                {savedMovie.notes && (
                  <p className="saved-movie-notes">“{savedMovie.notes}”</p>
                )}

                <button
                  type="button"
                  className="saved-movie-edit"
                  onClick={() => handleManageMovie(savedMovie)}
                >
                  {editingMovieId === savedMovie.savedMovieId
                    ? "Close"
                    : "Manage Movie"}
                </button>
                {editingMovieId === savedMovie.savedMovieId && (
                  <div className="saved-movie-manager">
                    <div className="saved-manager-field">
                      <label htmlFor={`status-${savedMovie.savedMovieId}`}>
                        Watch Status
                      </label>

                      <select
                        id={`status-${savedMovie.savedMovieId}`}
                        value={editForm.watchStatus}
                        onChange={(event) =>
                          setEditForm((current) => ({
                            ...current,
                            watchStatus: event.target.value,
                          }))
                        }
                      >
                        <option value="WANT_TO_WATCH">Want to Watch</option>

                        <option value="WATCHING">Watching</option>

                        <option value="WATCHED">Watched</option>
                      </select>
                    </div>

                    <div className="saved-manager-field">
                      <label htmlFor={`rating-${savedMovie.savedMovieId}`}>
                        My Rating
                      </label>

                      <select
                        id={`rating-${savedMovie.savedMovieId}`}
                        value={editForm.personalRating}
                        onChange={(event) =>
                          setEditForm((current) => ({
                            ...current,
                            personalRating: event.target.value,
                          }))
                        }
                      >
                        <option value="">Not rated</option>

                        <option value="1">1 / 5</option>

                        <option value="2">2 / 5</option>

                        <option value="3">3 / 5</option>

                        <option value="4">4 / 5</option>

                        <option value="5">5 / 5</option>
                      </select>
                    </div>

                    <div className="saved-manager-field">
                      <label htmlFor={`notes-${savedMovie.savedMovieId}`}>
                        Notes
                      </label>

                      <textarea
                        id={`notes-${savedMovie.savedMovieId}`}
                        value={editForm.notes}
                        maxLength={2000}
                        rows={4}
                        placeholder="What did you think about this movie?"
                        onChange={(event) =>
                          setEditForm((current) => ({
                            ...current,
                            notes: event.target.value,
                          }))
                        }
                      />
                    </div>

                    <div className="saved-manager-actions">
                      <button
                        type="button"
                        className="saved-manager-save"
                        disabled={updatingMovieId === savedMovie.savedMovieId}
                        onClick={() =>
                          handleSaveChanges(savedMovie.savedMovieId)
                        }
                      >
                        {updatingMovieId === savedMovie.savedMovieId
                          ? "Saving..."
                          : "Save Changes"}
                      </button>

                      <button
                        type="button"
                        className="saved-manager-remove"
                        disabled={deletingMovieId === savedMovie.savedMovieId}
                        onClick={() =>
                          handleRemoveMovie(savedMovie.savedMovieId)
                        }
                      >
                        {deletingMovieId === savedMovie.savedMovieId
                          ? "Removing..."
                          : "Remove Movie"}
                      </button>
                    </div>
                  </div>
                )}
              </div>
            </article>
          )
        })}
      </section>
    </main>
  )
}

function formatWatchStatus(status) {
  switch (status) {
    case "WATCHING":
      return "Watching"

    case "WATCHED":
      return "Watched"

    case "WANT_TO_WATCH":
    default:
      return "Want to Watch"
  }
}
