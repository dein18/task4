package task4.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import task4.dao.UserDao;
import task4.model.User;

import java.util.List;

@Service
@Slf4j
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void create(User user) {
        userDao.save(user);
    }

    public User getUser(Long id) {
        return userDao.findById(id);
    }

    public List<User> getUsers() {
        return userDao.findAll();
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }
}
