package lab05;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TestPerson {

    public static void main(String[] args) {

        List<Person> people = Person.createShortList();

        sortBySurname(people);

        System.out.println("=== Sorted Asc SurName ===");
        for (Person person : people) {
            System.out.println(person.getName());
        }
        
        System.out.println("");
        
        Map<Person.Gender, List<Person>> map = groupByGender(people);

        System.out.println("=== Grouped by Gender ===");
        for (Entry<Person.Gender, List<Person>> entry : map.entrySet()) {
            System.out.println(entry);
        }
        
        System.out.println("");
        
        List<Entry<Person.Gender, Double>> ages = averageAgeByGender(people);

        System.out.println("=== Ages by Gender ===");
        for (Entry<Person.Gender, Double> entry : ages) {
            System.out.println(entry);
        }

    }
    
    // (a)
    public static void sortBySurname(List<Person> list) {
        
        // Sort with Inner Class
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getSurName().compareTo(p2.getSurName());
            }
        });
    }
    
    // (b)
    public static Map<Person.Gender, List<Person>> groupByGender(List<Person> list) {
        
        Map<Person.Gender, List<Person>> map = new HashMap<>();   

        for (Person person : list) {
            Person.Gender gender = person.getGender();
            if (map.containsKey(gender)) {
                List<Person> people = map.get(gender);
                people.add(person);
                map.put(gender, people);
            } else {
                List<Person> people = new ArrayList();
                people.add(person);
                map.put(gender, people);
            }
        }
        
        return map;
    }
    
    // (c)
    public static List<Entry<Person.Gender, Double>> averageAgeByGender(List<Person> list) {
        
        double mAge = 0;
        int mCount = 0;
        double fAge = 0;
        int fCount = 0;
        
        for(Person person : list) {
            if(person.getGender() == Person.Gender.MALE) {
                mAge += person.getAge();
                mCount++;
            } else {
                fAge += person.getAge();
                fCount++;
            }
        }
        
        List<Entry<Person.Gender, Double>> ages = new ArrayList();
        ages.add(Map.entry(Person.Gender.MALE, mAge/mCount));
        ages.add(Map.entry(Person.Gender.FEMALE, fAge/fCount));
        return ages;
        
    }
    
    // A generic helper factory method to create an instance of Entry
    // For JDK9 or above, you can use Map.entry(K k, V v);
    static <K, V> Map.Entry<K, V> entry(K k, V v) {
        return new AbstractMap.SimpleEntry<>(k, v);
    }
}
