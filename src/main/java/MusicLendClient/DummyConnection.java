// Dummy connection implementation for user interface testing

package MusicLendClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class BadUser extends Exception { }

public class DummyConnection extends Connection {
    private Shop shop;
    private DummyUser user;

    private static Map<String, DummyUser> users;
    static {
        users = new HashMap<>();
        users.put("admin", new DummyUser("", Boolean.TRUE));
        users.put("user", new DummyUser("", Boolean.FALSE));
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
