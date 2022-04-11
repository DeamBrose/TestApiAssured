package support;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiHelper {

    public Response get( String url ){
        return given().get( url );
    }

    public Response post( String url, Object payload ){
        return given().body( payload ).contentType( "application/json" ).post( url );
    }

    public Response patch( String url, Object payload ){
        return given().body( payload ).contentType( "application/json" ).patch( url );
    }

    public Response put( String url, Object payload ){
        return given().body( payload ).contentType( "application/json" ).put( url );
    }
}
