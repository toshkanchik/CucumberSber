package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.appline.sberFramework.managers.PageMngr;
import ru.appline.sberFramework.managers.PropMngr;

import static ru.appline.sberFramework.managers.DriverMngr.getDriver;
import static ru.appline.sberFramework.managers.InitMngr.InitAll;
import static ru.appline.sberFramework.managers.InitMngr.closeAll;
import static ru.appline.sberFramework.managers.PageMngr.getPageMngr;

public class Hooks {
    protected PageMngr pages = getPageMngr();
    @Before
    public static void beforeAll()
    {
        InitAll();
        getDriver().get(PropMngr.GetPropMngr().getProperty("app.url"));
    }

    @After
    public static void afterAll()
    {
        addScreenshot();
        closeAll();
    }
    @Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
    public static byte[] addScreenshot() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
