import { useState } from "react"
import { Link, useNavigate } from "react-router-dom"
import { useAuth } from "../context/AuthContext"
import { ApiError } from "../api/client"
import { MovieConcierge } from "../components/MovieConcierge"
import { PublicNav } from "../components/PublicNav"

export function RegisterPage() {
  const { register } = useAuth()
  const navigate = useNavigate()

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
      await register(username, password)

      navigate("/login", {
        state: {
          registered: true,
        },
      })
    } catch (err) {
      if (err instanceof ApiError && err.status === 409) {
        setError("That username is already taken.")
      } else {
        setError("Registration failed. Is the backend running?")
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

          <h1 className="auth-title display-font">Join ReelVibe</h1>

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
                autoComplete="new-password"
                required
              />
            </div>

            <button type="submit" className="auth-submit" disabled={submitting}>
              {submitting ? "Creating account..." : "Create Account"}
            </button>
          </form>

          <p className="auth-switch">
            Already have an account? <Link to="/login">Log in</Link>
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
