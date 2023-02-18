package ve.com.tps.ejemplothymeleafreactive.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import ve.com.tps.ejemplothymeleafreactive.Entity.Productos;
import ve.com.tps.ejemplothymeleafreactive.Repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    //ESTE MÉTODO SE ENCARGA DE OBTENER LOS DOS FLUJOS DEL REPOSITORIO Y COMBINARLOS
    //RETORNANDO EL FLUJO NUEVO
    public Flux<Productos> retornarTodos(){

        //OBTENEMOS LOS 2 FLUJOS DEL REPOSITORIO
       Flux<Productos> buscarFlux1 = productoRepository.buscarTodos();

       Flux<Productos> buscarFlux2 = productoRepository.buscarOtros2();

       //LOS COMBINAMOS CON MERGE Y LO RETORNAMOS
       return Flux.merge(buscarFlux1,buscarFlux2);

    }
}
