package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.it.Ma;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.RequestRegister;
import support.RequestUser;

import java.util.List;
import java.util.Map;

public class ServicioRegisterDefinition {
    RequestRegister requestRegister;

    public ServicioRegisterDefinition() {
        requestRegister = new RequestRegister();
    }

    @Given("que el usuario no tiene una cuenta de usuario")
    public void queElUsuarioNoTieneUnaCuentaDeUsuario() {
    }

    @Then("registrar cuenta de usuario")
    public void registrarCuentaDeUsuario(DataTable cuenta) {
        List<Map<String,String>> list = cuenta.asMaps( String.class, String.class );
        for( Map<String,String> count: list ){
            requestRegister.registerCuenta( count.get("email"), count.get("password") );
        }
    }

    @And("validar que el código de respuesta sea {string}")
    public void validarQueElCodigoDeRespuestaSea(String codigo) {
        Assert.assertEquals( Integer.parseInt( codigo ), RequestRegister.responseRegister.getStatusCode() );
    }


    @And("mostrar la respuesta del satisfactorio.")
    public void mostrarDatosDeLaCuentaAutenticada() {
        ResponseBody<Response> body = RequestRegister.responseRegister;
        System.out.println( body.asString() );
    }

    @Given("que el usuario se registre con datos faltantes")
    public void queElUsuarioSeRegistreConDatosFaltantes() {
        //Solo informativo
    }

    @Then("validamos el caso")
    public void validamosElCaso( DataTable cuenta ) {
        List<Map<String,String>> list = cuenta.asMaps( String.class, String.class );
        for( Map<String,String> data: list ){
            requestRegister.registroError( data.get("email") );
            mostrarDatosDeLaCuentaAutenticada();
        }
    }
}
