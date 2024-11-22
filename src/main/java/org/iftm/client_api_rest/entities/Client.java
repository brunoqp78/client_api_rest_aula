package org.iftm.client_api_rest.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_client")
public class Client implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 120, nullable = false)
    private String name;
    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;
    @Column(name = "income", nullable = false)
    private Double income;
    @Column(name = "birthDate")
    private Instant birthDate;
    @Column(name = "children")
    private Integer children;

    //atributo que não persiste no banco de dados
    @Transient
    private String dadoTemporario;

    //@OneToOne
    @Transient
    private Address address;

    //@ManyToOne
    @Transient
    private Category category;

    //@ManyToMany
    @Transient
    private Set<Client> relatives = new HashSet<>();
    
    public Client() {
    }

    

    public Client(Long id, String name, String cpf, Double income, Instant birthDate, Integer children,
            Address address, Category category) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
        this.address = address;
        this.category = category;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }



    public Address getAddress() {
        return address;
    }



    public void setAddress(Address address) {
        this.address = address;
    }



    public Category getCategory() {
        return category;
    }



    public void setCategory(Category category) {
        this.category = category;
    }



    public Set<Client> getRelatives() {
        return relatives;
    }



    public void setRelatives(Set<Client> relatives) {
        this.relatives = relatives;
    }

    

    

    
    
    
}
