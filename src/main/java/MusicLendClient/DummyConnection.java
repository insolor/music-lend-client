package MusicLendClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class BadUser extends Exception { }

public class DummyConnection extends Connection {
    private Shop shop;

    private static Map<String, String> user_pass;
    static {
        user_pass = new HashMap<>();
        user_pass.put("user", "");
        user_pass.put("admin", "");
    }

    DummyConnection(String webserviceURL, String userName, String password) throws BadUser {
        if(!user_pass.containsKey(userName) || !user_pass.get(userName).equals(password)) {
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
    public User getUser() {
        return new User();
    }
}
