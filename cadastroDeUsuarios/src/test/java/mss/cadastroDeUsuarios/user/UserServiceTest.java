package mss.cadastroDeUsuarios.user;

import mss.cadastroDeUsuarios.adress.Address;
import mss.cadastroDeUsuarios.adress.AddressRepository;
import mss.cadastroDeUsuarios.exceptions.BusinessException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    private UserRepository userRepository;

    private Long id = 1L;
    private String name = "name";

    private final String street = "street";
    private final String cep = "cep";
    private final Integer number = 1;
    private final String city = "city";
    private Date date = mock(Date.class);
    private List<Address> addressList = Collections.singletonList(Mockito.mock(Address.class));
    private List<User> userList = Collections.singletonList(Mockito.mock(User.class));
    private User user = new User(1L, "nameUser", date, addressList);
    private final Address address = new Address(1L, street, cep, number, city, true, user);

    @Autowired
    private AddressRepository addressRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddUser() {
        when(userRepository.findByName(name)).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User user1 = userService.addUser(user);

        verify(userRepository, times(1)).findByName(anyString());
        verify(userRepository, times(1)).save(any(User.class));

        assertThat(user1).isEqualTo(user);
    }

    @Test
    public void shouldReturnBusinessExceptionWhenUserFound() {
        when(userRepository.findByName("nameUser")).thenReturn(user);

        assertThatThrownBy(() -> userService.addUser(user))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Já existe um usuário com esse nome");
    }

    @Test
    public void shouldReturnUserById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.ofNullable(user));

        User user1 = userService.findUserById(1L);

        verify(userRepository, times(1)).findById(1L);

        assertThat(user1).isEqualTo(user);

    }

    @Test
    public void shouldReturnBusinessExceptionWhenUserIsNotFound() {
        Exception exception = Assert.assertThrows(BusinessException.class, () -> userService.findUserById((long) 3L));
        Assert.assertEquals(exception.getMessage(), "Usuário não encontrado");
    }

    @Test
    public void shouldReturnListUsers() {
        when(userRepository.findAll()).thenReturn(userList);

        List<UserResponse> userListFound = userService.findAllUsers();

        verify(userRepository, times(1)).findAll();

        assertThat(userListFound).isNotEmpty();
    }

//    @Test
//    public void shouldReturnAddressToUser(){
//        when(addressRepository.findByCityAndStreetAndNumberAndUserId(city, street, number, id)).thenReturn(address);
//        when((userRepository.findById(anyLong()))).thenReturn(Optional.ofNullable(user));
//        when(address.getMainAddress()).thenReturn(true);
//        when(addressRepository.findAllByUserId(anyLong())).thenReturn(addressList);
//        when(userRepository.save(any(User.class))).thenReturn(user);
//
//        User userSaved = userService.addAddressToUser(1L, address);
//
//        assertThat(userSaved).isEqualTo(user);
//
//    }

}