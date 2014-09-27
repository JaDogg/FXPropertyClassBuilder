package info.simpll.fxprop;

/**
 * Java Data Types : contains basic wrapper class names
 * @author Bhathiya
 */
public enum JavaDataType {

    STRING("String"),
    INTEGER("Integer"),
    BOOLEAN("Boolean"),
    DOUBLE("Double"),
    FLOAT("Float"),
    LONG("Long");

    /**
     * String value
     */
    private final String value;

    private JavaDataType(String value) {
        this.value = value;
    }

    /**
     * 
     * @return string value
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * convert String to JavaDataType
     * @param type String type
     * @return JavaDataType or null if not found
     */
    public static JavaDataType fromString(String type) {
        switch (type) {
            case "String":
                return STRING;
            case "Integer":
                return INTEGER;
            case "Boolean":
                return BOOLEAN;
            case "Double":
                return DOUBLE;
            case "Float":
                return FLOAT;
            case "Long":
                return LONG;
        }
        return null;
    }
    

}
