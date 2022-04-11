package objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class payLoadUser {
    @JsonProperty( value = "name")
    private String nombre;

    @JsonProperty( value = "job")
    private String puesto;

    public payLoadUser(String nombre, String puesto) {
        this.nombre = nombre;
        this.puesto = puesto;
    }


}
