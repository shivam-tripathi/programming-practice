/**
 * The memento's principle can be achieved using the serialization, which is quite common in TS.
 * While it is not the most efficient way to make snapshots of object's state, it allows storage
 * of states while protecting originator's structure from other objects.
 *
 * Memento:
 *  - What classes does it consist of?
 *  - What roles do these classes play?
 *  - In what way the elements of the pattern are related?
 */

/**
 * The Originator holds some important state which may change over time. It also defines a mehthod for
 * saving state inside a memento and another method for retrieving it.
 */
class Originator {
    private state: {
        name: string;
        age: number;
    };
    constructor(state: { name: string; age: number }) {
        this.state = state;
        console.log(`Originator: Initial state is ${this.state}`)
    }
}