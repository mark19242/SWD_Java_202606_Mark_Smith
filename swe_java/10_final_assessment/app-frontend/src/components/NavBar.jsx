import { Link, useNavigate } from "react-router-dom"
import { useAuth } from "../context/AuthContext"
import "./NavBar.css"

export function NavBar() {
  const { username, isAdmin, logout } = useAuth()
  const navigate = useNavigate()

  function handleLogout() {
    logout()
    navigate("/")
  }

  return (
    <nav className="navbar">
      <div className="navbar-links">
        <Link to="/questionnaire">Find My Vibe</Link>

        <Link to="/saved">Saved Movies</Link>

        {isAdmin && <Link to="/admin">Admin</Link>}
      </div>

      <div className="navbar-user">
        <span className="navbar-username">{username}</span>

        <button type="button" className="navbar-logout" onClick={handleLogout}>
          Log out
        </button>
      </div>
    </nav>
  )
}
