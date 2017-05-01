package kr.studygram.core;

import com.google.gson.JsonArray;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import kr.studygram.utils.database.Database;
import org.bson.Document;

/**
 * Created by cynos07 on 2017-04-12.
 */
public class GramsHandler extends WebVerticle {
    private Database database;
    private Router router;
    private void Initialize()
    {
        database = Database.getInstance();
        router = getRouter();
    }

    public void start() {
        Initialize();
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
        searchQuery.put("_id", new Document("$gt", lastIndex));
        JsonArray jsonArray = database.findMany("grams", searchQuery, 15);

        response.putHeader("content-type", "application/json; charset=utf-8")
                .end(jsonArray.toString());
    }

    private void getGramOne(RoutingContext routingContext) {
        System.out.println(routingContext.request().getParam("id")+" + getGramOne");
        HttpServerResponse response = routingContext.response();
        int id = Integer.parseInt(routingContext.pathParam("id"));
        response.setChunked(true);
        Document searchQuery = new Document();
        searchQuery.put("_id", id);
        String json = database.findOne("grams", searchQuery);

        response.putHeader("content-type", "application/json; charset=utf-8")
                .end(json);
    }

    private void updateGram(RoutingContext routingContext) {
        System.out.println(routingContext.request().getParam("id")+" + updateGram");
        HttpServerResponse response = routingContext.response();
        int id = Integer.parseInt(routingContext.pathParam("id"));
        response.setChunked(true);
        System.out.println(routingContext.getBodyAsJson().toString());

        int modifiedCount = database.update("grams",id, new Document().parse(routingContext.getBodyAsJson().toString()));
        response.end(modifiedCount+"");
    }

    private void addGram(RoutingContext routingContext) {
        System.out.println("addGram");
        JsonObject json = routingContext.getBodyAsJson();
        database.insert("grams", new Document().parse(json.toString()));
    }

    private void deleteGram(RoutingContext routingContext) {
        System.out.println("deleteGram");
        database.remove("grams", new Document("_id", Integer.parseInt(routingContext.pathParam("id"))));
    }

}
