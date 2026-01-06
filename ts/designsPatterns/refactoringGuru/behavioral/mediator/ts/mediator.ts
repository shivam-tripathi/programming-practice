import { BaseComponent, ComponentOne, ComponentTwo } from "./components.js";

export interface Mediator {
  notify(sender: BaseComponent, event: string): void;
}

export class ConcreteMediator implements Mediator {
  private componentOne: BaseComponent;
  private componentTwo: BaseComponent;
  constructor(componentOne: ComponentOne, componentTwo: ComponentTwo) {
    this.componentOne = componentOne;
    this.componentTwo = componentTwo;
		this.componentOne.setMediator(this);
		this.componentTwo.setMediator(this);
  }

  public notify(sender: BaseComponent, event: string) {
    switch (event) {
      case "TaskA":
        console.log(
          "Mediator reacts on One:TaskA and triggers following operations"
        );
        (sender == this.componentOne
          ? this.componentTwo
          : this.componentOne
        ).taskB();
      case "TaskB":
        console.log(
          "Mediator reacts on Two:TaskB and triggers following operations"
        );
    }
  }
}
