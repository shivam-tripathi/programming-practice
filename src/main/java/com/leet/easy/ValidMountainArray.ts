function validMountainArray(arr: number[]): boolean {
  if (arr.length <= 2) {
    return false;
  }

  let pos;

  // start uphill
  for (pos = 0; pos < arr.length - 1; pos++) {
    if (arr[pos] >= arr[pos + 1]) {
      break;
    }
  }

  // if the peak is the first or the last element, it's not a mountain
  if (pos == 0 || pos == arr.length - 1) {
    return false;
  }

  // cross the peak
  pos++;

  // start downhill
  for (; pos < arr.length; pos++) {
    if (arr[pos] >= arr[pos - 1]) {
      break;
    }
  }

  // all the elements must be covered
  return pos === arr.length;
}
