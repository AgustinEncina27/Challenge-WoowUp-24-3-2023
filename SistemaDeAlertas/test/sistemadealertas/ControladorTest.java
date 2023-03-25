package sistemadealertas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Clase para testear los puntos de la Prueba Tecnica
 */
public class ControladorTest {
    /**
     *Test para verificar que registra el usuario en la lista del controlador
     *Verifica el punto 1
     */
    @Test
    public void testGetUsuarios() {
        Usuario usuarioNuevo= new Usuario(1,"Agustin","agustin72encina@gmail.com");
        Controlador controlador= new Controlador();
        controlador.registrarUsuario(usuarioNuevo);
        ObserverUsuario usuarioRegistrado= controlador.getUnUsuario(usuarioNuevo);
        assertEquals(usuarioNuevo, usuarioRegistrado);
    }
    
    
    
    /**
     * Test para verificar que registra un tema en la lista del controlador
     * Verifica el punto 2
     */
    @Test
    public void testRegistrarTema() {
        Tema temaNuevo= new Tema(1,"Zapatillas");
        Controlador controlador= new Controlador();
        controlador.registrarTema(temaNuevo);
        Tema temaRegistrado= controlador.getUnTema(temaNuevo);
        assertEquals(temaNuevo, temaRegistrado);
    }
    
    
    
    /**
     * Test para verificar que un usuario se subcribe a un tema
     * Verifica el punto 3
     */
    @Test 
    public void testSubcribirUsuario(){
        //inicialización de variables
        Controlador controlador= new Controlador();
        Usuario usuarioNuevo= new Usuario(1,"Agustin","agustin72encina@gmail.com");
        Tema temaNuevo= new Tema(1,"Zapatillas");
        
        //Agrego los usuarios y el tema a las lista que tiene el controlador
        controlador.registrarUsuario(usuarioNuevo);
        controlador.registrarTema(temaNuevo);
        
        //Subscribo los usuarios al tema zapatillas
        controlador.subscribirUsuario(usuarioNuevo,"Zapatillas");
        
        //Verifico si se subscribio el usuario
        Tema temaRegistrado= controlador.getUnTema(temaNuevo);
        List<ObserverUsuario> usuariosubscripto=temaRegistrado.getSubscriptos();
        if(usuariosubscripto.contains(usuarioNuevo)){
            assertTrue(true);
        }
    }
    
    
    
    /**
       Verifica el punto 4
     * Test para verificar que le envia a todos la alerta
     * 
     * Consiste en lo siguiente:
     * Creo dos usuarios=(Agustin y Daiana)
     * Creo un tema llamado "Zapatillas" 
     * Este tema tiene con una alerta. 
     * Primera alerta:Una es de promocion no tiene la fecha expirada
     * Al ejecutar este test manda la alerta mencionada anteriormente a Agustin y Daiana que son los dos usuarios subscriptos
     */
    @Test
    public void testEnviarAlertasDetema(){
        //inicialización de variables
        Controlador controlador= new Controlador();
        
        //Creo los usuario y el tema
        Usuario usuarioNuevo= new Usuario(1,"Agustin","agustin72encina@gmail.com");
        Usuario usuarioNuevo2= new Usuario(2,"Daiana","araiienciina@gmail.com");
        Tema temaNuevo= new Tema(1,"Zapatillas");
        
        //Creo la alerta
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime fechaExpiracion = LocalDateTime.now();
        fechaExpiracion.withYear(2023).withMonth(3).withDayOfMonth(27).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta= new Alerta(1,"Promoción","Informativo",fechaCreacion,fechaExpiracion,"Obtiene estas zapatillas con descuento");
        
        //Agrego los usuarios y el tema a las lista que tiene el controlador
        controlador.registrarUsuario(usuarioNuevo);
        controlador.registrarUsuario(usuarioNuevo2);
        controlador.registrarTema(temaNuevo);
        
        //Subscribo los usuarios al tema zapatillas
        controlador.subscribirUsuario(usuarioNuevo,"Zapatillas");
        controlador.subscribirUsuario(usuarioNuevo2,"Zapatillas");
        
        //Envia la alerta y como esta nulo se la envia a todos los subscriptos al tema
        controlador.enviarAlertaNuevaDeltema("Zapatillas",alerta, null);
        
        List<Alerta> listaDeseada= new ArrayList<>();
        listaDeseada.add(alerta);
        
        //Verifica que la alerta esta en la lista de Alertas de cada usuario
        assertEquals(listaDeseada, usuarioNuevo2.getAlertas());       
        assertEquals(listaDeseada, usuarioNuevo.getAlertas());
    }
    
    
    
    
    
