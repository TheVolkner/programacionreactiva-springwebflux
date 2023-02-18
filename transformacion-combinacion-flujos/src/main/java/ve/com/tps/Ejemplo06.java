package ve.com.tps;

import reactor.core.publisher.Flux;

public class Ejemplo06 {

    //EJEMPLO DE .zip
    public static void main(String[] args) {

        //CREAMOS DOS FLUJOS
        Flux<String> flux1 = Flux.just("A","B","C");
        Flux<String> flux2 = Flux.just("D","E","F");

        //LOS FUSIONAMOS CON ZIP CONCATENANDO A LOS ELEMENTOS DE FLUX 1 + FLUX 2
        //IMPRIMIMOS RESULTADOS
        Flux.zip(flux1,flux2,(first,second) -> first + second).subscribe(System.out::println);
    }
}
