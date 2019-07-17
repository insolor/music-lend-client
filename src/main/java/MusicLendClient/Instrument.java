package MusicLendClient;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Instrument {
    private Integer id;
    private String name;
    private String description;
    @SerializedName("price")
    private BigDecimal priceForDay;

    Instrument(Integer id, String name, String description, BigDecimal priceForDay) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceForDay = priceForDay;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPriceForDay() { return priceForDay; }
}
