package org.tyaa.demo.java.validators;

import org.tyaa.demo.java.validators.models.FirstFoo;
import org.tyaa.demo.java.validators.models.SecondFoo;
import org.tyaa.demo.java.validators.models.ThirdFoo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        FirstFoo o1 = new FirstFoo(1, "n"); // true
        FirstFoo o2 = new FirstFoo(null, ""); // true
        FirstFoo o3 = new FirstFoo(2, "y"); // false
        FirstFoo o4 = new FirstFoo(null, null); // false

        SecondFoo o5 = new SecondFoo(1d, true); // true
        SecondFoo o6 = new SecondFoo(null, true); // true
        SecondFoo o7 = new SecondFoo(2d, false); // false
        SecondFoo o8 = new SecondFoo(null, false); // false

        ThirdFoo o9 = new ThirdFoo();

        Map<String, Object> o1Requirements = Map.of("id", 1, "name", "n");
        Map<String, Object> o2Requirements = new LinkedHashMap<>();
        o2Requirements.put("id", null);
        o2Requirements.put("name", "");
        Map<String, Object> o3Requirements = Map.of("id", 3, "name", "n");
        Map<String, Object> o4Requirements = Map.of("id", 4, "name", "n");

        Map<String, Object> o5Requirements = Map.of("price", 1d, "selected", true);
        Map<String, Object> o6Requirements = new LinkedHashMap<>();
        o6Requirements.put("price", null);
        o6Requirements.put("selected", true);
        Map<String, Object> o7Requirements = Map.of("price", 3, "selected", true);
        Map<String, Object> o8Requirements = Map.of("price", 4, "selected", true);

        Map<String, Object> o8WrongRequirements = Map.of("id", 4, "name", "n");

        Map<String, Object> o9Requirements = Map.of("id", 4, "name", "n");

        List objects = List.of(o1, o2, o3, o4, o5, o6, o7, o8, o8, o9);
        List<Map<String, Object>> requirements =
                List.of(o1Requirements, o2Requirements, o3Requirements, o4Requirements, o5Requirements,
                        o6Requirements, o7Requirements, o8Requirements, o8WrongRequirements, o9Requirements);
        List<LinkedHashMap<String, String>> errors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            errors.add(new LinkedHashMap<>());
        }

        for (int i = 0; i < objects.size(); i++) {
            Validators.validateObjectContent(objects.get(i), requirements.get(i), errors.get(i));
        }

        errors.forEach(System.err::println);
    }
}
