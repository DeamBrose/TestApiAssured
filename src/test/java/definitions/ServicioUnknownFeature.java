package definitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.Assert;
import support.RequestUnknown;
import java.util.List;
import java.util.Map;

public class ServicioUnknownFeature {
    RequestUnknown requestUnknown;

    public ServicioUnknownFeature() {
        requestUnknown = new RequestUnknown();
    }

    @Given("listar Unknown esté operativo")
    public void queListarUnknownEsteOperativo() {
        requestUnknown.getListUnknown();
    }

    @Then("mostrar la lista de Unknown")
    public void mostrarLaListaDeUnknown() {
        ResponseBody<Response> body = RequestUnknown.responseUnknown;
        System.out.println( body.asString() );
    }

    @And("validar el código de estado {string}")
    public void validarElCodigoDeEstado(String codigo) {
        Assert.assertEquals( Integer.parseInt( codigo ), RequestUnknown.responseUnknown.getStatusCode() );
    }

    @Given("mostrando unknown con el id {string}")
    public void mostrandoUnknownConElId(String id) {
        requestUnknown.getUnknown( id );
    }

    @And("mostrar el registro")
    public void mostrarElRegistro() {
        mostrarLaListaDeUnknown();
    }

    @Then("validamos información de la consulta")
    public void validamosInformacionDeLaConsulta( DataTable unknown ) {
        ResponseBody<Response> body = RequestUnknown.responseUnknown;
        JsonPath json = new JsonPath( body.asString() ).setRootPath("data");
        List<Map<String,String>> list = unknown.asMaps( String.class, String.class );
        for (Map<String, String> stringStringMap : list) {
            Assert.assertEquals(stringStringMap.get("nombre"), json.getString("name"));
            Assert.assertEquals(stringStringMap.get("anio"), json.getString("year"));
            Assert.assertEquals(stringStringMap.get("color"), json.getString("color"));
            Assert.assertEquals(stringStringMap.get("pantone"), json.getString("pantone_value"));
        }
    }
}
