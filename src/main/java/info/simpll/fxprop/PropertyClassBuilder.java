package info.simpll.fxprop;

import java.util.List;

/**
 *
 * @author Bhathiya
 */
public class PropertyClassBuilder {

    /**
     * class name for code generator to use
     */
    private final String className;

    /**
     * constructor for code generator
     *
     * @param className class name
     */
    public PropertyClassBuilder(String className) {
        this.className = className;
    }

    /**
     * constructor for code generator, Class name used is : PropertyClassName
     */
    public PropertyClassBuilder() {
        this("PropertyClassName");
    }

    /**
     * generate code
     *
     * @param propertyInfoList
     * @return
     */
    public String generateCode(List<PropertyInfo> propertyInfoList) {
        String code = "import javafx.beans.property.*;\n" + "\n" + "/**\n"
                + " * Class Information\n" + " * @author Your Name\n" + " */\n"
                + "public class %s {\n" + "\n" + "%s\n" + "\n"
                + "    public %s() {\n" + "%s\n" + "    }\n" + "\n" + "%s\n"
                + "\n" + "}";
        StringBuilder fields = new StringBuilder();
        StringBuilder initCodes = new StringBuilder();
        StringBuilder methods = new StringBuilder();

        propertyInfoList.forEach((PropertyInfo propertyInfo) -> {
            JavaDataType type = JavaDataType.fromString(propertyInfo.getType());
            String name = propertyInfo.getName();
            fields.append(toPrivateField(type, name));
            initCodes.append(toInitCode(type, name));
            methods.append(toGetterMethod(type, name));
            methods.append("\n\n");
            methods.append(toSetterMethod(type, name));
            methods.append("\n\n");
            methods.append(toPropertyMethod(type, name));
            methods.append("\n\n");
        });

        return String.format(code, className, fields.toString(), className,
                initCodes.toString(), methods.toString());
    }

    //create private field code
    private static String toPrivateField(JavaDataType type, String name) {
        return String.format("    private final %s %s;\n",
                toPropertyType(type), name);
    }

    //create code for "creating new property object"
    private static String toInitCode(JavaDataType type, String name) {
        return String.format("        %s = new %s();\n", name,
                toPropertyType(type));
    }

    //to upper camel case
    private static String toUpperCamelCase(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    //create getter method
    private static String toGetterMethod(JavaDataType type, String name) {
        String code = "    public %s %s%s() {\n"
                + "        return %s.get();\n" + "    }";
        String getPrepend = "get";
        if (type == JavaDataType.BOOLEAN) {
            getPrepend = "is";
        }
        return String.format(code, type, getPrepend, toUpperCamelCase(name),
                name);
    }

    //create setter method
    private static String toSetterMethod(JavaDataType type, String name) {
        String code = "    public void set%1$s(%2$s %3$s) {\n"
                + "        this.%3$s.set(%3$s);\n" + "    }";

        return String.format(code, toUpperCamelCase(name),
                type, name);
    }

    //create property method
    private static String toPropertyMethod(JavaDataType type, String name) {
        String code = "    public %1$s %2$sProperty() {\n"
                + "        return %2$s;\n" + "    }";

        return String.format(code, toPropertyType(type), name);
    }

    //get property type based on Wrapper Class name
    private static String toPropertyType(JavaDataType type) {
        return String.format("Simple%sProperty", type);
    }
}
