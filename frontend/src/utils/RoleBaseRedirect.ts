import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import useAuth from '../hooks/useAuth';
import { Roles } from '../types/enums';

const RoleBasedRedirect = () => {
  const { auth } = useAuth(); // Assuming `useAuth` provides the current user's role
  const navigate = useNavigate();

  useEffect(() => {
    switch (auth.roles) {
      case Roles.ADMIN:
        navigate('/admin-profile');
        break;
      case Roles.USER:
        navigate('/user-profile');
        break;
      case Roles.RENTER:
        navigate('/renter-profile');
        break;
      case Roles.OWNER:
        navigate('/owner-profile');
        break;
      case Roles.COMPANY:
        navigate('/company-profile');
        break;
      case Roles.EMPLOYEE:
        navigate('/employee-profile');
        break;
      // Redirect to a default page if the role is unrecognized
      default:
        navigate('/'); // Adjust as needed
    }
  }, [auth.roles, navigate]);

  return null; // This component does not render anything itself
};

export default RoleBasedRedirect;