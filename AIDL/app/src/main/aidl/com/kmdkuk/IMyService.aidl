// IMyService.aidl
package com.kmdkuk;

// Declare any non-default types here with import statements

interface IMyService {
    int Add(int val1, int val2);
    int new_generation(int target, int up, int down, int left, int right);
    int new_life(inout int[] target);
}