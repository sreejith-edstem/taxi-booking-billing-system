package taxibooking.billingapplication.contract.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TaxiRequest {
    @NotBlank private String driverName;
    @NotBlank private String licenseNumber;
    @NotBlank private String currentLocation;
}
