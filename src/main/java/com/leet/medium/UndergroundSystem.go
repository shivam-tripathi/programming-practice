package main

type Entry struct {
	Station string
	Time    int
}

type UndergroundSystem struct {
	Stats   map[[2]string][2]int // arrays can be keys
	Enroute map[int]Entry
}

func Constructor() UndergroundSystem {
	return UndergroundSystem{Stats: map[[2]string][2]int{}, Enroute: map[int]Entry{}}
}

func (us *UndergroundSystem) CheckIn(id int, stationName string, t int) {
	us.Enroute[id] = Entry{stationName, t}
}

func (us *UndergroundSystem) CheckOut(id int, stationName string, t int) {
	entry := us.Enroute[id]
	key := [2]string{entry.Station, stationName}
	stat := us.Stats[key]
	us.Stats[key] = [2]int{stat[0] + t - entry.Time, stat[1] + 1}
}

func (us *UndergroundSystem) GetAverageTime(startStation string, endStation string) float64 {
	key := [2]string{startStation, endStation}
	if stat := us.Stats[key]; stat[1] > 0 {
		return float64(stat[0]) / float64(stat[1])
	}
	return 0
}
