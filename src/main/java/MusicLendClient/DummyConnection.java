// Dummy connection implementation for user interface testing

package MusicLendClient;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class DummyConnection implements Connection {
    private Shop shop;
    private DummyUser user;

    private static Map<String, DummyUser> users  = new HashMap<String, DummyUser>() {{
        put("admin", new DummyUser("", Boolean.TRUE));
        put("user", new DummyUser("", Boolean.FALSE));
    }};

    private static Map<String, BigDecimal> promocodes = new HashMap<String, BigDecimal>() {{
        put("PROMOCODE", BigDecimal.valueOf(15));
    }};

    DummyConnection(String userName, String password) throws BadUserException {
        if(!users.containsKey(userName)) {
            throw new BadUserException();
        }

        user = users.get(userName);
        if(!user.checkPassword(password)) {
            throw new BadUserException();
        }

        shop = new Shop();
        shop.getAvailableInstruments().addAll(Arrays.asList(
            new Instrument(1, "Гитара аккустическая 6-струнная", "Some description", BigDecimal.valueOf(100)),
            new Instrument(2, "Электрогитара", "Еще какое-то описание", BigDecimal.valueOf(123)),
            new Instrument(3, "Барабанная установка", "Описание", BigDecimal.valueOf(321)),
            new Instrument(4, "Бас-гитара", "Описание бас-гитары", BigDecimal.valueOf(444))
        ));
    }

    public Shop getShop() {
        return shop;
    }

    public User getUser() {
        return user;
    }

    public void addToCart(Instrument instrument) throws NullPointerException {
        if(instrument==null) {
            throw new NullPointerException("Null instrument");
        }

        // TODO: check if the instrument is available
        shop.getAvailableInstruments().remove(instrument);
        // TODO: check if the instrument not in cart yet
        user.getInstrumentsInCart().add(instrument);
    }

    public void removeFromCart(Instrument instrument) throws NullPointerException {
        if(instrument==null) {
            throw new NullPointerException("Null instrument");
        }

        // TODO: check if the instrument not in cart
        user.getInstrumentsInCart().remove(instrument);
        // TODO: check if the instrument is available
        shop.getAvailableInstruments().add(instrument);
    }

    public BigDecimal getPromocodePercent(String promocode) {
        if(promocode.equals("")) {
            return BigDecimal.ZERO;
        }
        else {
            return promocodes.getOrDefault(promocode, BigDecimal.ZERO);
        }
    }

    public CartCalculationResult calculateCart(Cart cart) {
        // set discount percent
        // set discount sum
        // set sum to pay
        BigDecimal sum = BigDecimal.ZERO;
        for (Instrument instrument: cart.getInstruments()) {
            sum = sum.add(instrument.getPriceForDay());
        }
        sum = sum.multiply(BigDecimal.valueOf(cart.getDays()));

        BigDecimal discountPercent = BigDecimal.ZERO;
        String promocode = cart.getPromocode();
        if(!promocode.equals("")) {
            discountPercent = getPromocodePercent(promocode);
        }

        // if no valid promocode and there are 3 or more instruments then 5% discount
        if(discountPercent.equals(BigDecimal.ZERO) && cart.getInstruments().size() >= 3) {
            discountPercent = BigDecimal.valueOf(5);
        }

        // discountSum = sum*discountPercent/100
        BigDecimal discountSum = sum.multiply(discountPercent)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN);

        BigDecimal sumToBePaid = sum.subtract(discountSum);

        return new CartCalculationResult(discountPercent, discountSum, sumToBePaid);
    }

    public void pay(Cart cart) {
        // For dummy connection just move instruments from cart to instruments in use
        user.getInstrumentsInUse().addAll(user.getInstrumentsInCart());
        user.getInstrumentsInCart().clear();
    }

    public void returnInstrument(Instrument instrument) {
        // TODO: add validation
        user.getInstrumentsInUse().remove(instrument);
        shop.getAvailableInstruments().add(instrument);
    }

    public void returnAllInstruments() {
        shop.getAvailableInstruments().addAll(user.getInstrumentsInUse());
        user.getInstrumentsInUse().clear();
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
