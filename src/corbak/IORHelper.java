package corbak;

/** 
 * Helper class for : IOR
 *  
 * @author OpenORB Compiler
 */ 
public class IORHelper
{
    /**
     * Insert IOR into an any
     * @param a an any
     * @param t IOR value
     */
    public static void insert(org.omg.CORBA.Any a, org.omg.CORBA.Object t)
    {
        a.type(type());
        write(a.create_output_stream(),t);
    }

    /**
     * Extract IOR from an any
     * @param a an any
     * @return the extracted IOR value
     */
    public static org.omg.CORBA.Object extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the IOR TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_alias_tc(id(),"IOR",orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_objref));
        }
        return _tc;
    }

    /**
     * Return the IOR IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:corbak/IOR:1.0";

    /**
     * Read IOR from a marshalled stream
     * @param istream the input stream
     * @return the readed IOR value
     */
    public static org.omg.CORBA.Object read(org.omg.CORBA.portable.InputStream istream)
    {
        org.omg.CORBA.Object new_one;
        new_one = istream.read_Object();

        return new_one;
    }

    /**
     * Write IOR into a marshalled stream
     * @param ostream the output stream
     * @param value IOR value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, org.omg.CORBA.Object value)
    {
        ostream.write_Object(value);
    }

}
