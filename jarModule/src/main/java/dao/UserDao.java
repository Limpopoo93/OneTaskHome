package dao;

import dto.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    User get(int idUser);
    User getLogin(String login, String password);
    void delete(User user);
    List<User> getUserList();
    boolean checkUser(String login, String password);
}
