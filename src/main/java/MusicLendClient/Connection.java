package MusicLendClient;

import java.math.BigDecimal;

interface Connection {
    User getUser();
    Shop getShop();
    Cart getCart();
    void addToCart(Instrument instrument);
    void removeFromCart(Instrument instrument);
    void removeFromCartAll();
    CartCalculationResult calculateCart(Cart cart);
    BigDecimal getPromocodePercent(String promocode);
    void pay(Cart cart);
    void returnInstrument(Instrument instrument);
    void returnAllInstruments();

    class BadUserException extends Exception { }
}

