package kr.studygram.core;

import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import kr.studygram.content.Accounts;
import kr.studygram.utils.database.Database;
import org.bson.Document;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;

/**
 * Created by cynos07 on 2017-04-17.
 */
public class LoginHandler extends WebVerticle {
    private Database database;
    private Router router;

    private void initialize()
    {
        database=Database.getInstance();
        router=getRouter();
    }

    public void start() {
        initialize();
        router.post("/test").handler(this::test);
        router.post("/api/login").handler(this::requestLogin);
    }

    private void test(RoutingContext routingContext) {
        MultiMap attributes = routingContext.request().formAttributes();
        System.out.println(attributes.get("username"));
        System.out.println(attributes.get("passwd"));
    }

    private void requestLogin(RoutingContext routingContext) {
        routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Cookie, Origin, X-Requested-With, Content-Type");
        routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, PATCH, GET, DELETE, OPTIONS, HEAD, CONNECT");
        routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8080/*");
        routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8080/");
        routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8080");
//        routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//        JsonObject json = routingContext.getBodyAsJson();
//        System.out.println(json);
//        String accessToken = json.getString("accessToken");
        Cookie cookie = routingContext.getCookie("accessToken");
        System.out.println(cookie);
        String accessToken = cookie.getValue();

        HttpServerResponse response = routingContext.response();
        try {
            InputStream input = new URL("https://graph.facebook.com/me?access_token="+accessToken).openStream();
            Reader reader = new InputStreamReader(input, "UTF-8");
            Accounts account = (new Gson().fromJson(reader, Accounts.class));
            if(!checkRegistered(account.getId()))
            {
                createAccount(account.getId(), account.getName());
                response.end("registered");
                routingContext.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.end("logined");
    }

    private boolean checkRegistered(String id)
    {
        return database.isExist("accounts", "id", id);
    }

    private void createAccount(String id, String name)
    {
        Document account = new Document("id", id).append("name", name);
        database.insert("accounts", account);
        System.out.println("account created: "+name);
    }
}
