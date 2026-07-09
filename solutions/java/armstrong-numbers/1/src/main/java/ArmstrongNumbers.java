 public class ArmstrongNumbers {

    public boolean isArmstrongNumber(int number) {
        String numStr = Integer.toString(number);
        int digits = numStr.length();
        int sum = 0;
        int temp = number;

        while (temp > 0) {
            int digit = temp % 10;
            sum += (int) Math.pow(digit, digits);
            temp /= 10;
        }

        return sum == number;
    }
}