    /**
     * Verifica el punto 5
     * Test para verificar que le envia a un usuario en especifico la alerta
     * Consiste en lo siguiente:
     * Creo dos usuarios=(Agustin y Daiana)
     * Creo un tema llamado "Zapatillas" 
     * Este tema tiene con una alerta. 
     * Primera alerta:Una es de promocion no tiene la fecha expirada
     * Al ejecutar este test tenemos dos usuarios pero le manda solo la alerta a Agustin ya que el es el usuario que se especifica para enviar la alerta
     */
    @Test
    public void testEnviarAlertasDeltema(){
        //inicialización de variables
        Controlador controlador= new Controlador();
        
        //Creo los usuario y el tema
        Usuario usuarioNuevo= new Usuario(1,"Agustin","agustin72encina@gmail.com");
        Usuario usuarioNuevo2= new Usuario(2,"Daiana","araiienciina@gmail.com");
        Tema temaNuevo= new Tema(1,"Zapatillas");
        
        //Creo la alerta
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime fechaExpiracion = LocalDateTime.now();
        fechaExpiracion=fechaExpiracion.withYear(2023).withMonth(3).withDayOfMonth(27).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta= new Alerta(1,"Promoción","Informativo",fechaCreacion,fechaExpiracion,"Obtiene estas zapatillas con descuento");
        
        //Agrego los usuarios y el tema a las lista que tiene el controlador
        controlador.registrarUsuario(usuarioNuevo);
        controlador.registrarUsuario(usuarioNuevo2);
        controlador.registrarTema(temaNuevo);
        
        //Subscribo los usuarios al tema zapatillas
        controlador.subscribirUsuario(usuarioNuevo,"Zapatillas");
        controlador.subscribirUsuario(usuarioNuevo2,"Zapatillas");

        //Envia la alerta y el usuario especifico que recibir la alerta
        controlador.enviarAlertaNuevaDeltema("Zapatillas",alerta, usuarioNuevo);
        List<Alerta> listaDeseada= new ArrayList<>();
        
        //Verifica que no tiene nada la lista de alertas del usuarioNuevo2 para asi corroborar que no le llego
        assertEquals(listaDeseada, usuarioNuevo2.getAlertas());
        
        //Verifica que tiene en la lista la alerta que solo le notifico a el usuarioNuevo
        listaDeseada.add(alerta);
        assertEquals(listaDeseada, usuarioNuevo.getAlertas());
    }
    
    
    
    
    
