import tw from "tailwind-styled-components"
import React, { useRef } from 'react';
import Hero from "./Hero";
import Testimonials from "../components/Testimonials";
import Features from "../components/Features";
import { useIsVisible } from "../hooks/useIsVisible";
import Navbar from "../components/Navbar";
import Footer from "../components/Footer";

const Landing: React.FC = () => {

    const ref1 = useRef<HTMLDivElement>(null);
    const isVisible1 = useIsVisible(ref1);

    const ref2 = useRef<HTMLDivElement>(null);
    const isVisible2 = useIsVisible(ref2);

    const ref3 = useRef<HTMLDivElement>(null);
    const isVisible3 = useIsVisible(ref3);

    return (
        <>
        <Navbar />
            <div ref={ref1} className={`transition-opacity ease-in duration-1000 ${isVisible1 ? "opacity-100" : "opacity-0"}`}>
                <Hero />
            </div>
            <div ref={ref2} className={`transition-opacity ease-in duration-1000 ${isVisible2 ? "opacity-100" : "opacity-0"}`}>
                <Testimonials />
            </div>
            <div ref={ref3} className={`transition-opacity ease-in duration-1000 ${isVisible3 ? "opacity-100" : "opacity-0"}`}>
                <Features />
            </div>
        <Footer />
        </>

    );
};

export default Landing;