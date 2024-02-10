package com.oaxaca.kitchen_staff_service.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class KitchenStaffDetails implements UserDetails {

    private KitchenStaff kitchenStaff;

    public KitchenStaffDetails(KitchenStaff kitchenStaff) {
        this.kitchenStaff = kitchenStaff;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Collections.singleton(new SimpleGrantedAuthority(getRole()));
    }

    @Override
    public String getPassword() {

        return kitchenStaff.getPassword();
    }

    @Override
    public String getUsername() {

        return kitchenStaff.getUsername();
    }

    public String getFirstName() {

        return kitchenStaff.getFirstName();
    }

    public String getLastName() {

        return kitchenStaff.getLastName();
    }

    public String getRole() {

        return kitchenStaff.getRole();
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
