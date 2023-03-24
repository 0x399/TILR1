package org.example;

import java.util.*;

public class LetterFrequency {
    public static void main(String[] args) {
        String str = "Тема: Вплив соціальних мереж на життя сучасної людини\n" +
                "\n" +
                "Соціальні мережі стали неодмінною складовою життя сучасної людини. Вони дозволяють нам спілкуватися з друзями та родичами, знайомитися з новими людьми, отримувати інформацію, навчатися та розважатися. Однак, разом з тим, їх вплив на наше життя може мати як позитивні, так і негативні наслідки.\n" +
                "\n" +
                "Серед позитивних аспектів соціальних мереж можна виділити можливість зв'язку зі світом навколо нас. Соціальні мережі дозволяють нам знаходити людей зі схожими інтересами, обговорювати новини, ділитися досвідом та знаннями. Вони також стали важливим інструментом для просування бізнесу, оскільки дають можливість залучати нових клієнтів та збільшувати продажі.\n" +
                "\n" +
                "Однак, соціальні мережі також можуть мати негативний вплив на наше життя. Занадто часте користування соціальними мережами може призвести до зменшення продуктивності та зосередженості, спричинити зниження самооцінки та почуття соціальної ізольованості. Більш того, соціальні мережі можуть стати причиною поширення фейкових новин та інформації, що може призвести до негативних наслідків для суспільства.";

        Map<Character, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c);
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }

        Map<Character, Integer> sortedMap = sortByValue(freqMap);

        int count = 0;
        int totalNum = 0;

        for (Map.Entry entry : sortedMap.entrySet()) {
            count++;
            totalNum += (Integer) entry.getValue();
        }

        System.out.println(count + " букви використано загалом.");
        System.out.println(totalNum + " - загальна кількість знаків.");

        double nameInfo = 0.0;

        for (Map.Entry entry : sortedMap.entrySet()) {
            System.out.print("Буква: " + entry.getKey() + " ");
            Integer a = (Integer) entry.getValue();
            Double ratio = a / (double)totalNum;
            System.out.print(ratio + " Кількість інформації: ");
            System.out.print(- Math.log(ratio) / Math.log(2));
            String name = "Святослав Приймак";
            if(name.indexOf((Character)entry.getKey()) != -1)
                nameInfo += (- Math.log(ratio) / Math.log(2));
            System.out.println();
        }

        double h = 0;
        for (Map.Entry entry : sortedMap.entrySet()) {
            Integer a = (Integer) entry.getValue();
            Double ratio = a / (double)totalNum;
            h -= ratio * (Math.log(ratio) / Math.log(2));
        }
        System.out.println("Загальна ентропія становить: " + h + " біт/символ.");
        System.out.println("Кількісь інформації в імені становить " + nameInfo );
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}