package main

import "fmt"

// Base State (with all behaviours)
type State interface {
	addItem(int) error
	requestItem() error
	insertMoney(int) error
	dispenseItem() error
}

// Vending Machine (with all states)
type VendingMachine struct {
	hasItem       State
	itemRequested State
	hasMoney      State
	noItem        State

	currentState State

	itemCount int
	itemPrice int
}

func (machine *VendingMachine) incrementItemCount(count int) {
	machine.itemCount += count
}

func (machine *VendingMachine) setCurrentState(state State) {
	machine.currentState = state
}

/*******************************************************************************
NoItemState
********************************************************************************/
// implements State interface
type NoItemState struct {
	machine *VendingMachine
}

func (state *NoItemState) addItem(count int) error {
	state.machine.itemCount += count
	state.machine.setCurrentState(state.machine.hasItem)
	return nil
}

func (state *NoItemState) requestItem() error {
	return fmt.Errorf("No item available")
}

func (state *NoItemState) insertMoney(money int) error {
	return fmt.Errorf("No item available")
}

func (state *NoItemState) dispenseItem() error {
	return fmt.Errorf("No item available")
}

/*******************************************************************************
HasItemState
********************************************************************************/
// implements State interface
type HasItemState struct {
	machine *VendingMachine
}

func (state *HasItemState) addItem(count int) error {
	state.machine.incrementItemCount(count)
	return nil
}

func (state *HasItemState) requestItem() error {
	if state.machine.itemCount == 0 {
		state.machine.setCurrentState(state.machine.noItem)
		return state.machine.currentState.requestItem()
	}
	fmt.Printf("Item requested\n")
	state.machine.currentState = state.machine.itemRequested
	return nil
}

func (state *HasItemState) insertMoney(money int) error {
	return fmt.Errorf("Please select an item first")
}

func (state *HasItemState) dispenseItem() error {
	return fmt.Errorf("Please select an item first")
}

/*******************************************************************************
RequestItemState
********************************************************************************/

type RequestItemState struct {
	machine *VendingMachine
}

func (state *RequestItemState) addItem(count int) error {
	state.machine.incrementItemCount(count)
	return nil
}

func (state *RequestItemState) requestItem() error {
	return fmt.Errorf("Item already requested")
}

func (state *RequestItemState) insertMoney(money int) error {
	if money < state.machine.itemPrice {
		return fmt.Errorf("Inserted money is less. Please insert: %d", state.machine.itemPrice)
	}
	state.machine.setCurrentState(state.machine.hasMoney)
	return nil
}

func (state *RequestItemState) dispenseItem() error {
	return fmt.Errorf("Please insert money first")
}

/*******************************************************************************
HasMoneyState
********************************************************************************/
type HasMoneyState struct {
	machine *VendingMachine
}

func (state *HasMoneyState) addItem(count int) error {
	state.machine.incrementItemCount(count)
	return nil
}

func (state *HasMoneyState) requestItem() error {
	return fmt.Errorf("Item already requested")
}

func (state *HasMoneyState) insertMoney(money int) error {
	return fmt.Errorf("Money already inserted")
}

func (state *HasMoneyState) dispenseItem() error {
	fmt.Printf("Item dispensed\n")
	state.machine.itemCount--
	if state.machine.itemCount == 0 {
		state.machine.setCurrentState(state.machine.noItem)
	} else {
		state.machine.setCurrentState(state.machine.hasItem)
	}
	return nil
}

/*******************************************************************************
NewVendingMachine constructor
********************************************************************************/

func NewVendingMachine(itemCount, itemPrice int) *VendingMachine {
	machine := &VendingMachine{
		itemCount: itemCount,
		itemPrice: itemPrice,
	}
	machine.noItem = &NoItemState{machine: machine}
	machine.hasItem = &HasItemState{machine: machine}
	machine.itemRequested = &RequestItemState{machine: machine}
	machine.hasMoney = &HasMoneyState{machine: machine}

	if machine.itemCount == 0 {
		machine.setCurrentState(machine.noItem)
	} else {
		machine.setCurrentState(machine.hasItem)
	}

	return machine
}
