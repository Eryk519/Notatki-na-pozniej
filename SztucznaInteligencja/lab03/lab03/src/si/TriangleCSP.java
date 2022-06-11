package si;

import si.core.CSP;
import si.core.Domain;
import si.core.NotEqualConstraint;
import si.core.Variable;

public class TriangleCSP extends CSP {
    public static final Variable X1 = new Variable("X1");
    public static final Variable X2 = new Variable("X2");
    public static final Variable X3 = new Variable("X3");
    public static final String RED = "RED";
    public static final String GREEN = "GREEN";
    public static final String BLUE = "BLUE";

    public TriangleCSP() {
        collectVariables();

        setDomain(X1,new Domain(new Object[]{RED, GREEN, BLUE}));
        setDomain(X2,new Domain(new Object[]{RED}));
        setDomain(X3,new Domain(new Object[]{GREEN}));

        addConstraint(new NotEqualConstraint(X1, X2));
        addConstraint(new NotEqualConstraint(X1, X3));
        addConstraint(new NotEqualConstraint(X2, X3));
    }

    private void collectVariables() {
        addVariable(X1);
        addVariable(X2);
        addVariable(X3);
    }
}