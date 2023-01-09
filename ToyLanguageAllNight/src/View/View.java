package View;

import Controller.Controller;
import Model.ADT.Dictionary.FileDictionary;
import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyHeap;
import Model.ADT.Heap.MyIHeap;
import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.Expression.ArithExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.PrgState.PrgState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.util.Scanner;


public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;

//
//        IStmt e1 = new CompStmt(new VarDeclStmt("v", new IntType()),
//                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
//
//
//        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a",
//                new ArithExp("+", new ValueExp(new IntValue(2)), new ArithExp("*", new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
//                new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
//
//
//        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
//                new CompStmt(new VarDeclStmt("v", new IntType()),new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
//                        new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
//                                new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
//



    }

    public void printMenu(){
        System.out.println("1. int v; v=2;Print(v)\n\n");
        System.out.println("2. int a;int b; a=2+3*5;b=a+1;Print(b)\n\n");
        System.out.println("3. bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)\n\n\"");
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pick a program:\n");
        printMenu();
        IStmt program;
        int n = scanner.nextInt();
        if(n == 1){
            program = new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        } else if(n == 2){
            program = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new AssignStmt("a",
                    new ArithExp("+", new ValueExp(new IntValue(2)), new ArithExp("*", new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                    new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        } else if (n == 3) {
            program = new CompStmt(new VarDeclStmt("a",new BoolType()),
                    new CompStmt(new VarDeclStmt("v", new IntType()),new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                            new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))),
                                    new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        } else {
            System.out.println("Invalid choice");
            run();
            return;
        }

        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl = new MyDictionary<String, Value>();
        MyIList<Value> ot = new MyList<Value>();
        FileDictionary<String, BufferedReader> fileTable = new FileDictionary<String, BufferedReader>();
        MyIHeap<Integer, Value> heap = new MyHeap<Integer, Value>();

        PrgState prg = new PrgState(stk, symtbl, ot, fileTable, heap, program);
        this.controller.addPrg(prg);
        try {
            this.controller.allStep();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(ot);

    }

}
