package com.GamePortal.Service;

import com.GamePortal.Entity.User;
import com.GamePortal.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserFinderService implements UserDetailsService {

    @Autowired
    IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = iUserRepository.findByUserName(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + userName));

        return user.map(MyUserDetailsService::new).get();
    }

}