package taxibooking.billingapplication.contract.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateAccountRequest {
    private double accountBalance;
}
