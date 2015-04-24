package javafx_calculator_finalproject;

import java.util.ArrayList;

public class MathOperations {
    static double result = 0;
    
    public static void compute(ArrayList<String> inputNum, ArrayList<String> operands){
        checkOperands(inputNum, operands, "^");
        checkOperands(inputNum, operands, "*");
        checkOperands(inputNum, operands, "/");
        checkOperands(inputNum, operands, "+");
        checkOperands(inputNum, operands, "-");
    }   
    
    public static void checkOperands(ArrayList<String> inputNum, ArrayList<String> operands, String o){
        int i = 0;
        while(operands.contains(o)){
            if(operands.get(i).equals(o)){
                if(o.equals("^"))
                    computeE(inputNum, operands, i);
                else if(o.equals("*"))
                    computeM(inputNum, operands, i);
                else if(o.equals("/"))
                    computeD(inputNum, operands, i);
                else if(o.equals("+"))
                    computeA(inputNum, operands, i);
                else if(o.equals("-"))
                    computeS(inputNum, operands, i);

                if(i == 0)
                    i--;
                else i -= 2;
            }
        i++;   
        }            
        JavaFX_Calculator_FinalProject.setInput(inputNum.get(0));
        JavaFX_Calculator_FinalProject.setDisplayText("");
    }
            
    public static void computeE(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num1 = Double.valueOf(inputNum.get(i));
        double num2 = Double.valueOf(inputNum.get(i+1));

        result = num1;            

        result = Math.pow(num1, num2);

        if(Double.toString(result).endsWith(".0"))
            inputNum.set(i,Integer.toString((int) result));
        else
            inputNum.set(i, Double.toString(result));

        inputNum.remove(i+1);
        operands.remove(i);
    }
    
    public static void computeM(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num1 = Double.valueOf(inputNum.get(i));
        double num2 = Double.valueOf(inputNum.get(i+1));

        result = num1 * num2;

        if(Double.toString(result).endsWith(".0"))
            inputNum.set(i,Integer.toString((int) result));
        else
            inputNum.set(i, Double.toString(result));

        inputNum.remove(i+1);
        operands.remove(i);
    }
    
    public static void computeD(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num1 = Double.valueOf(inputNum.get(i));
        double num2 = Double.valueOf(inputNum.get(i+1));   

        result = num1/num2;

        if(Double.toString(result).endsWith(".0"))
            inputNum.set(i,Integer.toString((int) result));
        else
        inputNum.set(i, Double.toString(result));

        inputNum.remove(i+1);
        operands.remove(i); 
    }
    
    public static void computeA(ArrayList<String> inputNum, ArrayList<String> operands, int i){
        double num1 = Double.valueOf(inputNum.get(i));
        double num2 = Double.valueOf(inputNum.get(i+1));   

        result = num1+num2;

        if(Double.toString(result).endsWith(".0"))
            inputNum.set(i,Integer.toString((int) result));
        else
            inputNum.set(i, Double.toString(result));

        inputNum.remove(i+1);
        operands.remove(i);
    }
    
    public static void computeS(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num1 = Double.valueOf(inputNum.get(i));
        double num2 = Double.valueOf(inputNum.get(i+1));   

       result = num1-num2;

        if(Double.toString(result).endsWith(".0"))
            inputNum.set(i,Integer.toString((int) result));
        else           
        inputNum.set(i, Double.toString(result));

        inputNum.remove(i+1);
        operands.remove(i);
    }
}
