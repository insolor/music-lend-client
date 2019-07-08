package MusicLendClient;

import java.math.BigDecimal;

public class DummyConnection extends Connection {
    private Shop shop;

    DummyConnection(String webserviceURL, String userName, String password) {
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
