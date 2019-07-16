package MusicLendClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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
        request.addHeader("Content-Type", "application/json");
        // TODO: make json from real data
        String jsonString = "{ \"user\": \"Михаил\", \"password\": \"\" }";
        StringEntity requestEntity = new StringEntity(jsonString, ContentType.APPLICATION_JSON);
        request.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(request);

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String content = rd.lines().collect(Collectors.joining("\n"));
        System.out.println(content);
        System.out.println("=================================================================================");
    }

    @Override
    public User getUser() {
        // GET /user/me
        return null;
    }

    @Override
    public Shop getShop() {
        // GET /instruments/available
        return null;
    }

    @Override
    public Cart getCart() {
        // GET /cart/my
        return null;
    }

    @Override
    public void addToCart(Instrument instrument) {
        // PUT /cart/my & instrument=ID
    }

    @Override
    public void removeFromCart(Instrument instrument) {
        // DELETE /cart/my & instrument=ID
    }

    @Override
    public void removeFromCartAll() {
        // DELETE /cart/my/all
    }

    @Override
    public CartCalculationResult calculateCart(Cart cart) {
        // GET /cart/my/calculation
        return null;
    }

    @Override
    public BigDecimal getPromocodePercent(String promocode) {
        // GET /promocode & text=TEXT
        return null;
    }

    @Override
    public void pay(Cart cart) {
        // PUT /cart/my/payment
    }

    @Override
    public void returnInstrument(Instrument instrument) {
        // DELETE /instruments/in_use/me & instrument=ID
    }

    @Override
    public void returnAllInstruments() {
        // DELETE /instruments/in_use/me/all
    }
}