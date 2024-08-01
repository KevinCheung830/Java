/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab05;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Van
 */
public class FruitSet {

    public static void main(String[] args) {

        Set<String> setA = newSet();
        setA.add("apple");
        setA.add("banana");
        setA.add("durian");
        setA.add("grape");
        setA.add("papaya");
        System.out.print("Set A: ");
        System.out.println(setA);

        Set<String> setB = newSet();
        setB.add("banana");
        setB.add("mango");
        setB.add("papaya");
        setB.add("tomato");
        setB.add("watermelon");
        System.out.print("Set B: ");
        System.out.println(setB);

        System.out.print("Union: ");
        Set<String> union = newSet();
        union.addAll(setA);
        union.addAll(setB);
        System.out.println(union);

        System.out.print("Intersection: ");
        Set<String> intersection = newSet();
        intersection.addAll(setA);
        intersection.retainAll(setB);
        System.out.println(intersection);

        System.out.print("Complement of A: ");
        Set<String> compA = newSet(setB);
        compA.removeAll(setA);
        System.out.println(compA);

        System.out.print("Complement of B: ");
        Set<String> compB = newSet(setA);
        compB.removeAll(setB);
        System.out.println(compB);

    }
    
    // Here is the only place deadling with the implementation of a Set interface
    public static Set newSet(){
        //return new HashSet<>();
        return new TreeSet<>();
    }
    
    public static Set newSet(Set other){
        Set set = newSet();
        set.addAll(other);
        return set;
    }

}
