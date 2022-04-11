package support;

import io.restassured.response.Response;
import objects.payLoadUser;

public class RequestUser {
    ApiHelper api;
    public static Response responseUser;
    payLoadUser user;

    public RequestUser() {
        api = new ApiHelper();
    }

    public void getListUsers(){
        String url = "https://reqres.in/api/users";
        responseUser = api.get( url );
    }

    public void getUser( String id ){
        String url = "https://reqres.in/api/users/"+id;
        responseUser = api.get( url );
    }

    public void createUsers( String nombre, String puesto ){
        String url = "https://reqres.in/api/users";
        String user = "{\n" +
                "    \"name\": \""+nombre+"\",\n" +
                "    \"job\": \""+puesto+"\"\n" +
                "}";
        //user = new payLoadUser( nombre, puesto );
        responseUser  = api.post( url, user );
    }

    public void updateUserput( String id, String nombre, String puesto ){
        String url = "https://reqres.in/api/users/"+id;
        user = new payLoadUser( nombre, puesto );
        responseUser = api.put( url, user );
    }

    public void updateUserpatch( String id, String nombre, String puesto ){
        String url = "https://reqres.in/api/users/"+id;
        user = new payLoadUser( nombre, puesto );
        responseUser = api.patch( url, user );
    }
}
