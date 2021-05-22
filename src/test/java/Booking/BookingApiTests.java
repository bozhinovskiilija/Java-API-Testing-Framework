package Booking;

import io.restassured.response.Response;
import models.booking.api.AuthApi;
import models.booking.api.BookingApi;
import models.booking.payloads.*;
import org.testng.annotations.Test;

import java.util.Date;


import static org.assertj.core.api.Assertions.assertThat;

public class BookingApiTests {

    @Test
    public void getBookingShouldReturn200(){
        Response response = BookingApi.getBookings();

        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @Test
    public void getBookingIdShouldReturn200(){

        Response response = BookingApi.getBooking(1, "application/json");

        assertThat(response.getStatusCode()).isEqualTo(200);

    }

    @Test
    public void getBookingIdWithBadAcceptShouldReturn418(){
        Response response = BookingApi.getBooking(1, "text/plain");

        assertThat(response.getStatusCode()).isEqualTo(418);

       // Approvals.verify(response.getStatusCode());
    }

    @Test
    public void postBookingReturns200(){

        BookingDates dates = new BookingDates.Builder()
                .setCheckin(new Date())
                .setCheckout(new Date())
                .build();

        Booking payload = new Booking.Builder()
                .setFirstname("Mary")
                .setLastname("White")
                .setTotalprice(200)
                .setDepositpaid(true)
                .setBookingdates(dates)
                .setAdditionalneeds("None")
                .build();

        Response response = BookingApi.postBooking(payload);

        assertThat(response.getStatusCode()).isEqualTo(200);


    }

    @Test
    public void deleteBookingReturns201(){
        BookingDates dates = new BookingDates.Builder()
                .setCheckin(new Date())
                .setCheckout(new Date())
                .build();

        Booking payload = new Booking.Builder()
                .setFirstname("Mary")
                .setLastname("White")
                .setTotalprice(200)
                .setDepositpaid(true)
                .setBookingdates(dates)
                .setAdditionalneeds("None")
                .build();

        BookingResponse createdBookingResponse = BookingApi.postBooking(payload).as(BookingResponse.class);

        Auth auth = new Auth.Builder()
                .setUsername("admin")
                .setPassword("password123")
                .build();

        AuthResponse authResponse = AuthApi.postAuth(auth).as(AuthResponse.class);

        Response deleteResponse = BookingApi.deleteBooking(
                createdBookingResponse.getBookingid(),
                authResponse.getToken());

        assertThat(deleteResponse.getStatusCode()).isEqualTo(201);

        //Approvals.verify(deleteResponse.getStatusCode());
    }
}
