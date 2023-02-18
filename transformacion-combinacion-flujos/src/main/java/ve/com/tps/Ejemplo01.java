package ve.com.tps;

import reactor.core.publisher.Flux;

//TRANSFORMACIONES Y COMBINACIONES
public class Ejemplo01 {

    public static void main(String[] args) {

        //.map PERMITE TRANSFORMAR LOS FLUJOS

        //EN ESTE CASO, TRANSFORMAMOS LAS CADENAS A MAYÚSCULAS
        Flux.fromArray(new String[]{"Omar","Jason","Reiverson","Leonardo","Sara"})
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}
