function maxArea(height: number[]): number {
  let low = 0, high = height.length - 1, ans = 0;
  while (low != high) {
    ans = Math.max(ans, (high - low) * Math.min(height[low], height[high]));
    if (height[low] < height[high]) low++;
    else high--;
  }
  return ans;
}
