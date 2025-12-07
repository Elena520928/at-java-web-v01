package work.part02;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MyXpathTest {
    @Test
    public void testElementSearchMethods() {
        open("https://slqamsk.github.io/tmp/xPath01.html");
        $x("//h1").shouldHave(text("Учебная страница для XPath"));
        $x("//h1").shouldHave(exactText("Учебная страница для XPath"));

    }
}