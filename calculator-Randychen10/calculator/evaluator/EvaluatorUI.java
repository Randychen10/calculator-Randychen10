package calculator.evaluator;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class EvaluatorUI extends JFrame implements ActionListener {
    private Evaluator evaluator;

     private JTextField expressionTextField = new JTextField();
     private JPanel buttonPanel = new JPanel();

     // total of 20 buttons on the calculator,
     // numbered from left to right, top to bottom
     // bText[] array contains the text for corresponding buttons
     private static final String[] buttonText = {
         "7", "8", "9", "+",
         "4", "5", "6", "-",
         "1", "2", "3", "*",
         "(", "0", ")", "/",
         "C", "CE", "=", "^"
     };

     /**
      * C  is for clear, clears entire expression
      * CE is for clear expression, clears last entry up until the last operator.
      */

     public static void main(String argv[]) {
         new EvaluatorUI();
     }

     public EvaluatorUI() {
         setLayout(new BorderLayout());
         this.expressionTextField.setPreferredSize(new Dimension(600, 50));
         this.expressionTextField.setFont(new Font("Courier", Font.BOLD, 24));
         this.expressionTextField.setHorizontalAlignment(JTextField.CENTER);

         add(expressionTextField, BorderLayout.NORTH);
         expressionTextField.setEditable(false);

         add(buttonPanel, BorderLayout.CENTER);
         buttonPanel.setLayout(new GridLayout(5, 4));

         //create 20 buttons with corresponding text in bText[] array
         JButton jb;
         for (String s : EvaluatorUI.buttonText) {
             jb = new JButton(s);
             jb.setFont(new Font("Courier", Font.BOLD, 24));
             jb.addActionListener(this);
             this.buttonPanel.add(jb);
         }

         setTitle("Calculator");
         setSize(400, 400);
         setLocationByPlatform(true);
         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         setVisible(true);
     }

     /**
      * This function is called anytime a button is pressed
      * on our Calculator GUI.
      * @param actionEventObject Event object generated when a
      *                          button is pressed.
      */
     public void actionPerformed(ActionEvent actionEventObject) {
         String cmd = actionEventObject.getActionCommand();
         switch (cmd) {
             case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> this.expressionTextField.setText(this.expressionTextField.getText() + cmd);
         }

         switch (cmd){
             case "+", "-", "*", "/", "^" -> this.expressionTextField.setText(this.expressionTextField.getText() + cmd);
         }

         if (cmd.equals("C")) {
             this.expressionTextField.setText("");
         }

         if (cmd.equals("CE")) {
             String currentText = this.expressionTextField.getText();
             if (!currentText.isEmpty()) {
                 currentText = currentText.substring(0, currentText.length() - 1);
                 this.expressionTextField.setText(currentText);
             }
         }

         if (cmd.equals("=")) {
         String expression = this.expressionTextField.getText();
         Evaluator evaluator = new Evaluator();
             if (!expression.isEmpty()) {
                 try {
                     int result = evaluator.evaluateExpression(expression);
                     this.expressionTextField.setText(String.valueOf(result));
                 } catch (Exception e) {
                     this.expressionTextField.setText("Error");
                 }
             }
         }
     }
 }
