package main

import (
	"fmt"
)

type inode interface {
	print(string)
	clone() inode
}

type file struct {
	name string
}

func (f *file) print(indentation string) {
	fmt.Println(indentation + f.name)
}

func (f *file) clone() inode {
	return &file{name: fmt.Sprintf("%s_clone", f.name)}
}

type folder struct {
	files []inode
	name  string
}

func (f *folder) print(indentation string) {
	fmt.Println(indentation + f.name)
	for _, file := range f.files {
		file.print(indentation + indentation)
	}
}

func (f *folder) clone() inode {
	files := []inode{}
	for _, file := range f.files {
		files = append(files, file.clone())
	}
	name := fmt.Sprintf("%s_clone", f.name)
	return &folder{files, name}
}

func main() {
	file1 := &file{"file1"}
	file2 := &file{"file2"}
	file3 := &file{"file3"}

	folder1 := &folder{
		[]inode{file1, file2},
		"folder1",
	}

	folder2 := &folder{
		[]inode{folder1, file3},
		"folder2",
	}

	fmt.Println("Printing folder2 structure")
	folder2.print(" ")

	clone := folder2.clone()
	fmt.Println("Printing folder2 clone structure")
	clone.print(" ")
}
