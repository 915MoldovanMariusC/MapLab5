package Controller;

import Model.ADT.List.MyList;
import Model.PrgState.PrgState;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.IRepository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {

    private IRepository repo;
    private ExecutorService executor;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

//    public PrgState oneStep(PrgState state) throws Exception {
//        MyIStack<IStmt> stk = state.getExeStack();
//        if (stk.isEmpty()) throw new Exception("prgstate stack is empty");
//        IStmt crtStmt = stk.pop();
//        return crtStmt.execute(state);
//    }



    void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException {
        //before the execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        //RUN concurrently one step for each of the existing PrgStates//-----------------------------------------------------------------------//prepare the list of callables
        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());
        //start the execution of the callables//it returns the list of new created PrgStates (namely threads)
        List<PrgState> newPrgList = executor.invokeAll(callList). stream()
                . map(future -> { try {
                    return future.get();}
                catch(Exception e) {
                    System.out.println(e.getMessage());
                    }
                    return null;
                })
                .filter(p -> p!=null)
                .collect(Collectors.toList());
        //add the new created threads to the list of existing threads
        prgList.addAll(newPrgList);
        //------------------------------------------------------------------------------//after the execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        MyList<PrgState> list = new MyList<PrgState>(prgList);
        repo.setPrgList(list);
    }


    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs

        List<PrgState>  prgList=removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0){
            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList=removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        // setPrgList of repository in order to change the repository
        // update the repository state
        MyList<PrgState> list = new MyList<PrgState>(prgList);
        repo.setPrgList(list);
    }








//    public void allStep() throws Exception {
//        PrgState prg = repo.getCrtPrg(); // repo is the controller field of type MyRepoInterface
//        // here you can display the prg state
//        System.out.println(prg.toString());
//        this.repo.logPrgStateExec();
//        while (!prg.getExeStack().isEmpty()) {
//            oneStep(prg);
//            System.out.println(prg);
//            this.repo.logPrgStateExec();
//            prg.getHeap().setContent((HashMap<Integer, Value>) unsafeGarbageCollector(
//                    getAddrFromSymTable(prg.getSymTable().getContent().values()),
//                    prg.getHeap().getContent()));
//            //here you can display the prg state
//        }
//    }

    public void addPrg(PrgState prg){
        this.repo.addPrg(prg);
    }


    Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                        .collect(Collectors.toMap(HashMap.Entry::getKey, Map.Entry::getValue));
    }

    List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());}

    List<Integer> getAddrFromHeapTable(Collection<Value> heapTableValues){
        return heapTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());}

    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

}

