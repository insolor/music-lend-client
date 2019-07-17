package MusicLendClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.stream.Collectors;

public class RESTConnection implements Connection {
    private String webserviceUrl;
    private HttpClient httpClient;
    private String token;
    private Shop shop;
    private DummyUser user;

    private class UserInfo {
        @SerializedName("username")
        String userName;
        String password;

        UserInfo(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }
    }

    RESTConnection(String webserviceUrl, String userName, String password) throws BadUserException,
            ConnectionErrorException, IOException {
        this.webserviceUrl = webserviceUrl;
        httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(webserviceUrl.concat("/auth"));
        request.addHeader("Content-Type", "application/json");

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(new UserInfo(userName, password));

        StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        request.setEntity(requestEntity);
        HttpResponse response = httpClient.execute(request);

        if(response.getStatusLine().getStatusCode() == 403) {
            throw new BadUserException();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        if(response.getStatusLine().getStatusCode() != 200) {
            String content = reader.lines().collect(Collectors.joining("\n"));
            throw new ConnectionErrorException(content);
        }

        token = reader.readLine();
    }

    @Override
    public User getUser() throws UnexpectedResultException, IOException {
        // GET /user/me
        URI uri;
        try {
            URIBuilder uriBuilder = new URIBuilder(webserviceUrl.concat("/user/me"));
            uriBuilder.addParameter("token", token);
            uri = uriBuilder.build();
        }
        catch (URISyntaxException ex) {
            // TODO: Handle somehow?
            return null;
        }

        HttpGet request = new HttpGet(uri);
        HttpResponse response = httpClient.execute(request);
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String content = reader.lines().collect(Collectors.joining("\n"));

        if(response.getStatusLine().getStatusCode() != 200) {
            throw new UnexpectedResultException(content);
        }

        return new Gson().fromJson(content, User.class);
    }

    @Override
    public Collection<Instrument> getAvailableInstruments() {
        // GET /instruments/available
        return null;
    }

    @Override
    public Collection<Instrument> getInstrumentsInUse() {
        // GET /instruments/inuse/me
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
