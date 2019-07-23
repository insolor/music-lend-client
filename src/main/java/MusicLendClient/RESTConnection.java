package MusicLendClient;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class RESTConnection implements Connection {
    private String webserviceUrl;
    private static HttpClient httpClient = HttpClientBuilder.create().build();
    private String token;

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

    private static String httpGet(String webserviceUrl, String path, String token)
            throws IOException, UnexpectedResultException {
        return httpGet(webserviceUrl, path, token, null);
    }

    private static URI buildUri(String url, String path, Map<String, String> parameters) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url.concat(path));
        if(parameters != null) {
            for(Map.Entry<String, String> param: parameters.entrySet()) {
                uriBuilder.addParameter(param.getKey(), param.getValue());
            }
        }
        return uriBuilder.build();
    }

    private static String httpGet(String webserviceUrl, String path, String token, Map<String, String> parameters)
            throws IOException, UnexpectedResultException {
        URI uri;
        try {
            if(parameters==null) {
                parameters = new HashMap<>();
            }
            parameters.put("token", token);
            uri = buildUri(webserviceUrl, path, parameters);
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
            throw new UnexpectedResultException(response.getStatusLine().toString());
        }

        return content;
    }

    @Override
    public User getUser() throws UnexpectedResultException, IOException {
        // GET /user/me
        String content = httpGet(webserviceUrl, "/user/me", token);
        return new Gson().fromJson(content, User.class);
    }

    @Override
    public Collection<Instrument> getAvailableInstruments() throws UnexpectedResultException, IOException {
        // GET /instruments/available
        String content = httpGet(webserviceUrl, "/instruments/available", token);
        return new Gson().fromJson(content, new TypeToken<LinkedList<Instrument>>(){}.getType());
    }

    @Override
    public Collection<Instrument> getInstrumentsInUse() throws UnexpectedResultException, IOException {
        // GET /instruments/inuse/me
        String content = httpGet(webserviceUrl, "/instruments/inuse/me", token);
        return new Gson().fromJson(content, new TypeToken<LinkedList<Instrument>>(){}.getType());
    }

    @Override
    public Cart getCart() throws UnexpectedResultException, IOException {
        // GET /cart/my
        String content = httpGet(webserviceUrl, "/cart/my", token);
        return new Gson().fromJson(content, Cart.class);
    }

    @Override
    public void addToCart(Instrument instrument) throws UnexpectedResultException, IOException {
        // PUT /cart/my & instrument=ID
    }

    @Override
    public void removeFromCart(Instrument instrument) throws UnexpectedResultException, IOException {
        // DELETE /cart/my & instrument=ID
    }

    @Override
    public void removeFromCartAll() throws UnexpectedResultException, IOException {
        // DELETE /cart/my/all
    }

    @Override
    public void updateCartData(String promocode, Integer days) throws UnexpectedResultException, IOException {
        // PUT /cart/my promocode=PROMOCODE days=DAYS
    }

    @Override
    public CartCalculationResult calculateCart() throws UnexpectedResultException, IOException {
        // GET /cart/my/calculation
        String content = httpGet(webserviceUrl, "/cart/my/calculation", token);
        return new Gson().fromJson(content, CartCalculationResult.class);
    }

    @Override
    public BigDecimal getPromocodePercent(String promocode) throws UnexpectedResultException, IOException {
        // GET /promocode & text=TEXT
        Map<String, String> parameters = new HashMap<>();
        parameters.put("text", promocode);
        String content = httpGet(webserviceUrl, "/promocode", token, parameters);
        return new Gson().fromJson(content, BigDecimal.class);
    }

    @Override
    public void pay(Cart cart) throws UnexpectedResultException, IOException {
        // PUT /cart/my/payment
    }

    @Override
    public void returnInstrument(Instrument instrument) throws UnexpectedResultException, IOException {
        // DELETE /instruments/in_use/me & instrument=ID
    }

    @Override
    public void returnAllInstruments() throws UnexpectedResultException, IOException {
        // DELETE /instruments/in_use/me/all
    }
}
