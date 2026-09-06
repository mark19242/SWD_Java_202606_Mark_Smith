import { useState } from "react"
import { Link, useLocation, useNavigate } from "react-router-dom"
import { useAuth } from "../context/AuthContext"
import { ApiError } from "../api/client"
import "./AuthForm.css"
import { MovieConcierge } from "../components/MovieConcierge"

export function LoginPage() {
  const { login } = useAuth()
  const navigate = useNavigate()
  const location = useLocation()
  const [username, setUsername] = useState("")
  const [password, setPassword] = useState("")
  const [activeField, setActiveField] = useState("idle")
  const [error, setError] = useState(null)
  const [submitting, setSubmitting] = useState(false)

  async function handleSubmit(event) {
    event.preventDefault()
    setError(null)
    setSubmitting(true)
    try {
      await login(username, password)
      navigate("/questionnaire")
    } catch (err) {
      if (err instanceof ApiError && err.status === 401) {
        setError("Invalid username or password.")
      } else {
        setError("Login failed. Is the backend running?")
      }
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <div className="auth-page">
      <div className="auth-card">
        <MovieConcierge
          activeField={activeField}
          usernameLength={username.length}
        />
        <h1>Log in</h1>
        {location.state?.registered && (
          <p className="auth-success">Account created — log in below.</p>
        )}
        {error && <p className="auth-error">{error}</p>}
        <form onSubmit={handleSubmit}>
          <div className="auth-field">
            <label htmlFor="username">Username</label>
            <input
              id="username"
              value={username}
              onChange={(event) => setUsername(event.target.value)}
              onFocus={() => setActiveField("username")}
              onBlur={() => setActiveField("idle")}
              autoComplete="username"
              required
            />
          </div>
          <div className="auth-field">
            <label htmlFor="password">Password</label>
            <input
              id="password"
              type="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              onFocus={() => setActiveField("password")}
              onBlur={() => setActiveField("idle")}
              autoComplete="current-password"
              required
            />
          </div>
          <button type="submit" className="auth-submit" disabled={submitting}>
            {submitting ? "Logging in…" : "Log in"}
          </button>
        </form>
        <p className="auth-switch">
          Need an account? <Link to="/register">Register</Link>
        </p>
      </div>
    </div>
  )
}
