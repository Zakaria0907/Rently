import { render, screen } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import AdminDashboard from '../pages/Admin/AdminDashboard';

test('renders AdminDashboard without crashing', () => {
  render(
    <BrowserRouter>
      <AdminDashboard />
    </BrowserRouter>
  );
  expect(screen.getByText('Buildings')).toBeInTheDocument();
});
