package corbak;

/**
 * Holder class for : authentificationEchouee
 * 
 * @author OpenORB Compiler
 */
final public class authentificationEchoueeHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal authentificationEchouee value
     */
    public corbak.authentificationEchouee value;

    /**
     * Default constructor
     */
    public authentificationEchoueeHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public authentificationEchoueeHolder(corbak.authentificationEchouee initial)
    {
        value = initial;
    }

    /**
     * Read authentificationEchouee from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = authentificationEchoueeHelper.read(istream);
    }

    /**
     * Write authentificationEchouee into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        authentificationEchoueeHelper.write(ostream,value);
    }

    /**
     * Return the authentificationEchouee TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return authentificationEchoueeHelper.type();
    }

}
