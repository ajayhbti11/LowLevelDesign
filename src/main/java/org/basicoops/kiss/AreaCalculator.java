package org.basicoops.kiss;

import java.util.List;

public class AreaCalculator {

    public double calculateAverage(List<Integer> numbers){

        int sum=0;
        for (int number:numbers){
            sum+=number;
        }

        return  sum/(double)numbers.size();

    }
}
