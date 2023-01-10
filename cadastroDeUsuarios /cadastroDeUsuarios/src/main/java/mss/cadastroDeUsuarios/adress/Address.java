package mss.cadastroDeUsuarios.adress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mss.cadastroDeUsuarios.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressId")
    private Long id;
    @NotNull(message = "Informe o logradouro")
    @Column(name = "street")
    private String street;
    @NotNull(message = "Informe o CEP")
    @Column(name = "cep")
    private String cep;
    @NotNull(message = "Informe a número")
    @Column(name = "number")
    private Integer number;
    @NotNull(message = "Informe a cidade")
    @Column(name = "city")
    private String city;
    @NotNull(message = "Informe se é o endereço principal")
    @Column(name = "mainAdress")
    private Boolean mainAddress;

    @ManyToOne()
    @JoinColumn(name = "userId")
    private User user;



    public Address(Address address) {
        this.street = address.getStreet();
        this.city =  address.getCity();
        this.cep = address.getCep();
        this.number = address.getNumber();
        this.mainAddress =address.getMainAddress();
        this.user = address.getUser();
    }
}
