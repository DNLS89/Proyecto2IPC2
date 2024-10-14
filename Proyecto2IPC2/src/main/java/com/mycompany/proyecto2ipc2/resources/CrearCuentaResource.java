
package com.mycompany.proyecto2ipc2.resources;

import Principales.MenuInicio;
import Principales.MotorPrograma;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("crearCuenta")
public class CrearCuentaResource {
    @POST
    @Path("/{nombreUsuario}/{contraseña}/{rol}/{foto}/{hobbies}/{descripcion}/{gustos}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSolicitudPorCodigo(@PathParam("nombreUsuario") String nombreUsuario, @PathParam("contraseña") String contraseña,
            @PathParam("rol") String rol, @PathParam("foto") String foto, @PathParam("hobbies") String hobbies, @PathParam("descripcion") String descripcion,
            @PathParam("gustos") String gustos) {

        
        MenuInicio menuInicio = new MenuInicio();
        
        if (!menuInicio.verificarNombreRegistrado(nombreUsuario)) {
            
            menuInicio.crearUsuarioNuevo(nombreUsuario, contraseña, rol, foto, hobbies, descripcion, gustos);
            //menuInicio.ingresarAlSistema(nombreUsuario);
            
            MotorPrograma motorPrograma = new MotorPrograma(nombreUsuario);
            motorPrograma.extraerDatosUsuario();
            
            //Devuelve un usuario, supongo es bueno para guardar el nombre dle usuario en la cuenta
            //request.getSession().setAttribute("usuario", motorPrograma.extraerDatosUsuario());
            
            
            return Response.ok().build();
            
        } else {
            
            //Nombre ya está registrado
            //Mandar estatus de inválido y mostrar un mensaje de usuario ya ingresado ya creada
            return Response.status(Response.Status.BAD_REQUEST).build();
            
            
            
            
        }
        

    }
}
