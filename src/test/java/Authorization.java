import static com.codeborne.selenide.Selenide.open;

class Authorization {

    private Authorization(){}

    static void openPage(String pageURL){
        open(pageURL);
    }

    static void logIn(){
        DataProvider.AuthInfo authInfo = DataProvider.getCorrectAuthInfo();
        DataProvider.VerificationCode verificationCode = DataProvider.getCorrectVerificationCode();
        Pages.Login.enterLogin(authInfo.getLogin());
        Pages.Login.enterPassword(authInfo.getPassword());
        Pages.Login.submit();
        Pages.VerificationCode.enterCode(verificationCode.getCode());
        Pages.VerificationCode.submit();
    }
}