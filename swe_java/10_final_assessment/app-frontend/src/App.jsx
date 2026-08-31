import { Navigate, Route, Routes, useLocation } from 'react-router-dom'
import { AuthProvider, useAuth } from './context/AuthContext'
import { ProtectedRoute } from './components/ProtectedRoute'
import { NavBar } from './components/NavBar'
import { LoginPage } from './pages/LoginPage'
import { RegisterPage } from './pages/RegisterPage'
import { NotesPage } from './pages/NotesPage'
import { AdminPage } from './pages/AdminPage'

function Layout() {
  const { token } = useAuth()
  const location = useLocation()
  const showNav = token && location.pathname !== '/login' && location.pathname !== '/register'

  return (
    <>
      {showNav && <NavBar />}
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route
          path="/notes"
          element={
            <ProtectedRoute>
              <NotesPage />
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
        <Route path="*" element={<Navigate to="/notes" replace />} />
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
