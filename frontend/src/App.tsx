import { Routes, Route } from 'react-router-dom'
import Layout from './components/Layout.tsx'
import Landing from './components/Landing.tsx'
import Login from './components/Login.tsx'
import Welcome from './components/Welcome.tsx'
import RequireAuth from './components/RequireAuth.tsx'
import { Roles } from './types/enums.ts'

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        {/* public routes */}
        <Route index element={<Landing />} />
        <Route path="login" element={<Login />} />

        {/* protected routes */}
        <Route element={<RequireAuth allowedRoles={[Roles.ADMIN, Roles.USER]} />}>
          <Route path="welcome" element={<Welcome />} />
        </Route>

        <Route element={<RequireAuth allowedRoles={[Roles.ADMIN]} />}>
          <Route path="welcome" element={<Welcome />} />
        </Route>


      </Route>
    </Routes>
  )
}

export default App;