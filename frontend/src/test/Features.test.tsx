import { render, screen } from "@testing-library/react";
import Features from "../components/Features"; // Adjust the import path as necessary

describe("Features Component", () => {
	beforeEach(() => {
		render(<Features />);
	});

	it("displays the main heading", () => {
		expect(
			screen.getByText("Empowering Property Management with Smart Tools")
		).toBeInTheDocument();
	});

	it("displays the description text", () => {
		expect(
			screen.getByText(
				/Leverage our suite of tools to manage your properties more effectively and share updates effortlessly with residents./
			)
		).toBeInTheDocument();
	});

	it("renders feature list items", () => {
		expect(screen.getByText("Reduce operational overhead")).toBeInTheDocument();

		expect(
			screen.getByText("Streamline communication and save time")
		).toBeInTheDocument();
		expect(screen.getByText("Optimize expenses and")).toBeInTheDocument();
	});

	it("renders images", () => {
		expect(
			screen.getAllByRole("img", { name: "Image Description" }).length
		).toBe(3);
	});
});
