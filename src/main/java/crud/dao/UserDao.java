package crud.dao;

import crud.entity.User;

import java.util.List;

public interface UserDao {

    void save(User user);

    List<User> findAll();

    User findById(long id);

    void update(User user);

    void deleteById(long id);
}