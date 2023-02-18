package org.example;

//EJEMPLO

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

//EJEMPLO MÍNIMO DE SUBSCRIPTORES
public class Ejemplo03 {

    public static void main(String[] args) throws InterruptedException {

        //CREAMOS UN FLUX QUE SE INICIA CON UN STREAM

        //CON .publish y .refCount GENERAMOS UN MÍNIMO DE 2 SUBSCRIPCIONES PARA QUE EL FLUJO COMIENZE A TRABAJAR
        Flux<String> netFlux = Flux.fromStream(Ejemplo03::getVideo)
                .delayElements(Duration.ofSeconds(2))
                .publish()
                .refCount(2);


        //SUBSCRIBIMOS AL FLUX PARA QUE DEVUELVA LAS PARTES
        netFlux.subscribe(part -> System.out.println("Subscriber 1: " + part));

        //COLOCAMOS UN SLEEP PARA QUE LOS HILOS NO SE DETENGAN MIENTRAS QUE SE ESPERAN LOS RESULTADOS DE LAS SUBSCRIPCIONES
        Thread.sleep(5000);

        //AL SUBSCRIBIRSE NUEVAMENTE, AL SER ESTA LA 2DA SUBSCRIPCIÓN, EL FLUJO COMIENZA A FUNCIONAR
        netFlux.subscribe(part -> System.out.println("Subscriber 2:" + part));

        Thread.sleep(60000);
    }

    //CREAMOS UN STREAM DE DATOS
    public static Stream<String> getVideo(){

        System.out.println("Request para vídeo");
        return Stream.of("part1","part2","part3","part4","part5");
    }
}
