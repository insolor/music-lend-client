package MusicLendClient;

public abstract class Connection {
    public abstract User getUser();
    public abstract Shop getShop();
    public abstract void addToCart(Instrument instrument);
}

class BadUserException extends Exception { }