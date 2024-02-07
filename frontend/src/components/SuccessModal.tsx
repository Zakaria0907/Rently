import React from "react";

// Define the props expected by SuccessModal
interface SuccessModalProps {
	onClose: () => void;
}

const SuccessModal: React.FC<SuccessModalProps> = ({ onClose }) => {
	return (
		<>
			<div
				id="hs-task-created-alert"
				className="fixed inset-0 z-80 overflow-x-hidden overflow-y-auto bg-black bg-opacity-50"
			>
				<div className="mt-7 opacity-100 duration-500 transition-all ease-out sm:max-w-lg sm:w-full m-3 mx-auto">
					<div className="relative flex flex-col bg-white shadow-lg rounded-xl dark:bg-gray-800">
						<div className="absolute top-2 right-2">
							<button
								type="button"
								className="flex justify-center items-center w-7 h-7 text-sm font-semibold rounded-lg border border-transparent text-gray-800 hover:bg-gray-100 disabled:opacity-50 disabled:pointer-events-none dark:text-white dark:border-transparent dark:hover:bg-gray-700 dark:focus:outline-none dark:focus:ring-1 dark:focus:ring-gray-600"
								onClick={onClose}
							>
								<span className="sr-only">Close</span>
								<svg
									className="flex-shrink-0 w-4 h-4"
									xmlns="http://www.w3.org/2000/svg"
									viewBox="0 0 24 24"
									fill="none"
									stroke="currentColor"
									strokeWidth="2"
									strokeLinecap="round"
									strokeLinejoin="round"
								>
									<path d="M18 6 6 18" />
									<path d="M6 6l12 12" />
								</svg>
							</button>
						</div>

						<div className="p-4 sm:p-10 text-center overflow-y-auto">
							<span className="mb-4 inline-flex justify-center items-center w-[46px] h-[46px] rounded-full border-4 border-green-50 bg-green-100 text-green-500 dark:bg-green-700 dark:border-green-600 dark:text-green-100">
								{/* Icon SVG */}
							</span>
							<h3 className="mb-2 text-xl font-bold text-gray-800 dark:text-gray-200">
								Property successfully created!
							</h3>

							<div className="mt-6 flex justify-center gap-x-4">
								<button
									type="button"
									className="py-2 px-3 inline-flex items-center gap-x-2 text-sm font-medium rounded-lg border border-gray-200 bg-white text-gray-800 shadow-sm hover:bg-gray-50 disabled:opacity-50 disabled:pointer-events-none dark:bg-slate-900 dark:border-gray-700 dark:text-white dark:hover:bg-gray-800 dark:focus:outline-none dark:focus:ring-1 dark:focus:ring-gray-600"
									onClick={onClose}
								>
									Close
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</>
	);
};

export default SuccessModal;
