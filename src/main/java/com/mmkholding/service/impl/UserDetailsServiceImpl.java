package com.mmkholding.service.impl;

import com.mmkholding.entity.Account;
import com.mmkholding.service.AccountServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountServiceInterface accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("User "+username+" not found");
        }
        if(account.getRoles() == null || account.getRoles().isEmpty()){
            throw new RuntimeException("User "+username+" has no roles");
        }

        Collection<GrantedAuthority> authorities = account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new User(
                account.getEmail(),
                account.getPassword(),
                account.isEnabled(),
                account.isCredentialsExpired(),
                account.isExpired(),
                account.isLocked(),
                authorities
        );
    }
}
