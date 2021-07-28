package elements.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseElement {
    String COLOR_ATTRIBUTE = "background: #3cff3c; border: 2px solid red";

    public void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        String originalAttribute = (String) javascriptExecutor.executeScript("return arguments[0].getAttribute('style')", element);
        javascriptExecutor.executeScript(String.format("arguments[0].setAttribute('style', '%s')", COLOR_ATTRIBUTE), element);
        javascriptExecutor.executeScript(String.format("arguments[0].setAttribute('style', '%s')", originalAttribute), element);
    }
}
