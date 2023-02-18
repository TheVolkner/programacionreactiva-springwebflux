package ve.com.tps.ejemplothymeleafreactive.Repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ve.com.tps.ejemplothymeleafreactive.Entity.Productos;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class ProductoRepository{


    //CREAMOS LAS LISTAS PARA LOS EJEMPLOS
    private static List<Productos> lista = new ArrayList<>();
    private static List<Productos> lista2 = new ArrayList<>();

    //UN BLOQUE ESTÁTICA SE EJECUTA AL PRINCIPIO DE LA APLICACIÓN Y SIRVE PARA INICIALIZAR ELEMENTOS
    static{
        lista.add(new Productos(1,200,"ordenador"));
        lista.add(new Productos(2,300,"tablet"));
        lista.add(new Productos(3,250,"auricular"));

        lista2.add(new Productos(4,280,"movil"));
        lista2.add(new Productos(5,150,"teclado"));
        lista2.add(new Productos(6,100,"raton"));
    }

    //CREAMOS MÉTODOS REACTIVOS PARA INICIALIZAR LOS FLUJOS CON LAS LISTAS

    //DEVUELVE UN FLUJO DE VARIOS ELEMENTOS EN ESTE CASO LA LISTA, Y CON UN DELAY DE 3 SEGS CADA ELEMENTO
    public Flux<Productos> buscarTodos(){
        return Flux.fromIterable(lista)
                .delayElements(Duration.ofSeconds(3));
    }
    //DEVUELVE UN FLUJO DE VARIOS ELEMENTOS EN ESTE CASO LA LISTA 2, Y CON UN DELAY DE 3 SEGS CADA ELEMENTO
    public Flux<Productos> buscarOtros2(){

        return Flux.fromIterable(lista2)
                .delayElements(Duration.ofSeconds(3));
    }
}
