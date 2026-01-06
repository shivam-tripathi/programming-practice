package main

func peakIndexInMountainArray(arr []int) int {
	for i := 1; i < len(arr)-1; i++ {
		if arr[i] > arr[i-1] && arr[i] > arr[i+1] {
			return i
		}
	}
	return -1
}

func peakIndexInMountainArray2(arr []int) int {
	low, high := 0, len(arr)-1
	for low < high {
		mid := low + (high-low)/2
		if arr[mid-1] < arr[mid] && arr[mid] > arr[mid+1] {
			return mid
		}
		if arr[mid] > arr[mid-1] {
			low = mid + 1
		} else {
			high = mid // don't make it mid - 1 else you may hit mid = 0
		}
	}
	return low
}
