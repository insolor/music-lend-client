package MusicLendClient;

public abstract class Connection {
    public abstract User getUser();
    public abstract Shop getShop();
    public abstract void addToCart(Instrument instrument);
    public abstract void removeFromCart(Instrument instrument);
    public abstract void calculateCart(Cart cart);
    public abstract void pay(Cart cart);
}

class BadUserException extends Exception { }