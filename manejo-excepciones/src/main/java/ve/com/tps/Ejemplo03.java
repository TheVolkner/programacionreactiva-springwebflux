package ve.com.tps;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//MANEJO DE EXCEPCIONES CON JAVA RX
public class Ejemplo03 {

    public static void main(String[] args) {

        //CREAMOS UN FLUX Y LO CONCATENAMOS CON UN FLUX ERROR

        //AL INTENTAR CONCATENAR ALGO MÁS, NO LO HARÁ POR EL ERROR
        //TENEMOS QUE PROCESAR DICHO ERROR CON onError

        //onErrorContinue NO DETIENE EL FLUJO, SI NO QUE ELIMINA EL ELEMENTO EN PARTICULAR QUE GENERA LA EXCEPCION
        //Y CONTINUA CON EL PROCESO NORMALMENTE, PROCESAMOS EL ELEMENTO EN UNA LAMBDA

        Flux.just(2,7,0,12,22,24)
                .map(element -> {
                    if(element == 0){
                        throw new RuntimeException("Excepcion Ocurred...");
                    }
                    return element;
                })
                .onErrorContinue((ex,element) -> {
                    System.out.println("Exception: " + ex);
                    System.out.println("Elemento causante: " + element);
                })
                .log()
                .subscribe();
    }
}
