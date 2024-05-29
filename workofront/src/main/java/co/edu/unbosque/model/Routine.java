package co.edu.unbosque.model;

public class Routine {
    private Long idroutine;
    private String name;
    private String description;
    private String difficulty;

    public Routine() {}

    public Routine(Long idroutine, String name, String description, String difficulty) {
        this.idroutine = idroutine;
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
    }

    public Long getIdroutine() {
        return idroutine;
    }

    public void setIdroutine(Long idroutine) {
        this.idroutine = idroutine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Routine [idroutine=" + idroutine + ", name=" + name + ", description=" + description + ", difficulty=" + difficulty + "]";
    }
}
