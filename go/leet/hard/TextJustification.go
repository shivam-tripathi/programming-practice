package main

import (
	"fmt"
	"math"
	"strings"
)

/*
68. Text Justification
Hard
Topics
Companies

Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

	A word is defined as a character sequence consisting of non-space characters only.
	Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
	The input array words contains at least one word.

Example 1:
Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[

	"This    is    an",
	"example  of text",
	"justification.  "

]

Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[

	"What   must   be",
	"acknowledgment  ",
	"shall be        "

]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.

Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[

	"Science  is  what we",
	"understand      well",
	"enough to explain to",
	"a  computer.  Art is",
	"everything  else  we",
	"do                  "

]
*/

// Line stores words in a line and sum of characters of each word
type Line struct {
	words []string
	chars int
}

// Size returns the number of characters in the line if they were joined by a single space
func (line *Line) Size() int {
	return line.chars + len(line.words) - 1
}

// Add adds a word to the line
func (line *Line) Add(word string) {
	line.words = append(line.words, word)
	line.chars += len(word)
}

// Justify returns the line justified to the given width
func (line *Line) Justify(maxWidth int) string {
	numWords := len(line.words)
	width := maxWidth - line.chars
	if numWords <= 1 {
		return strings.Join(line.words, strings.Repeat(" ", int(width)))
	}
	spaces := int(math.Ceil(float64(width) / float64(numWords-1)))
	fmt.Println("Width:", width, "NumWords:", numWords, "Chars:", line.chars, "MaxWidth:", maxWidth)
	extra := width - (numWords-1)*spaces
	sb := strings.Builder{}
	for idx, word := range line.words {
		sb.WriteString(word)
		if idx != len(line.words)-1 {
			sb.WriteString(strings.Repeat(" ", spaces+extra-1))
			if extra > 0 {
				extra--
			}
		}
	}
	return sb.String()
}

// fullJustify justifies the given words to the given width
func fullJustify(words []string, maxWidth int) []string {
	lines := []string{}
	line := Line{}
	for _, word := range words {
		if line.Size()+1+len(word) <= maxWidth {
			line.Add(word)
		} else {
			lines = append(lines, line.Justify(maxWidth))
			line = Line{}
			line.Add(word)
		}
	}
	if line.Size() > 0 {
		lines = append(lines, line.Justify(maxWidth))
	}
	return lines
}

type InputTextJustification struct {
	Words    []string
	MaxWidth int
	Result   []string
}

var inputs []InputTextJustification = []InputTextJustification{
	{
		[]string{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"},
		20,
		[]string{

			"Science  is  what we",
			"understand      well",
			"enough to explain to",
			"a  computer.  Art is",
			"everything  else  we",
			"do                  ",
		},
	},
	{
		[]string{"This", "is", "an", "example", "of", "text", "justification."},
		16,
		[]string{
			"This    is    an",
			"example  of text",
			"justification.  ",
		},
	},
	{
		[]string{"What", "must", "be", "acknowledgment", "shall", "be"},
		16,
		[]string{

			"What   must   be",
			"acknowledgment  ",
			"shall be        ",
		},
	},
}

func main() {
	printResult := func(result []string) {
		for _, line := range result {
			fmt.Printf("[%+v]\n", line)
		}
	}
	for _, input := range inputs {
		result := fullJustify(input.Words, input.MaxWidth)
		if strings.Join(result, "\n") == strings.Join(input.Result, "\n") {
			fmt.Println(">>Test case passed!")
			printResult(result)
		} else {
			fmt.Println("\n>>Test case failed:")
			fmt.Println("Result:")
			printResult(result)
			fmt.Println("Expected:")
			printResult(input.Result)
		}
	}
}
