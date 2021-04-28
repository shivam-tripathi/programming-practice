func moveZeroes(nums []int)  {
    slow, fast := 0, 0
    for fast < len(nums) {
        if nums[fast] != 0 {
            nums[slow] = nums[fast]
            slow++
        }
        fast++
    }
    for slow < len(nums) {
        nums[slow] = 0
        slow++
    }
}
