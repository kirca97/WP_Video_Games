package mk.kirca.games.demo.service;

import mk.kirca.games.demo.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
