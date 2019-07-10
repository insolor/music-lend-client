// Dummy connection implementation for user interface testing

package MusicLendClient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DummyConnection extends Connection {
    private Shop shop;
    private DummyUser user;

    private static Map<String, DummyUser> users;
    static {
        users = new HashMap<>();
        users.put("admin", new DummyUser("", Boolean.TRUE));
        users.put("user", new DummyUser("", Boolean.FALSE));
    }

    DummyConnection(String webserviceURL, String userName, String password) throws BadUserException {
        if(!users.containsKey(userName)) {
            throw new BadUserException();
        }

        user = users.get(userName);
        if(!user.checkPassword(password)) {
            throw new BadUserException();
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

    public void addToCart(Instrument instrument) {
        // TODO: check if the instrument is available
        shop.getAvailableInstruments().remove(instrument);
        // TODO: check if the instrument not in cart yet
        user.getInstrumentsInCart().add(instrument);
    }
}

class DummyUser extends User {
    private int passwordHash;

    DummyUser(String password, Boolean isAdmin) {
        super(isAdmin);
        this.passwordHash = password.hashCode();
    }

    public Boolean checkPassword(String password) { return password.hashCode() == passwordHash; }
}
