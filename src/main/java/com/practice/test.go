package main

/*
You need to buy some pieces of land. Each piece of land grows one particular fruit ( will be given in the input ).
conditions: we can buy only one consecutive block of land (containing multiple adjacent pieces of land)
We need to buy atleast one land piece for each fruit plantation.
Given that all the land pieces cost the same X, what would be the minimum cost is which we can fulfill the buying
requirements.
*/

import "fmt"

// DRY RUN for input:
// 1 1 2 3 2 3 1 2 3
// low = 0, i = 0, {1:0}
// low = 0, i = 1, {1:0} -> {1:1} -> seen = true
//		low = 0, fruitAtLow = 1, recordedIdxForFruitAtLow = 1 > 0 -> low = 1
//		len(fruitIndex) != 3
// low = 1, i = 2, {1:1} -> {1:1,2:2} -> seen = false
// 		len(fruitIndex) != 3
// low = 1, i = 3, {1:1, 2:2} -> {1:1, 2:2, 3:3} -> seen = false
//	  len(fruitIndex) == 3 -> ans = 3
// low = 1, i = 4, {1:1, 2:2, 3:3} -> {1:1, 2:4, 3:3} -> seen = true
//		low = 1, fruitAtLow = 1, recordedIdxForFruitAtLow = 1 == 1 -> break
// low = 1, i = 5 {1:1, 2:4, 3:3} -> {1:1, 2:4, 3:5} -> seen = true
//    low = 1, fruitAtLow = 1, recordedIdxForFruitAtLow = 1 == 1 -> break
// low = 1, i = 6, {1:1, 2:4, 3:5} -> {1:6, 2:4, 3:5} -> seen = true
// 		low = 1, fruitAtLow = 1, recordedIdxForFruitAtLow = 6 > 1 -> low = 2
//    low = 2, fruitAtLow = 2, recordedIdxForFruitAtLow = 4 > 2 -> low = 3
// 		low = 3, fruitAtLow = 3, recordedIdxForFruitAtLow = 5 > 3 -> low = 4
//    low = 4, fruitAtLow = 2, recordedIdxForFruitAtLow = 4 == 4 -> break
//		len(fruitIndex) == 3 -> ans = max(3, 6-4+1) = 3
// so on ...

func solve(fruits []string) int {
	// Calculate distinct fruits
	fruitsSet := map[string]bool{}
	for _, fruit := range fruits {
		fruitsSet[fruit] = true
	}
	fruitCount := len(fruitsSet)

	// Sliding window
	low := 0
	fruitIndexer := map[string]int{} // Store most recent index for a fruit

	ans := len(fruits)
	for i := 0; i < len(fruits); i++ {
		fruit := fruits[i]
		_, seen := fruitIndexer[fruit] // check if this fruit already has been seen
		fruitIndexer[fruit] = i        // record index for this fruit as well
		if seen {
			// slide the lower end of window
			for low <= i {
				fruitAtLow := fruits[low]
				recordedIdxForFruitAtLow := fruitIndexer[fruitAtLow]
				if recordedIdxForFruitAtLow > low {
					low++
				} else {
					break // cannot reduce the window any more
				}
			}
		}
		// Calculate ans for this index
		if len(fruitIndexer) == fruitCount && i-low+1 < ans {
			ans = i - low + 1
		}
	}
	return ans
}

func main() {
	ans := solve([]string{"1", "2", "2", "3", "3", "3", "5", "4"})
	fmt.Println(ans)
}
