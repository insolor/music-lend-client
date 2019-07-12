// Dummy connection implementation for user interface testing

package MusicLendClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class DummyConnection extends Connection {
    private Shop shop;
    private DummyUser user;

    private static Map<String, DummyUser> users  = new HashMap<String, DummyUser>() {{
        put("admin", new DummyUser("", Boolean.TRUE));
        put("user", new DummyUser("", Boolean.FALSE));
    }};

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
                .add(new Instrument(1, "Test", "Some description", BigDecimal.valueOf(100)));
    }

    @Override
    public Shop getShop() {
        return shop;
    }

    @Override
    public User getUser() { return user; }

    @Override
    public void addToCart(Instrument instrument) throws NullPointerException {
        if(instrument==null) {
            throw new NullPointerException("Null instrument");
        }

        // TODO: check if the instrument is available
        shop.getAvailableInstruments().remove(instrument);
        // TODO: check if the instrument not in cart yet
        user.getInstrumentsInCart().add(instrument);
    }

    @Override
    public void removeFromCart(Instrument instrument) throws NullPointerException {
        if(instrument==null) {
            throw new NullPointerException("Null instrument");
        }

        // TODO: check if the instrument not in cart
        user.getInstrumentsInCart().remove(instrument);
        // TODO: check if the instrument is available
        shop.getAvailableInstruments().add(instrument);
    }

    @Override
    public BigDecimal getPromocodePercent(String promocode) {
        return null;
    }

    @Override
    public CartCalculationResult calculateCart(Cart cart) {
        // set discount percent
        // set discount sum
        // set sum to pay
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Instrument instrument: cart.getInstruments()) {
            sum = sum.add(instrument.getPriceForDay());
        }
        sum = sum.multiply(BigDecimal.valueOf(cart.getDays()));

        BigDecimal discountPercent = null;
        String promocode = cart.getPromocode();
        if(!promocode.equals("")) {
            discountPercent = getPromocodePercent(promocode);
        }

        // if no valid promocode and there a there are 3 or more instruments then 5% discount
        if(discountPercent == null && cart.getInstruments().size() >= 3) {
            discountPercent = BigDecimal.valueOf(5);
        }

        // if there is still no discount set to 0
        if(discountPercent == null) {
            discountPercent = BigDecimal.valueOf(0);
        }

        // discountSum = sum*discountPercent/100
        BigDecimal discountSum = sum.multiply(discountPercent)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);

        BigDecimal sumToBePaid = sum.subtract(discountSum);

        return new CartCalculationResult(discountPercent, discountSum, sumToBePaid);
    }

    @Override
    public void pay(Cart cart) {
        // For dummy connection just move instruments from cart to instruments in use
        user.getInstrumentsInUse().addAll(user.getInstrumentsInCart());
        user.getInstrumentsInCart().clear();
    }
}

class DummyUser extends User {
    private int passwordHash;

    DummyUser(String password, Boolean isAdmin) {
        super(isAdmin);
        this.passwordHash = password.hashCode();
    }

    Boolean checkPassword(String password) {
        return password.hashCode() == passwordHash;
    }
}
