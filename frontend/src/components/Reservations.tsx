import React, { useState } from "react";
import tw from "tailwind-styled-components";
import { DateRangePicker } from "react-date-range";
import "react-date-range/dist/styles.css";
import "react-date-range/dist/theme/default.css";
import { addDays } from "date-fns";

interface ReservationDetail {
  facilityName: string;
  date: string;
}

interface ReservationsProps {
  reservations: ReservationDetail[];
}

const Reservations: React.FC<ReservationsProps> = () => {
  const [reservationRange, setReservationRange] = useState([
    {
      startDate: new Date(),
      endDate: addDays(new Date(), 7),
      key: "selection",
    },
  ]);

  const handleSelect = (ranges: any) => {
    setReservationRange([ranges.selection]);
  };

  const handleReserveClick = () => {
    alert(
      `Reservation made from ${reservationRange[0].startDate.toDateString()} to ${reservationRange[0].endDate.toDateString()}`
    );
    // send reservatoin data to the backend here for sprint 2
  };

  return (
    <MainContainer>
      <Container>
        <h2 className="text-xl font-bold text-gray-800 mb-4">
          Make a Reservation
        </h2>
        <DateRangeContainer>
          <DateRangePicker ranges={reservationRange} onChange={handleSelect} />
        </DateRangeContainer>
        <Button onClick={handleReserveClick}>Reserve</Button>
      </Container>
    </MainContainer>
  );
};

const MainContainer = tw.div`flex flex-col items-center justify-center bg-gray-100 py-8`;

const Container = tw.div`
    w-full max-w-2xl p-6 bg-white rounded-lg shadow-md
`;

const DateRangeContainer = tw.div`
    my-4
`;

const Button = tw.button`
    bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline
`;

export default Reservations;
