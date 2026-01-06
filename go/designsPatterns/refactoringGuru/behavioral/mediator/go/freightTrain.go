package main

import "fmt"

// Concrete implementation of Train

type FreightTrain struct {
	mediator Mediator
}

func (pt *FreightTrain) arrive() {
	if !pt.mediator.canArrive(pt) {
		fmt.Println("FreightTrain: Arrival blocked, waiting")
		return
	}
	fmt.Println("FreightTrain: Arrived")
}

func (pt *FreightTrain) depart() {
	fmt.Println("FreightTrain: Departing")
	pt.mediator.notifyAboutDeparture()
}

func (pt *FreightTrain) permitArrival() {
	fmt.Println("FreightTrain: Arrival Permitted")
	pt.arrive()
}
