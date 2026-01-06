package main

import (
	"fmt"
	"sort"
)

/*
https://leetcode.com/discuss/interview-question/1643907/Google-Interview-Question

Similar question: https://leetcode.com/discuss/interview-question/351313/Google-or-Phone-Screen-or-Salary-Adjustment

Given an amount of money to distribute, a list of recipients, and how much money each is owed, you
should return the list of recipients and how much each would be paid after following the business
logic below:

1. No recipient is paid more than they are owed
2. The amount is divided as evenly as possible between the recipients


Input: { c: 10, b: 10, a: 10, d: 10 }, 38

38/4 = 9.5 (rounded down) = 9
Remainder: 38 - 9*4 = 2 (2 folks can get 1 coin more)
iterate over the list:
	9 + 9 + 9 + 9 = 36 + 2 = 38
Output: { c: 9, b: 10, a: 10, d: 9

Input: {a: 10, b: 20, c: 30, d: 40}, 90


90/4 = 22.5 (rounded down) = 22
22 * 4 + 2 = 90
10 < 22, clear tab extra = 12
20 < 22, clear tab extra = 2
30 > 22 STOP

Remaining receipients = 2, remaining amount = 90 - 10 - 20 = 60
60 / 2 = 30 (rounded down) = 30
2 * 30 = 60
30 == 30, clear tab extra = 0
40 > 30 STOP

Remaining receipeints = 1, remaining amount =  60 - 30 = 30
30 / 1 = 30
30 > 40 for first receipient itself, so we stop and distribute to remaining equally

Output: {a:10, b:20, c:30, d:30}

Algorithm:
1. sort(owed) ascending
2. for pos = 0 -> len(owed); do:
		equally = total / (total recipients - pos)
		if owe[pos] <= equally (we can clear out some tabs):
      for pos -> len(owed) AND owe[pos] <= equally, do:
				pay[pos] = owe[pos]; pos++;
			end
    else:
			remainder = amount % recipients
			for pos -> len(owed); do:
				pay[pos] = equally + (1 if remainder > 0 else 0)
				pos += 1
				remainder -= 1
			end
		end
	end
*/

type DistributeMoney struct {
	owed        []int
	distributed []int
}

func (this *DistributeMoney) solve(pos int, amount int) []int {
	if pos >= len(this.owed) {
		return this.distributed
	}

	recipients := len(this.owed) - pos // remaining receipients
	equally := amount / recipients     // equally distributed amount

	// if we can clear out some tabs, we do now
	if equally >= this.owed[pos] {
		for pos < len(this.owed) && equally >= this.owed[pos] {
			this.distributed[pos] = this.owed[pos]
			amount -= this.owed[pos]
			pos++
		}
		return this.solve(pos, amount)
	} else {
		remainder := amount % recipients
		for pos < len(this.distributed) {
			if remainder > 0 {
				this.distributed[pos] = equally + 1
				remainder--
			} else {
				this.distributed[pos] = equally
			}
			pos++
		}
		return this.distributed
	}
}

type Tab struct {
	receipient string
	owed       int
}

func DistributeMoneySolution(money map[string]int, amount int) map[string]int {
	tabs := []Tab{}
	for receipient, owed := range money {
		tabs = append(tabs, Tab{receipient, owed})
	}
	sort.Slice(tabs, func(i, j int) bool {
		return tabs[i].owed < tabs[j].owed
	})
	owed := make([]int, len(tabs))
	for i, tab := range tabs {
		owed[i] = tab.owed
	}

	fmt.Println("owed:", owed)

	solution := &DistributeMoney{owed: owed, distributed: make([]int, len(owed))}
	distributed := solution.solve(0, amount)
	fmt.Println("distributed:", distributed)
	ans := map[string]int{}
	for i, tab := range tabs {
		ans[tab.receipient] = distributed[i]
	}
	return ans
}

// func main() {
// 	input := map[string]int{"a": 10, "b": 20, "c": 30, "d": 40}
// 	amount := 90
// 	ans := DistributeMoneySolution(input, amount)

// 	fmt.Printf("%#v\n", ans)
// }
