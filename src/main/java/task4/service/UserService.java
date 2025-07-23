package task4.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import task4.exeption.EntityNotFoundExeption;
import task4.model.User;
import task4.repository.UserRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void create(User user) {
        User newUser = userRepository.save(user);
        log.info("User сохранен: " + newUser);
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundExeption(String.format("User с id = %s не найден.", id)));
        log.info("User найден: " + user);
        return user;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void update(User user) {
        User newUser = userRepository.save(user);
        log.info("User обновлен: " + newUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("Удален user с id = " + id);
    }
}
