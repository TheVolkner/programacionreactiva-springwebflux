package ve.com.tps.apirestreactiveannotations.WebFlux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

//AL TRABAJAR CON FUNCTIONAL ENDPOINTS, EL ROUTER LLAMA
//A ESTE HANDLER PARA MANEJAR LA SOLICITUD DEL CLIENTE
@Component
public class HelloHandler {



    //AL TRABAJAR FUNCTIONAL ENDPOINTS NO USAMOS ANOTACIONES, SOLO LA DEL COMPONENTE
    //DE LA CLASE, ESTE MÉTODO LO TRABAJAREMOS CON SERVER RESPONSE COMO RESPUESTA Y REQUEST PARA PARÁMETRO

    //SERVER RESPONSE ES PARA RESPUESTAS REACTIVAS, Y SERVER REQUEST PARA PETICIONES REACTIVAS
    public static Mono<ServerResponse> mostrarMensajeMono(ServerRequest serverRequest){

        //DEVOLVEMOS UN OK DEL SERVER, INDICAMOS UN BODY QUE ES EL FLUJO MONO Y EL TIPO DE DATO QUE SERÁ TEXTO PLANO
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(
                        //EN EL BODY INDICAMOS EL FLUJO A DEVOLVER Y EL TIPO DE DATO QUE TENDRÁ
                        Mono.just("Prueba de Flujo Mono").log(),String.class
                );

    }

    //GENERAMOS EL MISMO MÉTODO PERO PARA UN FLUX
    public static Mono<ServerResponse> mostrarMensajeFlux(ServerRequest serverRequest){

        //DEVOLVEMOS UN OK DEL SERVER, INDICAMOS UN BODY QUE ES EL FLUJO FLUX Y EL TIPO DE DATO QUE SERÁ TEXTO PLANO
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        //INDICAMOS TIPO DE DATO Y FLUJO, DICHO FLUJO TENDRÁ DELAY DE 1 SEG POR DATO
                        Flux.just("Prueba","Flujo","Varios","Valores")
                                .delayElements(Duration.ofSeconds(1)).log(),String.class
                );

    }
}
