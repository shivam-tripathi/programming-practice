function printVertically(s: string): string[] {
  const words = s.split(" ");
  const ans: string[] = [];
  for (let i = 0; i < words.length; i++) {
    const word = words[i];
    for (let j = 0; j < word.length; j++) {
      // every level must have atleast i character before we append the current character
      const cur = ans[j] ?? "";
      ans[j] = cur + " ".repeat(i - cur.length) + word[j];
    }
  }
  return ans;
}
