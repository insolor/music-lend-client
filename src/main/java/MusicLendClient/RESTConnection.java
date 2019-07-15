package MusicLendClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.math.BigDecimal;

public class RESTConnection implements Connection {
    private HttpClient httpClient;
    private String token;

    RESTConnection(String webserviceUrl, String userName, String password) throws BadUserException, IOException {
        httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(webserviceUrl.concat("/auth"));
        request.addHeader("user", userName);
        request.addHeader("password", password);
        HttpResponse response = httpClient.execute(request);
        System.out.println(response.getEntity().getContent());
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public Shop getShop() {
        return null;
    }

    @Override
    public void addToCart(Instrument instrument) {

    }

    @Override
    public void removeFromCart(Instrument instrument) {

    }

    @Override
    public CartCalculationResult calculateCart(Cart cart) {
        return null;
    }

    @Override
    public BigDecimal getPromocodePercent(String promocode) {
        return null;
    }

    @Override
    public void pay(Cart cart) {

    }

    @Override
    public void returnInstrument(Instrument instrument) {

    }

    @Override
    public void returnAllInstruments() {

    }
}
