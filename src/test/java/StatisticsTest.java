import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import io.restassured.RestAssured;

public class StatisticsTest  extends AbstractFeatureServiceTest{

    @Test
    public void testAverageTone() {

        String path = request2path("gkgAvgTone.json");
        RestAssured.urlEncodingEnabled = false;

        try {
        RestAssured
            .given()

            .when()
                .log().uri()
                .get(path)

            .then()
                .log().ifError()
                .statusCode(200)
                .log().ifValidationFails()
                .body("displayFieldName", is(""))
                .body("fieldAliases.domain", is("domain"))
                .body("fieldAliases.average_urltone", is("average_urltone"))

                .body("fields.size()", is(2))
                .body("fields[1].name", is("domain"))
                .body("fields[1].type", is("esriFieldTypeString"))
                .body("fields[1].alias", is("domain"))
                .body("fields[1].length", is(128))
                .body("fields[0].name", is("average_urltone"))
                .body("fields[0].type", is("esriFieldTypeDouble"))
                .body("fields[0].alias", is("average_urltone"))

                .body("features.size()", is(2455))
                .body("features[0].attributes.domain", is("newsbeast.gr"))
                .body("features[0].attributes.average_urltone", is(12.96f))

                .body("features[9].attributes.domain", is("camdencourier.com.au"))
                .body("features[9].attributes.average_urltone", is(8.33f))

            ;
        }
        finally {
            RestAssured.reset();
        }
    }

    @Test
    public void testAverageMinMaxTone() {

        String path = request2path("gkgAvgMinMaxTone.json");
        RestAssured.urlEncodingEnabled = false;

        try{
        RestAssured
            .given()
            .when()
                .log().uri()
                .get(path)

            .then()
                .log().ifError()
                .statusCode(200)
                .log().ifValidationFails()
                .body("displayFieldName", is(""))
                .body("fieldAliases.domain", is("domain"))
                .body("fieldAliases.average_urltone", is("average_urltone"))
                .body("fieldAliases.minimum_urltone", is("minimum_urltone"))
                .body("fieldAliases.maximum_urltone", is("maximum_urltone"))

                .body("fields.size()", is(4))
                .body("fields[0].name", is("maximum_urltone"))
                .body("fields[0].type", is("esriFieldTypeDouble"))
                .body("fields[0].alias", is("maximum_urltone"))
                .body("fields[0].sqlType", is("sqlTypeOther"))
                .body("fields[1].name", is("domain"))
                .body("fields[1].type", is("esriFieldTypeString"))
                .body("fields[1].alias", is("domain"))
                .body("fields[1].sqlType", is("sqlTypeOther"))                
                .body("fields[2].name", is("average_urltone"))
                .body("fields[2].type", is("esriFieldTypeDouble"))
                .body("fields[2].alias", is("average_urltone"))
                .body("fields[2].sqlType", is("sqlTypeOther"))      
                .body("fields[3].name", is("minimum_urltone"))
                .body("fields[3].type", is("esriFieldTypeDouble"))
                .body("fields[3].alias", is("minimum_urltone"))
                .body("fields[3].sqlType", is("sqlTypeOther"))                      

                .body("features[0].attributes.domain", is("newsbeast.gr"))
                .body("features[0].attributes.average_urltone", is(12.96f))
                .body("features[0].attributes.minimum_urltone", is(12.96f))
                .body("features[0].attributes.maximum_urltone", is(12.96f))
            ;
        }
        finally{
            RestAssured.reset();
        }
    }


	@Test
    public void testStddevAndVarUrltone() {

        String path = request2path("stddevAndVarUrltone.json");

        RestAssured
            .given()
            .when()
                .log().uri()
                .get(path)

            .then()
                .log().ifError()
                .statusCode(200)
                .log().ifValidationFails()
                .body("displayFieldName", is(""))
                .body("fieldAliases.count_urltone", is("count_urltone"))
                .body("fieldAliases.min_urltone", is("min_urltone"))
                .body("fieldAliases.max_urltone", is("max_urltone"))
                .body("fieldAliases.avg_urltone", is("avg_urltone"))
                .body("fieldAliases.stddev_urltone", is("stddev_urltone"))
                .body("fieldAliases.var_urltone", is("var_urltone"))

                .body("fields.size()", is(6))
                .body("fields.name", hasItems("count_urltone", "min_urltone", "max_urltone", "avg_urltone", "stddev_urltone", "var_urltone"))
                
                .body("fields[0].name", is("var_urltone"))
                .body("fields[0].type", is("esriFieldTypeDouble"))
                .body("fields[0].alias", is("var_urltone"))
                .body("fields[5].name", is("stddev_urltone"))
                .body("fields[5].type", is("esriFieldTypeDouble"))
                .body("fields[5].alias", is("stddev_urltone"))

                .body("features.size()", is(1))
                .body("features[0].attributes.count_urltone", is(38765))
                .body("features[0].attributes.min_urltone", is(-21.77f))
                .body("features[0].attributes.max_urltone", is(16.23f))
                .body("features[0].attributes.avg_urltone", is(-1.1373726299497f))
                .body("features[0].attributes.stddev_urltone", is(3.63348765118955f))
                .body("features[0].attributes.var_urltone", is(13.2022325113469f))
            ;
    }
}
