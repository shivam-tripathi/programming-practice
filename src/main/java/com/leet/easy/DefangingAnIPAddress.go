package main

import "strings"

func defangIPaddr(address string) string {
	strings.ReplaceAll(address, ".", "[.]")
}
