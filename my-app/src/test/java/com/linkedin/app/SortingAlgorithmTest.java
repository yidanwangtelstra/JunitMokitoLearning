package com.linkedin.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SortingAlgorithmTest {
    SortingAlgorithm algorithm = new SortingAlgorithm();

    @ParameterizedTest
    @MethodSource("array")
    public void assortedTest(int[] array) {
        int[] result = algorithm.sort(Arrays.copyOf(array, array.length));
        assertArrayEquals(new int[]{1,2,3,4,5}, result);
    }

    static Stream<int[]> array(){
        return Stream.of(
                new int[]{5, 3, 1, 4, 2},
                new int[]{1, 2, 3, 4, 5}
                );
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void nullOrEmptyTest(int[] array){
        int[] result = algorithm.sort(array);
        assertArrayEquals(array, result);
    }

    @ParameterizedTest
    @MethodSource("duplicateArray")
    public void duplicateTest(int[] array) {
        int[] result = algorithm.sort(Arrays.copyOf(array, array.length));
        assertArrayEquals(array, result);
    }

    static Stream<int[]> duplicateArray(){
        return Stream.of(
                new int[]{1, 1, 2, 2, 3},
                new int[]{1, 1, 1, 1, 1});
    }
}
