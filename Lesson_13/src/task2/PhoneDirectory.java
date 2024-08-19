package task2;

import java.util.*;

public class PhoneDirectory {
    private final Map<String, List<String>> directory = new HashMap<>();

    public void add(String surname, String phoneNumber) {
        directory.computeIfAbsent(surname, k -> new ArrayList<>()).add(phoneNumber);
    }

    public List<String> get(String surname) {
        return directory.getOrDefault(surname, Collections.emptyList());
    }
}
