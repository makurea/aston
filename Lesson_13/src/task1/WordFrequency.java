package task1;

import java.util.*;

public class WordFrequency {

    public void findUniqueWords() {
        List<String> words = Arrays.asList(
                "красный", "синий", "зеленый", "красный", "синий", "желтый",
                "оранжевый", "фиолетовый", "красный", "синий", "оранжевый",
                "черный", "белый", "желтый", "фиолетовый", "зеленый",
                "синий", "серый", "серый"
        );

        Set<String> uniqueWords = new HashSet<>(words);
        System.out.println("Уникальные слова: " + uniqueWords);

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        System.out.println("Частота встречаемости слов: " + wordCount);
    }
}
