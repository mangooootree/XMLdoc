//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.11.05 at 02:17:50 PM MSK 
//


package Dance.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TypeOfMusic.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TypeOfMusic">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="phonogram"/>
 *     &lt;enumeration value="live music"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "TypeOfMusic")
@XmlEnum
public enum TypeOfMusic {

    @XmlEnumValue("phonogram")
    PHONOGRAM("phonogram"),
    @XmlEnumValue("live music")
    LIVE_MUSIC("live music");
    private final String value;

    TypeOfMusic(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeOfMusic fromValue(String v) {
        for (TypeOfMusic c : TypeOfMusic.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
