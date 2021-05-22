import { ComponentOne, ComponentTwo } from './components.js';
import { ConcreteMediator } from './mediator.js';
function main() {
    const c1 = new ComponentOne();
    const c2 = new ComponentTwo();
    const mediator = new ConcreteMediator(c1, c2);
    console.log('Client triggers component one task A');
    c1.taskA();
    console.log('Client triggers component two task B');
    c2.taskB();
}
main();
