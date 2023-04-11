package oop.ex6.Logic.Variables;

/**
 * The booleanVar class which extends the Variable class. Stores the characteristics of the boolean
 * variable such as the name, how deep in the scope, etc.
 */
public class BooleanVar extends Variable {
    /**
     * The constructor for the booleanVar class, calls the Variable super constructor
     * @param Name the name of the variable
     * @param depth how deep inside the scope of the method block the variable is
     * @param initialized whether this variable has been initialized
     * @param isfinal whether this variable is a final constant
     * @param isglobal whether this variable is global
     * @param type the type of the variable in string form, used for factory switch case
     */
    public BooleanVar(String Name, int depth, boolean initialized, boolean isfinal, boolean isglobal,
                      String type) {
        super(Name, depth, initialized, isfinal, isglobal, type);
    }
}