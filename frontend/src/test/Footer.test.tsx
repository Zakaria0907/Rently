import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import Footer from "../components/Footer";

describe("Footer Component", () => {
	beforeEach(() => {
		render(
			<BrowserRouter>
				<Footer />
			</BrowserRouter>
		);
	});

	it("renders navigation links", () => {
		expect(screen.getByText("Home")).toBeInTheDocument();
		expect(screen.getByText("About")).toBeInTheDocument();
		expect(screen.getByText("Teams")).toBeInTheDocument();
		expect(screen.getByText("Privacy")).toBeInTheDocument();
		expect(screen.getByText("Cookies")).toBeInTheDocument();
	});

	it("displays the rights text", () => {
		expect(screen.getByText("SOEN-390 Mini-Capstone")).toBeInTheDocument();
	});
});
