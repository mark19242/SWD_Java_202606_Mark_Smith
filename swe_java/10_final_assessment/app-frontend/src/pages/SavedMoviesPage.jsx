import { useEffect, useState } from "react"
import { Link } from "react-router-dom"
import { useAuth } from "../context/AuthContext"

const POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

export function SavedMoviesPage() {
  const { authedFetch } = useAuth()

  const [savedMovies, setSavedMovies] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

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

                <button type="button" className="saved-movie-edit">
                  Manage Movie
                </button>
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
