package ve.com.tps;

import reactor.core.publisher.Flux;

public class Ejemplo04 {

    //USO DEL OPERADOR CONCAT
    public static void main(String[] args) {

        //CREAMOS DOS FLUJOS
        Flux<String> firstFlux = Flux.fromArray(new String[]{"a","b","c"});
        Flux<String> secondFlux = Flux.fromArray(new String[]{"d","e","f"});

        //COMBINAMOS LOS DOS FLUJOS
        Flux<String> fluxCombined = Flux.concat(firstFlux,secondFlux);

        //IMPRIMIMOS RESULTADOS
        fluxCombined.subscribe(element -> System.out.println(element + " "));
    }
}
