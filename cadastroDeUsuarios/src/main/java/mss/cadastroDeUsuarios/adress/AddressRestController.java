package mss.cadastroDeUsuarios.adress;

import mss.cadastroDeUsuarios.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressRestController {

    @Autowired
    AddressService addressService;
    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address addAddress(@Valid @RequestBody Address address) {
        return addressService.saveAddress(address);
    }

}