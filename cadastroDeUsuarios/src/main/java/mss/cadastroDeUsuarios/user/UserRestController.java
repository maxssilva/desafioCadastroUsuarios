package mss.cadastroDeUsuarios.user;

import mss.cadastroDeUsuarios.adress.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The type User rest controller.
 */
@RestController
@RequestMapping("/user")
public class UserRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    /**
     * New user user.
     *
     * @param user the user
     * @return the user
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User newUser(@Valid @RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(UserResponse.convert(user));
        } else return ResponseEntity.ok().build();/**/
    }

    /**
     * User list.
     *
     * @return the list
     */
    @GetMapping /*RETOMAR AQUI*/
    public List<UserResponse> userList() {
        return userService.findAllUsers();
    }

    /**
     * Update user response entity.
     *
     * @param userId  the id
     * @param address the user
     * @return the response entity
     */
    @PutMapping("/{userId}")
    public ResponseEntity<User> AddAddressForUser(@Valid @PathVariable Long userId, @RequestBody Address address) {
        userService.addAddressToUser(userId, address);
        return ResponseEntity.ok().build();
    }

    /**
     * Update user response entity.
     *
     * @param id   the id
     * @param user the user
     * @return the response entity
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@Valid @PathVariable Long id, @RequestBody User user) {
        User userFound = userService.findUserById(id);
        if (userFound != null) {
            user.setId(id);
            user.setAdressList(userFound.getAdressList());
            userRepository.save(user);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}


