public class Calculator {       
    
    private double firstNumber;
    private double secondNumber;

    public Calculator(double firstNumber, double secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public String add() {
        double finalResult = firstNumber + secondNumber;
        String result = Double.toString(finalResult);
        return result;
    }

    public String subtract() {
        double finalResult = firstNumber - secondNumber;
        String result = Double.toString(finalResult);
        return result;
    }

    public String multiply() {
        double finalResult = firstNumber * secondNumber;
        String result = Double.toString(finalResult);
        return result;
    }

    public String divide() {
        double finalResult = firstNumber / secondNumber;
        String result = Double.toString(finalResult);
        return result;
    }
}