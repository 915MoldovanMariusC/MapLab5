package Model.Statement;

import Model.ADT.Dictionary.FileDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.PrgState.PrgState;
import Model.Value.Value;

import java.io.BufferedReader;

public class ForkStmt implements IStmt{

    IStmt statement;

    ForkStmt(IStmt statement){
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        MyIStack<IStmt> exeStack = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTable = (MyIDictionary<String, Value>) state.getSymTable().clone();
        MyIList<Value> out = state.getOut();
        MyIHeap<Integer, Value> heap = state.getHeap();
        FileDictionary<String, BufferedReader> fileTable = state.getFileTable();
        PrgState newThread = new PrgState(exeStack, symTable, out, fileTable, heap, statement);
        return newThread;
    }
}
