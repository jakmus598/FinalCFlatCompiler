import java.util.*;

/**
 * The Sym class defines a symbol-table entry. 
 * Each Sym contains a type (a Type).
 */
public class Sym {
    private Type type;
    private int offset;
    public static int currOffset = -1;
    
    public Sym(Type type) {
        this.type = type;
	this.offset = 0;
	//this.currOffset = -1;
    }
    
    public Type getType() {
        return type;
    }

    public void setOffset(int offset)
    {
	    this.offset = offset;
    }

    public int getOffset()
    {
	    return offset;
    }
    
    public String toString() {
        return type.toString();
    }
}

/**
 * The FnSym class is a subclass of the Sym class just for functions.
 * The returnType field holds the return type and there are fields to hold
 * information about the parameters.
 */
class FnSym extends Sym {
    // new fields
    private Type returnType;
    private int numParams;
    private List<Type> paramTypes;
    private int sizeOfLocals;
    private int sizeOfParams;
    
    public FnSym(Type type, int numparams) {
        super(new FnType());
        returnType = type;
        numParams = numparams;
	sizeOfLocals = 0;
	sizeOfParams = 0;
    }

    public void addFormals(List<Type> L) {
        paramTypes = L;
    }
    
    public Type getReturnType() {
        return returnType;
    }

    public int getNumParams() {
        return numParams;
    }

    public void setSizeOfParams(int size)
    {
	    sizeOfParams = size;
    }

    public int getSizeOfParams()
    {
	    return sizeOfParams;
    }

    public void setSizeOfLocals(int size)
    {
	    sizeOfLocals = size;
    }

    public int getSizeOfLocals()
    {
	    return sizeOfLocals;
    }

    public List<Type> getParamTypes() {
        return paramTypes;
    }

    public String toString() {
        // make list of formals
        String str = "";
        boolean notfirst = false;
        for (Type type : paramTypes) {
            if (notfirst)
                str += ",";
            else
                notfirst = true;
            str += type.toString();
        }

        str += "->" + returnType.toString();
        return str;
    }
}

/**
 * The StructSym class is a subclass of the Sym class just for variables 
 * declared to be a struct type. 
 * Each StructSym contains a symbol table to hold information about its 
 * fields.
 */
class StructSym extends Sym {
    // new fields
    private IdNode structType;  // name of the struct type
    
    public StructSym(IdNode id) {
        super(new StructType(id));
        structType = id;
    }

    public IdNode getStructType() {
        return structType;
    }    
}

/**
 * The StructDefSym class is a subclass of the Sym class just for the 
 * definition of a struct type. 
 * Each StructDefSym contains a symbol table to hold information about its 
 * fields.
 */
class StructDefSym extends Sym {
    // new fields
    private SymTable symTab;
    
    public StructDefSym(SymTable table) {
        super(new StructDefType());
        symTab = table;
    }

    public SymTable getSymTable() {
        return symTab;
    }
}
