package corbak;

/**
 * Holder class for : Signature
 * 
 * @author OpenORB Compiler
 */
final public class SignatureHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Signature value
     */
    public corbak.Signature value;

    /**
     * Default constructor
     */
    public SignatureHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public SignatureHolder(corbak.Signature initial)
    {
        value = initial;
    }

    /**
     * Read Signature from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = SignatureHelper.read(istream);
    }

    /**
     * Write Signature into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        SignatureHelper.write(ostream,value);
    }

    /**
     * Return the Signature TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return SignatureHelper.type();
    }

}
