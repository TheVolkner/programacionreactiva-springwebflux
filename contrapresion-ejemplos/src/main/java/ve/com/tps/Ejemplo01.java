package ve.com.tps;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

//LA CONTRA PRESIÓN SIRVE PARA INDICARLE AL FLUJO LA CANTIDAD DE DATOS QUE NECESITAMOS
public class Ejemplo01 {

    //EN ESTE EJEMPLO NO APLICAMOS CONTRAPRESIÓN, NOS DEVUELVE TODOS LOS RESULTADOS
    public static void main(String[] args) {

        Flux<String> ciudades = Flux.fromIterable(
                new ArrayList<>(Arrays.asList("New York","Detroit","Michigan","Texas","Alabama","Miami","Oklahoma","Toronto","Arizona"))
        );

        ciudades.log().subscribe();
    }
}
