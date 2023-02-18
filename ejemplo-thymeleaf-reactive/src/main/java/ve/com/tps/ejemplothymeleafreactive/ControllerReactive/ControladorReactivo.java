package ve.com.tps.ejemplothymeleafreactive.ControllerReactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import ve.com.tps.ejemplothymeleafreactive.Repository.ProductoRepository;
import ve.com.tps.ejemplothymeleafreactive.Services.ProductoService;

//CREAMOS UN CONTROLADOR REACTIVO
@Controller
@Slf4j
public class ControladorReactivo {

    //INYECTAMOS EL REPOSITORIO
    @Autowired
    private ProductoService productoService;


    //GENERAMOS UN MÉTODO PARA OBTENER EL FLUJO DEL SERVICIO, AÑADIRLO A UN REACTIVE DATA DRIVER CONTEXT VALUE
    //PARA AGREGARSELO AL MODEL
    @RequestMapping("/lista")
    public String listaProductos(Model model){

        //GUARDAMOS LA LISTA COMO UN ATRIBUTO REACTIVO
        //SIRVE PARA EMITIR ELEMENTOS DE FORMA ASÍNCRONA
        //PARA PODER RECIBIR LOS ATRIBUTOS QUE EL SERVICIO ENVÍA CADA 3 SEGS
        IReactiveDataDriverContextVariable listaReactiva = new ReactiveDataDriverContextVariable(productoService.retornarTodos());

        //AÑADIMOS AL MODELO LA LISTA
        model.addAttribute("listaProductos",listaReactiva);

        return "lista";
    }


}
