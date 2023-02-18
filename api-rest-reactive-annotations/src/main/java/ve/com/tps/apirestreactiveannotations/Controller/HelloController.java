package ve.com.tps.apirestreactiveannotations.Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

//PRUEBA DE API REST CON SPRING WEB FLUX BASADO EN ANOTACIONES
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    //MÉTODO ASINCROÍNICO PARA OBTENER UN FLUJO MONO(ÚNICO VALOR)

    //A AMBOS MÉTODOS LE INDICAMOS EL MEDIATYPE JSON VALUE
    @GetMapping("/mono")
    public Mono<String> getMono(){

        return Mono.just("Prueba de Flujo de 0 o 1 valores");
    }

    //MÉTODO ASINCRÓNICO PARA OBTENER UN FLUJO FLUX(VARIOS VALORES)
    @GetMapping(path = "/flux", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<String> getFlux(){

        //CREAMOS UN FLUJO DE VARIOS VALORES EL CÚAL TIENE UN DELAY PARA LEER CADA ELEMENTO DE 1 SEG E IRÁ IMPRIMIENDO EL LOG
        return Flux.just("Prueba","Flujo","Varios","Valores")
                .delayElements(Duration.ofSeconds(1))
                .log();
    }
}
