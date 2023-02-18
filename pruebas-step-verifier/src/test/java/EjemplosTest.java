import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class EjemplosTest {

    //STEP VERIFIER COMPRUEBA EL COMPORTAMIENTO DE FLUX Y MONO CUANDO
    //POSEE UN SUBSCRIPTOR

    //PROBAMOS COMPORTAMIENTO DE FLUX
    @Test
    public void testFlux(){

        Flux<Integer> fluxToTest = Flux.just(1,2,3,4,5);

        //CREAMOS LA PRUEBA
        //PERMITE INDICAR EL PROCESO COMO DEBERÍA DEVOLVER LOS RESULTADOS
        StepVerifier.create(fluxToTest)
                //.expectNext INDICA QUE VALOR SE ESPERA A CONTINUACIÓN
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectNext(4)
                .expectNext(5)
                //.expectComplete INDICA QUE SE ESPERA LA CULMINACIÓN DEL PROCESO
                .expectComplete()
                //.verify COMPRUEBA LA FINALIZACIÓN DEL STEP VERIFIER.
                .verify();
    }

    @Test
    public void testFluxString(){

        //HACEMOS UN FLUX DE VARIOS ELEMENTOS, EN ESTE CASO NOMBRES Y FILTRAMOS PARA REMOVER LOS QUE TIENEN 5 O MÁS LETRAS
        //ADEMÁS, LOS COLOCAMOS TODOS EN MAYÚSCULAS
        Flux<String> fluxToTest = Flux.just("Omar","Leo","Rei","Sara","Jason","Jaden")
                .filter(nombre -> nombre.length() < 5)
                .map(String::toUpperCase);

        //COMPROBAMOS QUE LA SALIDA DE RESULTADOS SEA LA ESPERADA
        StepVerifier.create(fluxToTest)
                .expectNext("OMAR")
                .expectNext("LEO")
                .expectNext("REI")
                .expectNextMatches(nombre -> nombre.startsWith("S"))
                .expectComplete()
                .verify();




    }
}
