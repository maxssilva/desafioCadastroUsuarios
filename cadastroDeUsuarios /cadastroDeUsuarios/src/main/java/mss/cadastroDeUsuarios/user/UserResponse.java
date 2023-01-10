package mss.cadastroDeUsuarios.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mss.cadastroDeUsuarios.adress.AddressResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {


    private String name;
    private Date birthday;
    private List<AddressResponse> adressList = new ArrayList<>();

    public static UserResponse convert(User user) {
        return new UserResponse(
                user.getName(),
                user.getBirthday(),
                user.getAdressList().stream().map(AddressResponse::convert).collect(Collectors.toList())/*filtrar somente o resultado para o endereço, limmita a recursão infinita*/

        );
    }
}
