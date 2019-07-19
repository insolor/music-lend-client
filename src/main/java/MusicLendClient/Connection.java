package MusicLendClient;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;

interface Connection {
    User getUser() throws UnexpectedResultException, IOException;
    Collection<Instrument> getAvailableInstruments() throws UnexpectedResultException, IOException;
    Collection<Instrument> getInstrumentsInUse() throws UnexpectedResultException, IOException;
    Cart getCart() throws UnexpectedResultException, IOException;
    void addToCart(Instrument instrument) throws UnexpectedResultException, IOException;
    void removeFromCart(Instrument instrument) throws UnexpectedResultException, IOException;
    void removeFromCartAll() throws UnexpectedResultException, IOException;
    CartCalculationResult calculateCart(Cart cart) throws UnexpectedResultException, IOException;
    BigDecimal getPromocodePercent(String promocode) throws UnexpectedResultException, IOException;
    void pay(Cart cart) throws UnexpectedResultException, IOException;
    void returnInstrument(Instrument instrument) throws UnexpectedResultException, IOException;
    void returnAllInstruments() throws UnexpectedResultException, IOException;

    class BadUserException extends Exception { }
    class ConnectionErrorException extends Exception {
        ConnectionErrorException(String message) {
            super(message);
        }
    }
    class UnexpectedResultException extends Exception {
        UnexpectedResultException(String message) {
            super(message);
        }
    }
}
