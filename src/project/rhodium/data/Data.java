/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.rhodium.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import project.rhodium.model.States;
import project.rhodium.model.Transitions;
import project.rhodium.test.Test;

/**
 *
 * @author Rhodium
 */
public class Data {
    public static ArrayList<Transitions> TransitionData(){
         DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document doc=builder.parse("src/project/rhodium/data/test.jff");
            
            NodeList transitionList=doc.getElementsByTagName("transition");
            ArrayList<Transitions> transitions=new ArrayList();
            for(int i=0;i<transitionList.getLength();i++){
                Element tr=(Element) transitionList.item(i);
                Transitions transition=new Transitions();
                NodeList fromList=tr.getElementsByTagName("from");
                Element from=(Element) fromList.item(0);
                transition.setIdFrom(Integer.parseInt(from.getTextContent()));
                NodeList toList=tr.getElementsByTagName("to");
                Element to=(Element) toList.item(0);
                transition.setIdTo(Integer.parseInt(to.getTextContent()));
                NodeList labelList=tr.getElementsByTagName("read");
                Element label=(Element) labelList.item(0);
                transition.setLabel(label.getTextContent());
                transitions.add(transition);
            }
            return transitions;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<States> StateData(){
         DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder=factory.newDocumentBuilder();
            Document doc=builder.parse("src/project/rhodium/data/test.jff");
            
            NodeList stateList=doc.getElementsByTagName("state");
            ArrayList<States> states=new ArrayList();
            for(int i=0;i<stateList.getLength();i++){
                Element s=(Element) stateList.item(i);
                States state=new States();
                state.setId(Integer.parseInt(s.getAttribute("id")));
                state.setName(s.getAttribute("name"));
                NodeList sList=s.getChildNodes();
                for(int j=0;j<sList.getLength();j++){
                    Node x=sList.item(j);
                        if(x.getNodeType()==Node.ELEMENT_NODE){
                            Element a=(Element) x;
                            if(a.getTagName().equals("x")){
                                state.setX(Double.parseDouble(a.getTextContent()));
                            }
                            if(a.getTagName().equals("y")){
                                state.setY(Double.parseDouble(a.getTextContent()));
                            }
                            if(a.getTagName().equals("initial")){
                                state.setInit(true);
                            }
                            if(a.getTagName().equals("final")){
                                state.setFinish(true);
                            }
                        }
                }
                states.add(state);
            }
            return states;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Integer> QData(){
        ArrayList<States> states=StateData();
        ArrayList<Integer> qList=new ArrayList();
        for(int i=0;i<states.size();i++){
            qList.add(states.get(i).getId());
        }
        return qList;
    }
}
