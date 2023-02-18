package ve.com.tps.pruebasserviciosstepverifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ve.com.tps.pruebasserviciosstepverifier.servicios.ServicioSencillo;

import java.time.Duration;

@SpringBootTest
public class EjemplosTest {

    //STEP VERIFIER COMPRUEBA EL COMPORTAMIENTO DE FLUX Y MONO
    //DE FORMA ASÍNCRONA CUANDO POSEE UN SUBSCRIPTOR

    //PROBAMOS COMPORTAMIENTO DE UN SERVICIO FLUX

    //INYECTAMOS EL SERVICIO
    @Autowired
    private ServicioSencillo servicioSencillo;

    //PROBAMO EL MÉTODO MONO DEL SERVICIO
    @Test
    public void testMono(){


        //LLAMAMOS AL SERVICIO Y OBTENEMOS EL RESULTADO DEL MONO
        Mono<String> uno = servicioSencillo.buscarUno();

        //CON STEP VERIFIER COMPROBAMOS QUE EL RESULTADO SEA EL CORRECTO
        StepVerifier.create(uno)
                .expectNext("hola")
                .expectComplete()
                .verify();

    }

    //PROBAMOS EL SERVICIO CON EL FLUX DE VARIOS
    @Test
    public void testVarios(){

        //OBTENEMOS EL FLUX
        Flux<String> varios = servicioSencillo.buscarTodos();

        //COMPROBAMOS LOS RESULTADOS
        StepVerifier.create(varios)
                .expectNext("hola")
                .expectNext("que")
                .expectNext("tal")
                .expectNext("estás")
                .expectComplete()
                .verify();
    }

    //PROBAMOS EL SERVICIO ASINCRONO QUE ESPERA ANTES DE ENVIAR CADA VALOR
    @Test
    public void testVariosAsync(){

        //OBTENEMOS EL FLUX DEL SERVICIO
        Flux<String> varios = servicioSencillo.buscarTodosLento();

        //COMPROBAMOS EL VALOR Y EL INTERVALO DE TIEMPO ENTRE CADA VALOR. APROX TARDA 1 SEGUNDO POR PETICIÓN + EL SEGUNDO DE DELAY = 10 SEGS
        StepVerifier.create(varios)
                .expectNext("hola")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("que")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("tal")
                .thenAwait(Duration.ofSeconds(1))
                .expectNext("estas")
                .thenAwait(Duration.ofSeconds(1))
                .expectComplete()
                .verify();

    }


}
