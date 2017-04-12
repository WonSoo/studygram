package kr.studygram.core;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServer;

/**
 * Created by production on 2017-03-29.
 */

public class vertxMain {
    private static Vertx vertx;
    private static HttpServer server;
    private static VertxOptions options = new VertxOptions();

    private static void initialize() {
        options.setMaxEventLoopExecuteTime(Long.MAX_VALUE);
        vertx = Vertx.vertx(options);
        server = vertx.createHttpServer();
    }

    public static void main(String[] args) {
        initialize();

        vertx.deployVerticle(new webVerticle());
        vertx.deployVerticle(new databaseVerticle());
    }

    public static Vertx getVertx() {
        return vertx;
    }

    public static void setVertx(Vertx vertx) {
        vertxMain.vertx = vertx;
    }

    public static HttpServer getServer() {
        return server;
    }

    public static void setServer(HttpServer server) {
        vertxMain.server = server;
    }
}
