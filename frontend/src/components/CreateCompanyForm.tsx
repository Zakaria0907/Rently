import React, { useState } from "react";
import useAxiosPrivate from "../hooks/useAxiosPrivate";
import { Box, Button, Input, FormControl, FormLabel } from "@chakra-ui/react";
import DefaultLayout from "../pages/DefaultLayout";

const CreateCompanyForm = () => {
  const [companyName, setCompanyName] = useState("");
  const axiosPrivate = useAxiosPrivate();
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    setIsLoading(true);
    try {
      const response = await axiosPrivate.post("/system-admin/create/company", {
        name: companyName,
      });
      console.log(response.data);
      alert("Company created successfully!");
    } catch (error) {
      console.error("Failed to create company:", error);
      alert("Failed to create company.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <DefaultLayout>
      <Box p={4}>
        <form onSubmit={handleSubmit}>
          <FormControl isRequired>
            <FormLabel htmlFor="companyName">Company Name</FormLabel>
            <Input
              id="companyName"
              placeholder="Enter company name"
              value={companyName}
              onChange={(e) => setCompanyName(e.target.value)}
            />
          </FormControl>
          <Button mt={4} colorScheme="blue" isLoading={isLoading} type="submit">
            Create Company
          </Button>
        </form>
      </Box>
    </DefaultLayout>
  );
};

export default CreateCompanyForm;
