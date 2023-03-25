package sistemadealertas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * Clase para crear las alertas
 */
public class Tema implements SujetoTema{
    //Atributos
    private int id;
    private String nombreTema;
    private List<ObserverUsuario> subscriptos;
    private List<Alerta> alertas;
    
    
    //Constructor de Alerta

    public Tema(int id,String nombreTema) {
        this.id=id;
        this.nombreTema = nombreTema;
        this.subscriptos = new ArrayList<>();
        this.alertas = new ArrayList<>();
    }

    //Inicio de area de getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
    }

    public List<ObserverUsuario> getSubscriptos() {
        return subscriptos;
    }

    public void setSubscriptos(List<ObserverUsuario> subscriptos) {
        this.subscriptos = subscriptos;
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }
    //Fin de Area de getters y setters
    
    
    /*
    Este metoo se puede usar de dos formas:
    1-Si el Subscriptor es nulo envia a todos la nueva alerta
    2-Si se le pasa un subscriptor se le envia solo a él la nueva alerta
    */
    public void agregarUnaALertaYenviarla(Alerta alerta,ObserverUsuario subscripto) {
        this.alertas.add(alerta);
        if(subscripto==null){
            notificarTodosUnaAlerta(alerta);
        }else{
            notificarAlertaUnUsuario(alerta,subscripto);
        }
        
    }
    
    //Agrega una alerta a la lista de alertas
    public void agregarAlerta(Alerta alerta) {
        alertas.add(alerta);
    }
    
    
    //Agrega un subscripto a la lista de usuarios subcriptos
    @Override
    public void agregarUsuario(ObserverUsuario subscripto) {
        if(!this.subscriptos.contains(subscripto)){
            this.subscriptos.add(subscripto);
        }else{
            
        }     
    }
    
    //Quita un subscripto d la lista de usuarios subcriptos
    @Override
    public void quitarUsuario(ObserverUsuario subscripto) {
        if(subscriptos.contains(subscripto)){
            subscriptos.remove(subscripto);
        }else{
            //throw new Exception($"Ya existe una siscripcion para {((Usuario)subscripto)}");
        }  
    }

    //Notifica a todos los usuarios que se encuentran en la lista de subcriptos
    @Override
    public void notificarTodosUnaAlerta(Alerta alerta) {
        for(ObserverUsuario subscriptor:subscriptos ){
            subscriptor.mostrarAlerta(alerta);
        }
    }
    
    //Envia las alertas a todos los usuarios subcriptos
    @Override
    public void notificarAlertaUnUsuario(Alerta alerta,ObserverUsuario subscripto) {
        if(subscriptos.contains(subscripto)){  
            subscripto.mostrarAlerta(alerta);
        }
    }
    
    //Notifica todas las alertas del tema a todos los usuarios subscriptos
    @Override
    public void notificarATodosTodasLasAlertas(){
        for(Alerta alerta:alertas){ 
            if(alerta.getFechaExpiracion().isEqual(LocalDateTime.now()) || alerta.getFechaExpiracion().isAfter(LocalDateTime.now()))
                for(ObserverUsuario subscriptor:subscriptos ){
                    subscriptor.mostrarAlerta(alerta);
            }
        }
    }
}
