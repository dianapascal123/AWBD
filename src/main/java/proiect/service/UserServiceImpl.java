package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import proiect.entity.Authority;
import proiect.entity.User;
import proiect.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User findUser(String username) {
        return  userRepository.findUser(username);
    }

    @Override
    public User save(User user) {
        String plainPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setUsername(user.getEmail());

        Authority authority = new Authority();
        authority.setUser(user);
        authority.setAuthority("ROLE_CLIENT");
        authority.setEmail(user.getEmail());
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);

        user.setListAuthorities(authorities);
        return userRepository.save(user);
    }
}
