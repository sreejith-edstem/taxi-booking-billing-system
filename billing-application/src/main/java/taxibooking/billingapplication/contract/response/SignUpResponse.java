package taxibooking.billingapplication.contract.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private Long id;
    private String name;
    private String email;
}
