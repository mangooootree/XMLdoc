//
//1.	Разработать для своего варианта структуру XML документа, описать её с помощью XSD. Создать файл XML,
// соответствующий разработанной XSD схеме.
//        2.	При разработке XSD использовать простые и комплексные типы, перечисления,
// шаблоны и предельные значения, обязательно использование атрибутов и типа ID.
//        3.	Создать Java-класс(классы), соответствующие разработанной схеме.
//        4.	Создать Java-приложение для разбора XML-документа и инициализации коллекции объектов информацией
// из XML-файла. Для разбора использовать SAX, DOM или StAX парсер. Для сортировки объектов использовать интерфейс Comparator.
//        5.	Произвести проверку корректности и валидности XML-документа с привлечением XSD.

//13.	Концерты танцевального коллектива.
//        Танцевальный номер, представленный на концерте, имеют следующие характеристики:
//        •	Type – направление танца (бальный, народный, эстрадный, восточный и т.д.).
//        •	Scene – место выступления (актовый зал, уличная площадка, телестудия и т.д.).
//        •	Number of dancers – массовый, сольный, парный.
//        •	Music – вид музыкального сопровождения (фонограмма, “живая” музыка).
//        •	Dancers (должно быть несколько) – имя танцора или название коллектива, возраст, сколько лет занимается
// (для танцора); год образования, имя руководителя (для коллектива)
//        •	Number – каким по счёту номером в программе.





package Dance;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {


    public static void main(String[] args) {

        //validating
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(new File("./src/main/resources/dances.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("./src/main/resources/Dances.xml")));
            System.out.println("It's ok!");
        } catch (SAXException | IOException e) {
            System.out.println("Validation failed");
        }

        //parsing
        SaxParser danceParser = new SaxParser();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        List<Dance> dances = new ArrayList<>();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse("./src/main/resources/Dances.xml", danceParser);
            dances = danceParser.getDances();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(dances, new DanceCompator());

        for (Dance dance : dances)
            System.out.println(dance);

    }

}
