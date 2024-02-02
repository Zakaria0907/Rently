"use client";
import { TypewriterEffectSmooth } from "../other/typewriter-effect";
import { Link } from "react-router-dom";

export default function Hero() {
  const words = [
    {
      text: "Manage",
    },
    {
      text: "your",
    },
    {
      text: "properties",
    },
    {
      text: "with",
    },
    {
      text: "Rently.",
      className: "text-blue-500 dark:text-blue-500",
    },
  ];
  return (
    <div className="flex flex-col items-center justify-center h-[40rem]  ">
      <p className="text-neutral-600 dark:text-neutral-200 text-xs sm:text-base  ">
        The road to freedom starts from here
      </p>
      <TypewriterEffectSmooth words={words} />
      <div className="flex flex-col md:flex-row space-y-4 md:space-y-0 space-x-0 md:space-x-4">
        <button className="w-40 h-10 rounded-xl bg-black border dark:border-white border-transparent text-white text-sm">
          <Link to={'/register'}>Signup</Link>
        </button>
        <button className="w-40 h-10 rounded-xl bg-white text-black border border-black  text-sm">
        <Link to={'/login'}>Login</Link>
        </button>
      </div>
    </div>
  );
}
