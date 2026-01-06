import {Mediator} from './mediator';

export abstract class BaseComponent {
  constructor(protected mediator: Mediator = null) {}
  public setMediator(mediator: Mediator): void {
    this.mediator = mediator;
  }
  abstract taskA(): void;
  abstract taskB(): void;
}

export class ComponentOne extends BaseComponent {
  public taskA(): void {
    console.log("ComponentOne:TaskA");
    this.mediator.notify(this, "TaskA");
  }
  public taskB(): void {
    console.log("ComponentOne:TaskB");
    this.mediator.notify(this, "TaskB");
  }
}

export class ComponentTwo extends BaseComponent {
  public taskA(): void {
    console.log("ComponentTwo:TaskA");
    this.mediator.notify(this, "TaskA");
  }
  public taskB(): void {
    console.log("ComponentTwo:TaskD");
    this.mediator.notify(this, "TaskD");
  }
}
