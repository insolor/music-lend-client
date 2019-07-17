package MusicLendClient;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedList;

public class Cart {
    private Collection<Instrument> instruments;
    private String promocode;
    private Integer days;

    Cart() {
        this(new LinkedList<>(), "", 1);
    }

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

    void setPromocode(String promocode) {
        this.promocode = promocode;
    }

    Integer getDays() {
        return days;
    }

    void setDays(Integer days) {
        this.days = days;
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

    BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    BigDecimal getDiscountSum() {
        return discountSum;
    }

    BigDecimal getSumToBePaid() {
        return sumToBePaid;
    }
}