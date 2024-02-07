import React, { useState } from "react";
import CreatePropertyForm from "./CreatePropertyForm";
import SuccessModal from "./SuccessModal";

const CreatePropertyTest: React.FC = () => {
	const [isModalOpen, setIsModalOpen] = useState(false);

	// This function could be called when the form is successfully submitted.
	const handleFormSubmit = () => {
		setIsModalOpen(true); // Open the SuccessModal
	};

	// This will be passed to the SuccessModal for the close button.
	const handleCloseModal = () => {
		setIsModalOpen(false); // Close the SuccessModal
	};

	// Debugging: Log the current state of isModalOpen
	console.log(isModalOpen); // Add this line

	return (
		<>
			<CreatePropertyForm onFormSubmit={handleFormSubmit} />
			{isModalOpen && <SuccessModal onClose={handleCloseModal} />}
		</>
	);
};

export default CreatePropertyTest;
