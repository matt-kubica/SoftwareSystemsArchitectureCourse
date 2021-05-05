import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class User {

    private String username;
    private Integer points;
    private List<String> badges;

    private Integer recentPoints;
    private String recentBadge;

    public User(String username) {
        this.username = username;
        this.points = 0;
        this.recentPoints = 0;
        this.badges = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public Integer getPoints() {
        return points;
    }

    public List<String> getBadges() {
        return badges;
    }

    public void addPoints(Integer points) {
        this.recentPoints = points;
        this.points += points;
    }

    public void addBadge(String badge) {
        this.recentBadge = badge;
        this.badges.add(badge);
    }

    public void removeBadge(String toRemove) {
        this.badges.removeIf(badge -> badge.equals(toRemove));
        this.recentBadge = null;
    }

    @Override
    public String toString() {
        return String.format("<User %s:%d>", this.username, this.points);
    }
}
