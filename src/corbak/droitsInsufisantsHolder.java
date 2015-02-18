package corbak;

/**
 * Holder class for : droitsInsufisants
 * 
 * @author OpenORB Compiler
 */
final public class droitsInsufisantsHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal droitsInsufisants value
     */
    public corbak.droitsInsufisants value;

    /**
     * Default constructor
     */
    public droitsInsufisantsHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public droitsInsufisantsHolder(corbak.droitsInsufisants initial)
    {
        value = initial;
    }

    /**
     * Read droitsInsufisants from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = droitsInsufisantsHelper.read(istream);
    }

    /**
     * Write droitsInsufisants into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        droitsInsufisantsHelper.write(ostream,value);
    }

    /**
     * Return the droitsInsufisants TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return droitsInsufisantsHelper.type();
    }

}
