package kr.studygram.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
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
        HttpServerResponse response = routingContext.response();
        // enable chunked responses because we will be adding data as
        // we execute over other handlers. This is only required once and
        // only if several handlers do output.
        response.setChunked(true);

        response.write("getGramMany\n");
//        Document doc = new Document();
//
//        doc.append("_id", routingContext.pathParam("lastIndex"));
    }

    private void test(RoutingContext routingContext) {
        System.out.println("test Method called");
        System.out.println(routingContext.statusCode());
    }

    private void getGramOne(RoutingContext routingContext) {
        System.out.println("fuck");
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
//        Document doc = new Document();
//        doc = jsonParser.decodeValue(routingContext.getBodyAsString(), Document.class);
//        Database.INSTANCE.insert("content", doc);
    }

    private void deleteGram(RoutingContext routingContext) {
        System.out.println("deleteGram");
        Document doc = new Document();
        doc = Json.decodeValue(routingContext.getBodyAsString(), Document.class);
        Database.INSTANCE.remove("content", doc);
    }

}
