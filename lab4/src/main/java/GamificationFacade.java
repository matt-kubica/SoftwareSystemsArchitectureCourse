import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamificationFacade {

    private static GamificationFacade instance;
    private Map <Class <? extends Task>, List<GameRule>> associations;

    public static  GamificationFacade getInstance() {
        if (instance == null) {
            instance = new GamificationFacade();
            return instance;
        }
        return instance;
    }

    private GamificationFacade() {
        this.associations = new HashMap<>();
    }

    public void setGameRule(GameRule rule, Class <? extends Task> taskClass) {
        if (!this.associations.containsKey(taskClass))
            this.associations.put(taskClass, new ArrayList<GameRule>());
        this.associations.get(taskClass).add(rule);
    }

    public Object execute(Task task) {
        List <GameRule> gameRules = this.associations.get(task.getClass());

        gameRules.forEach(GameRule::applyRuleBeforeExecution);
        try {
            Object result = task.execute();
            gameRules.forEach(gameRule -> gameRule.applyRuleAfterExecution(result));
            return result;
        } catch (FailedExecutionException e) {
            gameRules.forEach(gameRule -> gameRule.applyRuleInCaseOfException(e));
        }
        return null;
    }
}
