import { render, screen } from "@testing-library/react";
import AddPropertyPopup from "../components/AddPropertyPopup";

describe("AddPropertyPopup Component", () => {
	beforeEach(() => {
		render(<AddPropertyPopup closeModal={() => {}} company_id={1} />);
	});

	it("renders the modal title", () => {
		expect(screen.getByText("Add a new Building")).toBeInTheDocument();
	});

	it("instructs to add a property", () => {
		expect(
			screen.getByText("This will add a property to your property list")
		).toBeInTheDocument();
	});

	it("renders input field placeholders", () => {
		expect(
			screen.getByPlaceholderText("Description of the Building")
		).toBeInTheDocument();
	});

	it("renders action buttons", () => {
		expect(screen.getByRole("button", { name: "Cancel" })).toBeInTheDocument();
		expect(screen.getByRole("button", { name: "Add" })).toBeInTheDocument();
	});
});
