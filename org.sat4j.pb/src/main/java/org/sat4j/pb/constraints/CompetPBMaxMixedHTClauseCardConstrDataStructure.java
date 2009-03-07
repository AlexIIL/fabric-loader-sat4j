/*******************************************************************************
 * SAT4J: a SATisfiability library for Java Copyright (C) 2004-2008 Daniel Le Berre
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU Lesser General Public License Version 2.1 or later (the
 * "LGPL"), in which case the provisions of the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of the LGPL, and not to allow others to use your version of
 * this file under the terms of the EPL, indicate your decision by deleting
 * the provisions above and replace them with the notice and other provisions
 * required by the LGPL. If you do not delete the provisions above, a recipient
 * may use your version of this file under the terms of the EPL or the LGPL.
 * 
 * Based on the original MiniSat specification from:
 * 
 * An extensible SAT solver. Niklas Een and Niklas Sorensson. Proceedings of the
 * Sixth International Conference on Theory and Applications of Satisfiability
 * Testing, LNCS 2919, pp 502-518, 2003.
 *
 * See www.minisat.se for the original solver in C++.
 * 
 *******************************************************************************/
package org.sat4j.pb.constraints;

import java.math.BigInteger;

import org.sat4j.core.Vec;
import org.sat4j.core.VecInt;
import org.sat4j.minisat.core.Constr;
import org.sat4j.pb.constraints.pb.IDataStructurePB;
import org.sat4j.pb.constraints.pb.LearntBinaryClausePB;
import org.sat4j.pb.constraints.pb.LearntHTClausePB;
import org.sat4j.pb.constraints.pb.OriginalBinaryClausePB;
import org.sat4j.pb.constraints.pb.OriginalHTClausePB;
import org.sat4j.pb.constraints.pb.UnitClausePB;
import org.sat4j.specs.IVec;
import org.sat4j.specs.IVecInt;

public class CompetPBMaxMixedHTClauseCardConstrDataStructure extends
		CompetPBMaxClauseCardConstrDataStructure {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;

	@Override
	protected Constr constructClause(IVecInt v) {
		// return WLClausePB.brandNewClause(solver, getVocabulary(), v);
		if (v == null)
			return null;
		if (v.size() == 2) {
			return OriginalBinaryClausePB.brandNewClause(solver,
					getVocabulary(), v);
		}
		return OriginalHTClausePB.brandNewClause(solver, getVocabulary(), v);
	}

	@Override
	protected Constr constructLearntClause(IDataStructurePB dspb) {
		IVecInt resLits = new VecInt();
		IVec<BigInteger> resCoefs = new Vec<BigInteger>();
		dspb.buildConstraintFromConflict(resLits, resCoefs);
		if (resLits.size() == 1) {
			return new UnitClausePB(resLits.last(), getVocabulary());
		}
		if (resLits.size() == 2) {
			return new LearntBinaryClausePB(resLits, getVocabulary());
		}
		return new LearntHTClausePB(resLits, getVocabulary());
	}

}