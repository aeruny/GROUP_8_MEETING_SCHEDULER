import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;

public class Interpreter {
    //public static void main(String[] args){
    public ArrayList<Student> loadData(){

        ArrayList<Student> studentArray = new ArrayList<Student>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File("schedules.xml"));

            NodeList students = document.getElementsByTagName("student");

            for (int i = 0; i < students.getLength(); i++){

                String studentName;

                Node node = students.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE){

                    Element element = (Element) node;

                    String name = element.getElementsByTagName("name").item(0).getTextContent();

                    studentName = name;

                    //System.out.println("Name: " + name);

                    NodeList days = element.getElementsByTagName("day");

                    ArrayList<ArrayList<Time>> busyTime = new ArrayList<ArrayList<Time>>();

                    for (int j = 0; j < days.getLength(); j++){

                        Node day = days.item(j);

                        if (day.getNodeType() == Node.ELEMENT_NODE){
                            Element dayElement = (Element) day;

                            String id = dayElement.getAttributes().getNamedItem("id").getNodeValue();
                            //System.out.println("Day: " + id);

                            NodeList classes = dayElement.getElementsByTagName("class");

                            for (int k = 0; k < classes.getLength(); k++){

                                ArrayList<Time> timeFrame = new ArrayList<Time>();

                                Node cla = classes.item(k);

                                if (cla.getNodeType() == Node.ELEMENT_NODE){
                                    Element classElement = (Element) cla;

                                    NodeList startTimes = classElement.getElementsByTagName("starttime");

                                    Integer startHour = 0, startMinute = 0, startSecond = 0;

                                    Node sta = startTimes.item(0);

                                    if (sta.getNodeType() == Node.ELEMENT_NODE) {

                                        Element startElement = (Element) sta;

                                        startHour = Integer.parseInt(startElement.getElementsByTagName("hours").item(0).getTextContent());
                                        startMinute = Integer.parseInt(startElement.getElementsByTagName("minutes").item(0).getTextContent());
                                        startSecond = Integer.parseInt(startElement.getElementsByTagName("seconds").item(0).getTextContent());
                                    }

                                    NodeList endTimes = classElement.getElementsByTagName("endtime");

                                    Integer endHour = 0, endMinute = 0, endSecond = 0;

                                    Node en = endTimes.item(0);

                                    if (en.getNodeType() == Node.ELEMENT_NODE){

                                        Element endElement = (Element) en;

                                        endHour = Integer.parseInt(endElement.getElementsByTagName("hours").item(0).getTextContent());
                                        endMinute = Integer.parseInt(endElement.getElementsByTagName("minutes").item(0).getTextContent());
                                        endSecond = Integer.parseInt(endElement.getElementsByTagName("seconds").item(0).getTextContent());
                                    }

                                    Time startTime = new Time(startHour, startMinute, startSecond);
                                    timeFrame.add(startTime);
                                    //System.out.println("Start Time: " + startHour + startMinute + startSecond);
                                    Time endTime = new Time(endHour, endMinute, endSecond);
                                    timeFrame.add(endTime);
                                    //System.out.println("End Time: " + endHour + endMinute + endSecond);
                                }

                                busyTime.add(timeFrame);
                            }
                        }
                    }
                    Student newStudent = new Student(name, busyTime);
                    studentArray.add(newStudent);
                }
            }

        } catch (ParserConfigurationException| SAXException | IOException e){
            e.printStackTrace();
        }

        return studentArray;
    }
}