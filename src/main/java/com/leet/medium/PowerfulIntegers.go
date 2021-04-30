package main

func powerfulIntegers(x int, y int, bound int) []int {

	xarr, yarr := []int{}, []int{}
	xval, yval := 1, 1
	if x == 1 {
		xarr = []int{1}
	} else {
		for xval <= 1e6 {
			xarr = append(xarr, xval)
			xval *= x
		}
	}

	if y == 1 {
		yarr = []int{1}
	} else {
		for yval <= 1e6 {
			yarr = append(yarr, yval)
			yval *= y
		}
	}

	mapped := map[int]bool{}
	for _, xval := range xarr {
		target := bound - xval
		for _, yval := range yarr {
			if yval <= target {
				mapped[xval+yval] = true
			}
		}
	}

	ans := []int{}
	for key := range mapped {
		ans = append(ans, key)
	}

	return ans
}
