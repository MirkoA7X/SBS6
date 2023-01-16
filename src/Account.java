
import java.util.stream.Stream;

class Account {


    private String cardNumber;
    private String cardPin;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardPin() {
        return cardPin;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }

    String createCard() {
        String cardNumber = "400000" + String.format("%09d", (long) (Math.random() * 999999999L));
        this.cardNumber = checkSum(cardNumber);
        return cardNumber;
    }

    String createPIN() {
        String cardPIN = String.format("%04d", (long) (Math.random() * 9999L));
        this.cardPin = cardPIN;
        return cardPIN;
    }

    static String checkSum(String cardNumber) {
        String[] cardNumbers = cardNumber.split("");

        int[] intNumbers = Stream.of(cardNumbers).mapToInt(Integer::parseInt).toArray();
        int sum = 0;

        for (int i = 0; i < intNumbers.length; i++) {
            if (i % 2 == 0) {
                intNumbers[i] = returnDigit(intNumbers[i]);
            }
            sum += intNumbers[i];
        }
        return (cardNumber + getLastDigit(sum));

    }

    void addIncome(double income) {
        this.balance += income;
    }



    static boolean checkIfValid(String cardNumber) {
        char lastDigit = cardNumber.charAt(15);
        String[] digits15 = cardNumber.substring(0,cardNumber.length()-1).split("");
        int[] intNumbers = Stream.of(digits15).mapToInt(Integer::parseInt).toArray();
        int sum = 0;
        for (int i = 0; i < intNumbers.length; i++) {
            if (i % 2 == 0) {
                intNumbers[i] = returnDigit(intNumbers[i]);
            }
            sum += intNumbers[i];
        }
        return getLastDigit(sum).equals(String.valueOf(lastDigit));




        }



    private static int returnDigit(int num) {
        if (num < 10) {
            return num;
        }
        return (num % 10 + 1);
    }

    private static String getLastDigit(int sum) {

        int secondDigit = sum % 10;
        if (secondDigit == 0) {
            return "0";
        }
        return Integer.toString((10 - secondDigit));
    }
}