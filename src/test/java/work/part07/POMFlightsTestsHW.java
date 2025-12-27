package work.part07;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import work.part07.pages.LoginPage;
import work.part07.pages.RegistrationPage;
import work.part07.pages.SearchPage;
import work.part07.pages.FlightsListPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMFlightsTestsHW {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browser = "firefox";
    }

    @BeforeEach
    void setUp() {
        open("https://slqamsk.github.io/cases/slflights/v01/");
        getWebDriver().manage().window().maximize();
        sleep(10_000);
    }
    // ... Автотесты
    // 1. Каждая строка таблицы должна содержать кнопку "Зарегистрироваться",
    // позволяющую перейти к бронированию выбранного рейса.
    @Test
    void test01SR0302() {
        // Страница логина
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        // Страница поиска рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Страница со списком найденных рейсов и проверка наличия кнопки регистрации
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.checkRegisterButton();

    }

    // При переходе к бронированию система должна отображать детальную информацию о выбранном рейсе:
    // маршрут, дату, время, стоимость.
    @Test
    void test01SR0401() {
        // Страница логина
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        // Страница поиска рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Страница со списком найденных рейсов
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        // Страница регистрации на рейс
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");
        registrationPage.registration("", "", "", "");
        registrationPage.checkFields();
        registrationPage.isErrorFillAllFied();

    }

    // Приложение должно предоставлять кнопку "Новый поиск",
    // которая возвращает пользователя к форме поиска с предзаполненными параметрами последнего запроса.
    @Test
    void test01SR0307() {
        // Страница логина
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        // Страница поиска рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Страница со списком найденных рейсов
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.checkRegisterButton();

        //Нажать на кнопку Новый поиск
        flightsList.clickOnNewSearchButton();

        //Проверить предзаполненные параметры
        searchPage.checkFields();

    }

}