package taxibooking.billingapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import taxibooking.billingapplication.contract.request.BookingRequest;
import taxibooking.billingapplication.contract.response.BookingResponse;
import taxibooking.billingapplication.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/v1/user/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    @PostMapping("/{userId}/{taxiId}/create")
    public @ResponseBody BookingResponse createBooking(@PathVariable long userId, @PathVariable long taxiId, @RequestBody BookingRequest request){
        return this.bookingService.createBooking(userId,taxiId,request);
    }
    @GetMapping
    public @ResponseBody List<BookingResponse> viewBookingDetails(){
        return bookingService.viewBookingDetails();
    }
    @DeleteMapping("/{id}")
    public void cancelBooking(@PathVariable long id){
        bookingService.cancelBooking(id);
    }
}
