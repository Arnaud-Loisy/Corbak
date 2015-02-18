package corbak;

/**
 * Struct definition : Signature
 * 
 * @author OpenORB Compiler
*/
public final class Signature implements org.omg.CORBA.portable.IDLEntity
{
    /**
     * Struct member hash
     */
    public String hash;

    /**
     * Default constructor
     */
    public Signature()
    { }

    /**
     * Constructor with fields initialization
     * @param hash hash struct member
     */
    public Signature(String hash)
    {
        this.hash = hash;
    }

}
