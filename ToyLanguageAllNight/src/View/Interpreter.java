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
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Model.PrgState.PrgState;
import Model.Statement.*;
import Model.Type.IntType;
import Model.Value.StringValue;
import Model.Value.Value;
import Repository.IRepository;
import Repository.Repository;

import java.io.BufferedReader;

class Interpreter {
    public static void main(String[] args) {
        IStmt ex1 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new OpenRFileStmt(new ValueExp(new StringValue("log.txt"))),
                        new CompStmt(new ReadFileStmt(new ValueExp(new StringValue("log.txt")), "a"),
                                new CloseRFileStmt(new ValueExp(new StringValue("logd.txt"))))));


        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl = new MyDictionary<String, Value>();
        MyIList<Value> ot = new MyList<Value>();
        FileDictionary<String, BufferedReader> fileTable = new FileDictionary<String, BufferedReader>();
        MyIHeap<Integer, Value> heap = new MyHeap<Integer, Value>();

        PrgState prg1 = new PrgState(stk, symtbl, ot, fileTable, heap, ex1);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        //menu.addCommand(new RunExample("2", ex1.toString(), ctr2));
        //menu.addCommand(new RunExample("3", ex1.toString(), ctr3));
        menu.show();
    }

}