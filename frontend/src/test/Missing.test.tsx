import { render, screen } from "@testing-library/react";
import Missing from "../components/Missing";

describe("Missing (404) Component", () => {
	beforeEach(() => {
		render(<Missing />);
	});

	it("displays the 404 error message", () => {
		expect(screen.getByText("404 - Page Not Found")).toBeInTheDocument();
	});

	it("informs the user that the page does not exist", () => {
		expect(
			screen.getByText("Oops! The page you are looking for does not exist.")
		).toBeInTheDocument();
	});
});
