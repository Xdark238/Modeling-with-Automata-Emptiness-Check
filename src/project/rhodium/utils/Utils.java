/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.rhodium.utils;

import java.util.ArrayList;
import project.rhodium.model.States;
import project.rhodium.model.Transitions;

/**
 *
 * @author Rhodium
 */
public class Utils {
    public static ArrayList<Integer> BackwardBFS(ArrayList<Transitions> transitions,ArrayList<States> states,ArrayList<Integer> qList){
        ArrayList<Integer> sList=new ArrayList();
        ArrayList<Integer> bList=new ArrayList();
        for(int i=0;i<qList.size();i++){
            sList.add(qList.get(i));
            bList.add(qList.get(i));
        }
        
        while(bList.size()>0){
            ArrayList<Integer> tempList=new ArrayList();
            for(int i=0;i<bList.size();i++){
                for(int j=0;j<transitions.size();j++){
                    if(transitions.get(j).getIdTo()==bList.get(i)){
                        int q=transitions.get(j).getIdFrom();
                        
                        if(!sList.contains(q)){
                            tempList.add(q);
                        }
                    }
                }
            }
            bList.clear();
            for(int i=0;i<tempList.size();i++){
                bList.add(tempList.get(i));
            }
            for(int i=0;i<bList.size();i++){
                boolean check=false;
                int temp=bList.get(i);
                for(int j=0;j<sList.size();j++){
                    if(temp==sList.get(j)){
                        check=true;
                    }
                }
                if(check==false){
                    sList.add(temp);
                }
            }
        }
        return sList;
    }
    
    public static ArrayList<Integer> Pre(ArrayList<Transitions> transitions,ArrayList<Integer> qList){
        ArrayList<Integer> preList=new ArrayList();
        
        for(int i=0;i<qList.size();i++){
            
            for(int j=0;j<transitions.size();j++){
                if(transitions.get(j).getIdTo()==qList.get(i)){
                    int qFrom=transitions.get(j).getIdFrom();
                    if(!preList.contains(qFrom)){
                       preList.add(qFrom);
                   }
                }
            }
        }
        
        return preList;
            
    }
    
    public static boolean EmersonLei(ArrayList<Transitions> transitions,ArrayList<States> states,ArrayList<Integer> qList){
        ArrayList<Integer> lList=new ArrayList();
        for(int i=0;i<qList.size();i++){
            lList.add(qList.get(i));
        }
        ArrayList<Integer> fList=new ArrayList();
        for(int i=0;i<states.size();i++){
            if(states.get(i).isFinish()==true){
                fList.add(states.get(i).getId());
            }
        }
        ArrayList<Integer> oldlList=new ArrayList();
        do{
            oldlList.clear();
            for(int i=0;i<lList.size();i++){
                oldlList.add(lList.get(i));
            }
            
            ArrayList<Integer> oldLF=new ArrayList();
            for(int i=0;i<oldlList.size();i++){
                int l=oldlList.get(i);
                for(int j=0;j<fList.size();j++){
                    if(l==fList.get(j)){
                        oldLF.add(l);
                    }
                }
            }
            lList.clear();
            lList=Pre(transitions, oldLF);
            
            lList=BackwardBFS(transitions, states, lList);
            System.out.println(lList);
            System.out.println(oldlList);
        }while(!lList.containsAll(oldlList)||lList.size()!=oldlList.size());
        ArrayList<Integer> initList=new ArrayList();
        for(int i=0;i<states.size();i++){
            if(states.get(i).isInit()==true){
                initList.add(states.get(i).getId());
            }
        }
        for(int i=0;i<initList.size();i++){
            if(lList.contains(initList.get(i))){
                return true;
            }
        }
        return false;
    }
}
