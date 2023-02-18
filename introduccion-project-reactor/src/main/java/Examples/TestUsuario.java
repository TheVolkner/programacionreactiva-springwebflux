package Examples;

import DTO.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;


public class TestUsuario {

    //CREAMOS UN LOGGER
    private static final Logger log = LoggerFactory.getLogger(TestUsuario.class);

    public static void main(String[] args) {

        //CREAMOS UN FLUX DE NOMBRES
        Flux<String> nombres = Flux.just("Omar Arvelo","Leonardo Valera","Rei Bast","Jason Dash","Sara McFly");

        //Y CON ESE FLUX INICIAMOS OTRO FLUX DE OBJETOS USUARIO, EL ESPACIO ENTRE CADA NOMBRE Y APELLIDO SERÁ EL DIFERENCIADOR

        //FILTRAMOS LOS QUE EL APELLIDO SEA DASH, Y SOLO LOS QUE COINCIDAN CON ESE APELLIDO DEVOLVERÁ, LUEGO SE COMPRUEBA QUE NINGUNO SEA NULO

        //AL FINAL, DEVOLVERÁ LOS NOMBRES EN MINUSCULAS
        Flux<Usuario> usuarios = nombres.map(nombre -> new Usuario(nombre.split(" ")[0].toUpperCase(),nombre.split(" ")[1].toUpperCase()))
                .filter(usuario -> usuario.getApellido().equalsIgnoreCase("Dash"))
                .doOnNext(usuario -> {
                    if(usuario == null){

                        //SI HAY ALGÚN USUARIO NULO, DEVOLVEMOS ERROR
                        throw new RuntimeException("Los nombres no pueden estar vacios");
                    }
                    System.out.println(usuario.getNombre().concat(" ").concat(usuario.getApellido()));
                })
                .map(usuario -> {

                    String nombre = usuario.getNombre().toLowerCase();
                    usuario.setNombre(nombre);
                    return usuario;
                });

        //IMPRIMIMOS LOS RESULTADOS
        usuarios.subscribe(
                usuario -> log.info(usuario.toString()), //onNext del Publisher
                error -> log.error(error.getMessage()), //onError
                () -> System.out.println("¡Flujo Completo!") //onComplete
        );

    }
}
