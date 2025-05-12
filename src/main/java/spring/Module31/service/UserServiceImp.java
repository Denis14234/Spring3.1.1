package spring.Module31.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import spring.Module31.dao.UserDao;
import spring.Module31.model.User;


import java.util.List;
@Controller
public class UserServiceImp implements UserService {
    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getUserList(int count) {
        List<User> users = userDao.listUsers();
        if (count > users.size()) {
            count = users.size();
        }
        return users.subList(0, count);
    }
    @Override
    @Transactional
    public void addUser (User user) {
        userDao.add(user);
    }

    @Override
    @Transactional
    public void deleteUser (Long id) {
        User user = getUserById(id);
        if (user != null) {
            userDao.delete(user);
        }
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    @Transactional
    public void updateUser (User user) {
        userDao.update(user);
    }
}


