package ve.com.tps.ejemplothymeleafreactive.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Productos {

    private Integer numero;
    private Integer importe;

    private String concepto;

}
