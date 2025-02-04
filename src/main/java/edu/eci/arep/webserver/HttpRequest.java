package edu.eci.arep.webserver;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private String path = null;
    private Map<String, String> queryParams = null;

    public HttpRequest(String path, String query){
        this.path = path;
        this.queryParams = parseQuery(query);
    }

    /**
     * Método para analizar la cadena de consulta (query) y devolver un mapa con los parámetros.
     */
    private Map<String, String> parseQuery(String query) {
        Map<String, String> params = new HashMap<>();
        if (query != null && !query.isEmpty()) {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    params.put(keyValue[0], keyValue[1]);
                }
            }
        }
        return params;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the query parameters as a Map
     */
    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    /**
     * Método para obtener el valor de un parámetro específico.
     * @param var El nombre del parámetro
     * @return El valor del parámetro o null si no existe
     */
    public String getValue(String var) {
        return queryParams.get(var);
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @param query the query string to set
     */
    public void setQuery(String query) {
        this.queryParams = parseQuery(query);
    }
}