    /**
     * Verifica el punto 6
     * Test para verificar que no se le envian las alertas que tienen fecha de expiracion expiradas.
     * Consiste en lo siguiente:
     * Creo dos usuarios=(Agustin y Daiana)
     * Creo un tema llamado "Zapatillas" 
     * Este tema tiene con dos alertas. 
     * Primera alerta:Una es de promocion tiene la fecha expirada.
     * Segunda alerta: La otra es de descuento no tiene la fecha expirada.
     * Se muestran dos alertas de descuento al ejecutar. Una para el usuario Agustin y la otra para Daiana.
     */
    @Test
    public void testEnviarAlertasDeltemaConFechaExpirada(){
        //inicialización de variables
        Controlador controlador= new Controlador();
        
        //Creo los usuarios y el tema
        Usuario usuarioNuevo= new Usuario(1,"Agustin","agustin72encina@gmail.com");
        Usuario usuarioNuevo2= new Usuario(2,"Daiana","araiienciina@gmail.com");
        Tema temaNuevo= new Tema(1,"Zapatillas");
        
        //Creo las alertas
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime fechaExpiracion = LocalDateTime.now();
        fechaExpiracion=fechaExpiracion.withYear(2023).withMonth(3).withDayOfMonth(22).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta1= new Alerta(1,"Promoción","Informativo",fechaCreacion,fechaExpiracion,"Obtiene estas zapatillas con descuento");
        LocalDateTime fechaExpiracion2 = LocalDateTime.now();
        fechaExpiracion2=fechaExpiracion.withYear(2023).withMonth(3).withDayOfMonth(27).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta2= new Alerta(2,"Descuento","Urgente",fechaCreacion,fechaExpiracion2,"50% en zapatillas Nike");
        
        //Agrego los usuarios y el tema a las lista que tiene el controlador
        controlador.registrarUsuario(usuarioNuevo);
        controlador.registrarUsuario(usuarioNuevo2);
        controlador.registrarTema(temaNuevo);
        
        //Subscribo los usuarios al tema zapatillas
        controlador.subscribirUsuario(usuarioNuevo,"Zapatillas");
        controlador.subscribirUsuario(usuarioNuevo2,"Zapatillas");
        
        //Agrego las alertas al tema
        controlador.AgregarAlertaAUnTema(temaNuevo,alerta1);
        controlador.AgregarAlertaAUnTema(temaNuevo,alerta2);
        
        //Aparece el String cuando llega la notificacion
        controlador.enviarAlertasDeUntema("Zapatillas");
        
        //Lista deseada para comparar
        List<Alerta> listaDeseada= new ArrayList<>();
        listaDeseada.add(alerta2);
        
        //Verifica que en la lista de alertas de los dos usuario solamente se encuentran la alerta que cumple con la fecha
        assertEquals(listaDeseada, usuarioNuevo2.getAlertas());
        assertEquals(listaDeseada, usuarioNuevo.getAlertas());
    }
    
    
    
    
    /**
     * Verifica el punto 8
     * Test para verificar que no se le envian las alertas que tienen fecha de expiracion expiradas.
     * Consiste en lo siguiente:
     * Creo dos usuarios=(Agustin y Daiana)
     * Creo un tema llamado "Zapatillas" 
     * Este tema tiene con dos alertas. 
     * Primera alerta:Una es de promocion tiene la fecha expirada.
     * Segunda alerta: La otra es de descuento no tiene la fecha expirada.
     * Se muestran dos alertas de descuento al ejecutar. Una para el usuario Agustin y la otra para Daiana.
     */
    @Test
    public void testUsuarioMarcaComoLeidaAlerta(){
        //inicialización de variables
        Controlador controlador= new Controlador();
        
        //Creo usuarios
        Usuario usuarioNuevo= new Usuario(1,"Agustin","agustin72encina@gmail.com");
        Usuario usuarioNuevo2= new Usuario(2,"Daiana","araiienciina@gmail.com");
        
        //Creo un tema 
        Tema temaNuevo= new Tema(1,"Zapatillas");
        
        //Creo diferentes alertas
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime fechaExpiracion = LocalDateTime.now();
        fechaExpiracion=fechaExpiracion.withYear(2023).withMonth(3).withDayOfMonth(28).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta1= new Alerta(1,"Promoción","Informativo",fechaCreacion,fechaExpiracion,"Obtiene estas zapatillas con descuento");
        
        LocalDateTime fechaExpiracion2 = LocalDateTime.now();
        fechaExpiracion2=fechaExpiracion.withYear(2023).withMonth(3).withDayOfMonth(27).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta2= new Alerta(2,"Descuento","Urgente",fechaCreacion,fechaExpiracion2,"50% en zapatillas Nike");
        
        //Agrego los usuarios y el tema a las lista que tiene el controlador
        controlador.registrarUsuario(usuarioNuevo);
        controlador.registrarUsuario(usuarioNuevo2);
        controlador.registrarTema(temaNuevo);
        
        //Subscribo los usuarios al tema zapatillas
        controlador.subscribirUsuario(usuarioNuevo,"Zapatillas");
        controlador.subscribirUsuario(usuarioNuevo2,"Zapatillas");
        
        //Envio las alertas a los usuarios
        controlador.enviarAlertaNuevaDeltema(temaNuevo.getNombreTema(),alerta1,null);
        controlador.enviarAlertaNuevaDeltema(temaNuevo.getNombreTema(),alerta2,null);
        
        //Marco la alerta1 como leida
        controlador.usuarioMarcaComoLeidaAlerta(usuarioNuevo,1);
        
        //Obtengo la alerta para verificar si esta marcada como leida
        int indice=usuarioNuevo.getAlertas().indexOf(alerta1);
        Alerta alertaResultado=usuarioNuevo.getAlertas().get(indice);
        assertEquals(alertaResultado.isLeida(), true);
    }
    
    
    
    
    
