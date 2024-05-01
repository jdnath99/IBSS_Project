package glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;

public class GoogleSteps {
    String result;

    @Given("url {string} is launched")
    public void url(String url) {
        W.get().driver.get(url);
        acceptCookiesIfWarned();
    }

    private static void acceptCookiesIfWarned() {
        try {
            W.get().driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (NoSuchElementException ignored) {
        }
    }

    @When("About page is shown")
    public void about_page_is_shown() {

            W.get().driver.findElement(By.linkText("About")).click();
    }
    @Then("page displays {string}")
    public void page_displays(String string) {
    String txt=    W.get().driver.findElement(By.xpath("//*[@id=\"page-content\"]/section[1]/div/div[2]/h1")).getText();
        Assert.assertEquals(string,txt);

    }

    @When("searching for {string}")
    public void searching_for(String string) {

        W.get().driver.findElement(By.id("APjFqb")).sendKeys(string);
        Actions builder = new Actions(W.get().driver);
        builder.sendKeys(Keys.ENTER).build().perform();
    }
    @Then("results contain {string}")
    public void results_contain(String string) {

       String  text1= W.get().driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div/div/div/div/div[1]/div/span/a/h3")).getText();
        Assert.assertEquals(string,text1);
    }
    @Then("result stats are displayed")
    public void result_stats_are_displayed() {

        W.get().driver.findElement(By.id("hdtb-tls")).click();
         result= W.get().driver.findElement(By.id("result-stats")).getText();

    }
    @Then("number of {string} is more than {int}")
    public void number_of_is_more_than(String string, Integer int1) {

        switch (string){

            case "results" :
                String value1 = result.substring(6,7);

                if(Integer.parseInt(value1)>int1){
                    Assert.assertTrue(true);
            }
            case "seconds" :
                String value2 = result.substring(29,33);

               if(Float.parseFloat(value2)>int1){
                    Assert.assertTrue(true);
                }
        }


    }


}
