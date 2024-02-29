import React, { useState } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";
import tw from "tailwind-styled-components";
import DefaultLayout from "../pages/DefaultLayout"; // Adjust path as necessary

// Custom Tailwind-styled components
const MainContainer = tw.div`flex flex-col items-center justify-center min-h-screen bg-gray-100`;
const Container = tw.div`w-full max-w-4xl mx-auto p-8 bg-white rounded-lg shadow-xl`;
const Title = tw.h2`text-2xl font-bold text-gray-800 mb-8 text-center`;
const CalendarContainer = tw.div`flex justify-center items-center`;

const FacilitiesAvailabilityCalendar = () => {
  const [value, setValue] = useState<Date>(new Date());

  const availableDates = ["2024-03-10", "2024-03-15", "2024-03-20"];

  const formatDate = (date: Date) => {
    return `${date.getFullYear()}-${(date.getMonth() + 1)
      .toString()
      .padStart(2, "0")}-${date.getDate().toString().padStart(2, "0")}`;
  };

  const isDateAvailable = (date: Date) => {
    return availableDates.includes(formatDate(date));
  };

  const handleDateChange = (value: Date | Date[]) => {
    setValue(Array.isArray(value) ? value[0] : value);
  };

  return (
    <DefaultLayout>
      <Container>
        <Title>Facilities Availability</Title>
        <CalendarContainer>
          <Calendar
            onChange={handleDateChange}
            value={value}
            tileClassName={({ date, view }) =>
              view === "month" && isDateAvailable(date)
                ? "bg-blue-200 hover:bg-blue-300"
                : ""
            }
            onClickDay={(value) => alert(`Clicked date: ${formatDate(value)}`)}
          />
        </CalendarContainer>
      </Container>
    </DefaultLayout>
  );
};

export default FacilitiesAvailabilityCalendar;
