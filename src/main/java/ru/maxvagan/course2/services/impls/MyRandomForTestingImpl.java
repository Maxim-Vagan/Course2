package ru.maxvagan.course2.services.impls;

import org.springframework.stereotype.Service;
import ru.maxvagan.course2.services.MyRandomForTesting;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class MyRandomForTestingImpl implements MyRandomForTesting {
    private final Set<Integer> setOfIdxForNormalizeRandom = new HashSet<>();
    private final Random rnd = new Random();

    public int getRealRandomNextInt(int amount) {
        int itemIdx = rnd.nextInt(amount);
        if (setOfIdxForNormalizeRandom.size() <= 0)
            for (int i = 0; i < amount; i++) { setOfIdxForNormalizeRandom.add(i); }
        while (!setOfIdxForNormalizeRandom.contains((Integer) itemIdx)) {
            itemIdx = rnd.nextInt(amount);
        }
        setOfIdxForNormalizeRandom.remove(itemIdx);
        return itemIdx;
    }
}
