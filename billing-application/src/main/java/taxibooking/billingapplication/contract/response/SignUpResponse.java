package taxibooking.billingapplication.contract.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private long id;
    private String name;
    private String email;
    private String password;
}
