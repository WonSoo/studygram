package kr.studygram.core;

import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
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
        router.get("/api/gramImage/:filename").handler(this::getGramImage);
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
        MultiMap attributes = routingContext.request().formAttributes();
        // do something with the form data
        for(FileUpload fileUpload : routingContext.fileUploads()){
            System.out.println("fileName: "+fileUpload.fileName());
            System.out.println("name: "+fileUpload.name());
            System.out.println("uploadedFileName: "+fileUpload.uploadedFileName());
            System.out.println("charSet: "+fileUpload.charSet());
            System.out.println("contentTransferEncoding: "+fileUpload.contentTransferEncoding());
            System.out.println("contentType: "+fileUpload.contentType());
            System.out.println("size: "+fileUpload.size());

            routingContext.response().sendFile(fileUpload.uploadedFileName());
        }
        System.out.println("attributes.entries(): "+attributes.entries());
        System.out.println("attributes.get(): "+attributes.get("title"));
        System.out.println("attributes.getAll(): "+attributes.getAll("title"));
        JsonObject json = new JsonObject();
        json.put("title", attributes.get("title"));
        json.put("content", attributes.get("content"));
        json.put("content", attributes.get("content"));
        json.put("content", attributes.getAll("tags"));
//        json.put("writer", session)
//        System.out.println(json);
//
//        database.insert("grams", new Document().parse(json.toString()));
    }

    private void deleteGram(RoutingContext routingContext) {
        System.out.println("deleteGram");
        database.remove("grams", new Document("_id", Integer.parseInt(routingContext.pathParam("id"))));
    }

}
