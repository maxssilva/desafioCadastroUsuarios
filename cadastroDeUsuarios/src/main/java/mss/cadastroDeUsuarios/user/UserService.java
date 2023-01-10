package mss.cadastroDeUsuarios.user;

import mss.cadastroDeUsuarios.adress.Address;
import mss.cadastroDeUsuarios.adress.AddressRepository;
import mss.cadastroDeUsuarios.adress.AddressService;
import mss.cadastroDeUsuarios.exceptions.BusinessException;
import mss.cadastroDeUsuarios.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type User service.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;

    /**
     * Add user user.
     *
     * @param user the user
     * @return the user
     */
    public User addUser(User user) {
        User userFound = userRepository.findByName(user.getName());
        if (userFound != null) {
            throw new BusinessException("Já existe um usuário com esse nome");
        }
        return userRepository.save(user);
    }

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

//    public User update(Long userId, Address address) {
//        User userFound = findUserById(userId);
//        address.setUser(userFound);
//        userFound.getAdressList().add(new Address(address));
//        if(addressService.validateAddressPresent(address)){
//            setFalseAllMainAddress(userId);
//            return userRepository.save(userFound);
//        }
//        throw new BusinessException("Este endereço já está atribuido a este usuário");
//    }


    /*deixar passar um endereço secundário sem setar o endereço principal já cadastrado*/
    public User update(Long userId, Address address) {
        Boolean addressFound = addressService.validateAddressPresent(userId, address);
        Address address1 = addressService.getAddressByAddress(address, userId);
        User userFound = findUserById(userId);

        if (addressFound && address.getMainAddress() != address1.getMainAddress() && address.getMainAddress().equals(true)) {
            setFalseAllMainAddress(userId);
            address1.setMainAddress(true);
            return userRepository.save(userFound);
        }
        if (!addressFound) {
            address.setUser(userFound);
            userFound.getAdressList().add(new Address(address));
            setFalseAllMainAddress(userId);
            return userRepository.save(userFound);
        }

        throw new BusinessException("Este endereço já está atribuido a este usuário");
    }

    /**
     * Find all users list.
     *
     * @return the list
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Add user list.
     *
     * @param userList the user list
     * @return the list
     */
    public List<User> addUserList(List<User> userList) {
        userList.forEach(user -> userRepository.save(user));
        return userList;
    }

    public void setFalseAllMainAddress(Long userId) {
        List<Address> addressList = addressService.getAddressList(userId);
        addressList.forEach(address -> addressService.setFalseMainAddress(address.getId()));
    }


}
