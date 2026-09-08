import { useEffect, useState } from "react"
import { Link, useLocation } from "react-router-dom"
import { useAuth } from "../context/AuthContext"

const POSTER_BASE_URL = "https://image.tmdb.org/t/p/w500"

export function RecommendationsPage() {
  const location = useLocation()
  const { authedFetch } = useAuth()

  const [savedMovieIds, setSavedMovieIds] = useState([])
  const [savingMovieId, setSavingMovieId] = useState(null)
  const [saveError, setSaveError] = useState(null)

  const response = location.state?.recommendationResponse

  const recommendations = response?.recommendations ?? []

  useEffect(() => {
    async function loadSavedMovies() {
      try {
        const savedMovies = await authedFetch("/saved-movies")

        setSavedMovieIds(
          savedMovies.map((savedMovie) => savedMovie.tmdbMovieId),
        )
      } catch {
        // Recommendations can still display if this check fails.
      }
    }

    loadSavedMovies()
  }, [authedFetch])

  if (!response) {
    return (
      <main className="recommendations-page">
        <section className="recommendations-empty">
          <p className="recommendations-eyebrow">No ReelVibe yet</p>

          <h1 className="display-font">Let&apos;s find your movie.</h1>

          <p>
            Complete the vibe questionnaire to generate your recommendations.
          </p>

          <Link to="/questionnaire" className="btn btn-primary">
            Find My Vibe
          </Link>
        </section>
      </main>
    )
  }
  async function handleSaveMovie(tmdbMovieId) {
    setSaveError(null)
    setSavingMovieId(tmdbMovieId)

    try {
      await authedFetch("/saved-movies", {
        method: "POST",
        body: {
          tmdbMovieId,
        },
      })

      setSavedMovieIds((current) => [...current, tmdbMovieId])
    } catch {
      setSaveError("ReelVibe couldn't save that movie. Please try again.")
    } finally {
      setSavingMovieId(null)
    }
  }
  return (
    <main className="recommendations-page">
      <header className="recommendations-header">
        <div>
          <p className="recommendations-eyebrow">ReelVibe Results</p>

          <h1 className="recommendations-title display-font">
            Your ReelVibe Picks
          </h1>

          <p className="recommendations-subtitle">
            Ranked for the mood and experience you chose.
          </p>
        </div>

        <Link to="/questionnaire" className="recommendations-again">
          ↻ Try Another Vibe
        </Link>
      </header>

      {saveError && <p className="recommendations-save-error">{saveError}</p>}

      <section className="movie-grid">
        {recommendations.map((recommendation, index) => {
          const movie = recommendation.movie

          const posterUrl = movie.poster_path
            ? `${POSTER_BASE_URL}${movie.poster_path}`
            : null

          return (
            <article className="movie-card" key={movie.id}>
              <div className="movie-poster-wrap">
                <span className="movie-rank">#{index + 1}</span>

                {posterUrl ? (
                  <img
                    className="movie-poster"
                    src={posterUrl}
                    alt={`${movie.title} poster`}
                  />
                ) : (
                  <div className="movie-poster-placeholder">🎬</div>
                )}
              </div>

              <div className="movie-card-content">
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

                <div className="movie-score-row">
                  <div>
                    <span className="score-label">ReelVibe Match</span>

                    <strong className="score-value">
                      {recommendation.finalScore}
                    </strong>
                  </div>

                  <div>
                    <span className="score-label">Vibe Score</span>

                    <strong className="score-value">
                      {recommendation.vibeScore}
                    </strong>
                  </div>
                </div>

                <button
                  type="button"
                  className="movie-save-button"
                  disabled={
                    savedMovieIds.includes(movie.id) ||
                    savingMovieId === movie.id
                  }
                  onClick={() => handleSaveMovie(movie.id)}
                >
                  {savedMovieIds.includes(movie.id)
                    ? "✓ Saved"
                    : savingMovieId === movie.id
                      ? "Saving..."
                      : "+ Save Movie"}
                </button>
              </div>
            </article>
          )
        })}
      </section>
    </main>
  )
}
