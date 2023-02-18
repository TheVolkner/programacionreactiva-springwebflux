package Examples;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Ejemplo01 {

    //PROGRAMACIÓN REACTIVA
    //FLUJOS MONO Y FLUX
    public static void main(String[] args) {

        //CREAMOS UNA LISTA DE ELEMENTOS PARA RECIBIR LOS DATOS DE MONO Y FLUX
        List<Integer> elementosFromMono = new ArrayList<>();

        List<Integer> elementosFromFlux = new ArrayList<>();

        //MONO MANEJA DATOS DE 0 A 1.
       Mono<Integer> mono = Mono.just(121);

       //FLUX MANEJA DATOS DE 0 A N
        Flux<Integer> flux = Flux.just(12,20,14,5,3,7,8);

        //NOS SUSCRIBIMOS AL MONO Y AGREGAMOS EL ELEMENTO A LA LISTA
        mono.subscribe(elementosFromMono::add);

        //NOS SUSCRIBIMOS AL FLUX Y AGREGAMOS SUS ELEMENTOS A LA LISTA
        flux.subscribe(elementosFromFlux::add);

        //IMPRIMIMOS LOS RESULTADOS
        System.out.println(elementosFromMono);
        System.out.println(elementosFromFlux);
    }
}
