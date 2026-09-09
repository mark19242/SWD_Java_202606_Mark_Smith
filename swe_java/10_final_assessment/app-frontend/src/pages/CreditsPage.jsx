import { Link } from "react-router-dom"
import tmdbLogo from "../assets/tmdb-logo.svg"

export function CreditsPage() {
  return (
    <main className="credits-page">
      <section className="credits-card">
        <p className="credits-eyebrow">Credits & Data Sources</p>

        <h1 className="credits-title display-font">ReelVibe Credits</h1>

        <p className="credits-description">
          ReelVibe uses movie data and images provided by TMDB.
        </p>

        <a
          className="tmdb-link"
          href="https://www.themoviedb.org"
          target="_blank"
          rel="noreferrer"
        >
          <img className="tmdb-logo" src={tmdbLogo} alt="TMDB" />
        </a>

        <p className="tmdb-notice">
          This product uses the TMDB API but is not endorsed or certified by
          TMDB.
        </p>

        <Link to="/" className="credits-back">
          ← Back to ReelVibe
        </Link>
      </section>
    </main>
  )
}
