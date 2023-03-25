package main

import "sort"

type Pos struct {
	idx  int
	item int
}

func advantageCount(A []int, B []int) []int {
	sortedB := make([]*Pos, len(B))
	for i, val := range B {
		sortedB[i] = &Pos{i, val}
	}
	sort.Slice(sortedB, func(i int, j int) bool {
		return sortedB[i].item < sortedB[j].item
	})
	sort.Ints(A)
	pos, pt := 0, 0
	a, filled := make([]int, len(A)), make([]bool, len(A))
	unmatched := []int{}
	for i := 0; i < len(A); i++ {
		found := false
		for pt < len(B) {
			sb := sortedB[pt]
			if sb.item >= A[i] {
				pt++
				continue
			}
			a[sb.idx] = A[i]
			filled[sb.idx] = true
			found = true
			pt++
			break
		}
		if !found {
			unmatched = append(unmatched, A[i])
		}
	}

	for _, um := range unmatched {
		for pos < len(A) {
			if !filled[pos] {
				a[pos] = um
				pos++
				break
			}
			pos++
		}
	}
	return a
}
