import { render, screen } from "@testing-library/react";
import Login from "../pages/Login";
import { BrowserRouter } from "react-router-dom";

describe("Login Component", () => {
	beforeEach(() => {
		render(
			<BrowserRouter>
				<Login />
			</BrowserRouter>
		);
	});

	it("renders the 'Username' label", () => {
		expect(screen.getByText("Username")).toBeInTheDocument();
	});

	it("renders the 'Password' label", () => {
		expect(screen.getByText("Password")).toBeInTheDocument();
	});

	it("renders the 'Forget Password?' link", () => {
		expect(screen.getByText("Forget Password?")).toBeInTheDocument();
	});

	it("renders the 'Trust this device?' label", () => {
		expect(screen.getByText("Trust this device?")).toBeInTheDocument();
	});

	it("renders the 'Sign In' button", () => {
		expect(screen.getByText("Sign In")).toBeInTheDocument();
	});

	it("renders the 'or login with Social Media' text", () => {
		expect(screen.getByText("or login with Social Media")).toBeInTheDocument();
	});

	it("renders the 'Sign in with Google' button", () => {
		expect(screen.getByText("Sign in with Google")).toBeInTheDocument();
	});
});
