package sistemadealertas;


import java.util.ArrayList;
import java.util.List;

// Clase Usuario
class Usuario implements ObserverUsuario {
    private int id;
    private String nombre;
    private String correoElectronico;
    private List<String> temasInteres;
    private List<Alerta> alertas;
    
    //Constructor de clase Usuario
    public Usuario(int id,String nombre, String correoElectronico) {
        this.id=id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.temasInteres = new ArrayList<>();
        this.alertas = new ArrayList<>();
    }
    
    //Inicio de area de getters y setters
    public void setTemasInteres(List<String> temasInteres) {
        this.temasInteres = temasInteres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public List<String> getTemasInteres() {
        return temasInteres;
    }

    public void agregarTemaInteres(String tema) {
        this.temasInteres.add(tema);
    }

    public List<Alerta> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<Alerta> alertas) {
        this.alertas = alertas;
    }
        
    //Fin de area de getters y setters
    
    //Muestra las alertas recibidas
    @Override
    public void mostrarAlerta(Alerta alerta) {
        alertas.add(alerta);
    }
    
    //Muestra en consola el usuario
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", correoElectronico=" + correoElectronico + ", temasInteres=" + temasInteres + ", alertas=" + alertas + '}';
    }
    
    //Marca como leida una alerta
    public void marcaComoLeido(int id){
       for(Alerta alerta:alertas){
          if(alerta.getId()==id){
              alerta.setLeida(true);
          }
       }
    }
    
    
    
}