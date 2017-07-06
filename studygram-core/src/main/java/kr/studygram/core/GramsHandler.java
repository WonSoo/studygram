package kr.studygram.core;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Cookie;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import kr.studygram.utils.database.Database;
import org.bson.Document;

import java.util.Date;

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
        router.get("/api/image/:filename").handler(this::getGramImage);
        router.post("/api/gram").handler(this::addGram);
        router.put("/api/gram/:id").handler(this::updateGram);
        router.delete("/api/gram/:id").handler(this::deleteGram);
    }

    private void getGramImage(RoutingContext routingContext) {
        String filename = routingContext.pathParam("filename");
        routingContext.response().sendFile("file-uploads/"+filename);
    }

    private void getGramMany(RoutingContext routingContext) {
        System.out.println(routingContext.pathParam("lastIndex")+" + getGramMany");
        int lastIndex = Integer.parseInt(routingContext.pathParam("lastIndex"));
        HttpServerResponse response = routingContext.response();
        response.setChunked(true);
        Document searchQuery = new Document();
        searchQuery.put("_id", new Document("$gt", lastIndex));
        JsonArray jsonArray = database.findMany("grams", searchQuery, 15, lastIndex);
              response.putHeader("content-type", "text/plain")
                .putHeader("Access-Control-Allow-Origin", "*")
                      .putHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS")
      .putHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        Cookie cookie = Cookie.cookie("test", "HAHA");
        cookie.setPath("/");
        cookie.setDomain("studygram.kr");
        routingContext.addCookie(cookie);

        if(jsonArray.isEmpty())
        {
            response.setStatusCode(204);
            response.end();
        }
        else{
            response.putHeader("content-type", "application/json; charset=utf-8")
                    .end(jsonArray.toString());
        }
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
        MultiMap attributes = routingContext.request().formAttributes();
        JsonObject json = new JsonObject();
        // do something with the form data
        JsonArray fileArray = new JsonArray();
        for(FileUpload fileUpload : routingContext.fileUploads()){
            fileArray.add(fileUpload.uploadedFileName().replace("file-uploads\\", ""));
            routingContext.response().sendFile(fileUpload.uploadedFileName());
        }
        json.put("title", attributes.get("title"));
        json.put("content", attributes.get("content"));
        json.put("files", fileArray);
        json.put("tags",attributes.get("tags"));
        System.out.println(new Date().getTime());
        json.put("time", String.valueOf(new Date().getTime()));

        json.put("writer", "593292274197480");
        database.insert("grams", new Document().parse(json.toString()));
    }

    private void deleteGram(RoutingContext routingContext) {
        routingContext.response().putHeader("content-type", "text/plain")
                .putHeader("Access-Control-Allow-Origin", "*")
                .putHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS")
                .putHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        System.out.println("deleteGram");
        database.remove("grams", new Document("_id", Integer.parseInt(routingContext.pathParam("id"))));
    }
}
