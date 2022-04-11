package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.RequestUser;
import java.util.List;
import java.util.Map;

public class ServicioUserDefinition {
    RequestUser requestUser;

    public ServicioUserDefinition() {
        requestUser = new RequestUser();
    }

    @Given("listar usuarios")
    public void listarUsuarios() {
        requestUser.getListUsers();
    }

    @When("mostrar el listado de usuarios")
    public void mostrarElListadoDeUsuarios() {

        ResponseBody<Response> body = RequestUser.responseUser;
        System.out.println( body.asString() );

    }

    @And("validar código de respuesta {string}")
    public void validarCodigoDeRespuesta(String codigo) {
        //Validamos que codigo de status sea el que se mandó //example 200
        Assert.assertEquals( Integer.parseInt( codigo ), RequestUser.responseUser.getStatusCode() );
    }

    @Then("validar cantidad de registro")
    public void validarCantidadDeRegistro() {
        ResponseBody<Response> body = RequestUser.responseUser;
        JsonPath json = new JsonPath( body.asString() );
        List<String> listado = JsonPath.with( body.asString() ).get( "data" );
        int cantidad = json.getInt( "per_page" );
        Assert.assertEquals( cantidad, listado.size() );
    }

    @Given("listar usuario con id {string}")
    public void listarUsuarioConId(String id) {
        requestUser.getUser( id );
    }

    @When("mostrar información del usuario")
    public void mostrarInformacionDelUsuario() {
        mostrarElListadoDeUsuarios();
    }

    @Then("validar información de la consulta")
    public void validarInformacionDeLaConsulta( DataTable user ) {
        ResponseBody<Response> body = RequestUser.responseUser;
        JsonPath json = new JsonPath( body.asString() ).setRootPath("data");
        List<Map<String,String>> list = user.asMaps( String.class, String.class );
        for( int i=0; i < list.size(); i++ ){
            Assert.assertEquals( list.get( i ).get("email"), json.getString("email") );
            Assert.assertEquals( list.get( i ).get("nombre"), json.getString("first_name") );
            Assert.assertEquals( list.get( i ).get("apellido"), json.getString("last_name") );
        }
    }

    @Given("que no existe usuario registrado")
    public void queNoExisteUsuarioRegistrado() {
        //Solo informativo
    }

    @Then("registrar datos del usuario")
    public void registrarDatosDelUsuario( DataTable user ) {
        List<Map<String,String>> list = user.asMaps( String.class, String.class );
        for (Map<String, String> stringStringMap : list) {
            requestUser.createUsers( stringStringMap.get("nombre"), stringStringMap.get("puesto") );
            validarCodigoDeRespuesta( stringStringMap.get("codigo") );
            mostrarElListadoDeUsuarios();
        }
    }

    @And("mostar los datos del registro")
    public void mostarLosDatosDelRegistro() {

    }

    @Given("que el usuario está registrado")
    public void queElUsuarioEstaRegistrado() {
        //Informativo
    }

    @When("actualizar datos del usuario")
    public void actualizarDatosDelUsuario( DataTable user ) {
        List<Map<String,String>> list = user.asMaps( String.class, String.class );
        for( Map<String,String> data: list ){
            requestUser.updateUserput( data.get("id"), data.get("nombre"), data.get("puesto") );
            validarCodigoDeRespuesta( data.get("codigo") );
            mostrarElListadoDeUsuarios();
        }
    }

    @When("actualizar datos del usuario \\(patch)")
    public void actualizarDatosDelUsuarioPatch( DataTable user ) {
        List<Map<String,String>> list = user.asMaps( String.class, String.class );
        for( Map<String, String> data: list ){
            requestUser.updateUserpatch( data.get("id"), data.get("nombre"), data.get("puesto") );
            validarCodigoDeRespuesta( data.get("codigo") );
            mostrarElListadoDeUsuarios();
        }
    }
}
