package MusicLendClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.stream.Collectors;

public class RESTConnection implements Connection {
    private HttpClient httpClient;
    private String token;
    private Shop shop;
    private DummyUser user;

    RESTConnection(String webserviceUrl, String userName, String password) throws BadUserException, IOException {
        httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(webserviceUrl.concat("/auth"));
        request.addHeader("user", userName);
        request.addHeader("password", password);
        HttpResponse response = httpClient.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String content = rd.lines().collect(Collectors.joining("\n"));
        System.out.println(content);
        System.out.println("=================================================================================");
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
