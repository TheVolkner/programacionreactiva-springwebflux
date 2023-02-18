import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class EjerciciosTests {

    //PRUEBA DE .mergeWith
    @Test
    public void testMergeWith(){

        StepVerifier.create(returnMergeWith())
                .expectNext("a","c","b","d")
                .verifyComplete();

    }

    public static Flux<String> returnMergeWith(){

        Flux<String> firstFlux = Flux.fromArray(new String[]{"a","b"})
                .delayElements(Duration.ofMillis(100));
        Flux<String> secondFlux = Flux.fromArray(new String[]{"c","d"})
                .delayElements(Duration.ofMillis(125));

        return firstFlux.mergeWith(secondFlux);
    }
}
