#include <vector>
#include <unordered_map>
#include <string>
using namespace std;

class UndergroundSystem
{
	unordered_map<int, pair<string, int>> runningTrips;
	unordered_map<string, unordered_map<string, pair<int, int>>> agg;

public:
	UndergroundSystem()
	{
	}

	void checkIn(int id, string stationName, int t)
	{
		runningTrips[id] = {stationName, t};
	}

	void checkOut(int id, string stationName, int t)
	{
		auto curTrip = runningTrips[id];

		int timeTaken = t - curTrip.second;
		string startStation = curTrip.first, endStation = stationName;

		if (agg.find(curTrip.first) == agg.end())
		{
			agg[startStation] = unordered_map<string, pair<int, int>>();
		}

		if (agg[startStation].find(endStation) == agg[startStation].end())
		{
			agg[startStation][endStation] = {0, 0};
		}

		auto aggTrips = agg[startStation][endStation];
		int aggTime = aggTrips.first, aggCount = aggTrips.second;
		agg[startStation][endStation] = {aggTime + timeTaken, aggCount + 1};
	}

	double getAverageTime(string startStation, string endStation)
	{
		auto aggTrips = agg[startStation][endStation];
		int aggTime = aggTrips.first, aggCount = aggTrips.second;
		return ((double)aggTime) / aggCount;
	}
};

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem* obj = new UndergroundSystem();
 * obj->checkIn(id,stationName,t);
 * obj->checkOut(id,stationName,t);
 * double param_3 = obj->getAverageTime(startStation,endStation);
 */