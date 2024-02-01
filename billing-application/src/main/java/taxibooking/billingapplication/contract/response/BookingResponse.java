package taxibooking.billingapplication.contract.response;

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
public class BookingResponse {
    private long id;
    private String pickUpLocation;
    private String dropOffLocation;
    private double fare;
    @Enumerated(EnumType.STRING)
    private Status status;
}
