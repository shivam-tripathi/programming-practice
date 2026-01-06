package main

import "fmt"

/*
It’s impossible to implement the classic Factory Method pattern in Go due to lack of OOP
features such as classes and inheritance. However, we can still implement the basic version
of the pattern, the Simple Factory.
*/

// Concrete product for iGun

// Interface
type IGun interface {
	setName(name string)
	setPower(power int)
	getName() string
	getPower() int
}

type Gun struct {
	name  string
	power int
}

func (gun *Gun) setName(name string) {
	gun.name = name
}

func (gun *Gun) setPower(power int) {
	gun.power = power
}

func (gun *Gun) getName() string {
	return gun.name
}

func (gun *Gun) getPower() int {
	return gun.power
}

type AK47 struct {
	Gun
}

func NewAK47() IGun {
	return &Gun{
		name:  "ak47",
		power: 88,
	}
}

type Musket struct {
	Gun
}

func NewMusket() IGun {
	return &Gun{
		name:  "musket",
		power: 22,
	}
}

func GunFactory(gunType string) (IGun, error) {
	switch gunType {
	case "ak47":
		return NewAK47(), nil
	case "musket":
		return NewMusket(), nil
	default:
		return nil, fmt.Errorf("Invalid Gun Type")
	}
}

func printDetails(gun IGun) {
	fmt.Printf("Name:%s\n", gun.getName())
	fmt.Printf("Power:%d\n", gun.getPower())
}

func main() {
	ak47, _ := GunFactory("ak47")
	musket, _ := GunFactory("musket")
	printDetails(ak47)
	printDetails(musket)
}
