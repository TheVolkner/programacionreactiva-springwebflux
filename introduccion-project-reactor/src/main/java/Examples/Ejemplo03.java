package Examples;

import reactor.core.publisher.Mono;

//PROGRAMACIÓN REACTIVA
//FLUJO MONO EJEMPLOS
public class Ejemplo03 {

    public static void main(String[] args) {

        //UN SUPPLIER SE ENCARGA DE DEVOLVER UN RESULTADO SIN PASARLE PARÁMETROS
        //EN ESTE CASO, ES UNA FUNCIÓN QUE IMPRIME UN ERROR
        Mono<String> mono = Mono.fromSupplier(() -> {
           throw new RuntimeException("Excepción Ocurrida");
        });

        //SE EJEMPLIFICA EL PROCESO DE LECTURA DE LOS DATOS DEL PUBLISHER
        mono.subscribe(
                data -> System.out.println(data), //onNext del Publisher
                error -> System.out.println(error), //onError
                () -> System.out.println("¡Flujo Completo!") //onComplete
        );

    }
}
