import React, { useState } from "react";
import useAxiosPrivate from "../hooks/useAxiosPrivate";
import DefaultLayout from "../pages/DefaultLayout";

import {
  Box,
  Button,
  Input,
  FormControl,
  FormLabel,
  FormHelperText,
} from "@chakra-ui/react";

const CreateCompanyAdminForm = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    phoneNumber: "",
    bio: "",
    companyId: "",
  });
  const axiosPrivate = useAxiosPrivate();
  const [isLoading, setIsLoading] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData((prev) => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setIsLoading(true);
    try {
      const response = await axiosPrivate.post(
        "/system-admin/create/company-admin",
        formData
      );
      console.log(response.data);
      alert("Company admin account created successfully!");
    } catch (error) {
      console.error("Failed to create company admin:", error);
      alert("Failed to create company admin.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <DefaultLayout>
      <Box p={4}>
        <form onSubmit={handleSubmit}>
          <FormControl isRequired>
            <FormLabel htmlFor="email">Email</FormLabel>
            <Input
              id="email"
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
            />

            <FormLabel htmlFor="password">Password</FormLabel>
            <Input
              id="password"
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
            />

            <FormLabel htmlFor="firstName">First Name</FormLabel>
            <Input
              id="firstName"
              name="firstName"
              value={formData.firstName}
              onChange={handleChange}
            />

            <FormLabel htmlFor="lastName">Last Name</FormLabel>
            <Input
              id="lastName"
              name="lastName"
              value={formData.lastName}
              onChange={handleChange}
            />

            <FormLabel htmlFor="phoneNumber">Phone Number</FormLabel>
            <Input
              id="phoneNumber"
              name="phoneNumber"
              value={formData.phoneNumber}
              onChange={handleChange}
            />

            <FormLabel htmlFor="bio">Bio</FormLabel>
            <Input
              id="bio"
              name="bio"
              value={formData.bio}
              onChange={handleChange}
            />

            <FormLabel htmlFor="companyId">Company ID</FormLabel>
            <Input
              id="companyId"
              name="companyId"
              type="number"
              value={formData.companyId}
              onChange={handleChange}
            />

            <FormHelperText>
              Enter the ID of the company this admin will manage.
            </FormHelperText>
          </FormControl>
          <Button mt={4} colorScheme="blue" isLoading={isLoading} type="submit">
            Create Company Admin
          </Button>
        </form>
      </Box>
    </DefaultLayout>
  );
};

export default CreateCompanyAdminForm;
