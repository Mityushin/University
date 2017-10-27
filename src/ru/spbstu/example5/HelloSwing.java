package ru.spbstu.example5;

import ru.spbstu.example1.Birthday;
import ru.spbstu.example2.InterfaceExample;
import ru.spbstu.example3.WaitNotifyExample;
import ru.spbstu.example4.WorkersExample;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.*;

public class HelloSwing {
    final static String resF = "Result text field..";
    final static String argsF = "Enter the arguments:";
    final static String args1 = "INT(day) STRING(month) INT(year)";
    final static String args2 = "NONE";
    final static String args3 = "INT(N)";
    final static String args4 = "INT(workers) INT(boxes)";

    static public JTextField args;
    static public JButton executeButton;
    static public JTextArea result;
    static public JButton clearButton;
    static public JRadioButton task1;
    static public JRadioButton task2;
    static public JRadioButton task3;
    static public JRadioButton task4;

    static public int getNumber(JRadioButton... r){
        int n=0;
        for (int i=0;i < r.length;i++){
            if (r[i].isSelected()){
                n = i;
            }
        }
        return (n+1);
    }

    static public void setLine(String s){
        result.append(s+"\n");
    }

    static void execution(int number, String s){
        int exitCode;
        switch (number){
            case 1:{
                executeButton.setEnabled(false);
                exitCode = Birthday.execute(s.split(" "),result);
                executeButton.setEnabled(true);
                if (exitCode != 0){
                    args.setText(args1);
                }
                break;
            }
            case 2:{
                executeButton.setEnabled(false);
                exitCode = InterfaceExample.execute(s.split(" "),result);
                executeButton.setEnabled(true);
                if (exitCode != 0){
                    args.setText(args2);
                }
                break;
            }
            case 3:{
                executeButton.setEnabled(false);
                exitCode = WaitNotifyExample.execute(s.split(" "),result);
                executeButton.setEnabled(true);
                if (exitCode != 0){
                    args.setText(args3);
                }
                break;
            }
            case 4:{
                executeButton.setEnabled(false);
                exitCode = WorkersExample.execute(s.split(" "),result);
                executeButton.setEnabled(true);
                if (exitCode != 0){
                    args.setText(args4);
                }
                break;
            }default:{
                System.exit(1);
            }

        }
    }

    public static void run() {
        JFrame window = new JFrame("Super!");
        window.setSize( 400, 300);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setBackground(Color.BLUE);




        args = new JTextField(30);
        args.setText(argsF);
        args.setEditable(true);
        args.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                if (args.getText().isEmpty() || args.getText().equals(argsF)){
                    executeButton.setEnabled(false);
                }else{
                    executeButton.setEnabled(true);
                }

            }
        });
        args.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (args.getText().equals(argsF) || args.getText().equals(args1) || args.getText().equals(args2)
                        || args.getText().equals(args3)|| args.getText().equals(args4)){
                    args.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });




        result = new JTextArea(100,30);
        result.setEditable(false);
        JScrollPane s =new JScrollPane(result);

        JButton exitButton = new JButton("Exit");
        exitButton.setForeground(Color.RED);
        exitButton.setBackground(Color.BLACK);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.setVisible(false);
                System.exit(0);
            }
        });



        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                args.setText(argsF);
                result.setText(resF);
            }
        });


        executeButton = new JButton("Execute");
        executeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int number = getNumber(task1,task2,task3,task4);
                System.out.print(number);
                result.setText("");
                execution(number,args.getText());

            }
        });

        executeButton.setEnabled(false);




        task1 =new JRadioButton("Task 1");
        task2 =new JRadioButton("Task 2");
        task3 =new JRadioButton("Task 3");
        task4 =new JRadioButton("Task 4");
        ButtonGroup group = new ButtonGroup();
        group.add(task1);
        group.add(task2);
        group.add(task3);
        group.add(task4);


        JPanel tasKPanel = new JPanel(new GridBagLayout());
        JLabel selectTask = new JLabel("Task selector:");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        tasKPanel.add(selectTask,c);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(0,40,0,0);
        tasKPanel.add(task1,c);
        c.insets = new Insets(0,0,0,0);
        c.gridx=1;
        c.gridy=1;
        tasKPanel.add(task2,c);
        c.gridx=2;
        c.gridy=1;
        tasKPanel.add(task3,c);
        c.gridx=3;
        c.gridy=1;
        tasKPanel.add(task4,c);

        task1.setSelected(true);
        window.add(tasKPanel,BorderLayout.NORTH);

        JPanel bottomPanel =new JPanel(new BorderLayout());
        bottomPanel.add(exitButton,BorderLayout.LINE_END);
        JPanel clearExecutePanel = new JPanel(new GridLayout());

        clearExecutePanel.add(executeButton);
        clearExecutePanel.add(clearButton);
        bottomPanel.add(clearExecutePanel,BorderLayout.CENTER);

        JPanel argsPanel = new JPanel( new GridLayout());
        JLabel argsLabel = new JLabel(argsF);
        argsPanel.add(argsLabel);
        argsPanel.add(args);


        JPanel resPanel = new JPanel( new BorderLayout());
        resPanel.add(s,BorderLayout.CENTER);
        result.setText(resF);
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(argsPanel,BorderLayout.NORTH);
        centerPanel.add(resPanel,BorderLayout.CENTER);
        window.add(centerPanel,BorderLayout.CENTER);
        window.add(bottomPanel,BorderLayout.SOUTH);
        window.setVisible(true);
    }


    public static void main(String[] args) {
        run();
    }
}