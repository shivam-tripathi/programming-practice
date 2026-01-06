package main

import (
	"fmt"
	"os"
	"strings"
)

const (
	ChunkSize  = 1024 * 1024 // 1 MB
	NumWorkers = 4
)

type CopyJob struct {
	offset uint64
	size   uint64
}

func main() {
	if len(os.Args) < 3 {
		fmt.Println("Usage: go run main.go <source_file> <destination_file>")
		os.Exit(1)
	}

	srcPath := os.Args[1]
	dstPath := os.Args[2]

	fmt.Println("Copying %s -> %s", srcPath, dstPath)
	fmt.Println("Workers: %d, ChunkSize: %d bytes", NumWorkers, ChunkSize)
	fmt.Println(strings.Repeat("=", 10))
}
