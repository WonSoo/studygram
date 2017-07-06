package kr.studygram.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.StaticHandler;
import kr.studygram.utils.database.Database;


/**
 * Created by production on 2017-04-02.
 */
public class WebVerticle extends AbstractVerticle {

    private static Vertx vertx;
    private static Router router;
    private static Database database;
    private void initialize()
    {
        vertx = getVertx();
        router = VertxMain.getRouter();
    }

    @Override
    public void start() throws Exception {
        initialize();
        router.route().handler(CookieHandler.create());
        router.route().handler(BodyHandler.create());

        //Add Handler
        GramsHandler gramsHandler = new GramsHandler();
        gramsHandler.start();
        LoginHandler loginHandler = new LoginHandler();
        loginHandler.start();
        SearchHandler searchHandler = new SearchHandler();
        searchHandler.start();

        router.route().handler(StaticHandler.create("webroot"));
    }

    @Override
    public void stop() throws Exception {
        System.out.println("BasicVerticle stopped");
    }

    public static Database getDatabase() {
        return database;
    }

    public static void setDatabase(Database database) {
        WebVerticle.database = database;
    }

    public static Router getRouter() {
        return router;
    }

    public static void setRouter(Router router) {
        WebVerticle.router = router;
    }
}
