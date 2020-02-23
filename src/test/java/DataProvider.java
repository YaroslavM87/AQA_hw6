class DataProvider {

    private DataProvider() {}

    static AuthInfo getCorrectAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    static VerificationCode getCorrectVerificationCode() {
        return new VerificationCode("12345");
    }

    static CardNumber getCardNumber0001() {
        return new CardNumber("5559 0000 0000 0001");
    }

    static CardNumber getCardNumber0002() {
        return new CardNumber("5559 0000 0000 0002");
    }

    static String getAmount100() {
        return "100";
    }

    static String getAmount1000() {
        return "1000";
    }

    static String getAmount10000() {
        return "10000";
    }

    static int getBalanceFromStringParseToInt(String originalStr, String subStrStart, String subStrEnd) {
        int startIndex = originalStr.indexOf(subStrStart) + subStrStart.length() + 1;
        int endIndex = originalStr.indexOf(subStrEnd) - 1;
        String balanceStr = originalStr.substring(startIndex, endIndex).trim();
        return Integer.parseInt(balanceStr);
    }

    static class AuthInfo {
        private String login;
        private String password;
        AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }
        String getLogin() {
            return this.login;
        }
        String getPassword() {
            return this.password;
        }
    }

    static class VerificationCode {
        private String code;
        VerificationCode(String code) {
            this.code = code;
        }
        String getCode() {
            return code;
        }
    }

    static class CardNumber{
        private String number;
        CardNumber(String number) {
            this.number = number;
        }
        String getNumber() {
            return number;
        }
    }
}