package ve.com.tps.pruebasserviciosstepverifier.servicios;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

//ESTA SERÁ UN SERVICIO DE PRUEBA EL CÚAL SERÁ TESTEADO CON STEP VERIFIER
@Service
public class ServicioSencillo {

    public Mono<String> buscarUno(){

        return Mono.just("hola");
    }

    public Flux<String> buscarTodos(){

        return Flux.just("hola","que","tal","estás");
    }

    //MÉTODO QUE RETORNE LA BUSQUEDA DE MANERA ASÍNCRONA
    public Flux<String> buscarTodosLento(){

        //RETORNA CADA ELEMENTO DEL FLUX CON UN INTERVALO DE 10 SEGUNDOS CADA UNO
        return Flux.just("hola","que","tal","estas")
                .delaySequence(Duration.ofSeconds(10));
    }
}
