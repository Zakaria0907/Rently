import { render, screen } from "@testing-library/react";
import CreatePropertyForm from "../components/CreatePropertyForm";
import userEvent from "@testing-library/user-event";

describe("CreatePropertyForm Component", () => {
	const mockOnFormSubmit = jest.fn();

	beforeEach(() => {
		mockOnFormSubmit.mockClear();

		render(<CreatePropertyForm onFormSubmit={mockOnFormSubmit} />);
	});

	it("renders input fields for property attributes", () => {
		expect(screen.getByText("Property name")).toBeInTheDocument();
		expect(screen.getByText("Unit count")).toBeInTheDocument();
		expect(screen.getByText("Parking count")).toBeInTheDocument();
		expect(screen.getByText("Locker count")).toBeInTheDocument();
		expect(screen.getByText("Street address")).toBeInTheDocument();
		expect(screen.getByText("City")).toBeInTheDocument();
		expect(screen.getByText("Province")).toBeInTheDocument();
		expect(screen.getByText("Postal code")).toBeInTheDocument();
	});

	it("renders the 'Create Property' button", () => {
		expect(
			screen.getByRole("button", { name: "Create Property" })
		).toBeInTheDocument();
	});

	it("calls onFormSubmit when form is submitted", async () => {
		const submitButton = screen.getByRole("button", {
			name: "Create Property",
		});
		await userEvent.click(submitButton);

		expect(mockOnFormSubmit).toHaveBeenCalled();
	});
});
