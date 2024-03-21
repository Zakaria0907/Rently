import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import Hero from "../components/Hero";

describe("Hero Component", () => {
	beforeEach(() => {
		render(
			<BrowserRouter>
				<Hero />
			</BrowserRouter>
		);
	});

	it("renders the main heading", () => {
		expect(screen.getByText(/Manage Your Properties/i)).toBeInTheDocument();
	});

	it("renders the login link", () => {
		expect(screen.getByText(/Login/i)).toBeInTheDocument();
	});

	it("renders the signup link", () => {
		expect(screen.getByText(/Signup/i)).toBeInTheDocument();
	});

	it("displays the trusted by section", () => {
		expect(screen.getByText("Trusted by:")).toBeInTheDocument();
	});
});
