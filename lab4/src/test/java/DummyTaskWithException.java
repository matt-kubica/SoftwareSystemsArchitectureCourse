public class DummyTaskWithException implements Task {
    @Override
    public Object execute() throws FailedExecutionException {
        throw new FailedExecutionException("Cannot complete a task");
    }
}
