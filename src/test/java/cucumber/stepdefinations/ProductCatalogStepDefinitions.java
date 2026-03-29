package cucumber.stepdefinations;
import com.serenitydojo.playWright.pageObjects.*;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.assertj.core.api.Assertions;

public class ProductCatalogStepDefinitions {

    NavBar navBar;
    SearchComponent searchComponent;
    ProductList productList;

    @Before
    public void setupPageObjects() {
        navBar = new NavBar(playWrightCucumberFixtures.getPage());
        searchComponent = new SearchComponent(playWrightCucumberFixtures.getPage());
        productList = new ProductList(playWrightCucumberFixtures.getPage());
    }

    @Given("Sally is on Home page")
    public void sally_is_on_home_page() {
        navBar.openHomePage();
     }

    @When("Sally searches for {string}")
    public void sally_searches_for(String searchTerm) {
        searchComponent.searchBy(searchTerm);
    }

    @Then("{string} should be displayed")
    public void should_be_displayed(String productName) {
        var matchingProducts = productList.getProductNames();
        Assertions.assertThat(matchingProducts).contains(productName);
    }


}
