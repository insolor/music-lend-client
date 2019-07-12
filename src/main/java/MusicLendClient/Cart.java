package MusicLendClient;

import java.math.BigDecimal;
import java.util.Collection;

public class Cart {
    private Collection<Instrument> instruments;
    private String promocode;
    private Integer days;

    Cart(Collection<Instrument> instruments, String promocode, Integer days) {
        this.instruments = instruments;
        this.promocode = promocode;
        this.days = days;
    }

    Collection<Instrument> getInstruments() {
        return instruments;
    }

    String getPromocode() {
        return promocode;
    }

    Integer getDays() {
        return days;
    }
}

class CartCalculationResult {
    private BigDecimal discountPercent;
    private BigDecimal discountSum;
    private BigDecimal sumToBePaid;

    CartCalculationResult(BigDecimal discountPercent, BigDecimal discountSum, BigDecimal sumToBePaid) {
        this.discountPercent = discountPercent;
        this.discountSum = discountSum;
        this.sumToBePaid = sumToBePaid;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public BigDecimal getDiscountSum() {
        return discountSum;
    }

    public BigDecimal getSumToBePaid() {
        return sumToBePaid;
    }
}