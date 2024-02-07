import { Routes, Route } from 'react-router-dom'
import Layout from './components/Layout.tsx'
import Landing from './components/Landing.tsx'
import Login from './components/Login.tsx'
import Register from './components/Register.tsx'
import Welcome from './components/Welcome.tsx'
import RequireAuth from './components/RequireAuth.tsx'
import PersistLogin from './components/PersistLogin.tsx'
import Missing from './components/Missing.tsx'
import Temp from './components/Temp.tsx'
import { Roles } from './types/enums.ts'
import { ChakraProvider } from '@chakra-ui/react'
import CondoFees from './components/EnterFees.tsx'
import { CartProvider } from './context/CartContext.tsx'
import Fees from './components/Fees.tsx'


function App() {
  return (
    <ChakraProvider>
      <CartProvider>
      <Routes>
        <Route path="/" element={<Layout />}>
          {/* public routes */}
          <Route index element={<Landing />} />
          <Route path="login" element={<Login />} />
          <Route path="register" element={<Register />} />

          {/* protected routes */}
          <Route element={<PersistLogin />}>
            <Route element={<RequireAuth allowedRoles={[Roles.ADMIN, Roles.USER]} />}>
              <Route path="welcome" element={<Welcome />} />
            </Route>

            <Route element={<RequireAuth allowedRoles={[Roles.ADMIN]} />}>
              <Route path="welcomeAdmin" element={<Welcome />} />
            </Route>
          </Route>

          {/* catch all */}
          <Route path="*" element={<Missing />} />

          {/* for testing/dev purposes */}
          <Route path="/temp" element={<Temp />} />
          <Route path="/condo-fees" element={<CondoFees/>} />
          <Route path="/fees" element={<Fees fees={[
            { name: 'Monthly Service Fee', amount: 249.99 },
            { name: 'Monthly Cleaning Fee', amount: 174.99 },
            { name: 'Rent', amount: 1199.99 }
          ]}/>} />
        </Route>
      </Routes>
      </CartProvider>
    </ChakraProvider>
  )
}

export default App;