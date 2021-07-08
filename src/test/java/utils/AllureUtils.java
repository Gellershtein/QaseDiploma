package utils;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestContext;

import java.util.Base64;

public class AllureUtils {
    @Attachment(value = "Last screen state", type = "image/png")
    public static byte[] takeScreenshot(ITestContext iTestContext) {
        String screenshotAsBase64 = Selenide.screenshot(OutputType.BASE64);
        byte[] decodedScreenshot = Base64.getDecoder().decode(screenshotAsBase64);
        return decodedScreenshot;
    }
}