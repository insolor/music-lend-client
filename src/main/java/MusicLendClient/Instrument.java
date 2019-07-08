package MusicLendClient;

import java.math.BigDecimal;

public class Instrument {
    private Integer id;
    private String description;
    private BigDecimal priceForDay;

    Instrument(Integer id, String description, BigDecimal priceForDay) {
        this.id = id;
        this.description = description;
        this.priceForDay = priceForDay;
    }

    public Integer getId() { return id; }
    public String getDescription() { return description; }
    public BigDecimal getPriceForDay() { return priceForDay; }
}
