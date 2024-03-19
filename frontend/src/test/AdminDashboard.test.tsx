import { render, screen } from '@testing-library/react';
import { BrowserRouter } from 'react-router-dom';
import AdminDashboard from '../pages/Admin/AdminDashboard';

describe('AdminDashboard Component', () => {
  test('renders cards correctly', () => {
    render(
      <BrowserRouter>
        <AdminDashboard />
      </BrowserRouter>
    );

    const buildingElement = screen.getByText('Buildings');
    const ownersElement = screen.getByText('Owners');
    const employeesElement = screen.getByText('Employees');

    expect(buildingElement).toBeInTheDocument();
    expect(ownersElement).toBeInTheDocument();
    expect(employeesElement).toBeInTheDocument();
  });

  test('renders correct counts for cards', () => {
    render(
      <BrowserRouter>
        <AdminDashboard />
      </BrowserRouter>
    );

    const buildingsCount = screen.getByText('2');
    const ownersCount = screen.getByText('10');
    const employeesCount = screen.getByText('7');

    expect(buildingsCount).toBeInTheDocument();
    expect(ownersCount).toBeInTheDocument();
    expect(employeesCount).toBeInTheDocument();
  });
});
