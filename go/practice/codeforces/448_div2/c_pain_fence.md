# Explanation and Proof of the Painting Fence Algorithm

## Problem Recap

We are given a fence with n planks. Each plank i has a height a[i]. We want to
paint the entire fence using a brush that can paint exactly 1 meter width in
vertical or horizontal strokes. In a vertical stroke, one plank is painted
entirely, whereas a horizontal stroke paints a contiguous segment of planks up
to a given height. The goal is to determine the minimum number of strokes needed
to paint the entire fence.

## Algorithm Overview

The algorithm employs a recursive, divide-and-conquer approach. Here’s what it
does for any segment of the fence:

1. **Recursive Function Definition:** Define a function `solve(a, l, r, base)`
   where:
   - `a` is the array of plank heights.
   - `[l, r]` is the current segment of the fence being considered.
   - `base` is the height that has already been painted uniformly for the entire
     segment.

   The function returns the minimum number of strokes required to paint the
   remaining part of the segment (i.e., from height `base` up to the top of each
   plank in the segment).

2. **Base Case:** If the segment is empty (`l > r`), return 0 as there is
   nothing to paint.

3. **Find the Minimum Height:** In the current segment, determine the minimum
   height `minVal`. This represents the smallest height (besides the already
   painted `base`) that needs to be painted in this segment.

4. **Two Options to Paint the Segment:**

   - **Option 1 (Vertical Painting):** Paint each of the planks in the segment
     individually using vertical strokes. This would cost `r - l + 1` strokes.

   - **Option 2 (Horizontal then Recursive):** First, paint the entire segment
     horizontally from `base` up to `minVal`. This costs `minVal - base`
     strokes.

     After this horizontal stroke, the planks that were taller than `minVal`
     will have an unpainted part above `minVal`. These parts form subsegments
     separated by the planks that exactly match `minVal`. Recursively calculate
     the number of strokes needed to paint each of these subsegments above the
     already painted height `minVal` and add them to the horizontal stroke cost.

5. **Combine the Results:** The final answer for the segment is the minimum of:
   - The vertical painting option (`r - l + 1` strokes), and
   - The cost obtained by the horizontal painting option plus the strokes needed
     for the subsegments.

## Proof of Correctness

### 1. Correctness by Induction

- **Base Case (n = 0):** If the segment is empty (i.e., no planks to paint), the
  function correctly returns 0 strokes.

- **Inductive Step:** Assume that the algorithm correctly computes the minimum
  strokes for any segment with fewer than k planks. Now, consider a segment with
  k planks.

  - **Vertical Strokes Option:** Painting each plank individually is a valid
    strategy; hence, it requires exactly `k` strokes.

  - **Horizontal Stroke Option:** Let `minVal` be the smallest height in the
    segment. Performing a horizontal stroke from `base` to `minVal` paints every
    plank in one contiguous operation and costs `minVal - base` strokes. After
    this, only the parts where the planks extend above `minVal` remain
    unpainted. These remaining parts, by their nature, are separated by any
    planks that exactly reach `minVal` (since the horizontal stroke covers them
    completely). For each subsegment where a plank extends above `minVal`, the
    problem is reduced to a smaller instance with fewer planks and a new base
    equal to `minVal`. By the induction hypothesis, the recursive call correctly
    computes the optimal number of strokes for these subsegments.

  Since our algorithm considers both valid strategies (vertical and
  horizontal+recursive) and chooses the minimum, it must yield the minimum
  stroke count for the segment of k planks.

### 2. Greedy Choice Justification

The key observation in this problem is that a horizontal stroke is beneficial
when multiple adjacent planks need to be painted up to a certain level. The
horizontal stroke paints all the planks simultaneously, making it potentially
less expensive than painting each one separately, especially when the contiguous
segment is long. The optimal strategy is to compare this benefit against the
simple strategy of using vertical strokes. The greedy choice of painting up to
the current smallest height in the segment and recursively addressing the
remaining sections guarantees that we never overspend strokes.

### 3. Handling Overlapping Strokes

The algorithm correctly handles the fact that painting can be done multiple
times on the same area—an aspect that might initially seem to lead to
overcounting. By ensuring that once a horizontal stroke is done, the next
recursive call only deals with the parts above the painted level, we avoid
double counting strokes for the same section.

## Complexity Analysis

- Finding the minimum in a segment takes O(k) for k planks in the worst case.
- The recursive division could, in the worst-case scenario (e.g., if the heights
  are in increasing order), lead to a time complexity of O(n^2) in the worst
  case. Given that n ≤ 5000, this quadratic complexity is acceptable.

## Conclusion

The algorithm effectively and optimally uses a combination of vertical strokes
and a horizontal stroke strategy (with recursive subproblem solving) to minimize
the total number of strokes required to paint the fence. The inductive proof and
the greedy argument demonstrate the correctness of the approach.

Happy painting!
