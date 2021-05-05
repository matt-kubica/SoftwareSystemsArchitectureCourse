public interface GameRule {

    void applyRuleBeforeExecution();
    void applyRuleAfterExecution(Object taskResult);
    void applyRuleInCaseOfException(FailedExecutionException exception);

}
