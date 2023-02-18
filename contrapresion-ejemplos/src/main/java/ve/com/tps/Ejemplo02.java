package ve.com.tps;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;

public class Ejemplo02 {

    //SOLICITAMOS CON CONTRAPRESION, HACIENDO USO DE BASE SUBSCRIBER Y SU METODO HOOK ON SUBSCRIBE
    //QUE SOLO VAMOS A SOLICITAR 5 VALORES
    public static void main(String[] args) {

        Flux<Integer> flux = Flux.range(1,100);

        flux.log().subscribe(new BaseSubscriber<Integer>() {
            //CON ESTE MÉTODO PODEMOS INDICAR CUANTOS VALORES TOMA LA SUBSCRIPCIÓN
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                request(10);
            }

            //ESTE MÉTODO PERMITE LEER LOS DATOS DESDE LA SUBSCRIPCIÓN
            //Y HACER UNA ACCIÓN SEGÚN LA CANTIDAD DE DATOS LEIDOS
            @Override
            protected void hookOnNext(Integer value) {

                if(value == 5){

                    cancel();
                }
            }
        });
    }
}
