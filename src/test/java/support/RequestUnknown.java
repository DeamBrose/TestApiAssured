package support;

import io.restassured.response.Response;

public class RequestUnknown {
    ApiHelper api;
    public static Response responseUnknown;

    public RequestUnknown() {
        api = new ApiHelper();
    }

    public void getListUnknown(){
        String url = "https://reqres.in/api/unknown";
        responseUnknown = api.get( url );
    }

    public void getUnknown( String id ){
        String url = "https://reqres.in/api/unknown/"+id;
        responseUnknown = api.get( url );
    }
}
