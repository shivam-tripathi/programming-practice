class UndergroundSystem {
  constructor(
    private userStatus: {
      [userId: string]: { stationName: string; boardingTime: number };
    } = {},
    private stationStats: {
      [endStation: string]: {
        [startStation: string]: { count: number; time: number };
      };
    } = {}
  ) {}

  checkIn(id: number, startStation: string, t: number): void {
    this.userStatus[id] = { stationName: startStation, boardingTime: t };
  }

  checkOut(id: number, endStation: string, t: number): void {
    const { stationName: startStation, boardingTime } =
      this.userStatus[id] ?? {};

    const { count = 0, time = 0 } =
      this.stationStats[endStation]?.[startStation] ?? {};
    if (!this.stationStats[endStation]) this.stationStats[endStation] = {};
    this.stationStats[endStation][startStation] = {
      count: count + 1,
      time: time + t - boardingTime,
    };
  }

  getAverageTime(startStation: string, endStation: string): number {
    const { count = 0, time = 0 } =
      this.stationStats[endStation]?.[startStation] ?? {};
    return count == 0 ? -1 : time / count;
  }
}
