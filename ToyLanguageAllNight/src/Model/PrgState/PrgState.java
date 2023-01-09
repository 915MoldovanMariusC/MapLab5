package Model.PrgState;

import Model.ADT.Dictionary.FileDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.Statement.IStmt;
import Model.Value.BoolValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;

public class PrgState {

    static int id;
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;

    MyIHeap<Integer, Value> heap;
    IStmt originalProgram;

    public synchronized int getId(){
        return id;
    }

    public synchronized void setId(int id){
        PrgState.id = id;
    }

    public FileDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public void setFileTable(FileDictionary<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    FileDictionary<String, BufferedReader> fileTable;

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public MyIHeap<Integer, Value> getHeap() {
        return heap;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, FileDictionary<String, BufferedReader> fileTable, MyIHeap<Integer, Value> heap, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        this.heap = heap;
        this.fileTable = fileTable;
        //TODO: ceva
        // originalProgram=deepCopy(prg);
        stk.push(prg);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("ID: ").append(getId()).append("\n");
        string.append("EXE STACK:\n");
        string.append(exeStack.toString()).append("\n");
        string.append("SYM TABLE\n");
        string.append(symTable.toString()).append("\n");
        string.append("OUT:\n");
        string.append(out.toString()).append("\n");
        string.append("FILE TABLE\n");
        string.append(fileTable.toString()).append("\n");
        string.append("HEAP\n");
        string.append((heap.toString())).append("\n");
        return string.toString();

    }

    public Boolean isNotCompleted(){
        return !this.getExeStack().isEmpty();
    }

    public PrgState oneStep() throws Exception{
        if(exeStack.isEmpty())
            throw  new Exception("prgstate stack is empty");
        IStmt  crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }
}