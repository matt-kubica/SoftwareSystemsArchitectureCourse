public class UserRegistry {

    private ThreadLocal <User> threadUser;

    private static UserRegistry instance = null;

    private UserRegistry() {
        this.threadUser = new ThreadLocal<>();
    }

    public static UserRegistry getInstance() {
        if (instance == null) {
            instance = new UserRegistry();
        }
        return instance;
    }

    public void setCurrentUser(User user) {
        this.threadUser.set(user);
    }

    public User getCurrentUser() {
        return this.threadUser.get();
    }

    public void unsetCurrentUser() {
        this.threadUser.remove();
    }
}
