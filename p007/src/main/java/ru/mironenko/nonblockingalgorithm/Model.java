package ru.mironenko.nonblockingalgorithm;

/**
 * Created by nikita on 12.06.2017.
 */
public class Model {

    /**
     * id of model
     */
    private int id;
    /**
     * Name of model
     */
    private String name;
    /**
     * Version of model
     */
    private volatile int version = 0;

    public Model(){}

    /**
     * Constructor of Model class
     * @param id - id of model
     * @param name - name of model
     */
    public Model(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter of id
     * @return id of model
     */
    public int getId() {
        return id;
    }

    /**
     * Getter of name
     * @return name of model
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name. Sets new name to model and inc version of model
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        this.version++;
    }

    /**
     * Getter of models version
     */

    public int getVersion() {
        return this.version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;

        Model model = (Model) o;

        if (getId() != model.getId()) return false;
        if (getVersion() != model.getVersion()) return false;
        return getName() != null ? getName().equals(model.getName()) : model.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getVersion();
        return result;
    }
}
