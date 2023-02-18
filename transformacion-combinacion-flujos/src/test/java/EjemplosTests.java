import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

//EJEMPLO DE TRANSFORMACIONES CON .map Y .flatmap
public class EjemplosTests {

    //.MAP ES PARA DATOS SINCRONOS Y QUE SEAN DE UNA ENTRADA TENGA RESULTADO UNA SALIDA
    @Test
    public void testTransformMap(){

        //CREAMOS UNA LISTA DE STRING
        List<String> nombres = Arrays.asList("google","abc","fb","stackoverflow");

        //INICIALIZAMOS EL FLUX DE STRING CON ESA LISTA ITERABLE
        //FILTRAMOS A LOS QUE TENGAN MÁS DE 5 LETRAS Y LO TRANSFORMAMOS A MAYUSCULA

        //EN ESTE CASO, ENTRA UN DATO, Y SE DEVUELVE OTRO DATO ÚNICO.
        Flux<String> nombresFlux = Flux.fromIterable(nombres)
                .filter(nombre -> nombre.length() > 5)
                .map(nombre -> nombre.toUpperCase())
                .log();
        //CON STEP VERIFIER COMPROBAMOS QUE LA SALIDA SEA CORRECTA
        StepVerifier.create(nombresFlux)
                .expectNext("GOOGLE","STACKOVERFLOW")
                .verifyComplete();
    }

    //.flatMap ES PARA DATOS ASINCRONOS QUE TENGAN POR CADA ENTRADA DE DATOS, VARIAS SALIDAS.
    @Test
    public void testTransformUsingFlatMap(){
        //CREAMOS UNA LISTA DE STRING
        List<String> nombres = Arrays.asList("google","abc","fb","stackoverflow");

        //INICIALIZAMOS EL FLUX DE STRING CON ESA LISTA ITERABLE
        //FILTRAMOS A LOS QUE TENGAN MÁS DE 5 LETRAS Y LO TRANSFORMAMOS A MAYUSCULA

        //ESTAMOS GENERANDO UN FLUJO PARA CADA STRING, UNO PARA GOOGLE, UNO PARA ABC, ETC.
        //CON ESTO, ESTAMOS GENERANDO DENTRO DEL FLUJO FLUX VARIOS FLUJOS MONO.
        //PARA HACER ESTE TIPO DE TAREAS, SE PRECISA A .flatMap
        Flux<String> nombresFlux = Flux.fromIterable(nombres)
                .flatMap(nombre -> {
                    return Mono.just(nombre.toUpperCase());
                })
                .log();
        //CON STEP VERIFIER COMPROBAMOS QUE LA SALIDA SEA CORRECTA
        StepVerifier.create(nombresFlux)
                .expectNext("GOOGLE","ABC","FB","STACKOVERFLOW")
                .verifyComplete();

    }

    //.merge COMBINA FLUJOS DE DATOS EN UNO SOLO
    @Test
    public void testCombinarFlujosUsandoMerge(){

        //CREAMOS 2 FLUJOS DE DATOS
        Flux<String> names1 = Flux.just("Blenders","Old","Jhonny");
        Flux<String> names2 = Flux.just("Pride","Monk","Walker");

        //Y CON .merge LOS COMBINAMOS DENTRO DE UNO SOLO.

        //ESTA PETICION ES GUARDAR DENTRO DE UN FLUJO, A 2 FLUJOS DE DATOS
        Flux<String> namesMerged = Flux.merge(names1,names2).log();

        StepVerifier.create(namesMerged)
                .expectNext("Blenders","Old","Jhonny","Pride","Monk","Walker")
                .verifyComplete();
    }

    //USAMOS .merge PARA COMBINAR FLUJOS APLICANDO UN DELAY A LAS PETICIONES
    @Test
    public void testCombinarFlujosUsandoMergeConDelay(){

        //CREAMOS 2 FLUJOS DE DATOS
        Flux<String> names1 = Flux.just("Blenders","Old","Jhonny")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> names2 = Flux.just("Pride","Monk","Walker")
                .delayElements(Duration.ofSeconds(1));

        //Y CON .merge LOS COMBINAMOS DENTRO DE UNO SOLO.

        //ESTA PETICION ES GUARDAR DENTRO DE UN FLUJO, A 2 FLUJOS DE DATOS
        Flux<String> namesMerged = Flux.merge(names1,names2).log();

        //COMPROBAMS LOS DATOS DEL FLUX, EN ESTE CASO ESPERAMOS LA SUBSCRIPCION, Y QUE DURE 6 SEGUNDOS LA EJECUCIÓN
        StepVerifier.create(namesMerged)
                .expectSubscription()
                .expectNextCount(6)
                .verifyComplete();
    }


    //.concat PERMITE COMBINAR FLUJOS, MUY SIMILAR A .merge
    @Test
    public void testCombinarFlujosUsandoConcatYDelay(){

        //CREAMOS 2 FLUJOS DE DATOS
        Flux<String> names1 = Flux.just("Blenders","Old","Jhonny")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> names2 = Flux.just("Pride","Monk","Walker")
                .delayElements(Duration.ofSeconds(1));

        //Y CON .merge LOS COMBINAMOS DENTRO DE UNO SOLO.

        //ESTA PETICION ES GUARDAR DENTRO DE UN FLUJO, A 2 FLUJOS DE DATOS
        Flux<String> namesConcat = Flux.concat(names1,names2).log();

        //ESPERAMOS LA SUBSCRIPCIÓN Y ESPERAMOS LOS SIGUIENTES DATOS
        StepVerifier.create(namesConcat)
                .expectSubscription()
                .expectNext("Blenders","Old","Jhonny","Pride","Monk","Walker")
                .verifyComplete();
    }


    //.zip PERMITE A PARTIR DE UN ELEMENTO DE DOS FLUJOS, GENERAR OTRO FLUJO CON LA COMBINACIÓN DE ESOS 2 ELEMENTOS
    @Test
    public void testCombinarFlujosUsandoZip(){

        //CREAMOS 2 FLUJOS DE DATOS
        Flux<String> names1 = Flux.just("Blenders","Old","Jhonny")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> names2 = Flux.just("Pride","Monk","Walker")
                .delayElements(Duration.ofSeconds(1));

        //Y CON .merge LOS COMBINAMOS DENTRO DE UNO SOLO.

        //ESTA PETICION ES GUARDAR DENTRO DE UN FLUJO, A 2 FLUJOS DE DATOS

        //PARA GENERAR EL ZIP, INDICAS LOS 2 FLUJOS, Y POR ÚLTIMO MANDAS UNA LAMBDA INDICANDO
        //EL TIPO DE OPERACIÓN QUE SE HARÁ PARA FUSIONAR LOS ELEMENTOS DE LOS DOS FLUJOS ENTRE SÍ
        //EN ESTE, SOLO SE CONCATENAN CON UN ESPACIO INTERMEDIO
        Flux<String> namesZip = Flux.zip(names1,names2,((n1,n2) -> {
            return n1.concat(" ").concat(n2);
        })).log();

        //ESPERAMOS LA SUBSCRIPCIÓN Y ESPERAMOS LOS SIGUIENTES DATOS
        StepVerifier.create(namesZip)
                .expectNext("Blenders Pride","Old Monk","Jhonny Walker")
                .verifyComplete();
    }


}
