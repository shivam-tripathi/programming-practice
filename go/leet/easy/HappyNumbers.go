package main

/*
Time complexity : O(243*3 + logn + loglog n + logloglog n) = O(log⁡n)

Finding the next value for a given number has a cost of O(log⁡n) because we are processing each digit
in the number, and the number of digits in a number is given by log⁡n\log nlogn.

To work out the total time complexity, we'll need to think carefully about how many numbers are in
the chain, and how big they are.

We determined above that once a number is below 243243243, it is impossible for it to go back up
above 243243243. Therefore, based on our very shallow analysis we know for sure that once a number
is below 243243243, it is impossible for it to take more than another 243243243 steps to terminate.
Each of these numbers has at most 3 digits. With a little more analysis, we could replace the
243243243 with the length of the longest number chain below 243243243, however because the constant
doesn't matter anyway, we won't worry about it.

For an nnn above 243243243, we need to consider the cost of each number in the chain that is above
243243243. With a little math, we can show that in the worst case, these costs will be
O(log⁡n)+O(log⁡log⁡n)+O(log⁡log⁡log⁡n)....

Luckily for us, the O(log⁡n) is the dominating part, and the others are all tiny in comparison
(collectively, they add up to less than log⁡n), so we can ignore them.

Floyd's cycle detecting algorithm
Like above, we're treating the length of the chain to the cycle as insignificant compared to the
cost of calculating the next value for the first n. Therefore, the only thing we need to do is show
that the number of times the runners go back over previously seen numbers in the chain is constant.

Once both pointers are in the cycle (which will take constant time to happen) the fast runner will
get one step closer to the slow runner at each cycle. Once the fast runner is one step behind the
slow runner, they'll meet on the next step. Imagine there are k numbers in the cycle. If they
started at k−1 places apart (which is the furthest apart they can start), then it will take
k−1 steps for the fast runner to reach the slow runner, which again is constant for our purposes.
Therefore, the dominating operation is still calculating the next value for the starting n,
which is O(log⁡n).

There's only one cycle: 4→16→37→58→89→145→42→20→44. All other numbers are on chains that lead into
this cycle, or on chains that lead into 111.

*/

func getNext(cur int) int {
	next := 0
	for cur != 0 {
		digit := cur % 10
		next = next + digit*digit
		cur /= 10
	}
	return next
}

func isHappy(n int) bool {
	slow, fast := n, n
	for fast != 1 {
		slow = getNext(slow)
		fast = getNext(getNext(fast))
		if slow == fast {
			break
		}
	}

	return fast == 1
}
