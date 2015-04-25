package javafx_calculator_finalproject;

import java.util.ArrayList;


public class MathOperations {
    static double result = 0;
    
    public static void compute(ArrayList<String> inputNum, ArrayList<String> operands){
        checkFunctions(inputNum,operands,"Log2", "Log10");
        checkFunctions(inputNum, operands,"Sin", "Cos");
        checkFunctions(inputNum,operands,"âˆšx", null);
        
        checkOperands(inputNum, operands, "x^y", "NA");
        checkOperands(inputNum, operands, "*", "/");
        checkOperands(inputNum, operands, "+","-");
        result = 0;
    }   
    
    public static void checkOperands(ArrayList<String> inputNum, ArrayList<String> operands, String op1, String op2){
        int i = 0;
        while(operands.contains(op1)|| operands.contains(op2)){
            if(operands.get(i).equals(op1)){
                if(op1.equals("x^y"))
                    computeE(inputNum, operands, i);
                else if(op1.equals("*"))
                    computeM(inputNum, operands, i);
                else if(op1.equals("/"))
                    computeD(inputNum, operands, i);
                else if(op1.equals("+"))
                    computeA(inputNum, operands, i);
                else if(op1.equals("-"))
                    computeS(inputNum, operands, i);

                if(i == 0)
                    i--;
                else i -= 2;
            }
            else if(operands.get(i).equals(op2)){
                if(op2.equals("*"))
                    computeM(inputNum, operands, i);
                else if(op2.equals("/"))
                    computeD(inputNum, operands, i);
                else if(op2.equals("+"))
                    computeA(inputNum, operands, i);
                else if(op2.equals("-"))
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
    
    public static void checkFunctions(ArrayList<String> inputNum, ArrayList<String> operands, String func1, String func2){
        int i = 0;
        while(operands.contains(func1)|| operands.contains(func2)){
            if(operands.get(i).equals(func1)){
                if(func1.equals("Log2"))
                    computeLog2(inputNum, operands, i);
                else if(func1.equals("Log10"))
                    computeLog10(inputNum, operands, i);
                else if(func1.equals("Sin"))
                    computeSin(inputNum, operands, i);
                else if(func1.equals("Cos"))
                    computeCos(inputNum, operands, i);
                else if(func1.equals("âˆšx"))
                    computeSqrt(inputNum, operands,i);

                if(i == 0)
                    i--;
                else i -= 2;
            }
            else if(operands.get(i).equals(func2)){
                if(func2.equals("Log2"))
                    computeLog2(inputNum, operands, i);
                else if(func2.equals("Log10"))
                    computeLog10(inputNum, operands, i);
                else if(func2.equals("Sin"))
                    computeSin(inputNum, operands, i);
                else if(func2.equals("Cos"))
                    computeCos(inputNum, operands, i);
                else if(func2.equals("âˆšx"))
                    computeSqrt(inputNum, operands,i);

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
          
        result = Math.pow(num1, num2);

        checkTrailingZeros(inputNum,i);
        inputNum.remove(i+1);
        operands.remove(i);
    }
    
    public static void computeM(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num1 = Double.valueOf(inputNum.get(i));
        double num2 = Double.valueOf(inputNum.get(i+1));

        result = num1 * num2;

        checkTrailingZeros(inputNum,i);
        inputNum.remove(i+1);
        operands.remove(i);
    }
    
    public static void computeD(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num1 = Double.valueOf(inputNum.get(i));
        double num2 = Double.valueOf(inputNum.get(i+1));   

        result = num1/num2;

        checkTrailingZeros(inputNum,i);
        inputNum.remove(i+1);
        operands.remove(i); 
    }
    
    public static void computeA(ArrayList<String> inputNum, ArrayList<String> operands, int i){
        double num1 = Double.valueOf(inputNum.get(i));
        double num2 = Double.valueOf(inputNum.get(i+1));   

        result = num1+num2;


        checkTrailingZeros(inputNum,i);
        inputNum.remove(i+1);
        operands.remove(i);
    }
    
    public static void computeS(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num1 = Double.valueOf(inputNum.get(i));
        double num2 = Double.valueOf(inputNum.get(i+1));   

        result = num1 - num2;

        checkTrailingZeros(inputNum,i);
        inputNum.remove(i+1);
        operands.remove(i);
    }
    
    public static void computeLog2(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num1 = Double.valueOf(inputNum.get(i));
        
        result= Math.log(num1)/Math.log((double)2);
        
        checkTrailingZeros(inputNum,i);
        operands.remove(i);
    }
    
    public static void computeLog10(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num = Double.valueOf(inputNum.get(i));
        
        result = Math.log10(num);
        
        checkTrailingZeros(inputNum, i);
        operands.remove(i);
    }
    
    public static void computeSin(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num = Double.valueOf(inputNum.get(i));
        
        result= Math.sin(num);
        
        checkTrailingZeros(inputNum,i);
        operands.remove(i);
    }
    
    public static void computeCos(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num = Double.valueOf(inputNum.get(i));
        
        result=Math.cos(num);
        
        checkTrailingZeros(inputNum,i);
        operands.remove(i);
    }
    
    public static void computeSqrt(ArrayList<String> inputNum,ArrayList<String> operands, int i){
        double num = Double.valueOf(inputNum.get(i));
        
        result = Math.sqrt(num);
        
        checkTrailingZeros(inputNum,i);
        operands.remove(i);
    }
    
    public static void checkTrailingZeros(ArrayList<String> inputNum,int i){
         if(Double.toString(result).endsWith(".0"))
            inputNum.set(i,Integer.toString((int) result));
        else
            inputNum.set(i, Double.toString(result));
         
    }
}

