/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.rhodium.test;

import project.rhodium.data.Data;
import project.rhodium.utils.Utils;


/**
 *
 * @author Rhodium
 */
public class Test {
    public static void main(String[] args){
       System.out.println(Utils.EmersonLei(Data.TransitionData(), Data.StateData(), Data.QData()));
    }
}
