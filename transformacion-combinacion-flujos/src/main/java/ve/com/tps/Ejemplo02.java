package ve.com.tps;

import reactor.core.publisher.Flux;

public class Ejemplo02 {
    public static void main(String[] args) {

        //.map PERMITE TRANSFORMAR LOS FLUJOS

        //EN ESTE CASO FILTRAMOS LAS CADENAS A LAS QUE SEAN MAYORES A 5, Y LAS TRANSFORMAMOS A MAYÚSCULAS
        Flux.fromArray(new String[]{"Omar","Jason","Reiverson","Leonardo","Sara"})
                .filter(nombre -> nombre.length() > 5)
                .map(String::toUpperCase)
                .subscribe(System.out::println);
    }
}
