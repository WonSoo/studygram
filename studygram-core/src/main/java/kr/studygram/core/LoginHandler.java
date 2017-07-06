package kr.studygram.core;

import com.google.gson.Gson;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;
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
        router.post("/api/login").handler(this::requestLogin);
    }

    private void requestLogin(RoutingContext routingContext) {
        System.out.println("requestLogin called");
        routingContext.response().putHeader(com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Cookie, Origin, X-Requested-With, Content-Type");
        routingContext.response().putHeader(com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, PUT, PATCH, GET, DELETE, OPTIONS, HEAD, CONNECT");
        routingContext.response().putHeader(com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://browser.studygram.kr:8080/*");
        routingContext.response().putHeader(com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://browser.studygram.kr:8080/");
        routingContext.response().putHeader(com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://browser.studygram.kr:8080");
        routingContext.response().putHeader(com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");


        MultiMap attributes = routingContext.request().formAttributes();
        String accessToken = attributes.get("accessToken");
        HttpServerResponse response = routingContext.response();
        try {
            InputStream input = new URL("https://graph.facebook.com/me?type=email,name&access_token="+accessToken).openStream();
            Reader reader = new InputStreamReader(input, "UTF-8");
            Accounts account = (new Gson().fromJson(reader, Accounts.class));
            if(!checkRegistered(account.getId()))
            {
                createAccount(account.getId(), account.getName());
                response.end("registered");
                routingContext.next();
            }
            Session session = routingContext.session();
            session.put(accessToken,account.getId());
            Cookie cookie = Cookie.cookie("login_session", session.id());
            cookie.setPath("/");
            cookie.setDomain("studygram.kr");
            cookie.setMaxAge(50000);
            Cookie asdf = Cookie.cookie("test", session.id());
            asdf.setDomain(".studygram.kr");
            asdf.setPath("/");
            cookie.setMaxAge(50000);
            routingContext.addCookie(cookie);
            routingContext.addCookie(asdf);
            response.setChunked(true);
//            routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//            routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET");
//            routingContext.response().end("logined");
            response.end("logined");
        } catch (IOException e) {
            Cookie cookie = Cookie.cookie("login_session", "asd");
            cookie.setPath("/");
            cookie.setMaxAge(50000);
            cookie.setDomain("studygram.kr");
            Cookie asdf = Cookie.cookie("tes", "asdf");
            asdf.setDomain("studygram.kr");
            asdf.setPath("/");
            routingContext.addCookie(cookie);
            routingContext.addCookie(asdf);
            response.setChunked(true);
//            routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//            routingContext.response().putHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET");
            System.out.println("accessToken has been expired");
            response.end("logined");
//            routingContext.response().end("accessToken has been expired");
        }

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
