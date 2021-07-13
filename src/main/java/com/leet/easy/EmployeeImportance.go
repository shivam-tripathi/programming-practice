package main

type Employee struct {
	Id           int
	Importance   int
	Subordinates []int
}

func solve(id int, emap map[int]*Employee) int {
	e := emap[id]
	ans := e.Importance
	for _, sub := range e.Subordinates {
		ans += solve(sub, emap)
	}
	return ans
}

func getImportance(employees []*Employee, id int) int {
	emap := map[int]*Employee{}
	for _, e := range employees {
		emap[e.Id] = e
	}

	return solve(id, emap)
}
