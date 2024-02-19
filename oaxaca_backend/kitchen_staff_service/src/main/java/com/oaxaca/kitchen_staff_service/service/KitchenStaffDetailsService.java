package com.oaxaca.kitchen_staff_service.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;
import com.oaxaca.kitchen_staff_service.model.KitchenStaffDetails;
import com.oaxaca.kitchen_staff_service.repository.KitchenStaffRepository;


@Service
public class KitchenStaffDetailsService implements UserDetailsService{

    private KitchenStaffRepository kitchenStaffRepository;
    
    public KitchenStaffDetailsService(KitchenStaffRepository kitchenStaffRepository) {
        this.kitchenStaffRepository = kitchenStaffRepository;
       
    }

    public KitchenStaffDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<KitchenStaff> optionalUser = kitchenStaffRepository.findByUsername(username);

        if(optionalUser.isPresent()) {
            return new KitchenStaffDetails(optionalUser.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    
}
