public enum LoggingLevel {
    DEBUG(0),
    INFO(1),
    WARNING(2),
    ERROR(3);

    public final Integer value;

    private LoggingLevel(Integer value) {
        this.value = value;
    }
}
