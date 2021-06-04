package com.example.restservice.security;

import com.example.restservice.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userDetailsServiceImpl")
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final PasswordEncoder encoder = new BCryptPasswordEncoder(12);

    private final Map<String, String> passwords = new HashMap<>();
    {
        passwords.put("admin", "admin");
        passwords.put("user", "user");
    }

    private final Map<String, Role> roles = new HashMap<>();
    {
        roles.put("admin", Role.ADMIN);
        roles.put("user", Role.USER);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return SecurityUser.builder()
                .username(email)
                .password(encoder.encode(passwords.get(email)))
                .authorities(roles.get(email).getAuthorities())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
    }
}
