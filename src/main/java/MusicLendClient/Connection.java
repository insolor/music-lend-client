package MusicLendClient;

import java.math.BigDecimal;

abstract class Connection {
    abstract User getUser();
    abstract Shop getShop();
    abstract void addToCart(Instrument instrument);
    abstract void removeFromCart(Instrument instrument);
    abstract CartCalculationResult calculateCart(Cart cart);
    abstract BigDecimal getPromocodePercent(String promocode);
    abstract void pay(Cart cart);
}

class BadUserException extends Exception { }