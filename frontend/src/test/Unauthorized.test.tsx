import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import Unauthorized from "../components/Unauthorized";

describe("Unauthorized Component", () => {
	beforeEach(() => {
		render(
			<BrowserRouter>
				<Unauthorized />
			</BrowserRouter>
		);
	});

	it("renders the unauthorized message", () => {
		expect(screen.getByText("Unauthorized")).toBeInTheDocument();
		expect(
			screen.getByText("You do not have access to the requested page.")
		).toBeInTheDocument();
	});

	it("renders a 'Go Back' button", () => {
		expect(screen.getByRole("button", { name: "Go Back" })).toBeInTheDocument();
	});
});
