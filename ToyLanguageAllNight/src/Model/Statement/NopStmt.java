package Model.Statement;

import Model.PrgState.PrgState;

class NopStmt implements IStmt{

    @Override
    public PrgState execute(PrgState state) throws Exception {
        return null;
    }
}