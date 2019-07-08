package MusicLendClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class BadUser extends Exception { }

public class DummyConnection extends Connection {
    private Shop shop;
    private User user;

    private static Map<String, User> users;
    static {
        users = new HashMap<>();
        users.put("admin", new User("", Boolean.TRUE));
        users.put("user", new User("", Boolean.FALSE));
    }

    DummyConnection(String webserviceURL, String userName, String password) throws BadUser {
        if(!users.containsKey(userName)) {
            throw new BadUser();
        }

        user = users.get(userName);
        if(!user.checkPassword(password)) {
            throw new BadUser();
        }

        shop = new Shop();
        shop.getAvailableInstruments()
                .add(new Instrument(1, "Test", "Some description", new BigDecimal("100.00")));
    }

    @Override
    public Shop getShop() {
        return shop;
    }

    @Override
    public User getUser() { return user; }
}
