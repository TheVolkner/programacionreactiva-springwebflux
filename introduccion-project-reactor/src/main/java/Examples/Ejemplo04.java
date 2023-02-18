package Examples;

import reactor.core.publisher.Flux;

//PROGRAMACIÓN REACTIVA
//FLUJO FLUX EJEMPLOS
public class Ejemplo04 {

    public static void main(String[] args) {

        //CREAMOS UN FLUX Y LO INICIALIZAMOS POR UN ARRAY
        Flux<String> flux = Flux.fromArray(new String[]{"data1","data2","data3"});

        //NOS SUSCRIBIMOS E IMPRIMIMOS LOS ELEMENTOS DEL ARREGLO

        //flux.subscribe(data -> System.out.println(data));
        flux.doOnNext(data -> System.out.println(data)).subscribe();
    }
}
