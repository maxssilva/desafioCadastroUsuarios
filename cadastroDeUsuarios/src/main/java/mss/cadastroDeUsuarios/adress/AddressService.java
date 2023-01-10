package mss.cadastroDeUsuarios.adress;

import mss.cadastroDeUsuarios.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public Address saveAddress(Address address) {
        Address addressFound = addressRepository.findByCityAndStreetAndNumber(address.getCity(), address.getStreet(), address.getNumber());
        if (addressFound != null && addressFound.equals(address)) {
            throw new BusinessException("Este endereço já está atribuído ao usuário");
        }
        return addressRepository.save(address);
    }
    public Address getAddressByAddress(Address address, Long userId){
        return addressRepository.findByCityAndStreetAndNumberAndUserId(address.getCity(),address.getStreet(),address.getNumber(), userId);
    }
    public void setFalseMainAddress(Long AddressId) {
        Optional<Address> address = addressRepository.findById(AddressId);
        if (address.isPresent()) {
            address.get().setMainAddress(false);
        }
        else throw new BusinessException("Endereço não encontrado");
    }

   public List<Address> getAddressList(Long userId){
        return addressRepository.findAllByUserId(userId);
   }

    public Boolean validateAddressPresent(Long userId, Address address) {
       Optional<Address> addressFound = Optional.ofNullable(getAddressByAddress(address,userId));
        if (addressFound.isPresent()){
            return true;
        }
        else return false;
    }

}