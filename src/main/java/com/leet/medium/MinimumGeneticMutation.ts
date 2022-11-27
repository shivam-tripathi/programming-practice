/**
433. Minimum Genetic Mutation
https://leetcode.com/problems/minimum-genetic-mutation
Medium

A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and
'T'.

Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene
where one mutation is defined as one single character changed in the gene string.

    For example, "AACCGGTT" --> "AACCGGTA" is one mutation.

There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to
make it a valid gene string.

Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number
of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.

Note that the starting point is assumed to be valid, so it might not be included in the bank.


Example 1:
Input: startGene = "AACCGGTT", endGene = "AACCGGTA", bank = ["AACCGGTA"]
Output: 1

Example 2:
Input: startGene = "AACCGGTT", endGene = "AAACGGTA", bank = ["AACCGGTA","AACCGCTA","AAACGGTA"]
Output: 2

Constraints:
  0 <= bank.length <= 10
  startGene.length == endGene.length == bank[i].length == 8
  startGene, endGene, and bank[i] consist of only the characters ['A', 'C', 'G', 'T'].
 */

function canMut(a: string, b: string): boolean {
  let diff = 0;
  for (let i = 0; i < a.length; i++) {
    if (a[i] !== b[i]) diff++;
  }
  return diff === 1;
}

export function minMutation(
  startGene: string,
  endGene: string,
  bank: string[],
): number {
  let queue: string[] = [startGene];
  const seen: boolean[] = new Array(bank.length).fill(false);
  let steps = 0;
  while (queue.length) {
    const next: string[] = [];
    for (const cur of queue) {
      if (cur === endGene) {
        return steps;
      }
      for (let i = 0; i < bank.length; i++) {
        if (!seen[i] && canMut(cur, bank[i])) {
          next.push(bank[i]);
          seen[i] = true;
        }
      }
    }
    steps++;
    queue = next;
  }
  return -1;
}
