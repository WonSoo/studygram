package kr.studygram.core;

import com.google.gson.Gson;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
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
        router.post("/api/login").handler(this::requestLogin);
    }

    private void requestLogin(RoutingContext routingContext) {
        JsonObject json = routingContext.getBodyAsJson();
        System.out.println(json);
        String accessToken = json.getString("accessToken");
        HttpServerResponse response = routingContext.response();
        try {
            InputStream input = new URL("https://graph.facebook.com/me?access_token="+accessToken).openStream();
            Reader reader = new InputStreamReader(input, "UTF-8");
            Accounts account = (new Gson().fromJson(reader, Accounts.class));
            if(!checkRegistered(account.getId()))
            {
                createAccount(account.getId(), account.getName());
                response.end("registered");
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
