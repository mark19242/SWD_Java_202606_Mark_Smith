import reelVibeLogo from "../assets/reelvibe-logo.png"
import { Link, useLocation } from "react-router-dom"

export function PublicNav() {
  const location = useLocation()

  const onRegister = location.pathname === "/register"

  return (
    <header className="public-nav">
      <Link to="/" className="public-nav-logo" aria-label="ReelVibe home">
        <img src={reelVibeLogo} alt="ReelVibe" />
      </Link>

      <nav className="public-nav-links">
        <Link to="/">Home</Link>

        <Link
          to={onRegister ? "/login" : "/register"}
          className="public-nav-cta"
        >
          {onRegister ? "Log In" : "Create Account"}
        </Link>
      </nav>
    </header>
  )
}
