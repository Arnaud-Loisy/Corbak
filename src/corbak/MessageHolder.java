package corbak;

/**
 * Holder class for : Message
 * 
 * @author OpenORB Compiler
 */
final public class MessageHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Message value
     */
    public corbak.Message value;

    /**
     * Default constructor
     */
    public MessageHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public MessageHolder(corbak.Message initial)
    {
        value = initial;
    }

    /**
     * Read Message from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = MessageHelper.read(istream);
    }

    /**
     * Write Message into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        MessageHelper.write(ostream,value);
    }

    /**
     * Return the Message TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return MessageHelper.type();
    }

}
