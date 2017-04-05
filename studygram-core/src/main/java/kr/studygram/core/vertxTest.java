package kr.studygram.core;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;

/**
 * Created by production on 2017-03-29.
 */

public class vertxTest {
    private static Vertx vertx;
    private static HttpServer server;

    private static void initialize() {
        vertx = Vertx.vertx();
        server = vertx.createHttpServer();
    }

    public static void main(String[] args) {
        initialize();

        vertx.deployVerticle(new studyVerticle());
    }

    public static Vertx getVertx() {
        return vertx;
    }

    public static void setVertx(Vertx vertx) {
        vertxTest.vertx = vertx;
    }

    public static HttpServer getServer() {
        return server;
    }

    public static void setServer(HttpServer server) {
        vertxTest.server = server;
    }
}
