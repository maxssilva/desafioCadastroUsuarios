package mss.cadastroDeUsuarios.user;

import lombok.Getter;
import lombok.Setter;
import mss.cadastroDeUsuarios.adress.Address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type User.
 */

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Informe o nome")
    private String name;
    @NotNull(message = "Informe a data de nascimento")
    @Column(name = "birthday")
    private Date birthday;
    /*buscar significado/referencia anotações*/
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> adressList = new ArrayList<>();


//    public void addAdrees(Address address){
//        this.addressList.add(address);
//        address.setUser(this);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

}