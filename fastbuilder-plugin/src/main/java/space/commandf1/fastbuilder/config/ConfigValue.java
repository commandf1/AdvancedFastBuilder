package space.commandf1.fastbuilder.config;

public class ConfigValue<T> {
    private String path;
    private T value;
    private String description;

    public ConfigValue(String path) {
        this.path = path;
        this.value = null;
    }

    public ConfigValue(String path, T value) {
        this.value = value;
        this.path = path;
    }

    public ConfigValue(String path, T value, String description) {
        this.value = value;
        this.path = path;
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @SuppressWarnings("all")
    public void setObjectValue(Object value) {
        this.value = (T) value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
