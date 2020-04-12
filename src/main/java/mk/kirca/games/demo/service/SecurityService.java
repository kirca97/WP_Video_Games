package mk.kirca.games.demo.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
