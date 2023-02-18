package ve.com.tps;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo05 {

    //USO DEL OPERADOR CONCAT WITH
    public static void main(String[] args) {

        //CREAMOS DOS FLUJOS
        Flux<String> flux = Flux.fromArray(new String[]{"a","b","c"});
        Mono<String> mono = Mono.just("f");

        //COMBINAMOS LOS DOS FLUJOS CON CONCAT WITH

        //CONCAT WITH PERMITE COMBINAR FLUJOS FLUX Y MONO
        Flux<String> fluxCombined = flux.concatWith(mono);

        //IMPRIMIMOS RESULTADOS
        fluxCombined.subscribe(element -> System.out.println(element + " "));
    }
}
