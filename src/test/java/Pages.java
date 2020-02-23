import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selectors.withText;

class Pages {

    private Pages() {}

    private static SelenideElement loginPage = $("body");
    private static String cssForLoginField = "input[name=login]";
    private static String cssForPasswordField = "input[name=password]";
    private static String cssForSubmitLoginButton = "button.button";

    private static SelenideElement authCodePage = $("body");
    private static String cssForVerificationCodeField = "input[name=code]";
    private static String cssForSubmitVerificationCodeButton = "button.button";

    private static SelenideElement cardsPage = $("body");
    private static SelenideElement Account1Element = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private static String cssForReplenishAccount1Button = "[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button";
    private static SelenideElement Account2Element = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private static String cssForReplenishAccount2Button = "[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button";
    private static String cssForReloadCardsPageButton = "[data-test-id='action-reload']";

    private static SelenideElement replenishmentPage = $("body");
    private static String cssForReplenishmentAmountField = "[data-test-id='amount'] input";
    private static String cssForSourceOfReplenishmentField = ".input__control[type=tel]";
    private static String cssForMakeTransferButton = "button[data-test-id='action-transfer']";
    private static String cssForCancelTransferButton = "[data-test-id='action-cancel']";

    static class Login {
        static SelenideElement getPage() {
            return loginPage;
        }
        static void enterLogin(String login){
            loginPage.$(cssForLoginField).sendKeys(login);
        }
        static void enterPassword(String password){
            loginPage.$(cssForPasswordField).sendKeys(password);
        }
        static void submit(){
            loginPage.$(cssForSubmitLoginButton).click();
        }
    }

    static class VerificationCode {
        static SelenideElement getPage() {
            return authCodePage;
        }
        static void enterCode(String code){
            authCodePage.$(cssForVerificationCodeField).sendKeys(code);
        }
        static void submit(){
            authCodePage.$(cssForSubmitVerificationCodeButton).click();
        }
    }

    static class Cards {
        static SelenideElement getPage() {
            return cardsPage;
        }
        static SelenideElement getAccount1Element() {
            return Account1Element;
        }
        static SelenideElement getAccount2Element() {
            return Account2Element;
        }
        static void replenishAccount1(){
            cardsPage.$(cssForReplenishAccount1Button).click();
        }
        static void replenishAccount2(){
            cardsPage.$(cssForReplenishAccount2Button).click();
        }
        static void reloadPage(){
            cardsPage.$(cssForReloadCardsPageButton).click();
        }

    }

    static class AccountReplenishment {
        static SelenideElement getPage() {
            return replenishmentPage;
        }
        static void enterAmount(String amount){
            replenishmentPage.$(cssForReplenishmentAmountField).sendKeys(amount);
        }
        static void enterSource(String cardNo){
            replenishmentPage.$(cssForSourceOfReplenishmentField).sendKeys(cardNo);
        }
        static void makeTransfer(){
            replenishmentPage.$(cssForMakeTransferButton).click();
        }
        static void cancelAndGoBack(){
            replenishmentPage.$(cssForCancelTransferButton).click();
        }
        static void checkIfErrorNotificationIsShown(){
            replenishmentPage.$(withText("Ошибка!")).waitUntil(Condition.appears, 4000);
        }

    }
}
