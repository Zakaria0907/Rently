import { Routes, Route } from "react-router-dom";
import Layout from "./pages/Layout";
import Landing from "./pages/Landing";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Welcome from "./pages/Welcome";
import RequireAuth from "./components/RequireAuth";
import PersistLogin from "./components/PersistLogin";
import Missing from "./components/Missing";
import Temp from "./components/Temp";
import { Roles } from "./types/enums";
import { ChakraProvider } from "@chakra-ui/react";
import CondoFees from "./components/EnterFees";
import { CartProvider } from "./context/CartContext";
import Fees from "./components/Fees";
import CreatePropertyTest from "./components/CreatePropertyTest";
import Reservations from "./components/Reservations";
import OwnerDashboard from "./pages/OwnerDashboard";
import PageTitle from "./components/PageTitle";
import Profile from "./pages/Profile";
import Calendar from "./pages/Calendar";
import FormElements from "./pages/Form/FormElements";
import FormLayout from "./pages/Form/FormLayout";
import Chart from "./pages/Chart";
import PropertyAndRentals from "./pages/PropertyAndRentals";

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

            {/* NEW OFFICIAL ROUTES, WORK INSIDE THESE! */}
            <Route
              path="/my-properties-and-rentals"
              element={
                <>
                  <PageTitle title="Properties & rentals | Rently Condo Management SAAS" />
                  <PropertyAndRentals />
                </>
              }
            />
            <Route
              path="/profile"
              element={
                <>
                  <PageTitle title="Profile | Rently Condo Management SAAS" />
                  <Profile />
                </>
              }
            />
            <Route
              path="/calendar"
              element={
                <>
                  <PageTitle title="Calendar | Rently Condo Management SAAS" />
                  <Calendar />
                </>
              }
            />

            <Route
              path="/chart"
              element={
                <>
                  <PageTitle title="Basic Chart | TailAdmin - Tailwind CSS Admin Dashboard Template" />
                  <Chart />
                </>
              }
            />

            {/* protected routes */}
            <Route element={<PersistLogin />}>
              <Route
                element={
                  <RequireAuth allowedRoles={[Roles.ADMIN, Roles.USER]} />
                }
              >
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
            <Route path="/condo-fees" element={<CondoFees />} />
            <Route
              path="/fees"
              element={
                <Fees
                  fees={[
                    { name: "Monthly Service Fee", amount: 249.99 },
                    { name: "Monthly Cleaning Fee", amount: 174.99 },
                    { name: "Rent", amount: 1199.99 },
                  ]}
                />
              }
            />
            <Route
              path="/forms/form-elements"
              element={
                <>
                  <PageTitle title="Form Elements | TailAdmin - Tailwind CSS Admin Dashboard Template" />
                  <FormElements />
                </>
              }
            />
            <Route
              path="/forms/form-layout"
              element={
                <>
                  <PageTitle title="Form Layout | TailAdmin - Tailwind CSS Admin Dashboard Template" />
                  <FormLayout />
                </>
              }
            />

            <Route
              path="/chart"
              element={
                <>
                  <PageTitle title="Basic Chart | TailAdmin - Tailwind CSS Admin Dashboard Template" />
                  <Chart />
                </>
              }
            />

            <Route
              path="/createPropertyTest"
              element={<CreatePropertyTest />}
            />
            <Route
              path="/reservations"
              element={<Reservations reservations={[]} />}
            />
            <Route path="/ownerDashboard" element={<OwnerDashboard />} />
          </Route>
        </Routes>
      </CartProvider>
    </ChakraProvider>
  );
}

export default App;
