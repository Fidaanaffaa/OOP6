package oop.ex6.Logic.Variables;


/**
 * The Variable Abstract class that is the superclass for all variable type classes
 */
public abstract class Variable {
    /**
     * how deep inside the scope of the method block the variable is
     */
    private int depth;
    /**
     * whether this variable is a final constant
     */
    private boolean Final;
    /**
     * the name of the variable
     */
    private String Name;
    /**
     * whether this variable has been initialized
     */
    private boolean initialized;
    /**
     * whether this variable is global
     */
    private boolean global;
    /**
     * the type of the variable in string form
     */
    private String type;


    /**
     * The constructor for the abstract Variable class
     * @param Name the name of the variable
     * @param depth how deep inside the scope of the method block the variable is
     * @param initialized whether this variable has been initialized
     * @param isfinal whether this variable is a final constant
     * @param isglobal whether this variable is global
     * @param type the type of the variable in string form, used for factory switch case
     */
    public Variable(String Name, int depth, boolean initialized, boolean isfinal, boolean isglobal,
                    String type){
        this.Name = Name;
        this.depth = depth;
        this.Final = isfinal;
        this.initialized = initialized;
        this.global = isglobal;
        this.type = type;
    }

    /**
     * getter for whether variable is global or not
     */
    public boolean getIsGlobal(){
        return global;
    }
    /**
     * getter for the depth of this variable
     */
    public int getDepth(){
        return this.depth;
    }

    /**
     *getter for the the name of this variable
     */
    public String getName(){
        return this.Name;
    }

    /**
     * The getter for whether this variable has been initialized or not
     */
    public boolean getInitialization(){
        return this.initialized;
    }

    /**
     * The getter for the type of this variable in string form
     */
    public String getType(){return this.type;}
}
