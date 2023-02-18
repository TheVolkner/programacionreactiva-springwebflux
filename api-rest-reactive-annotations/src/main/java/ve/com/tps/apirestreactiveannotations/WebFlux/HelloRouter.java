package ve.com.tps.apirestreactiveannotations.WebFlux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

//ESTE ROUTER SE ENCARGARÁ DE INTERCEPTAR LAS PETICIONES DEL CLIENTE A TRAVES
//DEL SERVIDOR, EN ESTE CASO NETTY. Y REDIRIGIRLAS AL HANDLER
@Configuration
public class HelloRouter {

    //GENERAMOS ESTE MÉTODO Y LE MANDAMOS COMO PARÁMETRO AL HANDLER PREVIAMENTE CREADO
    @Bean
    public RouterFunction<ServerResponse> functionalRoutes(HelloHandler helloHandler){

        //ACÁ INDICAMOS LAS RUTAS QUE EL ROUTER VA A INTERCEPTAR EN LAS LLAMADAS
        // Y EL HANDLER CON SU MÉTODO ASIGNADO PARA CADA CASO
        return RouterFunctions
                .route(RequestPredicates.GET("/functional/mono"),HelloHandler::mostrarMensajeMono)
                .andRoute(RequestPredicates.GET("/functional/flux"),HelloHandler::mostrarMensajeFlux);

    }
}
