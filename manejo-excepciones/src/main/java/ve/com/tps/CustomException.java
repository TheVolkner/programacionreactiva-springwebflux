package ve.com.tps;
//CREAMOS UNA CLASE PERSONALIZADA PARA TRANSFORMAR EL DATO DEL ERROR A ESTE EN EL MAP
public class CustomException extends Exception{

    public  CustomException(String msj,Throwable exception){

        super(msj,exception);
    }
}

