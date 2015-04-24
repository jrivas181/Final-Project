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
               num.setOnKeyTyped(new keyPressed());
                
               numberPad.add(num, c, r);
            }        
        
        Button zero = new Button(numSym[0][0]);
        zero.setOnMouseClicked(e -> getInput().setText(getInput().getText() + numSym[0][0]));
        zero.setOnKeyTyped(new keyPressed());
        zero.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button dot = new Button(numSym[1][0]);
        dot.setOnMouseClicked(new dotClicked());
        dot.setOnKeyTyped(new keyPressed());
        dot.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        Button equals = new Button(numSym[1][1]);
        equals.setOnKeyTyped(new keyPressed());
        equals.setOnMouseClicked(e -> {
           inputNum.add(input.getText());
           MathOperations.compute(inputNum, operands);
        }); 
        equals.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        
        Button add = new Button(numSym[1][2]);
        add.setOnMouseClicked(new operationClicked());
        add.setOnKeyTyped(new keyPressed());
        
        Button divide = new Button(numSym[1][3]);
        divide.setOnMouseClicked(new operationClicked());
        divide.setOnKeyTyped(new keyPressed());
        
        Button subtract = new Button(numSym[1][4]);
        subtract.setOnMouseClicked(new operationClicked());
        subtract.setOnKeyTyped(new keyPressed());
        
        Button multiply = new Button(numSym[1][5]);
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
        clearLast.setOnMouseClicked(e -> getInput().setText(input.getText().substring(0,input.getText().length()-1)));
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
    
    public static void getOperationForInput(String calcInput){
        operands.add(calcInput);
        if(!inputNum.isEmpty() && operands.isEmpty()){
            if("".equals(displayText.getText())){
                displayText.setText(input.getText() + calcInput);
            }
            else{
                displayText.setText(displayText.getText() + input.getText() + calcInput);
            }
        }
        else{
            inputNum.add(input.getText());
                
            if("".equals(displayText.getText())){
                displayText.setText(input.getText() + calcInput);
            }
            else{
                displayText.setText(displayText.getText() + input.getText() + calcInput);
            }        
        }
        setInput("");
        clicked = false;
    }
    
    //temp removed to clear old equations
    public static void clearAll(){
        inputNum.clear();
        operands.clear();
        input.setText("");               
        }
   
    //tested and performs reasonably well. may have some isues to work out
    //multple operand equations do not work : calc reports 9 - 6 + 4 == -1 (should be 7)
    //or add some functionality
    static class keyPressed implements EventHandler<KeyEvent>{
        @Override
        public void handle(KeyEvent kb){
            String kbEntry = kb.getCharacter();
            
            //if a key entry is found, switch case runs
            if(find(numSym, kbEntry))
                switch (kb.getCharacter()) {
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
                        getOperationForInput(kbEntry);
                        break;
                    case "\r":
                        inputNum.add(input.getText());
                        MathOperations.compute(inputNum, operands);
                        break;
                    case ".":
                        if(clicked == false){
                            getInput().setText(getInput().getText()+ ".");
                            clicked=true;
                        }
                        break;
                    //adds number after no other 
                    default:
                        setInput(getInput().getText() + kbEntry);
                        break;
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
    
    //does not work, performs unpredictably. unfinished
    static class operationClicked implements EventHandler<MouseEvent>{
   
        @Override
        public void handle(MouseEvent e){
            
            String temp = e.getSource().toString();
            //System.out.println(temp);
            if(find(numSym, temp)){
                getOperationForInput(temp);
                setInput(getInput().getText() + temp);
            }
 /*********THESE NEED TO ALL BE MADE IN THE MATH OPERATIONS CLASS**************/
             else if(temp.contains("'x^y'")){
             }
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
