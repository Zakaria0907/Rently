import { render, screen } from "@testing-library/react";
import AddUnitPopup from "../components/AddUnitPopup";

describe("AddUnitPopup Component", () => {
	beforeEach(() => {
		render(<AddUnitPopup closeModal={() => {}} userId={1} buildingId={1} />);
	});

	it("renders the modal title", () => {
		expect(screen.getByText("Add a new Unit")).toBeInTheDocument();
	});

	it("provides instructions for adding a unit", () => {
		expect(
			screen.getByText("This will add a unit to your unit list")
		).toBeInTheDocument();
	});

	it("renders input field labels", () => {
		expect(screen.getByText("Unit Name")).toBeInTheDocument();
		expect(screen.getByText("Address")).toBeInTheDocument();
		expect(screen.getByText("Unit Number")).toBeInTheDocument();
		expect(screen.getByText("Unit Status")).toBeInTheDocument();
		expect(screen.getByText("Unit Type")).toBeInTheDocument();
		expect(screen.getByText("Description")).toBeInTheDocument();
	});

	it("renders action buttons", () => {
		expect(screen.getByRole("button", { name: "Cancel" })).toBeInTheDocument();
		expect(screen.getByRole("button", { name: "Add" })).toBeInTheDocument();
	});
});
