import React from "react";

const fileUploadBtn: React.FC = () => {
	return (
		<>
			<label
				htmlFor="dropzone-file"
				className="flex items-center px-3 py-2 mx-auto mt-2 text-center bg-white border-2 border-dashed rounded-lg cursor-pointer dark:border-gray-400"
			>
				<svg
					xmlns="http://www.w3.org/2000/svg"
					className="w-6 h-6 text-gray-300 dark:text-gray-500"
					fill="none"
					viewBox="0 0 24 24"
					stroke="currentColor"
					strokeWidth="1"
				>
					<path
						strokeLinecap="round"
						strokeLinejoin="round"
						d="M4 16v1a3 3 0 003 3h10a3 3 0 003-3v-1m-4-8l-4-4m0 0L8 8m4-4v12"
					/>
				</svg>

				<h2 className="mx-3 text-gray-400">Upload File</h2>

				<input id="dropzone-file" type="file" className="hidden" />
			</label>
		</>
	);
};

export default fileUploadBtn;
