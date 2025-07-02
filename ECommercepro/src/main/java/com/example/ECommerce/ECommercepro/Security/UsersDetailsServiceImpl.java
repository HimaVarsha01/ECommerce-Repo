package com.example.ECommerce.ECommercepro.Security;

import com.example.ECommerce.ECommercepro.Model.Users;
import com.example.ECommerce.ECommercepro.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UsersDetailsImpl(user);
    }
}
