package org.example;

//EJEMPLO HOT PUBLISHER

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

//EMITE LOS DATOS DESDE DONDE LO DEJO EL ANTERIOR SUBSCRIPTOR
//EL SUBSCRIPTOR #1 COMIENZA DESDE 0, Y DONDE DEJE EL FLUJO CONTINUARÁ EL PRÓXIMO SUBSCRIPTOR, Y ASI SUSCESIVAMENTE
public class Ejemplo02 {

    public static void main(String[] args) throws InterruptedException {

        //CREAMOS UN FLUX QUE SE INICIA CON UN STREAM

        //POR DEFECTO LOS PUBLISHERS SON COLD, PARA CAMBIARLO A HOT SE USA .share()
        Flux<String> netFlux = Flux.fromStream(Ejemplo02::getVideo)
                .delayElements(Duration.ofSeconds(2))
                .share();


        //SUBSCRIBIMOS AL FLUX PARA QUE DEVUELVA LAS PARTES
        netFlux.subscribe(part -> System.out.println("Subscriber 1: " + part));

        //COLOCAMOS UN SLEEP PARA QUE LOS HILOS NO SE DETENGAN MIENTRAS QUE SE ESPERAN LOS RESULTADOS DE LAS SUBSCRIPCIONES
        Thread.sleep(5000);

        //AL SUBSCRIBIRSE NUEVAMENTE, COMIENZA DESDE DONDE LO DEJÓ EL PUBLISHER #1 AL MOMENTO DE COMENZAR ESTA PETICIÓN
        netFlux.subscribe(part -> System.out.println("Subscriber 2:" + part));

        Thread.sleep(60000);
    }

    //CREAMOS UN STREAM DE DATOS
    public static Stream<String> getVideo(){

        System.out.println("Request para vídeo");
        return Stream.of("part1","part2","part3","part4","part5");
    }
}
