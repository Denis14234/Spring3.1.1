package spring.Module31.service;



import spring.Module31.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserList(int count);
    void addUser (User user);
    void deleteUser (Long id);
    User getUserById(Long id);
    void updateUser (User user);
}
