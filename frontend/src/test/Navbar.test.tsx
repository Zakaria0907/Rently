import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import Navbar from "../components/Navbar";

describe("Navbar Component", () => {
	beforeEach(() => {
		render(
			<BrowserRouter>
				<Navbar />
			</BrowserRouter>
		);
	});

	it("renders the logo text", () => {
		expect(screen.getByText("Rently")).toBeInTheDocument();
	});

	it("renders the toggle menu button", () => {
		expect(screen.getByLabelText("toggle menu")).toBeInTheDocument();
	});

	it("renders navbar menu items", () => {
		expect(screen.getByText("Landing")).toBeInTheDocument();
		expect(screen.getByText("Account")).toBeInTheDocument();
		expect(screen.getByText("About")).toBeInTheDocument();
		expect(screen.getByText("Contact Us")).toBeInTheDocument();
	});

	it("renders the login link when not authenticated", () => {
		expect(screen.getByText("Login")).toBeInTheDocument();
	});
});
