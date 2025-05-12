package spring.Module31.dao;



import spring.Module31.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    void delete(User user);
    User findById(Long id);
    void update(User user);
}
