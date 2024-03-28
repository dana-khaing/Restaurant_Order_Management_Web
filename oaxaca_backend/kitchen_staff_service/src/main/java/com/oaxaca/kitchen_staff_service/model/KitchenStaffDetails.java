package com.oaxaca.kitchen_staff_service.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class KitchenStaffDetails implements UserDetails {

    private KitchenStaff kitchenStaff;
    /**
     * Constructs a KitchenStaffDetails object with the given kitchen staff member.
     *
     * @param kitchenStaff The kitchen staff member.
     */
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
    /**
     * Gets the first name of the kitchen staff member.
     *
     * @return The first name.
     */
    public String getFirstName() {

        return kitchenStaff.getFirstName();
    }
    /**
     * Gets the last name of the kitchen staff member.
     *
     * @return The last name.
     */
    public String getLastName() {

        return kitchenStaff.getLastName();
    }
    /**
     * Gets the role of the kitchen staff member.
     *
     * @return The role.
     */
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
