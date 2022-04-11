package support;

import io.restassured.response.Response;

import java.util.Objects;

public class RequestRegister {
    ApiHelper api;
    public static Response responseRegister;

    public RequestRegister() {
        api = new ApiHelper();
    }

    public void registerCuenta( String email, String password ){
        String url = "https://reqres.in/api/register";
        String cuenta = "{\n" +
                "    \"email\": \""+email+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}";
        responseRegister = api.post( url, cuenta );
    }

    public void registroError( String email ){
        String url = "https://reqres.in/api/register";
        String cuentaErr = "{\n" +
                "    \"email\": \""+email+"\"\n" +
                "}";
        responseRegister = api.post( url, cuentaErr );
    }
}
