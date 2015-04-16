//Final Project 
package javafx_calculator_finalproject;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class JavaFX_Calculator_FinalProject extends Application {
    private static Text input = new Text("");
    private static Text displayText = new Text();
    private static ArrayList<String> inputNum = new ArrayList<String>();
    private static ArrayList<String> operands = new ArrayList<String>();
    private static Boolean clicked=false,computed = false;
    

    public static void main(String[] args) {
        launch(args);
    }
    
    public static Text getInput() {
        return input;
    }

    public static void setInput(String aInput) {
        input.setText(aInput);
    }
    
    public static Text getDisplayText(){
        return displayText;
    }
    
    public static void setDisplayText(String aDisplayText) {
        displayText.setText(aDisplayText);
    }

    public static ArrayList<String> getInputNum() {
        return inputNum;
    }


    public static void setInputNum(int i, String n) {
        inputNum.add(i, n);
    }
    public static void setInputNum(int i) {
        inputNum.remove(i);
    }


    public static void setOperands(int i) {
        operands.remove(i);
    }
    
    @Override
    public void start(Stage primaryStage) {

        GridPane numPad = createNumberPad();
        GridPane equations = createEquationsPad();
        
   
     
        
        HBox pad = new HBox(5);
        pad.getChildren().add(0, equations);
        pad.getChildren().add(1, numPad);
        pad.setAlignment(Pos.CENTER);
        
        
        VBox display = new VBox(displayText, getInput(),pad);

        display.setAlignment(Pos.TOP_CENTER);
        
        Scene scene = new Scene(display, 275, 150);
        
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    public static GridPane createNumberPad(){
        GridPane numberPad = new GridPane();
        numberPad.setHgap(5);
        numberPad.setVgap(5);
        Button num;
        int i=1;
        for(int r = 2; r>=0;r--)
            for(int c =0; c<=2; c++){
            
                char temp = Integer.toString(i).charAt(0);
                
                
                num = new Button(Integer.toString(i));
                num.setOnMouseClicked(e->getInput().setText(getInput().getText()+ temp));
                num.setOnKeyTyped(new keyPressed());
                
                numberPad.add(num,c,r);

                i++;
            }
        
        
        Button zero = new Button("0");
        zero.setOnMouseClicked(e->getInput().setText(getInput().getText()+"0"));
        zero.setOnKeyTyped(new keyPressed());
        zero.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button dot = new Button(".");
        dot.setOnMouseClicked(new dotClicked());
        dot.setOnKeyTyped(new dotPressed());
        
        dot.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button equals = new Button("=");
        equals.setOnKeyTyped(new keyPressed());
        equals.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button add=new Button(" +");
        add.setOnMouseClicked(new operationClicked());
        add.setOnKeyTyped(new keyPressed());
        
        Button divide = new Button(" / ");
        divide.setOnMouseClicked(new operationClicked());
        divide.setOnKeyTyped(new keyPressed());
        
        Button subtract = new Button(" - ");
        subtract.setOnMouseClicked(new operationClicked());
        subtract.setOnKeyTyped(new keyPressed());
        
        Button multiply = new Button(" * ");
        multiply.setOnMouseClicked(new operationClicked());
        multiply.setOnKeyTyped(new keyPressed());
        
        
        numberPad.addColumn(4, divide,multiply);
        numberPad.addColumn(5, subtract,add);
        numberPad.add(zero,0,3);
        numberPad.add(dot,2,3);
        numberPad.add(equals, 4,2);
        
        numberPad.setColumnSpan(equals,2);
        numberPad.setRowSpan(equals,2);
        numberPad.setColumnSpan(zero,2);

       
        return numberPad;
        
    }
    
    public static GridPane createEquationsPad(){
        GridPane equations = new GridPane();
        equations.setHgap(5);
        equations.setVgap(5);
        
        Button sqrt = new Button("√x");
        sqrt.setOnKeyTyped(new keyPressed());
        sqrt.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button log_2 = new Button("Log2");
        log_2.setOnKeyTyped(new keyPressed());
        log_2.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button log_10 = new Button("Log10");
        log_10.setOnKeyTyped(new keyPressed());
        log_10.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button x_y = new Button("x^y");
        x_y.setOnKeyTyped(new keyPressed());
        x_y.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button sin = new Button("Sine");
        sin.setOnKeyTyped(new keyPressed());
        sin.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button clearAll = new Button("Clear");
        clearAll.setOnMouseClicked(e-> getInput().setText(""));
        clearAll.setOnKeyTyped(new keyPressed());
        clearAll.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
       
        Button clearLast = new Button("->");
        clearLast.setOnMouseClicked(e-> getInput().setText(input.getText().substring(0,input.getText().length()-1)));
        clearLast.setOnKeyTyped(new keyPressed());
        clearLast.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button cos = new Button("Cosine");
        
        equations.addColumn(0, log_2,log_10,sin,cos);
        equations.addColumn(1,clearAll,clearLast,sqrt,x_y);
        
        equations.setMinWidth(cos.getWidth());
        return equations;
    }
    
    public static void add(){
        inputNum.add(input.getText());
        operands.add("+");
                
        if(displayText.getText() ==""){
             displayText.setText(input.getText()+" + ");
            }
        else{
             displayText.setText(displayText.getText()+ input.getText()+" + ");
                    }
         setInput("");
        
    }
    
    public static void subtract(){
            inputNum.add(input.getText());
            operands.add("-");
            
            if(displayText.getText() ==""){
                    displayText.setText(input.getText()+" - ");
            }
            else{
                  displayText.setText(displayText.getText()+ input.getText()+" - ");
                    }
             setInput("");
    
    }
    
    public static void multiply(){
            inputNum.add(input.getText());
            operands.add("*");
                
            if(displayText.getText() ==""){
                    displayText.setText(input.getText()+" * ");
            }
            else{
                  displayText.setText(displayText.getText()+ input.getText()+" * ");
                    }
             setInput("");
    }
    
    public static void divide(){
        inputNum.add(input.getText());
        operands.add("/");
        
        if(displayText.getText() ==""){
                displayText.setText(input.getText()+" / ");
            }
        else{
                  displayText.setText(displayText.getText()+ input.getText()+" / ");
                    }
        setInput("");
    }
    
    public static void clearAll(){
        String temp = input.getText();
        
        inputNum.removeAll(inputNum);
        operands.removeAll(operands);
        input.setText("");
        displayText.setText(temp);
        
        
        
        
    }
   
    static class keyPressed implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent kb){
    
            if(!input.getText().equals("") && MathOperations.result.toString().equals(input.getText()) && !input.getText().equals("0"))
              
                clearAll();
                
            if (kb.getCharacter().equals("0"))
               setInput(getInput().getText()+ "0");
            else if(kb.getCharacter().equals("1"))
               setInput(getInput().getText()+ "1");
           else if(kb.getCharacter().equals("2"))
               setInput(getInput().getText()+ "2");
           else if(kb.getCharacter().equals("3"))
               setInput(getInput().getText()+ "3");
           else if(kb.getCharacter().equals("4"))
               setInput(getInput().getText()+ "4");
           else if(kb.getCharacter().equals("5"))
               setInput(getInput().getText()+ "5");
           else if(kb.getCharacter().equals("6"))
               setInput(getInput().getText()+ "6");
           else if(kb.getCharacter().equals("7"))
               setInput(getInput().getText()+ "7");
           else if(kb.getCharacter().equals("8"))
               setInput(getInput().getText()+ "8");
           else if(kb.getCharacter().equals("9"))
              setInput(getInput().getText()+"9");
           else if(kb.getCharacter().equals("c"))
               clearAll();
            else if(kb.getCharacter().equals("\b"))
               setInput(input.getText().substring(0,input.getText().length()-1));
            else if(kb.getCharacter().equals("+")){
                add();
            }
            else if(kb.getCharacter().equals("-")){
               subtract();
            }
            else if(kb.getCharacter().equals("*")){
                multiply();
            }
            else if(kb.getCharacter().equals("/")){
                divide();
            }
            else if(kb.getCharacter().equals("\r")){
                inputNum.add(input.getText());
                if(clicked)
                    MathOperationsDecimal.computeBigDecimal(inputNum,operands);
                else
                    MathOperations.computeBigInteger(inputNum,operands);
            }
            else if(input.getText().equals(inputNum.get(0)))
                clearAll();
    
    }

    }
     

    static class dotClicked implements EventHandler<MouseEvent>{
        
        @Override
        public void handle(MouseEvent e){
                if(clicked == false){
                    getInput().setText(getInput().getText()+".");
                    clicked=true;
                }
        }
    }
    static class dotPressed implements EventHandler<KeyEvent>{
        
        @Override
        public void handle(KeyEvent kb){
                if(clicked == false){
                    if(kb.getCharacter().equals(".")){
                    getInput().setText(getInput().getText()+".");
                    clicked=true;
                }}
        }
    }
    
    static class operationClicked implements EventHandler<MouseEvent>{
   
        @Override
        public void handle(MouseEvent e){
           
            String temp="";
            for(int i=e.getSource().toString().length()-5; i <e.getSource().toString().length();i++)
                temp += e.getSource().toString().charAt(i);
            
             if(temp.equals("]' +'")){
                 add();
                }
             else if(temp.equals("' / '")){
                 divide();
        }
             else if(temp.equals("' - '")){
                 subtract();
             }
             else if(temp.equals("' * '")){
                 multiply();
    }
        

        }
        
    
    }
    
    }
    


