package kr.studygram.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;


/**
 * Created by production on 2017-04-02.
 */
public class WebVerticle extends AbstractVerticle {

    public static final String webroot = "kr.studygram.core";

    @Override
    public void start() throws Exception {
        System.out.println("start called");
        Vertx vertx = getVertx();
        Router router = VertxMain.getRouter();

        router.route().handler(StaticHandler.create("webroot"));
    }

    @Override
    public void stop() throws Exception {
        System.out.println("BasicVerticle stopped");
    }
}
