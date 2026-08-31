import { createContext, useCallback, useContext, useMemo, useState } from 'react'
import { apiFetch, ApiError } from '../api/client'

const AuthContext = createContext(null)

function decodeScope(token) {
  try {
    const payload = token.split('.')[1]
    const normalized = payload.replace(/-/g, '+').replace(/_/g, '/')
    const json = atob(normalized)
    const claims = JSON.parse(json)
    return typeof claims.scope === 'string' ? claims.scope.split(' ') : []
  } catch {
    return []
  }
}

export function AuthProvider({ children }) {
  const [token, setToken] = useState(null)
  const [username, setUsername] = useState(null)
  const [isAdmin, setIsAdmin] = useState(false)

  const login = useCallback(async (loginUsername, password) => {
    const data = await apiFetch('/auth/login', {
      method: 'POST',
      body: { username: loginUsername, password },
    })
    const authorities = decodeScope(data.token)
    setToken(data.token)
    setUsername(loginUsername)
    setIsAdmin(authorities.includes('ROLE_ADMIN'))
  }, [])

  const register = useCallback(async (registerUsername, password) => {
    await apiFetch('/auth/register', {
      method: 'POST',
      body: { username: registerUsername, password },
    })
  }, [])

  const logout = useCallback(() => {
    setToken(null)
    setUsername(null)
    setIsAdmin(false)
  }, [])

  const authedFetch = useCallback(
    async (path, options = {}) => {
      try {
        return await apiFetch(path, { ...options, token })
      } catch (err) {
        if (err instanceof ApiError && err.status === 401) {
          logout()
        }
        throw err
      }
    },
    [token, logout],
  )

  const value = useMemo(
    () => ({ token, username, isAdmin, login, register, logout, authedFetch }),
    [token, username, isAdmin, login, register, logout, authedFetch],
  )

  return <AuthContext value={value}>{children}</AuthContext>
}

export function useAuth() {
  const context = useContext(AuthContext)
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider')
  }
  return context
}
