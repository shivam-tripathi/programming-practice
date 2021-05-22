import {BaseComponent, ComponentOne, ComponentTwo} from './components.js';
import {Mediator, ConcreteMediator} from './mediator.js';

function main() {
    const c1: BaseComponent = new ComponentOne();
    const c2: BaseComponent = new ComponentTwo();
    const mediator: Mediator = new ConcreteMediator(c1, c2);

    console.log('Client triggers component one task A');
    c1.taskA();

    console.log('Client triggers component two task B');
    c2.taskB();
}

main()
