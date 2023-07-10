import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
        @Test(dataProvider = "IncorrectLoginProviders", dataProviderClass = BaseTest.class)
        public void incorrectLoginTests(String email, String password)  {
            login(email, password);
            System.out.println("URL is " + url);
            Assert.assertEquals(driver.getCurrentUrl(), url);
        }
        @Test(dataProvider = "CorrectLoginProvider", dataProviderClass = BaseTest.class)
        public void correctLoginTests(String email, String password){
            login(email, password);
            System.out.println(driver.getCurrentUrl());
            Assert.assertTrue(avatarIsDisplayed());
        }
}