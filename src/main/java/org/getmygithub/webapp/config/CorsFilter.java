package org.getmygithub.webapp.config;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        // Autoriser l'accès depuis n'importe quel domaine
        headers.add("Access-Control-Allow-Origin", "*");

        // Autoriser les méthodes HTTP spécifiques (GET, POST, PUT, DELETE, etc.)
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

        // Autoriser les en-têtes personnalisés (ajouter les en-têtes requis ici)
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Configurer la mise en cache des en-têtes CORS pendant 1 heure
        headers.add("Access-Control-Max-Age", "3600");
    }
}