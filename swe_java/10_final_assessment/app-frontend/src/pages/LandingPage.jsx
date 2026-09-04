import { Link } from "react-router-dom"

export function LandingPage() {
  return (
    <main className="landing-page">
      <section className="landing-content">
        <p className="landing-eyebrow">Your mood. Your moment. Your movie.</p>

        <h1 className="landing-title display-font">
          Stop scrolling.
          <span> Start watching.</span>
        </h1>

        <p className="landing-description">
          Tell ReelVibe how you feel right now and what kind of experience you
          want. We&apos;ll find movies that match the moment.
        </p>

        <div className="landing-actions">
          <Link to="/login" className="btn btn-primary">
            Find My Vibe
          </Link>

          <Link to="/register" className="btn btn-secondary">
            Create Account
          </Link>
        </div>
      </section>

      <p className="landing-footer">
        What&apos;s your movie vibe in reel time?
      </p>
    </main>
  )
}