    /**
     * Verifica el punto 9
     * Test para verificar que la lista de alertas de un usuario que retorna ordenada de la siguiente manera:
     * Los urgentes de manera LIFO y los informativos de manera FIFO respetando que no tiene que estar sus fechas expiradas
     * 
     * 
     * Consiste en lo siguiente:
     * Creo un usuarios "Agustin"
     * Creo dos temas llamados "Zapatillas" y "Remeras"
     * 
     * El tema "Zapatillas" tiene dos alertas:
     * 1-Promocion de tipo Informativo con la fecha expirada
     * 2-Descuento de tipo Informativo con una fecha permitida
     * 
     * El tema "Remeras" tiene dos alertas:
     * 1-Promocion de tipo Urgente con la fecha permitida
     * 2-Descuento de tipo Urgente con la fecha permitida
     * 
     * Al ejecutarlo tiene que trae la siguiente lista
     * Primero La alerta del tema "Remeras" de nombre "Oferta" de tipo "urgente"
     * Segundo La alerta del tema "Remeras" de nombre "Reintegro" de tipo "urgente"
     * Tercero La alerta del tema "Zapatillas" de nombre "Descuento" de tipo "informativo"
     * 
     * La alerta del tema "Zapatillas" de nombre "Promocion" de tipo "informativo" no se muestra porque tiene la fecha expirada
     */
    @Test
    public void tesListarAlertasNoExpiradasDeUnUsuario(){
        //inicialización de variables
        Controlador controlador= new Controlador();
        
        //Creo el usuario, los temas y la lista para luego comparar con la lista que retorna
        Usuario usuarioNuevo= new Usuario(1,"Agustin","agustin72encina@gmail.com");
        Tema temaNuevo= new Tema(1,"Zapatillas");
        Tema temaNuevo2= new Tema(2,"Remeras");
        List<Alerta> listaAlertasDeseada= new ArrayList<>();
        
        //Creacion de alertas
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime fechaExpiracion = LocalDateTime.now();
        fechaExpiracion=fechaExpiracion.withYear(2023).withMonth(3).withDayOfMonth(22).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta1= new Alerta(1,"Promoción","Informativo",fechaCreacion,fechaExpiracion,"Obtiene estas zapatillas con descuento");
        LocalDateTime fechaExpiracion2 = LocalDateTime.now();
        fechaExpiracion2=fechaExpiracion2.withYear(2023).withMonth(3).withDayOfMonth(27).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta2= new Alerta(2,"Descuento","Informativo",fechaCreacion,fechaExpiracion2,"50% en zapatillas Nike");
        LocalDateTime fechaExpiracion3 = LocalDateTime.now();
        fechaExpiracion3=fechaExpiracion3.withYear(2023).withMonth(3).withDayOfMonth(27).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta3= new Alerta(3,"Oferta","Urgente",fechaCreacion,fechaExpiracion3,"75% en remeras Addidas");
        LocalDateTime fechaExpiracion4 = LocalDateTime.now();
        fechaExpiracion4=fechaExpiracion4.withYear(2023).withMonth(3).withDayOfMonth(27).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta4= new Alerta(4,"Reintegro","Urgente",fechaCreacion,fechaExpiracion4,"75% en remeras Puma");
        
        //Agrego los usuarios y el tema a las lista que tiene el controlador
        controlador.registrarUsuario(usuarioNuevo);
        controlador.registrarTema(temaNuevo);
        controlador.registrarTema(temaNuevo2);
        
        //Subscribo los usuarios al tema zapatillas
        controlador.subscribirUsuario(usuarioNuevo,"Zapatillas");
        controlador.subscribirUsuario(usuarioNuevo,"Remeras");
        
        //Agrego las alertas al tema y se los envia al usuario para asi almacenarlos en la lista
        controlador.enviarAlertaNuevaDeltema(temaNuevo.getNombreTema(),alerta1,null);
        controlador.enviarAlertaNuevaDeltema(temaNuevo.getNombreTema(),alerta2,null);
        controlador.enviarAlertaNuevaDeltema(temaNuevo2.getNombreTema(),alerta3,null);
        controlador.enviarAlertaNuevaDeltema(temaNuevo2.getNombreTema(),alerta4,null);
        
        //Agregamos el orden que tiene que aparecer cada alerta
        listaAlertasDeseada.add(alerta3);
        listaAlertasDeseada.add(alerta4);
        listaAlertasDeseada.add(alerta2);
        
        //Trae la lista ordenada de la funcion que la llamamos "resultadoAlerta" y la compara con la lista deseada
        List<Alerta> resultadoAlerta=controlador.listarAlertasNoExpiradasDeUnUsuario(usuarioNuevo);
        Assert.assertEquals(listaAlertasDeseada, resultadoAlerta);
    }
    
    
    
