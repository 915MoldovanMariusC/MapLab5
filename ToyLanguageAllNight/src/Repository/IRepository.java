package Repository;

import Model.ADT.List.MyIList;
import Model.PrgState.PrgState;

import java.util.List;

public interface IRepository {
    public void addPrg(PrgState prgState);
//    public PrgState getCrtPrg();

    public void logPrgStateExec(PrgState prg) throws Exception;

    public void setPrgList(MyIList<PrgState> prgList);

    public List<PrgState> getPrgList();
}
