import java.util.Random;

public class DummyTaskPositiveResult implements Task {

    @Override
    public Object execute() throws FailedExecutionException {
        return new Random().nextInt(100) + 1;
    }
}
