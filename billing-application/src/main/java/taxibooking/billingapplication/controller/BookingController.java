package taxibooking.billingapplication.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import taxibooking.billingapplication.contract.request.BookingRequest;
import taxibooking.billingapplication.contract.response.BookingResponse;
import taxibooking.billingapplication.service.BookingService;

@RestController
@RequestMapping("/v1/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/{userId}/create")
    public @ResponseBody BookingResponse createBooking(
            @PathVariable long userId, @Valid @RequestBody BookingRequest request) {
        return this.bookingService.createBooking(userId, request);
    }

    @GetMapping("/{id}")
    public @ResponseBody BookingResponse viewBookingDetailsById(@PathVariable long id) {
        return bookingService.viewBookingDetailsById(id);
    }

    @GetMapping
    public @ResponseBody List<BookingResponse> viewAllBookingDetails() {
        return bookingService.viewAllBookingDetails();
    }

    @PostMapping("/{userId}/fare")
    public void fareCalculation(@PathVariable long userId, @RequestParam double distance) {
        bookingService.fareCalculation(userId, distance);
    }

    @PostMapping("/cancel/{id}")
    public long cancelBooking(@PathVariable long id) {
        bookingService.cancelBooking(id);
        return id;
    }
}
