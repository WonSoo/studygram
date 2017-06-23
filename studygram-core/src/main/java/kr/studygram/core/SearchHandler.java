package kr.studygram.core;

import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import kr.studygram.utils.database.Database;
import org.bson.Document;

/**
 * Created by cynos07 on 2017-05-24.
 */
public class SearchHandler extends WebVerticle{
    private Database database;
    private Router router;
    private void Initialize()
    {
        database = Database.getInstance();
        router = getRouter();
    }

    public void start() {
        Initialize();
        router.get("/api/getKeyword/:startWith").handler(this::getKeyword);
    }

    private void getKeyword(RoutingContext routingContext) {
        System.out.println(routingContext.pathParam("startWith"));
        String startWith = routingContext.pathParam("startWith");
        routingContext.response().setChunked(true);
        Document searchQuery = new Document();
        searchQuery.put("$or", new Document("title",java.util.regex.Pattern.compile(startWith)));
        searchQuery.put("$or", new Document("tags",java.util.regex.Pattern.compile(startWith)));

        JsonArray jsonArray = database.findKeyword("grams", searchQuery);

        System.out.println(jsonArray.toString());
        routingContext.response().end(jsonArray.toString());
    }
}
