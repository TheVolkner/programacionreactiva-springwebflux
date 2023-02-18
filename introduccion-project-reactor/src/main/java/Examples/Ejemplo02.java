package Examples;

import reactor.core.publisher.Mono;

//PROGRAMACIÓN REACTIVA
//FLUJO MONO EJEMPLOS
public class Ejemplo02 {

    public static void main(String[] args) {

        //CREAMOS EL MONO, SOLO GENERA 1 ELEMENTO O NINGUNO
        Mono<String> mono = Mono.just("Hola");

        //SUBSCRIBIMOS AL MONO E IMPRIMIMOS SUS DATOS

        //SE EJEMPLIFICA EL PROCESO DE LECTURA DE LOS DATOS DEL PUBLISHER
        mono.subscribe(
                data -> System.out.println(data), //onNext del Publisher
                error -> System.out.println(error), //onError
                () -> System.out.println("¡Flujo Completo!") //onComplete
        );

    }
}
