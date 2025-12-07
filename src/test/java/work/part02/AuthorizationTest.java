package work.part02;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTest {
    @Test
    public void testAuthorization() {
        open("https://slqamsk.github.io/cases/slflights/v01/");

        $("input[id=username]").sendKeys("standard_user");
        $("input[id=password]").sendKeys("stand_pass1");
        $(By.id("loginButton")).click();
        $(By.id("flightForm")).shouldBe(visible);

    }
    @Test
    public void testWrongPassword() {
        open("https://slqamsk.github.io/cases/slflights/v01/");

        $("input[id=username]").sendKeys("standard_user");
        $("input[id=password]").sendKeys("stand_pass");
        $(By.id("loginButton")).click();
        $(By.className("error")).shouldHave(text("Неверное имя пользователя или пароль."));

    }
}