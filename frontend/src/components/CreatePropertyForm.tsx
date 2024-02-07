import tw from "tailwind-styled-components";
import React, { useState } from "react";
import RentlyLogo from "../assets/icons/rently-logo.svg?react";
import FileUploadBtn from "./FileUploadBtn";

const createPropertyForm: React.FC<{ onFormSubmit: () => void }> = ({
	onFormSubmit,
}) => {
	// State for each form field
	const [propertyName, setPropertyName] = useState("");
	const [unitCount, setUnitCount] = useState("");
	const [parkingCount, setParkingCount] = useState("");
	const [lockerCount, setLockerCount] = useState("");
	const [streetAddress, setStreetAddress] = useState("");
	const [city, setCity] = useState("");
	const [province, setProvince] = useState("");
	const [postalCode, setPostalCode] = useState("");

	const handleSubmit = (event: React.FormEvent) => {
		event.preventDefault(); // Prevent the default form submission behavior
		onFormSubmit(); // Call the handler passed from the parent component

		// Reset state for each form field to its initial value
		setPropertyName("");
		setUnitCount("");
		setParkingCount("");
		setLockerCount("");
		setStreetAddress("");
		setCity("");
		setProvince("");
		setPostalCode("");
	};

	return (
		<>
			<MainContainer>
				<Container>
					<LogoContainer>
						<RentlyLogo className="w-auto h-12" />
					</LogoContainer>
					<Form onSubmit={handleSubmit}>
						<FormContainer>
							<Label htmlFor="propertyName">Property name</Label>
							<Input
								type="text"
								value={propertyName}
								onChange={(e) => setPropertyName(e.target.value)}
							/>
						</FormContainer>
						<FormContainer>
							<Label htmlFor="unitCount">Unit count</Label>
							<Input
								type="number"
								value={unitCount}
								onChange={(e) => setUnitCount(e.target.value)}
							/>
						</FormContainer>
						<FormContainer>
							<Label htmlFor="parkingCount">Parking count</Label>
							<Input
								type="number"
								value={parkingCount}
								onChange={(e) => setParkingCount(e.target.value)}
							/>
						</FormContainer>
						<FormContainer>
							<Label htmlFor="lockerCount">Locker count</Label>
							<Input
								type="number"
								value={lockerCount}
								onChange={(e) => setLockerCount(e.target.value)}
							/>
						</FormContainer>
						<FormContainer>
							<Label htmlFor="streetAddress">Street address</Label>
							<Input
								type="text"
								value={streetAddress}
								onChange={(e) => setStreetAddress(e.target.value)}
							/>
						</FormContainer>
						<FormContainer>
							<Label htmlFor="city">City</Label>
							<Input
								type="text"
								value={city}
								onChange={(e) => setCity(e.target.value)}
							/>
						</FormContainer>
						<FormContainer>
							<Label htmlFor="province">Province</Label>
							<Input
								type="text"
								value={province}
								onChange={(e) => setProvince(e.target.value)}
							/>
						</FormContainer>
						<FormContainer>
							<Label htmlFor="postalCode">Postal code</Label>
							<Input
								type="text"
								value={postalCode}
								onChange={(e) => setPostalCode(e.target.value)}
							/>
						</FormContainer>
						<FormContainer>
							<Label htmlFor="uploadFile">Upload condo file</Label>
							<FileUploadBtn />
						</FormContainer>
						<div className="mt-6">
							<Button>Create Property</Button>
						</div>
					</Form>
				</Container>
			</MainContainer>
		</>
	);
};

export default createPropertyForm;

const MainContainer = tw.div`h-screen flex items-center justify-center bg-gray-100`;

const FormContainer = tw.div`mt-4`;

const Container = tw.div`
    w-full max-w-sm p-6 m-auto mx-auto bg-white rounded-lg shadow-md
`;

const LogoContainer = tw.div`
    flex justify-center mx-auto
`;

const Label = tw.label`
    block text-sm text-gray-800
`;

const Input = tw.input`
    block w-full px-4 py-2 mt-2 text-gray-700 bg-white border rounded-lg focus:ring-blue-300 focus:outline-none focus:ring focus:ring-opacity-40
`;

const Button = tw.button`
    w-full px-6 py-2.5 text-sm font-medium tracking-wide text-white capitalize transition-colors duration-300 transform bg-gray-800 rounded-lg hover:bg-gray-700 focus:outline-none focus:ring focus:ring-gray-300 focus:ring-opacity-50
`;

const Form = tw.form`
    mt-6
`;
