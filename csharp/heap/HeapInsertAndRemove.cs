﻿using System;
using System.Collections;

public class HeapInsertAndRemove
{
    // leftChild = heap[2 * i]
    // rightChild = heap[(2 * i) + 1] 
    // parent = heap[i // 2]

    List<int> heap;

    public HeapInsertAndRemove()
    {
        heap = new List<int>{0};
    }

    public void Push(int val)
    {
        heap.Add(val);
        int i = heap.Count - 1;

        // Percolate up
        while (i > 1 && heap[i] < heap[i / 2])
        {
            int tmp = heap[i];
            heap[i] = heap[i / 2];
            heap[i / 2] = tmp;
            i = i / 2;
        }
    }

    public int Pop()
    {
        if (heap.Count == 1)
        {
            // return null;
        }
        if (heap.Count == 2)
        {
            int res = heap[heap.Count - 1];
            heap.Remove(heap.Count - 1);
            return res; // equivalent to heap.remove(1)
        }

        int res = heap[1];
        // Move last value to root
        heap[1] = res;
        heap.Remove(heap.Count - 1);
        int i = 1;
        // Percolate down
        while (2 * i < heap.Count)
        {
            if (2 * i + 1 < heap.Count &&
            heap[2 * i + 1] < heap[2 * i] &&
            heap[i] > heap[2 * i + 1])
            {
                // Swap right child
                int tmp = heap[i];
                heap[i] = heap[2 * i + 1];
                heap[2 * i + 1] = tmp;
                i = 2 * i + 1;
            }
            else if (heap[i] > heap[2 * i])
            {
                // Swap left child
                int tmp = heap[i];
                heap[i] = heap[2 * i];
                heap[2 * i] = tmp;
                i = 2 * i;
            }
            else
            {
                break;
            }
        }
        return res;
    }

}

