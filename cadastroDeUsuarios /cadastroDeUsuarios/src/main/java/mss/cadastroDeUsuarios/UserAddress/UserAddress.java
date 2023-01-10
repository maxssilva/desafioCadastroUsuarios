package mss.cadastroDeUsuarios.UserAddress;

import lombok.Data;
import lombok.NoArgsConstructor;
import mss.cadastroDeUsuarios.adress.Address;
import mss.cadastroDeUsuarios.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Data
@Table
@Deprecated
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @NotNull(message = "Aluno deve ser informado")
    @JoinColumn(name = "address_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Address address;


    public UserAddress(User user, Address address) {
        this.user = user;
        this.address = address;
    }
}
