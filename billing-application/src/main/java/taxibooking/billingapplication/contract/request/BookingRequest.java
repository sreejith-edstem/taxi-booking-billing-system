package taxibooking.billingapplication.contract.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String pickupLocation;
    private String dropOffLocation;
    private double fare;
    @Enumerated(EnumType.STRING)
    private Status status;
}
