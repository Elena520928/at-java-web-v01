package work.part07.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationPage {
    SelenideElement
        flightInfo = $("#flightRegistrationInfo"),
        route = $x("//p[text()=' Москва → Нью-Йорк']"),
        date = $x("//p[text()=' 2026-03-16']"),
        time = $x("//p[text()=' 06:00']"),
        price = $x("//p[contains(., '52') and contains(., 'руб.')]"),
        buttonFinishRegistration = $x("//button[contains(.,'Завершить регистрацию')]"),
        fio = $("#passengerName"),
        passport = $("#passportNumber"),
        email = $("#email"),
        phone = $("#phone"),
        message = $("#registrationMessage");



    @Step("Успешная регистрация")
    public void successRegistration() {
        buttonFinishRegistration.click();
        Alert alert= switchTo().alert();
        assertTrue(alert.getText().contains("Бронирование завершено"));
        alert.accept();
        this.message.shouldHave(text("Регистрация успешно завершена!"));
    }

    @Step("Заполнение полей и регистрация")
    public void registration(String fio, String passport, String email, String phone) {
        this.fio.setValue(fio);
        this.passport.setValue(passport);
        this.email.setValue(email);
        this.phone.setValue(phone);
        buttonFinishRegistration.click();
    }

    @Step("Проверка, что данные рейса корректные")
    public void isFlightDataCorrect(String cityFrom, String cityTo) {
        flightInfo
                .shouldBe(visible)
                .shouldHave(text(cityFrom + " → " + cityTo));
    }

    @Step("Появилась ошибка Заполните все поля")
    public void isErrorFillAllFied() {
        this.message.shouldHave(text("Пожалуйста, заполните все поля."));
    }

    @Step("Проверить наличие полей Маршрут, Дата, Время, Стоимость")
    public void checkFields() {
        this.route.shouldHave(text("Москва → Нью-Йорк"));
        this.date.shouldHave(text("2026-03-16"));
        this.time.shouldHave(text("06:00"));
        this.price.shouldHave(text("52 000"));

    }

}