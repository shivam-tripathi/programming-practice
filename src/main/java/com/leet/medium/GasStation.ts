function canCompleteCircuit(gas: number[], cost: number[]): number {
  let start = 0, end = 0, running = 0, covered = 0, cities = gas.length;
  while (start < cities && covered < cities) {
    const cur = gas[end] - cost[end];
    if (start == end) {
      if (cur < 0) {
        running = 0;
        start += 1;
        end = (end + 1) % cities;
      } else {
        running += cur;
        end = (end + 1) % cities;
        covered += 1;
      }
    } else {
      if ((running + cur) < 0) {
        running -= gas[start] - cost[start];
        start += 1;
        covered -= 1;
      } else {
        running += cur;
        end = (end + 1) % cities;
        covered += 1;
      }
    }
  }

  if (covered == cities) {
    return start;
  }

  return -1;
}

Deno.test({
  name: "canCompleteCircuit",
  fn: () => {
    const gas = [
      900,
      418,
      641,
      66,
      443,
      513,
      536,
      660,
      878,
      683,
      34,
      576,
      66,
      913,
      128,
      179,
      977,
      639,
      383,
      896,
      66,
      324,
      901,
      884,
      599,
      517,
      491,
      915,
      336,
      602,
      311,
      192,
      306,
      827,
      12,
      456,
      473,
      940,
      275,
      965,
      694,
      256,
      227,
      156,
      168,
      780,
      701,
      555,
      50,
      798,
      230,
      528,
      710,
      544,
      852,
      22,
      460,
      388,
      797,
      638,
      753,
      690,
      446,
      419,
      340,
      716,
      842,
      404,
      389,
      672,
      642,
      17,
      421,
      723,
      534,
      577,
      237,
      144,
      756,
      190,
      615,
      861,
      709,
      207,
      301,
      467,
      245,
      1000,
      840,
      675,
      337,
    ];
    const cost = [
      779,
      132,
      915,
      895,
      171,
      784,
      949,
      524,
      96,
      584,
      99,
      414,
      753,
      272,
      856,
      891,
      374,
      369,
      585,
      630,
      254,
      753,
      93,
      786,
      612,
      329,
      467,
      820,
      620,
      487,
      124,
      493,
      247,
      537,
      618,
      833,
      452,
      565,
      350,
      374,
      118,
      647,
      110,
      251,
      764,
      605,
      473,
      884,
      522,
      444,
      952,
      897,
      238,
      181,
      141,
      59,
      836,
      31,
      178,
      131,
      378,
      835,
      483,
      642,
      305,
      799,
      195,
      973,
      44,
      763,
      674,
      57,
      125,
      277,
      440,
      128,
      562,
      87,
      260,
      264,
      420,
      52,
      549,
      845,
      979,
      570,
      569,
      856,
      523,
      158,
      890,
    ];
    const ans = canCompleteCircuit(gas, cost);
    console.log({ ans });
  },
});