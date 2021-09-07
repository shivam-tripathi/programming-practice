package main

import "fmt"

// Component interface
type Pizza interface {
	getPrice() int
}

// ----------------------------------------------------------------

// Concrete Pizza
type VeggieMania struct {
}

func (p *VeggieMania) getPrice() int {
	return 15
}

// ----------------------------------------------------------------

// Concrete Pizza
type PeppyPaneer struct {
}

func (p *PeppyPaneer) getPrice() int {
	return 20
}

// ----------------------------------------------------------------

type TomatoTopping struct {
	pizza Pizza
}

func (p *TomatoTopping) getPrice() int {
	price := p.pizza.getPrice() + 5
	return price
}

// ----------------------------------------------------------------

// Another decorator
type CheeseTopping struct {
	pizza Pizza
}

func (p *CheeseTopping) getPrice() int {
	return p.pizza.getPrice() + 10
}

// ----------------------------------------------------------------

func main() {
	veggieMania := &VeggieMania{}
	veggieManiaWithCheese := &CheeseTopping{veggieMania}
	veggieManiaWithCheeseAndTomato := &TomatoTopping{veggieManiaWithCheese}
	fmt.Println(
		"Price for veggie mania with cheese and tomato is:", veggieManiaWithCheeseAndTomato.getPrice(),
	)

	peppyPaneer := &PeppyPaneer{}
	peppyPaneerWithCheese := &CheeseTopping{peppyPaneer}
	peppyPaneerWithCheeseAndTomato := &TomatoTopping{peppyPaneerWithCheese}
	fmt.Println(
		"Price for peppy paneer with cheese and tomato is:", peppyPaneerWithCheeseAndTomato.getPrice(),
	)
}
