package Dance;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SaxParser extends DefaultHandler {
    private Stack<StartElementInformation> elements = new Stack<>();
    private List<Dance> listOfDances = new ArrayList<>();
    private Dance dance;
    private TypeOfDance danceType;
    private TypeOfScene sceneType;
    private TypeOfMusic musicType;
    private DanceGroup danceGroup;
    private SoloPairGroup soloPairGroup;
    private String danceID;
    private MassType massType;
    private PairType pairType;
    private SoloType soloType;
    private Dancer dancer;
    private boolean endElement = false;

    static class StartElementInformation {
        String uri;
        String localName;
        String qName;
        Attributes attributes;

        public StartElementInformation(String uri, String localName, String qName, Attributes attributes) {
            this.uri = uri;
            this.localName = localName;
            this.qName = qName;
            this.attributes = attributes;
        }
    }

    public List<Dance> getDances() {
        return listOfDances;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        StartElementInformation startElementInformation = new StartElementInformation(uri, localName, qName, attributes);
        elements.push(startElementInformation);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        StartElementInformation currentElement = elements.peek();

        if (endElement) {
            endElement = false;
            return;
        }

        switch (currentElement.localName) {
            case "Dance":
                dance = new Dance();
                dance.setDanceID(currentElement.attributes.getValue("DanceID"));
                break;
            case "DanceType":
                danceType = TypeOfDance.fromValue(new String(ch, start, length));
                dance.setDanceType(danceType);
                break;
            case "SceneType":
                sceneType = TypeOfScene.fromValue(new String(ch, start, length));
                dance.setSceneType(sceneType);
                break;
            case "MusicType":
                musicType = TypeOfMusic.fromValue(new String(ch, start, length));
                dance.setMusicType(musicType);
                break;
            case "DanceGroup":
                danceGroup = new DanceGroup();
                break;
            case "DanceGroupName":
                danceGroup.setDanceGroupName(new String(ch, start, length));
                break;
            case "YearOfFoundation":
                danceGroup.setYearOfFoundation(Integer.parseInt(new String(ch, start, length)));
                break;
            case "FounderName":
                danceGroup.setFounderName(new String(ch, start, length));
                dance.setDanceGroup(danceGroup);
                break;
            case "SoloPairGroup":
                soloPairGroup = new SoloPairGroup();
                break;
            case "Mass":
                massType = new MassType();
                soloPairGroup.setMass(massType);
                dance.setSoloPairGroup(soloPairGroup);
                break;
            case "Pair":
                pairType = new PairType();
                soloPairGroup.setPair(pairType);
                dance.setSoloPairGroup(soloPairGroup);
                break;
            case "Solo":
                soloType = new SoloType();
                soloPairGroup.setSolo(soloType);
                dance.setSoloPairGroup(soloPairGroup);
                break;
            case "Dancer":
                dancer = new Dancer();
                break;
            case "DancerName":
                dancer.setDancerName(new String(ch, start, length));
                break;
            case "DancerGender":
                dancer.setDancerGender(new String(ch, start, length));
                break;
            case "DancerAge":
                dancer.setDancerAge(Integer.parseInt(new String(ch, start, length)));
                break;
            case "DancerAgeOfExperience":
                dancer.setDancerAgeOfExperience(Integer.parseInt(new String(ch, start, length)));
                if (dance.getSoloPairGroup().getMass() != null)
                    dance.getSoloPairGroup().getMass().getRest().add(dancer);
                if (dance.getSoloPairGroup().getPair() != null)
                    dance.getSoloPairGroup().getPair().getRest().add(dancer);
                if (dance.getSoloPairGroup().getSolo() != null)
                    dance.getSoloPairGroup().getSolo().setDancer(dancer);
                break;

        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        elements.pop();
        endElement = true;
        if (qName.equals("Dance"))
            listOfDances.add(dance);
    }
}