import React from 'react';

interface FeeDetail {
    name: string;
    amount: number;
}

interface FeesProps {
    fees: FeeDetail[];
}

const Fees: React.FC<FeesProps> = ({ fees }) => {
    const beforeTax = fees.reduce((total, fee) => total + fee.amount, 0);
    const tax = beforeTax * 1.15;
    const afterTax = beforeTax + tax;

    return (
        <div className="mt-20 mr-10 ml-10 bg-gray-100 p-4 rounded-lg">
            <h2 className="text-xl font-bold text-gray-800">Fees Breakdown</h2>
            <div className="mt-4">
                {fees.map((fee) => (
                    <div key={fee.name} className="flex justify-between my-2">
                        <span>{fee.name}</span>
                        <span>${fee.amount.toFixed(2)}</span>
                    </div>
                ))}
                <div className="flex justify-between my-2">
                    <span>{"Tax"}</span>
                    <span>${tax.toFixed(2)}</span>
                </div>
                <div className="border-t mt-4 pt-4 flex justify-between">
                    <span className="font-semibold">Total Fees</span>
                    <span className="font-semibold">${afterTax.toFixed(2)}</span>
                </div>
            </div>
        </div>
    );
};

export default Fees;