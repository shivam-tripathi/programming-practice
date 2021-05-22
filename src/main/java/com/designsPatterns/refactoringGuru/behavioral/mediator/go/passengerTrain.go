package main

import "fmt"

// Concrete implementation of Train

type PassengerTrain struct {
	mediator Mediator
}

func (pt *PassengerTrain) arrive() {
	if !pt.mediator.canArrive(pt) {
		fmt.Println("PassengerTrain: Arrival blocked, waiting")
		return
	}
	fmt.Println("PassengerTrain: Arrived")
}

func (pt *PassengerTrain) depart() {
	fmt.Println("PassengerTrain: Departing")
	pt.mediator.notifyAboutDeparture()
}

func (pt *PassengerTrain) permitArrival() {
	fmt.Println("PassengerTrain: Arrival Permitted")
	pt.arrive()
}
