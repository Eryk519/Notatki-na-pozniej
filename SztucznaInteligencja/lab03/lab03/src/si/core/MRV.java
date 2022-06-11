package si.core;

public class MRV extends SolutionStrategy {

    public Assignment solve(CSP csp) {
        return recursiveBackTrackingSearch(csp, new Assignment());
    }

    private Assignment recursiveBackTrackingSearch(CSP csp, Assignment assignment) {
        Assignment result = null;
        if (assignment.isComplete(csp.getVariables())) {
            result = assignment;
        } else {
            Variable var = selectUnassignedVariableMRV(assignment, csp);
            for (Object value : orderDomainValues(var, assignment, csp)) {
                assignment.setAssignment(var, value);
                fireStateChanged(assignment, csp);
                if (assignment.isConsistent(csp.getConstraints(var))) {
                    DomainRestoreInfo info = inference(var, assignment, csp);
                    if (!info.isEmpty())
                        fireStateChanged(csp);
                    if (!info.isEmptyDomainFound()) {
                        result = recursiveBackTrackingSearch(csp, assignment);
                        if (result != null)
                            break;
                    }
                    info.restoreDomains(csp);
                }
                assignment.removeAssignment(var);
            }
        }
        return result;
    }

    protected Variable selectUnassignedVariableMRV(Assignment assignment, CSP csp) {
        Variable min=csp.getVariables().get(0);
        for (Variable var : csp.getVariables()) {
            if (!(assignment.hasAssignmentFor(var))){
                int r=csp.getDomain(var).size();
                int n=csp.getDomain(min).size();
                if (r < n){
                    min = var;
                }
            }
        }
        return min;
    }


    protected Iterable<?> orderDomainValues(Variable var, Assignment assignment, CSP csp) {
        return csp.getDomain(var);
    }

    protected DomainRestoreInfo inference(Variable var, Assignment assignment,  CSP csp) {
        return new DomainRestoreInfo().compactify();
    }
}
