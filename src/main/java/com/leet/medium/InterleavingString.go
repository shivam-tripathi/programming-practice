package main

var s1 string
var s2 string
var s3 string
var dp map[int]map[int]int

func solve(pos int, idx1 int, idx2 int) bool {
	if pos == len(s3) {
		return idx1 == len(s1) && idx2 == len(s2)
	}

	if _, ok := dp[idx1]; !ok {
		dp[idx1] = map[int]int{}
	}

	if dp[idx1][idx2] != 0 {
		return dp[pos][idx1][idx2] == 1
	}

	if idx1 < len(s1) && s3[pos] == s1[idx1] && decodeStringSolve(pos+1, idx1+1, idx2) {
		dp[idx1][idx2] = 1
		return true
	}

	if idx2 < len(s2) && s3[pos] == s2[idx2] && decodeStringSolve(pos+1, idx1, idx2+1) {
		dp[idx1][idx2] = 1
		return true
	}

	dp[idx1][idx2] = -1
	return false
}

func isInterleave(ss1 string, ss2 string, ss3 string) bool {
	s1, s2, s3 = ss1, ss2, ss3
	dp = map[int]map[int]int{}
	return decodeStringSolve(0, 0, 0)
}
