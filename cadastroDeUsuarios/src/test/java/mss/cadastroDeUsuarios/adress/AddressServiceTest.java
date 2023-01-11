package mss.cadastroDeUsuarios.adress;

import mss.cadastroDeUsuarios.user.User;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@SpringBootTest
public class AddressServiceTest {
    @InjectMocks
    AddressService addressService;
    @Mock
    private AddressRepository addressRepository;

    private final Long id = 1L;
    private final String street = "street";
    private final String cep = "cep";
    private final Integer number = 1;
    private final String city = "city";
    private final User user = Mockito.mock(User.class);
    private final Address address = new Address(1L, street, cep, number, city, true, user);
    private final List<Address> addressList = Collections.singletonList(Mockito.mock(Address.class));


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(addressRepository.findAllByUserId(1L)).thenReturn(addressList);

    }

    @Test
    void shouldReturnAddress() {
        when(addressRepository.findByCityAndStreetAndNumberAndUserId(anyString(), anyString(), anyInt(), anyLong())).thenReturn(address);

        Address addressFound = addressService.getAddressByAddress(address, id);

        verify(addressRepository, times(1)).findByCityAndStreetAndNumberAndUserId(city, street, number, id);

        Assertions.assertThat(addressFound).isNotNull();
    }

    @Test
    void shoulReturnAddressListWhenUserid() {
        when(addressRepository.findAllByUserId(anyLong())).thenReturn(addressList);

        List<Address> addressList = addressService.getAddressList(id);

        verify(addressRepository, times(1)).findAllByUserId(id);
        Assertions.assertThat(addressList).isNotEmpty();

    }


}