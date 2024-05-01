package glue;

import account.Account;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class AccountSteps {

    Account account = null;
    String Acctno;
    String Acctname;

    String scenarioname;
    int totaldepositedamount;
    float totalwithdrawlsamount;
    List<List<String>> data1;
    List<List<String>> data2;

    @Before
    public void getscenarioname(Scenario scenario) {
        scenarioname = scenario.getName();
    }

    @Given("^Account exists for Acc No\\. \"([^\"]*)\" with Name \"([^\"]*)\"$")
    public void accountExistsForAccNoWithName(String number, String name) {

        Acctno = "Account: " + number;
        Acctname = "Name: " + name;
    }

    @Given("deposits are made")
    public void deposits_are_made(io.cucumber.datatable.DataTable dataTable) {

        data1 = dataTable.cells();
        String amount;
        for(int i=0;i<data1.size();i++){
            amount = data1.get(i).get(1);
            totaldepositedamount = totaldepositedamount + Integer.parseInt(amount);
        }

    }

    @Given("withdrawls are made")
    public void withdrawls_are_made(io.cucumber.datatable.DataTable dataTable) {
         data2 = dataTable.cells();

        String amount;
        for(int i=0;i<data2.size();i++){
            amount = data2.get(i).get(1);
            totalwithdrawlsamount = totalwithdrawlsamount + Float.parseFloat(amount);
        }


    }

    @When("statement is produced")
    public void statement_is_produced() {

    }

    @Then("statement includes {string}")
    public void statement_includes(String string) {

        if (scenarioname.equals("Statement includes account details")) 
        {
            switch (string) {
                case "Name: Bob Smith":
                    Assert.assertEquals(string, Acctname);
                    break;
                case "Account: 12345678":
                    Assert.assertEquals(string, Acctno);
                    break;
            }
        } else if (scenarioname.equals("Balance is calculated on the statement")) {

            float totalbalance = totaldepositedamount - (float)totalwithdrawlsamount;

            String Actualacctbalance = "Balance: " + String.format("%.2f",totalbalance );
            Assert.assertEquals(string,Actualacctbalance);

        } else if(scenarioname.equals("Statement includes transaction details")) {

            switch (string){
                case "INIT" :
                    Assert.assertEquals("INIT",data1.get(0).get(0));
                    break;
                case "DEP1" :
                    Assert.assertEquals("DEP1",data1.get(1).get(0));
                    break;
                case "DEP2" :
                    Assert.assertEquals("DEP2",data1.get(2).get(0));
                    break;
                case "DEP3" :
                    Assert.assertEquals("DEP3",data1.get(3).get(0));
                    break;
                case "CHQ001" :
                    Assert.assertEquals("CHQ001",data2.get(0).get(0));
                    break;

            }

        }

    }
}



