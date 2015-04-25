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
    private static Boolean clicked = false, funcClicked= false;
    
    /*2d array set of valid characters / functions
      we can use these to build the buttons ... for/each, getOperationForInput string
      although looping becomes far less useful for the individual naming/event handling of non-numeric buttons
      it is a full list of the calculator button functionality
    */
    private static final String[][] numSym = {
        {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
        {".", "=", "+", "-", "*", "/" },
        {"x^y", "âˆšx", "Log2", "Log10", "Sin", "Cos", "Clear", "->", "\b", "\r", "c", "."}
    };    
                      
    public static boolean find(String [][] set, String toFind){
        for(String[] operation: set)
            for(String op: operation){
                if(toFind.equals(op))
                    return true;
                }
        return false;
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
        scene.setOnKeyTyped(new keyPressed());
        
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static GridPane createNumberPad(){
        GridPane numberPad = new GridPane();
        numberPad.setHgap(5);
        numberPad.setVgap(5);
        Button num;        
        
        //add numbers to pad and generate event handlers
        for(int r = 2, i = 1; r >= 0; r--)
            for(int c = 0; c <= 2; c++){
               String temp = numSym[0][i];
               i++;
               num = new Button(temp);
               num.setOnMouseClicked(e->getInput().setText(getInput().getText() + temp));

               numberPad.add(num, c, r);
            }        
        
        Button zero = new Button(numSym[0][0]);
        zero.setOnMouseClicked(e -> getInput().setText(getInput().getText() + numSym[0][0]));
        zero.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button dot = new Button(numSym[1][0]);
        dot.setOnMouseClicked(new dotClicked());
        dot.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Button equals = new Button(numSym[1][1]);
        equals.setOnMouseClicked(e -> {
           inputNum.add(input.getText());
           MathOperations.compute(inputNum, operands);
        }); 
        equals.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button add = new Button(numSym[1][2]);
        add.setOnMouseClicked(new operationClicked());
        
        Button divide = new Button(numSym[1][3]);
        divide.setOnMouseClicked(new operationClicked());
        
        Button subtract = new Button(numSym[1][4]);
        subtract.setOnMouseClicked(new operationClicked());
        
        Button multiply = new Button(numSym[1][5]);
        multiply.setOnMouseClicked(new operationClicked());        
        
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
        sqrt.setOnMouseClicked(new operationClicked());
        sqrt.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

        Button log_2 = new Button("Log2");
        log_2.setOnMouseClicked(new operationClicked());
        log_2.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button log_10 = new Button("Log10");
        log_10.setOnMouseClicked(new operationClicked());
        log_10.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button x_y = new Button("x^y");
        x_y.setOnMouseClicked(new operationClicked());
        x_y.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button sin = new Button("Sin");
        sin.setOnMouseClicked(new operationClicked());
        sin.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button clearAll = new Button("Clear");
        clearAll.setOnMouseClicked(e-> clearAll());
        clearAll.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
       
        Button clearLast = new Button("->");
        clearLast.setOnMouseClicked(e -> getInput().setText(input.getText().substring(0,input.getText().length()-1)));
        clearLast.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        
        Button cos = new Button("Cos");
        cos.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //cos.setOnMouseClicked(new operationClicked());
        
        equations.addColumn(0, log_2, log_10, sin, cos);
        equations.addColumn(1, clearAll, clearLast, sqrt,x_y);
        
        equations.setMinWidth(cos.getWidth());
        return equations;
    }
    
    public static void getOperationForInput(String calcInput){
        
        if(!inputNum.isEmpty() && operands.isEmpty()){
            if(calcInput.equals("x^y")){
                if("".equals(displayText.getText())){
                    displayText.setText(input.getText() + "^");
            }
                else{
                    displayText.setText(displayText.getText() + input.getText() + "^");
            }
        }
            else{
                if("".equals(displayText.getText())){
                    displayText.setText(input.getText() + calcInput);
            }
                else{
                    displayText.setText(displayText.getText() + input.getText() + calcInput);
            }}
        }
        else{
            if(!funcClicked)
            inputNum.add(input.getText());
           
            if(calcInput.equals("x^y")){    
                if("".equals(displayText.getText())){
                    displayText.setText(input.getText() + "^");
            }
                else{
                    displayText.setText(displayText.getText() + input.getText() + "^");
            }        
        }
            else{
                if("".equals(displayText.getText())){
                    displayText.setText(input.getText() + calcInput);
             }
                else{
                    displayText.setText(displayText.getText() + input.getText() + calcInput);
            }   
            }
        }
        operands.add(calcInput);
        setInput("");
        clicked = false;
        funcClicked=false;
    }
    
    public static void getFunctionsForInput(String calcInput){
            inputNum.add(input.getText());
            
            if(calcInput.equals("x^y")){
            if("".equals(displayText.getText())){
                displayText.setText(calcInput +"^" + input.getText() );
            }
            else{
                displayText.setText(displayText.getText() + calcInput +"^" + input.getText());
            }
            }
            else{
            if("".equals(displayText.getText())){
                displayText.setText(calcInput +"(" + input.getText()+ ")" );
            }
            else{
                displayText.setText(displayText.getText() + calcInput +"(" + input.getText()+ ")");
            }
            }

        operands.add(calcInput);
        setInput("");
        clicked = false;

    }
    
    //temp removed to clear old equations
    public static void clearAll(){
        inputNum.clear();
        operands.clear();
        input.setText("");
        displayText.setText("");
        clicked= false;
        funcClicked = false;
        }
    
    //tested and performs reasonably well. may have some isues to work out
    //multple operand equations do not work : calc reports 9 - 6 + 4 == -1 (should be 7)
    //or add some functionality
    static class keyPressed implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent kb){
            String kbEntry = kb.getCharacter();
            
            //if a key entry is found, switch case runs
            numSymHandler(kbEntry);                
        }
    }
    
    public static void numSymHandler(String toCheck){
        if(find(numSym, toCheck))
                switch (toCheck) {
                    case "c":
                        clearAll();
                        break;
                    case "\b":
                        setInput(input.getText().substring(0, input.getText().length() - 1));
                        break;
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                    case "x^y": 
                        getOperationForInput(toCheck);
                        funcClicked=false;
                        break;
                    case "Log2":
                    case "Log10":
                    case "Sin":
                    case "Cos":
                    case "âˆšx":   
                        getFunctionsForInput(toCheck);
                        funcClicked = true;
                        break;
                    
                    case "\r":
                        //fixes error where it would add an empty object when using functions
                        if(!funcClicked)
                        inputNum.add(input.getText());
                        System.out.println(inputNum);
                        MathOperations.compute(inputNum, operands);
                        System.out.println(inputNum);
                        break;
                    case ".":
                        if(clicked == false){
                            getInput().setText(getInput().getText()+ ".");
                            clicked=true;
                        }
                        break;    
                    //adds number after no other key match
                    default:
                        setInput(getInput().getText() + toCheck);
                        break;
                }
    }
    
    static class dotClicked implements EventHandler<MouseEvent>{
        @Override
        public void handle(MouseEvent e){
            if(clicked == false){
                getInput().setText(getInput().getText()+ ".");
                clicked=true;
            }
        }
    }
    
    //does not work, performs unpredictably. unfinished
    static class operationClicked implements EventHandler<MouseEvent>{
   
        @Override
        public void handle(MouseEvent e){
            String temp = e.getSource().toString();
            //temp generates a lot of garbage we cannot use. must substring
            temp = temp.substring(temp.indexOf("'"));
            temp = temp.replace("'", "");
            System.out.println(temp);
                 numSymHandler(temp);
 
        }
    }   
}
