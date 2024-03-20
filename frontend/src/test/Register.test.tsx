import { render, screen } from "@testing-library/react";
import Register from "../pages/Register"; // Adjust the import path as necessary
import { BrowserRouter } from "react-router-dom";

describe("Register Component", () => {
	beforeEach(() => {
		render(
			<BrowserRouter>
				<Register />
			</BrowserRouter>
		);
	});

	it("renders the 'First name' label", () => {
		expect(screen.getByText("First name")).toBeInTheDocument();
	});

	it("renders the 'Last Name' label", () => {
		expect(screen.getByText("Last Name")).toBeInTheDocument();
	});

	it("renders the 'Email' label", () => {
		expect(screen.getByText("Email")).toBeInTheDocument();
	});

	it("renders the 'Phone Number' label", () => {
		expect(screen.getByText("Phone Number")).toBeInTheDocument();
	});

	it("renders the 'Password' label", () => {
		expect(screen.getByText("Password")).toBeInTheDocument();
	});

	it("renders the 'Confirm Password' label", () => {
		expect(screen.getByText("Confirm Password")).toBeInTheDocument();
	});
});
