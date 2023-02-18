package ve.com.tps;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//MANEJO DE EXCEPCIONES CON JAVA RX
public class Ejemplo01 {

    public static void main(String[] args) {

        //CREAMOS UN FLUX Y LO CONCATENAMOS CON UN FLUX ERROR

        //AL INTENTAR CONCATENAR ALGO MÁS, NO LO HARÁ POR EL ERROR
        //TENEMOS QUE PROCESAR DICHO ERROR CON onError

        //onErrorReturn DEVUELVE UN NUMERO ENTERO HACIENDO REFERENCIA AL ERROR

        Flux.just(2,7,10)
                .concatWith(Flux.error(new RuntimeException("Exception Ocurred...")))
                .concatWith(Mono.just(12))
                .onErrorReturn(72)
                .log()
                .subscribe();
    }
}
