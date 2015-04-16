package javafx_calculator_finalproject;

import java.math.BigInteger;
import java.util.ArrayList;



public class MathOperations {
    
    
    public static void computeBigInteger(ArrayList<String> inputNum, ArrayList<String> operands){

        
        
        checkOperands(inputNum, operands,"^");
        checkOperands(inputNum, operands, "*");
        checkOperands(inputNum, operands, "/");
        checkOperands(inputNum, operands,"+");
        checkOperands(inputNum, operands, "-");
        
        
    
    }
   
   
    public static void checkOperands(ArrayList<String> inputNum,ArrayList<String> operands, String o){
            int i =0;
            while(operands.contains(o)){
                if(operands.get(i).equals(o) && i ==0){
                        if(o.equals("^"))
                           computeE(inputNum, operands , i);
                        else if(o.equals("*"))
                           computeM(inputNum, operands, i);
                        else if(o.equals("/"))
                            computeD(inputNum, operands,i);
                        else if(o.equals("+"))
                            computeA(inputNum, operands,i);
                        else if(o.equals("-"))
                            computeS(inputNum, operands,i);
                        
                        i-=1;
                   }   
                else if(operands.get(i).equals(o)){
                        if(o.equals("^"))
                           computeE(inputNum, operands , i);
                        else if(o.equals("*"))
                           computeM(inputNum, operands, i);
                        else if(o.equals("/"))
                            computeD(inputNum, operands,i);
                        else if(o.equals("+"))
                            computeA(inputNum, operands,i);
                        else if(o.equals("-"))
                            computeS(inputNum, operands,i);
                        i-=2;
                   }
                i++;   
               }    
            
            JavaFX_Calculator_FinalProject.setInput(inputNum.get(0).toString());
            JavaFX_Calculator_FinalProject.setDisplayText("");
            
            
    }
            
    public static void computeE(ArrayList<String> inputNum,ArrayList<String> operands, int i){
            BigInteger num1 = new BigInteger(inputNum.get(i));
            int num2 = Integer.parseInt(inputNum.get(i+1));
            
            BigInteger result = num1.pow(num2);
            
            inputNum.set(i, result.toString());
            inputNum.remove(i+1);
            operands.remove(i);
            
            JavaFX_Calculator_FinalProject.setInputNum(i, result.toString());
            JavaFX_Calculator_FinalProject.setInputNum(i+1);
            JavaFX_Calculator_FinalProject.setOperands(i);
            
    }
    
    public static void computeM(ArrayList<String> inputNum,ArrayList<String> operands, int i){
            BigInteger num1 = new BigInteger(inputNum.get(i));
            BigInteger num2 = new BigInteger(inputNum.get(i+1));
            
            BigInteger result = num1.multiply(num2);
            
            inputNum.set(i, result.toString());
            inputNum.remove(i+1);
            operands.remove(i);
            
            JavaFX_Calculator_FinalProject.setInputNum(i, result.toString());
            JavaFX_Calculator_FinalProject.setInputNum(i+1);
            JavaFX_Calculator_FinalProject.setOperands(i);
            
    }
    
    public static void computeD(ArrayList<String> inputNum,ArrayList<String> operands, int i){
            BigInteger num1 = new BigInteger(inputNum.get(i));
            BigInteger num2 = new BigInteger(inputNum.get(i+1));   
            
            BigInteger result = num1.divide(num2);
            
            inputNum.set(i, result.toString());
            inputNum.remove(i+1);
            operands.remove(i);
            
            JavaFX_Calculator_FinalProject.setInputNum(i, result.toString());
            JavaFX_Calculator_FinalProject.setInputNum(i+1);
            JavaFX_Calculator_FinalProject.setOperands(i);
            
    }
    
    public static void computeA(ArrayList<String> inputNum,ArrayList<String> operands, int i){
            System.out.print(inputNum.get(i));
            System.out.print(inputNum.get(i+1));
            BigInteger num1 = new BigInteger(inputNum.get(i).toString());
            
            BigInteger num2 = new BigInteger(inputNum.get(i+1));   
            
            BigInteger result = num1.add(num2);
            System.out.println(inputNum);
            inputNum.set(i, result.toString());
            inputNum.remove(i+1);
            operands.remove(i);
            System.out.println(inputNum);
           // System.out.println(JavaFX_Calculator_FinalProject.getInputNum());
            JavaFX_Calculator_FinalProject.setInputNum(i, result.toString());
            JavaFX_Calculator_FinalProject.setInputNum(i+1);
            
           // System.out.println(JavaFX_Calculator_FinalProject.getInputNum());
            //JavaFX_Calculator_FinalProject.setOperands(i);
    }
    
    public static void computeS(ArrayList<String> inputNum,ArrayList<String> operands, int i){
             BigInteger num1 = new BigInteger(inputNum.get(i));
            BigInteger num2 = new BigInteger(inputNum.get(i+1));   
            
            BigInteger result = num1.subtract(num2);
            
            inputNum.set(i, result.toString());
            inputNum.remove(i+1);
            operands.remove(i);
            
            JavaFX_Calculator_FinalProject.setInputNum(i, result.toString());
            JavaFX_Calculator_FinalProject.setInputNum(i+1);
            JavaFX_Calculator_FinalProject.setOperands(i);
    }
}
