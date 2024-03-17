import { render, screen } from '@testing-library/react';
import AdminDashboard from '../pages/Admin/AdminDashboard';

describe('AdminDashboard', () => {
    test('renders the card collection', () => {
        render(<AdminDashboard />);
        
        const cardTitles = screen.getAllByText(/(Buildings|Owners|Employees)/i);
        expect(cardTitles).toHaveLength(3);
    });
});
