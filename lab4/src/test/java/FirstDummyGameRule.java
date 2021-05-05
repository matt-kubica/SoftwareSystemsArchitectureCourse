public class FirstDummyGameRule implements GameRule {

    @Override
    public void applyRuleBeforeExecution() {
        User user = UserRegistry.getInstance().getCurrentUser();
        user.addPoints(50);
        user.addBadge("Initial badge, with initial 50 points :)");
        System.out.println(" * Applied rule before execution");
    }

    @Override
    public void applyRuleAfterExecution(Object taskResult) {
        User user = UserRegistry.getInstance().getCurrentUser();
        Integer numericResult = (Integer) taskResult;

        if (numericResult.compareTo(0) < 0) {
            user.addBadge("Task has negative result :(");
            user.addPoints(-5);
        } else {
            user.addBadge("Task has positive result :)");
            user.addPoints(15);
        }
        System.out.println(" * Applied rule after execution");
    }

    @Override
    public void applyRuleInCaseOfException(FailedExecutionException exception) {
        User user = UserRegistry.getInstance().getCurrentUser();
        user.addBadge("Task has thrown an exception...");
        user.addPoints(-20);
        System.out.println(" * Applied rule in case of exception");
    }
}
