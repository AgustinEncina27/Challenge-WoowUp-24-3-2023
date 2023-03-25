package sistemadealertas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


// Clase Alerta
class Alerta {
    private int id;
    private String tema;
    private String tipo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaExpiracion;
    private String mensaje;
    private boolean leida;
    private boolean usuarioEspecifico;


    //Constructor Clase alerta
    public Alerta(int id,String tema, String tipo, LocalDateTime fechaCreacion, LocalDateTime fechaExpiracion, String mensaje) {
        this.id=id;
        this.tema = tema;
        this.tipo = tipo;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.mensaje = mensaje;
        this.leida = false;
        this.usuarioEspecifico = false;
    }
    
    //Inicio de area de getters y setters

    public boolean isUsuarioEspecifico() {
        return usuarioEspecifico;
    }

    public void setUsuarioEspecifico(boolean usuarioEspecifico) {
        this.usuarioEspecifico = usuarioEspecifico;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }
     //Fin de area de getters y setters
   
    
    //Muestra en consola la instancia de Alerta
    @Override
    public String toString() {
        return "Alerta{" + "id=" + id + ", tema=" + tema + ", tipo=" + tipo + ", fechaCreacion=" + fechaCreacion + ", fechaExpiracion=" + fechaExpiracion + ", mensaje=" + mensaje + ", leida=" + leida + ", usuarioEspecifico=" + usuarioEspecifico + '}';
    }
  
}