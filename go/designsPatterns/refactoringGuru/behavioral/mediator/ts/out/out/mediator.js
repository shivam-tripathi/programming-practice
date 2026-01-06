export class ConcreteMediator {
    constructor(componentOne, componentTwo) {
        this.componentOne = componentOne;
        this.componentTwo = componentTwo;
        this.componentOne.setMediator(this);
        this.componentTwo.setMediator(this);
    }
    notify(sender, event) {
        switch (event) {
            case "TaskA":
                console.log("Mediator reacts on One:TaskA and triggers following operations");
                (sender == this.componentOne
                    ? this.componentTwo
                    : this.componentOne).taskB();
            case "TaskB":
                console.log("Mediator reacts on Two:TaskB and triggers following operations");
        }
    }
}
