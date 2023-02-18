package DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//ESTE OBJETO SERÁ UTILIZADO PARA EL EJEMPLO DE PROGRAMACIÓN REACTIVA MONO Y FLUX
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private String nombre;
    private String apellido;
}
