package task4.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import task4.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class UserDaoImpl implements UserDao {

    private final DataSource dataSource;

    public UserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users(name) VALUES (?)";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.executeUpdate();
            log.info("User сохранен: " + user);
        } catch (SQLException e) {
            log.error("Ошибка при сохранении user - {}. {}", user, e.getMessage());
        }
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = User.builder()
                            .id(rs.getLong("id"))
                            .name(rs.getString("name"))
                            .build();
                    log.info("User найден: " + user);
                    return user;
                }
            }
        } catch (SQLException e) {
            log.error("Ошибка при поиске User с id = {} не найден. {}", id, e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = User.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build();
                users.add(user);
            }
            log.info("Users найдены: " + users.size());
        } catch (SQLException e) {
            log.error("Ошибка при поиске users. {}", e.getMessage());
        }
        return users;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET name = ? WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getId());
            stmt.executeUpdate();
            log.info("Обновлен user: " + user);
        } catch (SQLException e) {
            log.error("Ошибка при обновлении user с id = {}. {}", user, e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
            log.info("Удален user с id = " + id);
        } catch (SQLException e) {
            log.error("Ошибка при удалении user с id = {}. {}", id, e.getMessage());
        }
    }
}