import { useEffect, useState } from 'react'
import { useAuth } from '../context/AuthContext'
import './AdminPage.css'

export function AdminPage() {
  const { authedFetch } = useAuth()
  const [users, setUsers] = useState([])
  const [error, setError] = useState(null)

  useEffect(() => {
    authedFetch('/admin/users')
      .then(setUsers)
      .catch(() => setError('Could not load users.'))
  }, [authedFetch])

  return (
    <div className="admin-page">
      <h1>Registered Users</h1>
      {error && <p className="admin-error">{error}</p>}
      <table className="admin-table">
        <thead>
          <tr>
            <th>Username</th>
            <th>Status</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.userName}>
              <td>{user.userName}</td>
              <td className={user.enabled ? 'admin-status-enabled' : 'admin-status-disabled'}>
                {user.enabled ? 'Enabled' : 'Disabled'}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  )
}
