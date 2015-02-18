package corbak;

/**
 * Holder class for : certificatInvalide
 * 
 * @author OpenORB Compiler
 */
final public class certificatInvalideHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal certificatInvalide value
     */
    public corbak.certificatInvalide value;

    /**
     * Default constructor
     */
    public certificatInvalideHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public certificatInvalideHolder(corbak.certificatInvalide initial)
    {
        value = initial;
    }

    /**
     * Read certificatInvalide from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = certificatInvalideHelper.read(istream);
    }

    /**
     * Write certificatInvalide into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        certificatInvalideHelper.write(ostream,value);
    }

    /**
     * Return the certificatInvalide TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return certificatInvalideHelper.type();
    }

}
