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
import UserSettings from "./pages/UserPages/UserSettings";
import RenterDashboard from "./pages/RenterPages/RenterDashboard";
import RenterSettings from "./pages/RenterPages/RenterSettings";
import CompanyFinancialReport from "./pages/CompanyPages/CompanyFinancialReport";
import CompanySettings from "./pages/CompanyPages/CompanySettings";
import AdminDashboard from "./pages/Admin/AdminDashboard";
import AdminBuildings from "./pages/Admin/AdminViewBuildings";
import AdminOwners from "./pages/Admin/AdminViewOwners";
import AdminViewOwnerDetail from "./pages/Admin/AdminViewOwnerDetail";
import ManageOwners from "./pages/CompanyPages/ManageOwners";
import ManageEmployees from "./pages/CompanyPages/ManageEmployees";
import CompanyRequests from "./pages/CompanyPages/CompanyRequests";
import EmployeeDashboard from "./pages/EmployeePages/EmployeeDashboard";
import EmployeeRequestDashboard from "./pages/EmployeePages/EmployeeRequestDashboard";
import CompanyEmployeeDetail from "./pages/CompanyPages/CompanyEmployeeDetail";
import OwnerRequestDashboard from "./pages/OwnerPages/OwnerRequestDashboard";
import CreateCompanyForm from "./components/CreateCompanyForm";
import CreateCompanyAdminForm from "./components/CreateCompanyAdminForm";
import GenerateKey from "./pages/CompanyPages/GenerateKey";
import ViewUnit from "./pages/CompanyPages/ViewUnit";


function App() {
  return (
    <ChakraProvider>
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
            <Route
              element={<RequireAuth allowedRoles={[Roles.ADMIN, Roles.USER]} />}
            >
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

              <Route
                path="/user-settings"
                element={
                  <>
                    <PageTitle title="User Settings | Rently Condo Management SAAS" />
                    <UserSettings />
                  </>
                }
              />
            </Route>

            {/* renter routes */}
            <Route
              element={
                <RequireAuth allowedRoles={[Roles.ADMIN, Roles.RENTER]} />
              }
            >
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

              <Route
                path="/renter-dashboard"
                element={
                  <>
                    <PageTitle title="User Dashboard | Rently Condo Management SAAS" />
                    <RenterDashboard />
                  </>
                }
              />

              <Route
                path="/renter-settings"
                element={
                  <>
                    <PageTitle title="User Settings | Rently Condo Management SAAS" />
                    <RenterSettings />
                  </>
                }
              />

              <Route
                path="/renter-calendar"
                element={
                  <>
                    <PageTitle title="Calendar | Rently Condo Management SAAS" />
                    <Calendar />
                  </>
                }
              />
            </Route>

            {/* owner routes*/}
            <Route
              element={
                <RequireAuth allowedRoles={[Roles.ADMIN, Roles.OWNER]} />
              }
            >
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

              <Route
                path="/owner-requests"
                element={
                  <>
                    <PageTitle title="Request | Rently Condo Management SAAS" />
                    <OwnerRequestDashboard />
                  </>
                }
              />
            </Route>

            {/*management company routes, once it is in the backend, we can enforce the role*/}
            <Route
              element={
                <RequireAuth allowedRoles={[Roles.ADMIN, Roles.COMPANY]} />
              }
            >
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
                path="/company-settings"
                element={
                  <>
                    <PageTitle title="Company Settings | Rently Condo Management SAAS" />
                    <CompanySettings />
                  </>
                }
              />

              <Route
                path="/company-financial-report"
                element={
                  <>
                    <PageTitle title="Calendar | Rently Condo Management SAAS" />
                    <CompanyFinancialReport />
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

              <Route
                path="/company-owners"
                element={
                  <>
                    <PageTitle title="Manage Owners | Rently Condo Management SAAS" />
                    <ManageOwners />
                  </>
                }
              />

              <Route
                path="/company-employees"
                element={
                  <>
                    <PageTitle title="Manage Employees | Rently Condo Management SAAS" />
                    <ManageEmployees />
                  </>
                }
              />

              <Route path="/employee-detail/:employeeId" element={<CompanyEmployeeDetail />} />

              <Route
                path="/company-requests"
                element={
                  <>
                    <PageTitle title="Requests | Rently Condo Management SAAS" />
                    <CompanyRequests />
                  </>
                }
              />
            

                <Route
                path="/manage-unit/:id"
                element={
                  <>
                    <PageTitle title="View Unit| Rently Condo Management SAAS" />
                    <ViewUnit />
                  </>
                }
              />

            </Route>

            {/*Admin Routes*/}
            <Route
              element={
                <RequireAuth allowedRoles={[Roles.ADMIN, Roles.COMPANY]} />
              }
            >
              <Route path="welcome" element={<Welcome />} />

              <Route
                path="/admin-dashboard"
                element={
                  <>
                    <PageTitle title="Admin Dashboard | Rently Condo Management SAAS" />
                    <AdminDashboard />
                  </>
                }
              />

              <Route
                path="/admin-profile"
                element={
                  <>
                    <PageTitle title="Profile | Rently Condo Management SAAS" />
                    <Profile />
                  </>
                }
              />

              <Route
                path="/admin-view-buildings"
                element={
                  <>
                    <PageTitle title="Admin Buildings | Rently Condo Management Administration" />
                    <AdminBuildings />
                  </>
                }
              />
              <Route
                path="/admin-view-owners"
                element={
                  <>
                    <PageTitle title="Admin Owners | Rently Condo Management Administration" />
                    <AdminOwners />
                  </>
                }
              />

              <Route
                path="/admin-owner-details/1"
                element={
                  <>
                    <PageTitle title="Owners Details | Rently Condo Management Administration" />
                    <AdminViewOwnerDetail />
                  </>
                }
              />
              <Route
                path="/create-company"
                element={
                  <>
                    <PageTitle title="Create Company | Rently Condo Management SAAS" />
                    <CreateCompanyForm />
                  </>
                }
              />
              <Route
                path="/create-company-admin"
                element={
                  <>
                    <PageTitle title="Create Company Admin | Rently Condo Management SAAS" />
                    <CreateCompanyAdminForm />
                  </>
                }
              />
            </Route>

            {/*Employee Routes*/}
            <Route
              element={
                <RequireAuth allowedRoles={[Roles.ADMIN, Roles.EMPLOYEE]} />
              }
            >
              <Route path="welcome" element={<Welcome />} />

              <Route
                path="/employee-dashboard"
                element={
                  <>
                    <PageTitle title="Employee Dashboard | Rently Condo Management SAAS" />
                    <EmployeeDashboard />
                  </>
                }
              />

              <Route
                path="/employee-profile"
                element={
                  <>
                    <PageTitle title="Profile | Rently Condo Management SAAS" />
                    <Profile />
                  </>
                }
              />

              <Route
                path="/employee-request-dashboard"
                element={
                  <>
                    <PageTitle title="Request Dashboard | Rently Condo Management Employee" />
                    <EmployeeRequestDashboard />
                  </>
                }
              />
            </Route>
          </Route>

          {/* catch all */}
          <Route path="*" element={<Missing />} />
          <Route
            path="/condo-upload"
            element={
              <>
                <PageTitle title="Condo Upload" />
                <AdminDashboard />
              </>
            }
          />
          {/* for testing/dev purposes */}
          <Route path="/temp" element={<Temp />} />

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

          <Route path="/createPropertyTest" element={<CreatePropertyTest />} />
          <Route
            path="/reservations"
            element={<Reservations reservations={[]} />}
          />
        </Route>
      </Routes>
    </ChakraProvider>
  );
}

export default App;
