package MusicLendClient;

import java.math.BigDecimal;
import java.util.Collection;

public class Cart {
    private Collection<Instrument> instruments;
    private String promocode;
    private Integer days;
    private BigDecimal discountPercent;
    private BigDecimal discountSum;
    private BigDecimal sumToBePaid;

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

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountSum() {
        return discountSum;
    }

    public void setDiscountSum(BigDecimal discountSum) {
        this.discountSum = discountSum;
    }

    public BigDecimal getSumToBePaid() {
        return sumToBePaid;
    }
}
