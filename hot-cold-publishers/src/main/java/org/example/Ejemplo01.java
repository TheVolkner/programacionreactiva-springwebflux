package org.example;

//EJEMPLO COLD PUBLISHER

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

//NO EMITE DATOS HASTA QUE TENGA UN SUBSCRIPTOR
//Y ADEMÁS, PARA CADA SUBSCRIPTOR LOS DATOS LOS ENVÍA DESDE EL PRINCIPIO
public class Ejemplo01 {

    public static void main(String[] args) throws InterruptedException {

        //CREAMOS UN FLUX QUE SE INICIA CON UN STREAM
        Flux<String> netFlux = Flux.fromStream(Ejemplo01::getVideo)
                .delayElements(Duration.ofSeconds(2));


        //SUBSCRIBIMOS AL FLUX PARA QUE DEVUELVA LAS PARTES
        netFlux.subscribe(part -> System.out.println("Subscriber 1: " + part));

        //COLOCAMOS UN SLEEP PARA QUE LOS HILOS NO SE DETENGAN MIENTRAS QUE SE ESPERAN LOS RESULTADOS DE LAS SUBSCRIPCIONES
        Thread.sleep(5000);

        //AL SUBSCRIBIRSE NUEVAMENTE, LA PETICIÓN COMIENZA DE 0 Y LE DEVOLVERÁ TODAS LAS PARTES IGUALMENTE
        netFlux.subscribe(part -> System.out.println("Subscriber 2:" + part));

        Thread.sleep(60000);
    }

    //CREAMOS UN STREAM DE DATOS
    public static Stream<String> getVideo(){

        System.out.println("Request para vídeo");
        return Stream.of("part1","part2","part3","part4","part5");
    }
}
