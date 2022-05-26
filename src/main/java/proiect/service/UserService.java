package proiect.service;

import proiect.entity.User;

public interface UserService {
     User findUser(String username);

     User save(User user);
}
