package sistemadealertas;

/**
 *
 * Interfaz para el Observado que es el Tema 
 */
public interface SujetoTema {
    public abstract void agregarUsuario(ObserverUsuario usuario);
    public abstract void quitarUsuario(ObserverUsuario usuario);
    public abstract void notificarTodosUnaAlerta(Alerta alerta);
    public abstract void notificarATodosTodasLasAlertas();
    public void notificarAlertaUnUsuario(Alerta alerta,ObserverUsuario usuario); 
}
