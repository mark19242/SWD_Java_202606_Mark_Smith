import { Navigate, Route, Routes, useLocation } from "react-router-dom"
import { AuthProvider, useAuth } from "./context/AuthContext"
import { ProtectedRoute } from "./components/ProtectedRoute"
import { NavBar } from "./components/NavBar"

import { LandingPage } from "./pages/LandingPage"
import { LoginPage } from "./pages/LoginPage"
import { RegisterPage } from "./pages/RegisterPage"
import { QuestionnairePage } from "./pages/QuestionnairePage"
import { RecommendationsPage } from "./pages/RecommendationsPage"
import { SavedMoviesPage } from "./pages/SavedMoviesPage"
import { AdminPage } from "./pages/AdminPage"

function Layout() {
  const { token } = useAuth()
  const location = useLocation()

  const showNav =
    token && location.pathname !== "/login" && location.pathname !== "/register"

  return (
    <>
      {showNav && <NavBar />}

      <Routes>
        <Route path="/" element={<LandingPage />} />

        <Route path="/login" element={<LoginPage />} />

        <Route path="/register" element={<RegisterPage />} />

        <Route
          path="/questionnaire"
          element={
            <ProtectedRoute>
              <QuestionnairePage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/recommendations"
          element={
            <ProtectedRoute>
              <RecommendationsPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/saved"
          element={
            <ProtectedRoute>
              <SavedMoviesPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/admin"
          element={
            <ProtectedRoute requireAdmin>
              <AdminPage />
            </ProtectedRoute>
          }
        />

        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </>
  )
}

function App() {
  return (
    <AuthProvider>
      <Layout />
    </AuthProvider>
  )
}

export default App