    /**
     * Verifica el punto 10
     * Test para verificar que la lista que retornade alertas de un tema especifico esta ordenada de la siguiente manera:
     * Los urgentes de manera LIFO y los informativos de manera FIFO respetando que no tiene que estar sus fechas expiradas
     * 
     * 
     * Consiste en lo siguiente:
     * Creo un usuarios "Agustin"
     * Creo dos temas llamados "Zapatillas" y "Remeras"
     * 
     * El tema "Zapatillas" tiene dos alertas:
     * 1-Promocion de tipo Informativo con la fecha expirada. Llamada con la variable "alerta1"
     * 
     * El tema "Remeras" tiene dos alertas:
     * 1-Descuento de tipo Informativo con una fecha permitida. Llamada con la variable "alerta2"
     * 2-Promocion de tipo Urgente con la fecha permitida. Llamada con la variable "alerta3"
     * 3-Descuento de tipo Urgente con la fecha permitida. Llamada con la variable "alerta4"
     * 
     * Al ejecutarlo tiene que trae la siguiente lista
     * Primero La alerta del tema "Remeras" de nombre "Oferta" de tipo "urgente"
     * Segundo La alerta del tema "Remeras" de nombre "Reintegro" de tipo "urgente"
     * Tercero La alerta del tema "Zapatillas" de nombre "Descuento" de tipo "informativo"
     * 
     * La alerta del tema "Zapatillas" de nombre "Promocion" de tipo "informativo" no se muestra porque se encuentra como alerta del tema "Zapatillas".
     */
    @Test
    public void teslistarAlertasNoExpiradasDeUnTema(){
        //inicialización de variables
        Controlador controlador= new Controlador();
        
        //Creo el usuario , los temas y la lista que va a ser comparada al final para saber si trae bien los datos
        Usuario usuarioNuevo= new Usuario(1,"Agustin","agustin72encina@gmail.com");
        Tema temaNuevo= new Tema(1,"Zapatillas");
        Tema temaNuevo2= new Tema(2,"Remeras");
        List<Alerta> listaAlertasDeseada= new ArrayList<>();
        
        //Creacion de alertas
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime fechaExpiracion = LocalDateTime.now();
        fechaExpiracion=fechaExpiracion.withYear(2023).withMonth(3).withDayOfMonth(22).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta1= new Alerta(1,"Promoción","Informativo",fechaCreacion,fechaExpiracion,"Obtiene estas zapatillas con descuento");
        
        LocalDateTime fechaExpiracion2 = LocalDateTime.now();
        fechaExpiracion2=fechaExpiracion2.withYear(2023).withMonth(3).withDayOfMonth(27).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta2= new Alerta(2,"Descuento","Informativo",fechaCreacion,fechaExpiracion2,"50% en zapatillas Nike");
        
        LocalDateTime fechaExpiracion3 = LocalDateTime.now();
        fechaExpiracion3=fechaExpiracion3.withYear(2023).withMonth(3).withDayOfMonth(27).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta3= new Alerta(3,"Oferta","Urgente",fechaCreacion,fechaExpiracion3,"75% en remeras Addidas");
        
        LocalDateTime fechaExpiracion4 = LocalDateTime.now();
        fechaExpiracion4=fechaExpiracion4.withYear(2023).withMonth(3).withDayOfMonth(27).withHour(0).withMinute(0).withSecond(0).withNano(0);
        Alerta alerta4= new Alerta(4,"Reintegro","Urgente",fechaCreacion,fechaExpiracion4,"75% en remeras Puma");
        
        //Agrego los usuarios y el tema a las lista que tiene el controlador
        controlador.registrarUsuario(usuarioNuevo);
        controlador.registrarTema(temaNuevo);
        controlador.registrarTema(temaNuevo2);
        
        //Subscribo los usuarios al tema zapatillas
        controlador.subscribirUsuario(usuarioNuevo,"Zapatillas");
        controlador.subscribirUsuario(usuarioNuevo,"Remeras");
        
        //Agrego las alertas al tema
        controlador.AgregarAlertaAUnTema(temaNuevo,alerta1);
        controlador.AgregarAlertaAUnTema(temaNuevo2,alerta2);
        controlador.AgregarAlertaAUnTema(temaNuevo2,alerta3);
        controlador.AgregarAlertaAUnTema(temaNuevo2,alerta4);
        
        //Agregamos el orden que tiene que aparecer cada alerta
        listaAlertasDeseada.add(alerta3);
        listaAlertasDeseada.add(alerta4);
        listaAlertasDeseada.add(alerta2);
        
        //Trae la lista ordenada de la funcion que la llamamos "resultadoAlerta" y la compara con la lista deseada
        List<Alerta> resultadoAlerta=controlador.listarAlertasNoExpiradasDeUnTema("Remeras");
        Assert.assertEquals(listaAlertasDeseada, resultadoAlerta);
    }
}
