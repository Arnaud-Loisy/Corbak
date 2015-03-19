package corbak;

/** 
 * Helper class for : Message
 *  
 * @author OpenORB Compiler
 */ 
public class MessageHelper
{
    private static final boolean HAS_OPENORB;
    static {
        boolean hasOpenORB = false;
        try {
            Thread.currentThread().getContextClassLoader().loadClass("org.openorb.CORBA.Any");
            hasOpenORB = true;
        }
        catch(ClassNotFoundException ex) {
        }
        HAS_OPENORB = hasOpenORB;
    }
    /**
     * Insert Message into an any
     * @param a an any
     * @param t Message value
     */
    public static void insert(org.omg.CORBA.Any a, corbak.Message t)
    {
        a.insert_Streamable(new corbak.MessageHolder(t));
    }

    /**
     * Extract Message from an any
     * @param a an any
     * @return the extracted Message value
     */
    public static corbak.Message extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        if (HAS_OPENORB && a instanceof org.openorb.CORBA.Any) {
            // streamable extraction. The jdk stubs incorrectly define the Any stub
            org.openorb.CORBA.Any any = (org.openorb.CORBA.Any)a;
            try {
                org.omg.CORBA.portable.Streamable s = any.extract_Streamable();
                if(s instanceof corbak.MessageHolder)
                    return ((corbak.MessageHolder)s).value;
            } catch (org.omg.CORBA.BAD_INV_ORDER ex) {
            }
            corbak.MessageHolder h = new corbak.MessageHolder(read(a.create_input_stream()));
            a.insert_Streamable(h);
            return h.value;
        }
        return read(a.create_input_stream());
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;
    private static boolean _working = false;

    /**
     * Return the Message TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            synchronized(org.omg.CORBA.TypeCode.class) {
                if (_tc != null)
                    return _tc;
                if (_working)
                    return org.omg.CORBA.ORB.init().create_recursive_tc(id());
                _working = true;
                org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
                org.omg.CORBA.StructMember []_members = new org.omg.CORBA.StructMember[3];

                _members[0] = new org.omg.CORBA.StructMember();
                _members[0].name = "sign";
                _members[0].type = corbak.SignatureHelper.type();
                _members[1] = new org.omg.CORBA.StructMember();
                _members[1].name = "text";
                _members[1].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_string);
                _members[2] = new org.omg.CORBA.StructMember();
                _members[2].name = "estChiffre";
                _members[2].type = orb.get_primitive_tc(org.omg.CORBA.TCKind.tk_boolean);
                _tc = orb.create_struct_tc(id(),"Message",_members);
                _working = false;
            }
        }
        return _tc;
    }

    /**
     * Return the Message IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:corbak/Message:1.0";

    /**
     * Read Message from a marshalled stream
     * @param istream the input stream
     * @return the readed Message value
     */
    public static corbak.Message read(org.omg.CORBA.portable.InputStream istream)
    {
        corbak.Message new_one = new corbak.Message();

        new_one.sign = corbak.SignatureHelper.read(istream);
        new_one.text = istream.read_string();
        new_one.estChiffre = istream.read_boolean();

        return new_one;
    }

    /**
     * Write Message into a marshalled stream
     * @param ostream the output stream
     * @param value Message value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, corbak.Message value)
    {
        corbak.SignatureHelper.write(ostream,value.sign);
        ostream.write_string(value.text);
        ostream.write_boolean(value.estChiffre);
    }

}
