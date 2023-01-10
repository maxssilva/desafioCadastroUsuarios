package mss.cadastroDeUsuarios.adress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private String street;
    private String cep;
    private Integer number;
    private String city;
    private Boolean mainAddress;


    public static AddressResponse convert(Address address) {
        return new AddressResponse(
                address.getStreet(),
                address.getCep(),
                address.getNumber(),
                address.getCity(),
                address.getMainAddress()
        );
    }
}
