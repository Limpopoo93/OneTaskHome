package dao;

import dto.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class UserDaolmpl implements UserDao {
    private static UserDaolmpl ourInstance;
    private static List<User> userList = null;
    private final static AtomicLong ID = new AtomicLong(1);

    public static UserDaolmpl getInstance() {
        if (ourInstance == null) {
            ourInstance = new UserDaolmpl();
        }
        return ourInstance;
    }

    private UserDaolmpl() {
        userList = new ArrayList<>();
    }

    @Override
    public void create(User user) {
        long id = ID.getAndIncrement();
        user.setId(id);
        userList.add(user);
    }

    @Override
    public void delete(User user) {
        userList.remove(user);
    }

    @Override
    public User get(int idUser) {
        for (User user : userList) {
            if (user.getId() == idUser) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getLogin(String login, String password) {
        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getUserList() {
        return userList;
    }

    @Override
    public boolean checkUser(String login, String password) {
        boolean result = false;
        for (User user : userList) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return result = true;
            }
        }
        return result;
    }

}
