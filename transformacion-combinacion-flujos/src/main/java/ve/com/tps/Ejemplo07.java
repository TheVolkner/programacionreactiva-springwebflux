package ve.com.tps;

import reactor.core.publisher.Flux;

public class Ejemplo07 {

    //EJEMPLO DE .zipWith
    public static void main(String[] args) {

        //CREAMOS DOS FLUJOS
        Flux<Integer> flux1 = Flux.just(1,2,3,4,5);
        Flux<Integer> flux2 = Flux.just(6,7,8,9,10);

        //LOS FUSIONAMOS CON ZIP CONCATENANDO A LOS ELEMENTOS DE FLUX 1 + FLUX 2
        //IMPRIMIMOS RESULTADOS

        //A DIFERENCIA DE .zip, .zipWith SE LE APLICA DIRECTAMENTE AL FLUJO YA CREADO
        flux1.zipWith(flux2,(first,second) -> first + second).subscribe(System.out::println);
    }
}
