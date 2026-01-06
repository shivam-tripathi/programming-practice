package main

import (
	"errors"
	"fmt"
)

type iShoe interface {
	setLogo(string)
	setSize(int)
	getLogo() string
	getSize() int
}

type shoe struct {
	logo string
	size int
}

func (s *shoe) setLogo(logo string) {
	s.logo = logo
}

func (s *shoe) setSize(size int) {
	s.size = size
}

func (s *shoe) getLogo() string {
	return s.logo
}

func (s *shoe) getSize() int {
	return s.size
}

type iShirt interface {
	setLogo(string)
	setSize(int)
	getLogo() string
	getSize() int
}

type shirt struct {
	logo string
	size int
}

func (s *shirt) setLogo(logo string) {
	s.logo = logo
}

func (s *shirt) setSize(size int) {
	s.size = size
}

func (s *shirt) getLogo() string {
	return s.logo
}

func (s *shirt) getSize() int {
	return s.size
}

type adidasShoe struct {
	shoe
}

type nikeShoe struct {
	shoe
}

type adidasShirt struct {
	shirt
}

type nikeShirt struct {
	shirt
}

type iSportsFactory interface {
	makeShoe() iShoe
	makeShirt() iShirt
}

type adidas struct{}

func (a *adidas) makeShoe() iShoe {
	shoe := &adidasShoe{}
	shoe.setLogo("adidas")
	shoe.setSize(12)
	return shoe
}

func (a *adidas) makeShirt() iShirt {
	shirt := &adidasShirt{}
	shirt.setLogo("adidas")
	shirt.setSize(12)
	return shirt
}

type nike struct{}

func (n *nike) makeShoe() iShoe {
	shoe := &nikeShoe{}
	shoe.setLogo("nike")
	shoe.setSize(14)
	return shoe
}

func (n *nike) makeShirt() iShirt {
	shirt := &nikeShirt{}
	shirt.setLogo("nike")
	shirt.setSize(14)
	return shirt
}

func runner(factory iSportsFactory) {
	fmt.Println(factory.makeShoe())
	fmt.Println(factory.makeShirt())
}

func getSportsFactoryBrand(brand string) (iSportsFactory, error) {
	switch brand {
	case "adidas":
		return &adidas{}, nil
	case "nike":
		return &nike{}, nil
	default:
		return nil, errors.New("Invalid brand")
	}
}

func main() {
	adidasFactory, _ := getSportsFactoryBrand("adidas")
	runner(adidasFactory)

	nikeFactory, _ := getSportsFactoryBrand("nike")
	runner(nikeFactory)
}
