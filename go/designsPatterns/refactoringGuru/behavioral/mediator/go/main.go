package main

func main() {
	stationManager := NewStationManager()
	passengerTrain := &PassengerTrain{mediator: stationManager}
	freightTrain := &FreightTrain{mediator: stationManager}

	passengerTrain.arrive()
	freightTrain.arrive()
	passengerTrain.depart()
	freightTrain.depart()
}
