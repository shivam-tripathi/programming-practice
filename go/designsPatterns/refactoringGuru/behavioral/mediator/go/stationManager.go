package main

// Concrete implementation of Mediator

type StationManager struct {
	isPlatformFree bool
	trainQueue     []Train
}

func NewStationManager() *StationManager {
	return &StationManager{isPlatformFree: true}
}

func (manager *StationManager) canArrive(train Train) bool {
	if manager.isPlatformFree {
		manager.isPlatformFree = false
		return true
	}
	manager.trainQueue = append(manager.trainQueue, train)
	return false
}

func (manager *StationManager) notifyAboutDeparture() {
	if !manager.isPlatformFree {
		manager.isPlatformFree = true
	}
	if len(manager.trainQueue) > 0 {
		firstTrainInQueue := manager.trainQueue[0]
		manager.trainQueue = manager.trainQueue[1:]
		firstTrainInQueue.permitArrival()
	}
}
