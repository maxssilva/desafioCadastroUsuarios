package mss.cadastroDeUsuarios.adress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The type Address response.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private String street;
    private String cep;
    private Integer number;
    private String city;
    private Boolean mainAddress;

    /**
     * Convert address response.
     *
     * @param address the address
     * @return the address response
     */
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
