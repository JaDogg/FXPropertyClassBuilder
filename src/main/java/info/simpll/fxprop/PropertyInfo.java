package info.simpll.fxprop;

import javafx.beans.property.*;

/**
 * Property info property class
 * @author Bhathiya
 */
public class PropertyInfo {
    /**
     * field name for a property
     */
    private final SimpleStringProperty name;
    
    /**
     * data type for a property
     */
    private final SimpleStringProperty type;

    /**
     * PropertyInfo default constructor
     * initialize final fields
     */
    public PropertyInfo() {
        name = new SimpleStringProperty();
        type = new SimpleStringProperty();
    }

    /**
     * PropertyInfo constructor
     * @param name field name for a property
     * @param type data type for a property
     */
    public PropertyInfo(String name, String type) {
        this();
        this.name.set(name);
        this.type.set(type);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }
    
}
