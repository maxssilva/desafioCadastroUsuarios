package mss.cadastroDeUsuarios.adress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCityAndStreetAndNumber(String city, String street, Integer number);

    Address findByCityAndStreetAndNumberAndUserId(String city, String street, Integer number, Long id);

    List<Address> findAllByUserId(Long userId);

    Address findByCityAndStreetAndNumberAndMainAddress(String city, String street, Integer number, Boolean mainAddress);
}

