package taxibooking.billingapplication.contract.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taxibooking.billingapplication.constant.Status;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
    private Long id;
    private String pickUpLocation;
    private String dropOffLocation;
    private double fare;
    private Status status;
}
