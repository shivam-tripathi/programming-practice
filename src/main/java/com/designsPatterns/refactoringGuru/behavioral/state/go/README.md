# State pattern in Go

Vending machine

### Assumptions
1. Vending machine has only one type of product
2. Vending machine can have only four states:
	- hasItem
	- noItem
	- itemsRequested
	- hasMoney
3. Vending machine actions:
	- Select an item
	- Add an item
	- Insert money
	- Dispense item


The State design pattern should be used when the object can be in many different states and depending upon incoming request the object needs to change its current state.

The code of the vending machine is not polluted with all business logic, all the state-dependent code lives in respective state implementations.

Vending machine can have one of the given states, and constantly switches between them. Depending on current state, the machine can behave differently to the same requests.
