package ve.com.tps;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Ejemplo03 {

    public static void main(String[] args) {

        //.map PERMITE TRANSFORMAR LOS FLUJOS
        Flux.fromArray(new String[]{"Omar","Jason","Reiverson","Leonardo","Sara"})
                .flatMap(Ejemplo03::ponerNombreModificadoEnMono)
                .subscribe(System.out::println);

    }

    //ESTE MÉTODO DEVUELVE LA SALIDA DE LA MODIFICACIÓN DEL STRING
    //AL SER UNA NUEVA SALIDA DEL FLUJO MODIFICADO DEBE LLAMARSE CON FLAT MAP
    private static Mono<String> ponerNombreModificadoEnMono(String nombre){

        return Mono.just(nombre.concat(" modificado"));

    }
}
