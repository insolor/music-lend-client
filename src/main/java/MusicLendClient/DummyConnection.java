package MusicLendClient;

public class DummyConnection extends Connection {
    DummyConnection(String webserviceURL, String userName, String password) {

    }

    @Override
    public Shop getShop() {
        return new Shop();
    }

    @Override
    public User getUser() {
        return new User();
    }
}
