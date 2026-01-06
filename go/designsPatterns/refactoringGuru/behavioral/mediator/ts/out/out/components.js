export class BaseComponent {
    constructor(mediator = null) {
        this.mediator = mediator;
    }
    setMediator(mediator) {
        this.mediator = mediator;
    }
}
export class ComponentOne extends BaseComponent {
    taskA() {
        console.log("ComponentOne:TaskA");
        this.mediator.notify(this, "TaskA");
    }
    taskB() {
        console.log("ComponentOne:TaskB");
        this.mediator.notify(this, "TaskB");
    }
}
export class ComponentTwo extends BaseComponent {
    taskA() {
        console.log("ComponentTwo:TaskA");
        this.mediator.notify(this, "TaskA");
    }
    taskB() {
        console.log("ComponentTwo:TaskD");
        this.mediator.notify(this, "TaskD");
    }
}
