package kr.studygram.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import kr.studygram.utils.database.Database;
import org.bson.Document;

/**
 * Created by cynos07 on 2017-04-12.
 */
public class databaseVerticle extends AbstractVerticle {
    private Document doc;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Database.INSTANCE.insert("account", new Document("name", "user").append("id", "jkh6100").append("password","0000"));
    }

    @Override
    public void stop(Future<Void> stopFuture) throws Exception {

    }
}
