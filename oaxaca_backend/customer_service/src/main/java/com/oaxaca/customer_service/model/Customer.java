package com.oaxaca.customer_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.Column;

@Entity
public class Customer extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "email", length = 30, nullable = false, unique = true)
    private String email;

    @Column(name = "username", length = 30, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 30 ,nullable = false)
    private String password;

    @Column(name = "address", length = 30)
    private String address;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "creditCard", length = 30)
    private String creditCard;

    public Customer() {
    }
    
   

    public Customer(String name, String email, String username, String password, String address, String phone, String creditCard) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.creditCard = creditCard;
    }

    public Customer(String email, String username, String password){
        this.email = email;
        this.username = username;
        this.password = password;
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


    public String getEmail() { 
        return email; 
    }


    public void setEmail(String email) { 
        this.email = email; 
    }


    public String getUsername() { 
        return username; 
    }

    public void setUsername(String username) { 
        this.username = username; 
    }

    public String getPassword() { 
        return password; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getAddress() { 
        return address; 
    }


    public void setAddress(String address) { 
        this.address = address; 
    }


    public String getPhone() { 
        return phone; 
    }


    public void setPhone(String phone) { 
        this.phone = phone; 
    }


    public String getCreditCard() { 
        return creditCard; 
    }

    public void setCreditCard(String creditCard) { 
        this.creditCard = creditCard; 
    }

    @Override
    public String toString() {
        return "Customer [address=" + address + ", creditCard=" + creditCard + ", email=" + email + ", id=" + id
                + ", name=" + name + ", password=" + password + ", phone=" + phone + ", username=" + username + "]";
    }



    public static Object withDefaultPasswordEncoder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withDefaultPasswordEncoder'");
    }



  


    

    
}
