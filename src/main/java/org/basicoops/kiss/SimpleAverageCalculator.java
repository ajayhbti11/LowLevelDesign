package org.basicoops.kiss;

import java.util.List;

public class SimpleAverageCalculator {

    public double calculateAverage(List<Integer> numbers){


        return  numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);



    }
}
