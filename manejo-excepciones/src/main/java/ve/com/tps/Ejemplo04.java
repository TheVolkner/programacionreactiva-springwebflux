package ve.com.tps;

import reactor.core.publisher.Flux;

//MANEJO DE EXCEPCIONES CON JAVA RX
public class Ejemplo04 {

    public static void main(String[] args) {

        //CREAMOS UN FLUX Y LO CONCATENAMOS CON UN FLUX ERROR

        //AL INTENTAR CONCATENAR ALGO MÁS, NO LO HARÁ POR EL ERROR
        //TENEMOS QUE PROCESAR DICHO ERROR CON onError

        //onErrorMap FUNCIONA MUY SIMILAR a onErrorContinue PERO ESTA PERMITE TRANSFORMAR DATOS

        Flux.just(2,7,0,12,22,24)
                .map(element -> {
                    if(element == 7){
                        throw new RuntimeException("Excepcion Ocurred...");
                    }
                    return element;
                })
                .onErrorMap((ex) -> {
                    System.out.println("Exception: " + ex);
                    return new CustomException(ex.getMessage(),ex);
                })
                .log()
                .subscribe();
    }


}
