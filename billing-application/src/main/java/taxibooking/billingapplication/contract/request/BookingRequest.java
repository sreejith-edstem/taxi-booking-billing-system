package taxibooking.billingapplication.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import taxibooking.billingapplication.constant.Status;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequest {
    @NotBlank private String pickupLocation;
    @NotBlank private String dropOffLocation;
    private double fare;
    private Status status;
}
