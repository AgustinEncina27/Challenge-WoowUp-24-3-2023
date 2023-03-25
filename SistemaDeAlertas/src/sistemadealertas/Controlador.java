package sistemadealertas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

/**
 *
 * Clase controlador para gestionar todo los puntos de la Prueba
 */
public class Controlador{
    private List<Usuario> usuarios;
    private List<Tema> temas;

    //Constructor del controlador
    public Controlador() {
        this.usuarios= new ArrayList<>();
        this.temas= new ArrayList<>();
    }
    
    //Inicio de area de getters y setters
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Tema> getTemas() {
        return temas;
    }

    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }
    //fin de area de getters y setters
    
    //Devuleve un usuario de la lista usuarios
    public Usuario getUnUsuario(Usuario usuario){
        int usuarioEncontrado=usuarios.indexOf(usuario);
        return usuarios.get(usuarioEncontrado);
    }
    
    //Devuelve un tema de la lista tema
    public Tema getUnTema(Tema tema){
        int temaEncontrado=temas.indexOf(tema);
        return temas.get(temaEncontrado);
    }
       
    
    //Agrega una alerta a un temam
    public void AgregarAlertaAUnTema(Tema tema,Alerta alerta){
        int temaEncontrado=temas.indexOf(tema);
        Tema temaRegistrado=temas.get(temaEncontrado);
        temaRegistrado.agregarAlerta(alerta);
    }
      
    
    
    //Punto 1--Registra un usuario
    public void registrarUsuario(Usuario usuario){
        usuarios.add(usuario);  
    }
    
    //Punto 2--Registra un tema
    public void registrarTema(Tema tema){
        temas.add(tema);
    }
    
    
    //Punto 3--Suscribe un usuario a un tema
    public void subscribirUsuario(Usuario usuario,String nombreTema){
        Usuario usuarioAgregar=buscarUsuarioEnListaUsuarios(usuario);      
        for(Tema tema:temas){
            if(tema.getNombreTema().equals(nombreTema)){
                tema.agregarUsuario(usuarioAgregar);
                usuario.agregarTemaInteres(nombreTema);
            }
        }         
    }
    
    /*
    Punto 4--Si le mandas el usuario nulo le envia a todos la alerta
    punto 5--Si le mandas con el nombre de usuario le envia solamente a ese usuario
    */
    public void enviarAlertaNuevaDeltema(String nombreTema,Alerta alerta,Usuario usuario){  
        if(usuario==null){
            Tema tema=buscarTemaEnListaTemas(nombreTema);
            tema.agregarUnaALertaYenviarla(alerta,usuario);
        }else{
            if(usuarios.contains(usuario)){
                Tema tema=buscarTemaEnListaTemas(nombreTema);
                tema.agregarUnaALertaYenviarla(alerta,usuario); 
            }
        }
        
    }
    
    //Punto 6--Enviar a todos los usuarios las alertas(las expiradas no se les envian)
    public void enviarAlertasDeUntema(String nombreTema){  
        Tema tema=buscarTemaEnListaTemas(nombreTema);
        tema.notificarATodosTodasLasAlertas();
    }

    
    //Punto 8--Usuario marca como leida una alerta
    public void usuarioMarcaComoLeidaAlerta(Usuario usuario,int idAlerta){
        Usuario usuarioEncontrado=this.buscarUsuarioEnListaUsuarios(usuario);
        usuarioEncontrado.marcaComoLeido(idAlerta);
    }
    
    //Punto 9--Listar las alertas no expiradas de un usuario 
    public List<Alerta> listarAlertasNoExpiradasDeUnUsuario(Usuario usuario){
        //Tenemos una cola, una pila y las listas para gestionar las alertas
        List<Alerta> alertasUsuario= new ArrayList<>();
        List<Alerta> alertasOrdenadas= new ArrayList<>();
        Queue<Alerta> colaAlertasInformativas = new LinkedList<Alerta>();
        Stack<Alerta> pilaAlertasInformativas = new Stack<>();
        
        
        //Busca al usuario y almacena sus temas de interes
        Usuario usuarioEncontrado= buscarUsuarioEnListaUsuarios(usuario);
        alertasUsuario=usuarioEncontrado.getAlertas();
        for(Alerta alerta:alertasUsuario){
                        //Comprueba si la fecha esta expirada
                        if(alerta.getFechaExpiracion().isEqual(LocalDateTime.now()) || alerta.getFechaExpiracion().isAfter(LocalDateTime.now())){
                            if(alerta.getTipo().equals("Informativo")){
                                colaAlertasInformativas.add(alerta);
                            }else{
                                pilaAlertasInformativas.push(alerta);
                            }
                        }
        }
        
        //Agrega las alertas urgentes a la lista alertasOrdenas
        for(Alerta pila:pilaAlertasInformativas){
            alertasOrdenadas.add(pila);
        }
        //Agrega las alertas informativas a la lista alertasOrdenas
        for(Alerta cola:colaAlertasInformativas){
            alertasOrdenadas.add(cola);
        } 
        
        //Limpia la cola y la pila
        colaAlertasInformativas.clear();
        pilaAlertasInformativas.clear();
        
        return  alertasOrdenadas;
    }

    //Punto 10--Listar las alertas no expiradas de un tema tema
    public List<Alerta> listarAlertasNoExpiradasDeUnTema(String nombreTema){
        Queue<Alerta> colaAlertasInformativas = new LinkedList<Alerta>();
        Stack<Alerta> pilaAlertasInformativas = new Stack<>();
        List<Alerta> alertasDelTemaSeleccionado= new ArrayList<>();  
        List<Alerta> alertasOrdenadas= new ArrayList<>();
        
        /*
        Busca las alertas de los temas y los almacena en la cola llamada "colaAlertasInformativas" si es una alerta informativa
        y en la pila llamada "pilaAlertasInformativas" las alertas "Urgente"
        */
        for(Tema tema:temas){
            if(tema.getNombreTema().equals(nombreTema)){
                alertasDelTemaSeleccionado=tema.getAlertas();
                for(Alerta alerta:alertasDelTemaSeleccionado){
                    //Comprueba si la fecha esta expirada
                    if(alerta.getFechaExpiracion().isEqual(LocalDateTime.now()) || alerta.getFechaExpiracion().isAfter(LocalDateTime.now())){
                        if(alerta.getTipo().equals("Informativo")){
                            colaAlertasInformativas.add(alerta);
                        }else{
                            pilaAlertasInformativas.push(alerta);
                        } 
                    }
                }
                break;
            }
        }
        
        //Agrega las alertas urgentes a la lista alertasOrdenas
        for(Alerta pila:pilaAlertasInformativas){
            alertasOrdenadas.add(pila);
        }
        //Agrega las alertas informativas a la lista alertasOrdenas
        for(Alerta cola:colaAlertasInformativas){
            alertasOrdenadas.add(cola);
        } 
        //Limpia la cola y la pila
        colaAlertasInformativas.clear();
        pilaAlertasInformativas.clear();
        
        return alertasOrdenadas;
    }
    
    //Busca al usuario en la lista de Usuarios
    public Usuario buscarUsuarioEnListaUsuarios(Usuario usuario){
        int indice=usuarios.indexOf(usuario);
        if(indice!=-1){
            return usuarios.get(indice);
        }else{
            System.out.println("El usuario no esta registrado");
            return null;
        }
    }
    
    //Busca el tema en la lista de temas
    public Tema buscarTemaEnListaTemas(String nombreTema){
        for(Tema tema:temas){
            if(tema.getNombreTema().equals(nombreTema)){
                return tema;
            }
        }
        System.out.println("No se encontro el tema");
        Tema tema1=null;
        return tema1;
    }
    
}