import { render, screen } from "@testing-library/react";
import Testimonials from "../components/Testimonials";

describe("Testimonials Component", () => {
	beforeEach(() => {
		render(<Testimonials />);
	});

	it("renders the main heading", () => {
		expect(
			screen.getByText("Streamlining Condo Management")
		).toBeInTheDocument();
	});

	it("renders the introductory paragraph", () => {
		expect(
			screen.getByText(
				/Experience hassle-free condo management with instant setup and rapid response to your needs./i
			)
		).toBeInTheDocument();
	});

	it("displays a specific testimonial quote", () => {
		expect(
			screen.getByText(
				/The efficiency and ease of use have transformed our condo management experience. Highly recommend!/i
			)
		).toBeInTheDocument();
	});

	it("displays statistics about condo communities managed", () => {
		expect(screen.getByText("300+")).toBeInTheDocument();
		expect(screen.getByText(/condo communities managed/i)).toBeInTheDocument();
	});

	it("mentions a reduction in maintenance request processing time", () => {
		expect(screen.getByText("40%")).toBeInTheDocument();
		expect(
			screen.getByText(/reduction in maintenance request processing time/i)
		).toBeInTheDocument();
	});

	it("mentions a satisfaction rate from residents and property managers", () => {
		expect(screen.getByText("95%")).toBeInTheDocument();
		expect(
			screen.getByText(
				/satisfaction rate from residents and property managers/i
			)
		).toBeInTheDocument();
	});

	it("mentions support availability for emergency and non-emergency issues", () => {
		expect(screen.getByText("24/7")).toBeInTheDocument();
		expect(
			screen.getByText(
				/support availability for emergency and non-emergency issues/i
			)
		).toBeInTheDocument();
	});
});
