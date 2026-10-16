package com.rh.workshop.resource;

import com.rh.workshop.ai.RhAssistantService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/assistente")
public class AssistenteResource {

    @Inject
    RhAssistantService rhAssistantService;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String perguntar(String mensagem) {
        return rhAssistantService.responder(mensagem);
    }
}
