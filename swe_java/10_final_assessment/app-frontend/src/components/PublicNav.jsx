import { Link } from "react-router-dom"
import reelVibeLogo from "../assets/reelvibe-logo.png"

export function PublicNav() {
  return (
    <header className="public-nav">
      <Link to="/" className="public-nav-logo" aria-label="ReelVibe home">
        <img src={reelVibeLogo} alt="ReelVibe" />
      </Link>

      <nav className="public-nav-links">
        <Link to="/">Home</Link>

        <Link to="/register" className="public-nav-cta">
          Create Account
        </Link>
      </nav>
    </header>
  )
}
