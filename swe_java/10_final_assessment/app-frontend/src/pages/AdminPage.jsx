import { useEffect, useState } from "react"
import { useAuth } from "../context/AuthContext"

export function AdminPage() {
  const { authedFetch } = useAuth()

  const [users, setUsers] = useState([])
  const [adminStatus, setAdminStatus] = useState(null)
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)

  const [togglingUsername, setTogglingUsername] = useState(null)
  const [actionError, setActionError] = useState(null)

  useEffect(() => {
    async function loadAdminData() {
      try {
        setError(null)

        const [statusData, userData] = await Promise.all([
          authedFetch("/admin/status"),
          authedFetch("/admin/users"),
        ])

        setAdminStatus(statusData)

        setUsers(
          [...userData].sort((a, b) => a.userName.localeCompare(b.userName)),
        )
      } catch {
        setError("ReelVibe couldn't load the admin dashboard.")
      } finally {
        setLoading(false)
      }
    }

    loadAdminData()
  }, [authedFetch])

  async function handleToggleUser(user) {
    if (user.userName === adminStatus?.username) {
      return
    }

    const nextEnabled = !user.enabled

    if (!nextEnabled) {
      const confirmed = window.confirm(`Disable ${user.userName}'s account?`)

      if (!confirmed) {
        return
      }
    }

    setActionError(null)
    setTogglingUsername(user.userName)

    try {
      await authedFetch(
        `/admin/users/${encodeURIComponent(user.userName)}/enabled`,
        {
          method: "PUT",
          body: {
            enabled: nextEnabled,
          },
        },
      )

      setUsers((current) =>
        current.map((currentUser) =>
          currentUser.userName === user.userName
            ? {
                ...currentUser,
                enabled: nextEnabled,
              }
            : currentUser,
        ),
      )
    } catch {
      setActionError(`ReelVibe couldn't update ${user.userName}.`)
    } finally {
      setTogglingUsername(null)
    }
  }

  const enabledUsers = users.filter((user) => user.enabled).length

  const disabledUsers = users.length - enabledUsers

  if (loading) {
    return (
      <main className="admin-page">
        <section className="admin-message">
          <p className="admin-eyebrow">ReelVibe Administration</p>

          <h1 className="display-font">Loading dashboard...</h1>
        </section>
      </main>
    )
  }

  return (
    <main className="admin-page">
      <header className="admin-header">
        <div>
          <p className="admin-eyebrow">ReelVibe Administration</p>

          <h1 className="admin-title display-font">Admin Dashboard</h1>

          {adminStatus && (
            <p className="admin-welcome">Welcome, {adminStatus.username}.</p>
          )}
        </div>

        <span className="admin-access-badge">Admin Access</span>
      </header>

      {error && <p className="admin-error">{error}</p>}

      {actionError && <p className="admin-error">{actionError}</p>}

      {!error && (
        <>
          <section className="admin-stats">
            <article className="admin-stat-card">
              <span>Registered Users</span>
              <strong>{users.length}</strong>
            </article>

            <article className="admin-stat-card">
              <span>Enabled</span>
              <strong>{enabledUsers}</strong>
            </article>

            <article className="admin-stat-card">
              <span>Disabled</span>
              <strong>{disabledUsers}</strong>
            </article>
          </section>

          <section className="admin-users-panel">
            <div className="admin-users-heading">
              <p className="admin-eyebrow">User Management</p>

              <h2 className="display-font">Registered Users</h2>
            </div>

            <div className="admin-table-wrap">
              <table className="admin-table">
                <thead>
                  <tr>
                    <th>Username</th>
                    <th>Account Status</th>
                    <th>Action</th>
                  </tr>
                </thead>

                <tbody>
                  {users.map((user) => {
                    const isCurrentAdmin =
                      user.userName === adminStatus?.username

                    const isUpdating = togglingUsername === user.userName

                    return (
                      <tr key={user.userName}>
                        <td>{user.userName}</td>

                        <td>
                          <span
                            className={
                              user.enabled
                                ? "admin-status admin-status-enabled"
                                : "admin-status admin-status-disabled"
                            }
                          >
                            {user.enabled ? "Enabled" : "Disabled"}
                          </span>
                        </td>

                        <td>
                          {isCurrentAdmin ? (
                            <span className="admin-current-user">
                              Current Admin
                            </span>
                          ) : (
                            <button
                              type="button"
                              className={
                                user.enabled
                                  ? "admin-user-action admin-user-disable"
                                  : "admin-user-action admin-user-enable"
                              }
                              disabled={isUpdating}
                              onClick={() => handleToggleUser(user)}
                            >
                              {isUpdating
                                ? "Updating..."
                                : user.enabled
                                  ? "Disable"
                                  : "Enable"}
                            </button>
                          )}
                        </td>
                      </tr>
                    )
                  })}
                </tbody>
              </table>
            </div>
          </section>
        </>
      )}
    </main>
  )
}
