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
    private static Boolean clicked = false;
    
    //2d array set of valid characters / functions
    //we can use these to build the buttons ... for/each, add string
    //it is a full list of the calculator button functionality
    private static String[][] numSym = { ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"] /*numeric*/
                                         [".", "+", "-", "*", "/", ] /*basic functions*/
                                         ["x^y", "âˆšx", "Log2", "Log10", "Sin", "Cos", "Clear", "->" ]; /*special functions*/
                                       } 
                                       
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
        int i = 1;
        
        //add numbers to pad and generate event handlers
        for(int r = 3; r > 0;r--)
            for(int c = 0; c < 3; c++){
               String temp = numSym[0][i];
               i++;
               num = new Button(temp);
               num.setOnMouseClicked(e->getInput().setText(getInput().getText() + temp));
               num.setOnKeyTyped(new keyPressed());
                
               numberPad.add(num, c, r);    
               /*
                char temp = Integer.toString(i).charAt(0);                
                
                num = new Button(Integer.toString(i));
                num.setOnMouseClicked(e->getInput().setText(getInput().getText()+ temp));
                num.setOnKeyTyped(new keyPressed());
                
                numberPad.add(num,c,r);                
               */
            }        
        
        Button zero = new Button(numSym[0][0]);
        zero.setOnMouseClicked(e -> getInput().setText(getInput().getText() + numSym[0][0]));
        zero.setOnKeyTyped(new keyPressed());
        zero.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button dot = new Button(".");
        dot.setOnMouseClicked(new dotClicked());
        dot.setOnKeyTyped(new keyPressed());
        
        dot.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button equals = new Button("=");
        equals.setOnKeyTyped(new keyPressed());
        equals.setOnMouseClicked(e -> {
           inputNum.add(input.getText());
           MathOperations.compute(inputNum,operands);
        }); 
        equals.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button add = new Button("+");
        add.setOnMouseClicked(new operationClicked());
        add.setOnKeyTyped(new keyPressed());
        
        Button divide = new Button("/");
        divide.setOnMouseClicked(new operationClicked());
        divide.setOnKeyTyped(new keyPressed());
        
        Button subtract = new Button("-");
        subtract.setOnMouseClicked(new operationClicked());
        subtract.setOnKeyTyped(new keyPressed());
        
        Button multiply = new Button("*");
        multiply.setOnMouseClicked(new operationClicked());
        multiply.setOnKeyTyped(new keyPressed());        
        
        numberPad.addColumn(4, divide, multiply);
        numberPad.addColumn(5, subtract, add);
        numberPad.add(zero, 0, 3);
        numberPad.add(dot, 2, 3);
        numberPad.add(equals, 4, 2);
        
        GridPane.setColumnSpan(equals, 2);
        GridPane.setRowSpan(equals, 2);
        GridPane.setColumnSpan(zero, 2);
       
        return numberPad;        
    }
    
    public static GridPane createEquationsPad(){
        GridPane equations = new GridPane();
        equations.setHgap(5);
        equations.setVgap(5);
        
        Button sqrt = new Button("âˆšx");
        sqrt.setOnKeyTyped(new keyPressed());
        sqrt.setOnMouseClicked(new operationClicked());
        sqrt.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button log_2 = new Button("Log2");
        log_2.setOnKeyTyped(new keyPressed());
        log_2.setOnMouseClicked(new operationClicked());
        log_2.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button log_10 = new Button("Log10");
        log_10.setOnKeyTyped(new keyPressed());
        log_10.setOnMouseClicked(new operationClicked());
        log_10.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button x_y = new Button("x^y");
        x_y.setOnKeyTyped(new keyPressed());
        x_y.setOnMouseClicked(new operationClicked());
        x_y.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button sin = new Button("Sin");
        sin.setOnKeyTyped(new keyPressed());
        sin.setOnMouseClicked(new operationClicked());
        sin.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button clearAll = new Button("Clear");
        clearAll.setOnMouseClicked(e-> getInput().setText(""));
        clearAll.setOnKeyTyped(new keyPressed());
        clearAll.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
       
        Button clearLast = new Button("->");
        clearLast.setOnMouseClicked(e-> getInput().setText(input.getText().substring(0,input.getText().length()-1)));
        clearLast.setOnKeyTyped(new keyPressed());
        clearLast.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button cos = new Button("Cos");
        cos.setOnMouseClicked(new operationClicked());
        cos.setOnKeyTyped(new keyPressed());
        
        equations.addColumn(0, log_2,log_10,sin,cos);
        equations.addColumn(1,clearAll,clearLast,sqrt,x_y);
        
        equations.setMinWidth(cos.getWidth());
        return equations;
    }
    
    public static void add(){
        if(!inputNum.isEmpty() && operands.isEmpty()){
            
            operands.add("+");
                
             if(displayText.getText() == ""){
             displayText.setText(input.getText()+" + ");
            }
            else{
             displayText.setText(displayText.getText()+ input.getText()+" + ");
                    }
            setInput("");
        }
        else{
            inputNum.add(input.getText());
            operands.add("+");
                
            if(displayText.getText() == ""){
                 displayText.setText(input.getText()+" + ");
            }
            else{
                displayText.setText(displayText.getText()+ input.getText()+" + ");
                    }
            setInput("");        
        }
        clicked = false;
    }
    
    public static void subtract(){
        if(!inputNum.isEmpty()&& operands.isEmpty()){
            operands.add("-");
            
            if(displayText.getText() ==""){
                    displayText.setText(input.getText()+" - ");
            }
            else{
                  displayText.setText(displayText.getText()+ input.getText()+" - ");
            }
            setInput("");
        }
        else{
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
        clicked = false;
    }
    
    public static void multiply(){
        if(!inputNum.isEmpty()&& operands.isEmpty()){
            operands.add("*");
                
            if(displayText.getText() ==""){
                    displayText.setText(input.getText()+" * ");
            }
            else{
                  displayText.setText(displayText.getText()+ input.getText()+" * ");
                    }
             setInput("");
        }
        else{
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
        clicked = false;
    }
    
    public static void divide(){
        if(!inputNum.isEmpty() && operands.isEmpty()){
            operands.add("/");
        
            if(displayText.getText() ==""){
                displayText.setText(input.getText()+" / ");
            }
            else{
                  displayText.setText(displayText.getText()+ input.getText()+" / ");
            }
            
        setInput("");
        
        }
        else{
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
        
        clicked = false;
    }
    
    public static void powerOf(){
        if(!inputNum.isEmpty() && operands.isEmpty()){
            operands.add("^");
        
            if(displayText.getText() ==""){
                displayText.setText(input.getText()+" ^ ");
            }
            else{
                  displayText.setText(displayText.getText()+ input.getText()+" ^ ");
                    }
        setInput("");
        }
        else{
            inputNum.add(input.getText());
            operands.add("^");
        
            if(displayText.getText() ==""){
                displayText.setText(input.getText()+" ^ ");
            }
            else{
                  displayText.setText(displayText.getText()+ input.getText()+" ^ ");
                    }
        setInput("");
    }
        clicked = false;    
    }
    
    public static void clearAll(){
        String temp = input.getText();
       
        inputNum.clear();
        operands.clear();
        
        input.setText("");
        displayText.setText(temp);                
    }
   
    static class keyPressed implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent kb){
           /*try this instead, I will test it at home. 
             you can replace the whole if/else with this single line of code
             
             setInput.getInput().getText() + kb.getCharacter();
             
             if you want to do a numeric or symbolic validation, that can be a different method
             i'm going to make an array of valid characters. we will look for those.
             Anything else not on that list can be ignored.
           */
           if (kb.getCharacter().equals("0"))
               setInput(getInput().getText() + "0");
           else if(kb.getCharacter().equals("1"))
               setInput(getInput().getText() + "1");
           else if(kb.getCharacter().equals("2"))
               setInput(getInput().getText() + "2");
           else if(kb.getCharacter().equals("3"))
               setInput(getInput().getText() + "3");
           else if(kb.getCharacter().equals("4"))
               setInput(getInput().getText() + "4");
           else if(kb.getCharacter().equals("5"))
               setInput(getInput().getText() + "5");
           else if(kb.getCharacter().equals("6"))
               setInput(getInput().getText() + "6");
           else if(kb.getCharacter().equals("7"))
               setInput(getInput().getText() + "7");
           else if(kb.getCharacter().equals("8"))
               setInput(getInput().getText() + "8");
           else if(kb.getCharacter().equals("9"))
              setInput(getInput().getText() +"9");
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
                MathOperations.compute(inputNum,operands);
            
            }
            else if(kb.getCharacter().equals("."))
                 if(clicked == false){
                    getInput().setText(getInput().getText()+".");
                    clicked=true;
            }    
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

    
    static class operationClicked implements EventHandler<MouseEvent>{
   
        @Override
        public void handle(MouseEvent e){
            
            String temp=e.getSource().toString();
            System.out.println(temp);
             if(temp.contains("' +'"))
                 add();
                
             else if (temp.contains("' / '"))
                 divide();
        
             else if(temp.contains("' - '"))
                 subtract();
             
             else if(temp.contains("' * '"))
                 multiply();
             
             else if(temp.contains("'x^y'"))
                 powerOf();
                     
             
 /*********THESE NEED TO ALL BE MADE IN THE MATH OPERATIONS CLASS**************/
             else if(temp.contains("'Log2'")){
             
             }
             else if(temp.contains("'Log10'")){
             
             }
             else if(temp.contains("'Sine'")){
             
             }
             else if(temp.contains("'Cosine'")){
                 
             }

             else if(temp.contains("'âˆšx'")){
                 
             }
/******************************************************************************/

        }
        
    
    }
    
    }
