package MusicLendClient;

import java.math.BigDecimal;

public abstract class Connection {
    public abstract User getUser();
    public abstract Shop getShop();
    public abstract void addToCart(Instrument instrument);
    public abstract void removeFromCart(Instrument instrument);
    public abstract CartCalculationResult calculateCart(Cart cart);
    public abstract BigDecimal getPromocodePercent(String promocode);
    public abstract void pay(Cart cart);
}

class BadUserException extends Exception { }