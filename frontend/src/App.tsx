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
import PageTitle from "./components/PageTitle";
import Profile from "./pages/Profile";
import Calendar from "./pages/Calendar";
import FormElements from "./pages/Form/FormElements";
import FormLayout from "./pages/Form/FormLayout";
import Chart from "./pages/Chart";
import PropertyAndRentals from "./pages/PropertyAndRentals";
import CompanyDashboard from "./pages/CompanyPages/CompanyDashboard";
import ManageBuilding from "./pages/CompanyPages/ManageBuilding";
import ManageUnits from "./pages/CompanyPages/ManageUnits";
import OwnerDashboard from "./pages/OwnerPages/OwnerDashboard";
import OwnerManageUnits from "./pages/OwnerPages/OwnerManageUnits";
import RoleBasedRedirect from "./utils/RoleBaseRedirect";
import UserDashboard from "./pages/UserPages/UserDashboard";

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

              {/* route to allow role-based redirection*/}
              <Route path="/login-success" element={<RoleBasedRedirect />} />

              {/* admin routes */}
              <Route element={<RequireAuth allowedRoles={[Roles.ADMIN]} />}>
                <Route path="welcomeAdmin" element={<Welcome />} />

                <Route
                  path="/admin-profile"
                  element={
                    <>
                      <PageTitle title="Profile | Rently Condo Management SAAS" />
                      <Profile />
                    </>
                  }
                />
              </Route>

              {/* user routes */}
              <Route element={<RequireAuth allowedRoles={[Roles.ADMIN, Roles.USER]} />}>
                <Route path="welcome" element={<Welcome />} />

                <Route
                  path="/user-profile"
                  element={
                    <>
                      <PageTitle title="Profile | Rently Condo Management SAAS" />
                      <Profile />
                    </>
                  }
                />

                <Route
                  path="/user-dashboard"
                  element={
                    <>
                      <PageTitle title="User Dashboard | Rently Condo Management SAAS" />
                      <UserDashboard />
                    </>
                  }
                />


              </Route>

              {/* renter routes */}
              <Route element={<RequireAuth allowedRoles={[Roles.ADMIN, Roles.RENTER]} />}>
                <Route path="welcomeAdmin" element={<Welcome />} />

                <Route
                  path="/renter-profile"
                  element={
                    <>
                      <PageTitle title="Profile | Rently Condo Management SAAS" />
                      <Profile />
                    </>
                  }
                />
              </Route>

              {/* owner routes*/}
              <Route element={<RequireAuth allowedRoles={[Roles.ADMIN, Roles.OWNER]} />}>
                <Route path="welcomeOwner" element={<Welcome />} />
                <Route
                  path="/owner-dashboard"
                  element={
                    <>
                      <PageTitle title="Owner Dashboard | Rently Condo Management SAAS" />
                      <OwnerDashboard />
                    </>
                  }
                />

                <Route
                  path="/manage-units"
                  element={
                    <>
                      <PageTitle title="Manage Units | Rently Condo Management SAAS" />
                      <OwnerManageUnits />
                    </>
                  }
                />

                <Route
                  path="/owner-profile"
                  element={
                    <>
                      <PageTitle title="Profile | Rently Condo Management SAAS" />
                      <Profile />
                    </>
                  }
                />

                <Route
                  path="/owner-calendar"
                  element={
                    <>
                      <PageTitle title="Calendar | Rently Condo Management SAAS" />
                      <Calendar />
                    </>
                  }
                />
              </Route>

              {/*management company routes, once it is in the backend, we can enforce the role*/}
              <Route element={<RequireAuth allowedRoles={[Roles.ADMIN, Roles.COMPANY]} />}>
                <Route path="welcome" element={<Welcome />} />

                <Route
                  path="/company-profile"
                  element={
                    <>
                      <PageTitle title="Profile | Rently Condo Management SAAS" />
                      <Profile />
                    </>
                  }
                />

                <Route
                  path="/company-dashboard"
                  element={
                    <>
                      <PageTitle title="Management Dashboard | Rently Condo Management SAAS" />
                      <CompanyDashboard />
                    </>
                  }
                />

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
                  path="/company-calendar"
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
                      <PageTitle title="Basic Chart | Rently Condo Management SAAS" />
                      <Chart />
                    </>
                  }
                />

                <Route
                  path="/forms/form-elements"
                  element={
                    <>
                      <PageTitle title="Form Elements | Rently Condo Management SAAS" />
                      <FormElements />
                    </>
                  }
                />
                <Route
                  path="/forms/form-layout"
                  element={
                    <>
                      <PageTitle title="Form Layout | Rently Condo Management SAAS" />
                      <FormLayout />
                    </>
                  }
                />
              </Route>

              <Route
                path="/manage-building"
                element={
                  <>
                    <PageTitle title="Manage Building | Rently Condo Management SAAS" />
                    <ManageBuilding />
                  </>
                }
              />

              <Route
                path="/manage-building/building/:buildingId"
                element={
                  <>
                    <PageTitle title="Manage Units | Rently Condo Management SAAS" />
                    <ManageUnits />
                  </>
                }
              />

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
              path="/createPropertyTest"
              element={<CreatePropertyTest />}
            />
            <Route
              path="/reservations"
              element={<Reservations reservations={[]} />}
            />
          </Route>
        </Routes>
      </CartProvider>
    </ChakraProvider>
  );
}

export default App;
