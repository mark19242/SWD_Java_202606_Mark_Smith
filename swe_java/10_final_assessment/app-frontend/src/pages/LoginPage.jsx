import { useState } from "react"
import { Link, useLocation, useNavigate } from "react-router-dom"
import { useAuth } from "../context/AuthContext"
import { ApiError } from "../api/client"
import { MovieConcierge } from "../components/MovieConcierge"
import { PublicNav } from "../components/PublicNav"

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
    <main className="auth-page">
      <PublicNav />

      <section className="auth-login-area">
        <div className="auth-card">
          <MovieConcierge
            activeField={activeField}
            usernameLength={username.length}
          />

          <h1 className="auth-title display-font">Welcome Back</h1>

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
              {submitting ? "Logging in..." : "Log In"}
            </button>
          </form>

          <p className="auth-switch">
            New to ReelVibe? <Link to="/register">Create an account</Link>
          </p>
        </div>
      </section>
      <Link to="/" className="auth-back-link">
        <span aria-hidden="true">←</span>
        Back to ReelVibe
      </Link>
    </main>
  )
}
