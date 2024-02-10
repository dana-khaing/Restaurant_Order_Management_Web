package com.oaxaca.waiter_service.model;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class WaiterDetails implements UserDetails {

    private Waiter waiter;

    public WaiterDetails(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return waiter.getPassword();
    }

    @Override
    public String getUsername() {
        return waiter.getUsername();
    }

    public String getFirstName() {
        return waiter.getFirstName();
    }

    public String getLastName() {
        return waiter.getLastName();
    }

    public String getRestaurantAddress() {
        return waiter.getRestaurantAddress();
    }

    public String getRestaurantName() {
        return waiter.getRestaurantName();
    }

    public String getManagerName() {
        return waiter.getManagerName();
    }

    @Override
    public boolean isAccountNonExpired() {
        
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
