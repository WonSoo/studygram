package kr.studygram.core;

import com.google.gson.JsonArray;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import kr.studygram.utils.database.Database;
import org.bson.Document;

/**
 * Created by cynos07 on 2017-04-12.
 */
public class GramsVerticle extends AbstractVerticle {
    private Database database;

    private void Initialize()
    {
        database = Database.getInstance();
    }
    @Override
    public void start() {
        Initialize();
        Router router = VertxMain.getRouter();
        router.route().handler(BodyHandler.create());
        router.get("/api/gram/:lastIndex").handler(this::getGramMany);
        router.get("/api/gramOne/:id").handler(this::getGramOne);
        router.post("/api/gram").handler(this::addGram);
        router.put("/api/gram/:id").handler(this::updateGram);
        router.delete("/api/gram/:id").handler(this::deleteGram);
    }

    private void getGramMany(RoutingContext routingContext) {
        System.out.println(routingContext.pathParam("lastIndex")+" + getGramMany");
        int lastIndex = Integer.parseInt(routingContext.pathParam("lastIndex"));
        HttpServerResponse response = routingContext.response();
        response.setChunked(true);
        Document searchQuery = new Document();
        searchQuery.put("id", new Document("$gt", lastIndex));
        JsonArray jsonArray = database.findMany("grams", searchQuery, 15);

        response.write(jsonArray.toString());
    }

    private void getGramOne(RoutingContext routingContext) {
        System.out.println(routingContext.request().getParam("id")+" + getGramOne");
        HttpServerResponse response = routingContext.response();
        response
                .putHeader("content-type", "text/html")
                .end("<h1>Hello from my first Vert.x 3 application</h1>");
    }

    private void updateGram(RoutingContext routingContext) {
        System.out.println(routingContext.request().getParam("id")+" + updateGram");
    }

    @Override
    public void stop() {

    }

    private void addGram(RoutingContext routingContext) {
        System.out.println("addGram");
        JsonObject json = routingContext.getBodyAsJson();
//        String accessToken = json.getString("accessToken");
        database.insert("grams", new Document().parse(json.toString()));
    }

    private void deleteGram(RoutingContext routingContext) {
        System.out.println("deleteGram");
        Document doc = new Document();
        doc = Json.decodeValue(routingContext.getBodyAsString(), Document.class);
        Database.INSTANCE.remove("content", doc);
    }

}
