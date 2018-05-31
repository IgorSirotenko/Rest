import static io.restassured.RestAssured.get;
import io.restassured.response.Response;
import org.json.JSONObjest;
import org.junit.Test;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class MyTests {

    private String apiKey = "2803c5c5";
    private String host = "http://omdbapi.com";

        @Test
    public void testCaseNumber1() {
        get(host + "/?apikey=" + apiKey + "&s=Rambo")
                .then()

                .body("Search.findAll{it.Type.equals(\"movie\")}", hasSize(5));
    }

        @Test
    public void testCaseNumber2() {
            Response responseByName = get(host + "/?t=Ready+Player+One&apikey=" + apiKey);
            JSONObject jsonObject = new JSONObject(responseByName.asString());

            String imdbId = jsonObject.get("imdbID").toString();

            Response responseByImdbSearch = get(host + "/?i=" + imdbId + "apikey=" + apiKey);

            assertEquals(responseByName.asString(), responseByImdbSearch.asString());
        }

    }
