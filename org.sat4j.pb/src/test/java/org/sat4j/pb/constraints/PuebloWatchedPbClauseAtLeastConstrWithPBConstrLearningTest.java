package org.sat4j.pb.constraints;

import org.sat4j.pb.IPBSolver;
import org.sat4j.pb.SolverFactory;

public class PuebloWatchedPbClauseAtLeastConstrWithPBConstrLearningTest extends
        AbstractPseudoBooleanAndPigeonHoleTest {

    public PuebloWatchedPbClauseAtLeastConstrWithPBConstrLearningTest(String arg) {
        super(arg);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected IPBSolver createSolver() {
        return SolverFactory.newMiniOPBClauseAtLeastMinPueblo();
    }

}
