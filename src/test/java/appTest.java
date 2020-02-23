import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selectors.withText;

class appTest {
    private String pageURL;
    private DataProvider.AuthInfo correctAuthInfo;
    private DataProvider.VerificationCode correctVerificationCode;
    private DataProvider.CardNumber cardNo1;
    private DataProvider.CardNumber cardNo2;
    private String amount100;
    private String amount1000;
    private String amount10000;

    @BeforeEach
    void setUpAll() {
        pageURL = "http://localhost:9999/";
        correctAuthInfo = DataProvider.getCorrectAuthInfo();
        correctVerificationCode = DataProvider.getCorrectVerificationCode();
        cardNo1 = DataProvider.getCardNumber0001();
        cardNo2 = DataProvider.getCardNumber0002();
        amount100 = DataProvider.getAmount100();
        amount1000 = DataProvider.getAmount1000();
        amount10000 = DataProvider.getAmount10000();
    }

    @Test
    void shouldLogInSuccessfully() {
        Authorization.openPage(pageURL);
        Authorization.logIn();
        Pages.Cards.getPage().$(withText("Личный кабинет")).should(Condition.visible);
    }

   @Test
    void shouldCorrectlyTransferAmountFromOneCardToAnotherForCredit() {
        String transferAmount = amount1000;
        Authorization.openPage(pageURL);
        Authorization.logIn();
        String innerTextWithBalanceAcc1 = Pages.Cards.getAccount1Element().getText();
        int acc1BalanceBefore = DataProvider.getBalanceFromStringParseToInt(
                innerTextWithBalanceAcc1,
                "баланс:",
                "р."
        );
        Pages.Cards.replenishAccount1();
        Pages.AccountReplenishment.enterAmount(transferAmount);
        Pages.AccountReplenishment.enterSource(cardNo2.getNumber());
        Pages.AccountReplenishment.makeTransfer();
        innerTextWithBalanceAcc1 = Pages.Cards.getAccount1Element().getText();
        int acc1BalanceAfter = DataProvider.getBalanceFromStringParseToInt(
                innerTextWithBalanceAcc1,
                "баланс:",
                "р."
        );
        assertEquals(acc1BalanceAfter, acc1BalanceBefore + Integer.parseInt(transferAmount));
    }

    @Test
    void shouldCorrectlyTransferAmountFromOneCardToAnotherForDebit() {
        String transferAmount = amount100;
        Authorization.openPage(pageURL);
        Authorization.logIn();
        String innerTextWithBalanceAcc2 = Pages.Cards.getAccount2Element().getText();
        int acc2BalanceBefore = DataProvider.getBalanceFromStringParseToInt(
                innerTextWithBalanceAcc2,
                "баланс:",
                "р."
        );
        Pages.Cards.replenishAccount1();
        Pages.AccountReplenishment.enterAmount(transferAmount);
        Pages.AccountReplenishment.enterSource(cardNo2.getNumber());
        Pages.AccountReplenishment.makeTransfer();
        innerTextWithBalanceAcc2 = Pages.Cards.getAccount2Element().getText();
        int acc2BalanceAfter = DataProvider.getBalanceFromStringParseToInt(
                innerTextWithBalanceAcc2,
                "баланс:",
                "р."
        );
        assertEquals(acc2BalanceAfter, acc2BalanceBefore - Integer.parseInt(transferAmount));
    }

    @Test
    void shouldNotifyIfTransferIsImpossibleForThisAmount() {
        Authorization.openPage(pageURL);
        Authorization.logIn();
        String innerTextWithBalanceOfSourceAcc = Pages.Cards.getAccount2Element().getText();
        int sourceAccBalanceBefore = DataProvider.getBalanceFromStringParseToInt(
                innerTextWithBalanceOfSourceAcc,
                "баланс:",
                "р."
        );
        Pages.Cards.replenishAccount1();
        String incorrectAmount = Integer.toString(sourceAccBalanceBefore + 10000);
        Pages.AccountReplenishment.enterAmount(incorrectAmount);
        Pages.AccountReplenishment.enterSource(cardNo2.getNumber());
        Pages.AccountReplenishment.makeTransfer();
        Pages.AccountReplenishment.checkIfErrorNotificationIsShown();
    }
}