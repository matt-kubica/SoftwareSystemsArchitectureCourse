import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {

    @BeforeEach
    public void initialize() {
        UserRegistry.getInstance().setCurrentUser(new User("DummyUser"));
    }

    @Test
    public void testTaskWithNegativeResult() {
        GamificationFacade gf = GamificationFacade.getInstance();
        gf.setGameRule(new FirstDummyGameRule(), DummyTaskNegativeResult.class);
        gf.setGameRule(new SecondDummyGameRule(), DummyTaskNegativeResult.class);
        gf.setGameRule(new ThirdDummyGameRule(), DummyTaskNegativeResult.class);
        gf.execute(new DummyTaskNegativeResult());
        assertEquals(130, UserRegistry.getInstance().getCurrentUser().getPoints());
    }
}
