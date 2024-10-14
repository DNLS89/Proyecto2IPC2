package com.mycompany.proyecto2ipc2.resources;

import Principales.MotorPrograma;
import Principales.Revista;
import Usuarios.Usuario;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;


@Path("explorarRevistas")
public class ExploradorRevistasResource {
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistas() {
        
        String nombreUsuario = "MARIA";
        MotorPrograma motorPrograma = MotorPrograma.getInstance(nombreUsuario);
        
        //MotorPrograma motorPrograma = MotorPrograma.getInstance();
        
        if (motorPrograma.hayRevistasCreadas()) {
            
            Revista[] revistas = motorPrograma.getRevistas();
            
            return Response.ok(revistas).build();
            

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        
    }
    
    
    //LO de abajo es para pasar información, ANTES TENÍA @GET lo cambié por @POST ver eso
    @GET
    @Path("/revistasPublicadas/{nombreAutor}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRevistasPublicadas(@PathParam("nombreAutor") String nombreAutor) {
        
        System.out.println("Recibiendo revistas publicas");
        MotorPrograma motorPrograma = MotorPrograma.getInstance(nombreAutor);
        ArrayList<Revista> revistasPublicadas = motorPrograma.obtenerRevistasPublicadas(nombreAutor);
        
        if (revistasPublicadas != null) {
            System.out.println("Hay revistas");
            return Response.ok(revistasPublicadas).build();
        }
        
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    
    
    
}
