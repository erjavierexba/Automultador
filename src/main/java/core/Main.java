package core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.asyncsql.MySQLClient;

public class Main extends AbstractVerticle {
	private SQLClient mysql;

	public void start(Future<Void> startFuture) {
		JsonObject mySQLClientConfig = new JsonObject().put("host", "127.0.0.1").put("port", 3306)
				.put("database", "automultador").put("username", "root").put("password", "root");

		mysql = MySQLClient.createShared(vertx, mySQLClientConfig);
		// Router lógico:
		Router router = Router.router(vertx);
		vertx.createHttpServer().requestHandler(router::accept).listen(8085, res -> {
			if (res.succeeded()) {
				System.out.println("Servidor desplegado");
			} else {
				System.out.println("Error: " + res.cause());
			}
		});
		router.route("/api/elements").handler(BodyHandler.create());
		router.get("/api/elements").handler(this::getAll);
		router.put("/api/elements").handler(this::putOne);
		router.get("/api/elements/:id").handler(this::getOne);

	}

	private void getOne(RoutingContext routingContext) {
		String paramStr = routingContext.request().getParam("idautomultador");

		if (paramStr != null) {
			try {
				int param = new Integer(paramStr);
				mysql.getConnection(conn -> {
					if (conn.succeeded()) {
						SQLConnection miConexion = conn.result();
						String query = "SELECT idautomultador, velocidad, fecha, fuente " + "FROM automultador "
								+ "WHERE id = ?";
						JsonArray paramQuery = new JsonArray().add(param);
						miConexion.queryWithParams(query, paramQuery, res -> {
							if (res.succeeded()) {
								routingContext.response().end(res.result().getRows().get(0).encode());
							} else {
								routingContext.response().setStatusCode(400).end(res.cause().toString());
							}
						});
					} else {
						routingContext.response().setStatusCode(400).end(conn.cause().toString());

					}
				});

				// routingContext.response().setStatusCode(200).end(Json.encodePrettily(database.get(param)));
			} catch (ClassCastException e) {
				routingContext.response().setStatusCode(400).end();
			}
		} else {
			routingContext.response().setStatusCode(400).end();
		}
	}

	private void getAll(RoutingContext routingContext) {
		String paramStr = routingContext.request().getParam("idautomultador");

		if (paramStr != null) {
			try {
				int param = new Integer(paramStr);
				mysql.getConnection(conn -> {
					if (conn.succeeded()) {
						SQLConnection miConexion = conn.result();
						String query = "SELECT idautomultador, velocidad, fecha, fuente " + "FROM automultador ";
						JsonArray paramQuery = new JsonArray().add(param);
						miConexion.queryWithParams(query, paramQuery, res -> {
							if (res.succeeded()) {routingContext.response().end(Json.encodePrettily(res.result()));
							} else {routingContext.response().setStatusCode(400).end(res.cause().toString());
			}});
						} else {	routingContext.response().setStatusCode(400).end(conn.cause().toString());		}
				});
	} catch (ClassCastException e) {routingContext.response().setStatusCode(400).end();}
		} else {routingContext.response().setStatusCode(400).end();}
	}

	private void putOne(RoutingContext routingContext) {
		// DomoState state = Json.decodeValue(routingContext.getBody(),
		// DomoState.class);
		// database.put(state.getId(),
		// state);routingContext.response().setStatusCode(201).putHeader("content-type",
		// "application/json; charset=utf-8").end(Json.encodePrettily(state));

	}
}
