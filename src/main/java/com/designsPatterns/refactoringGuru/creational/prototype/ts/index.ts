/**
 * Prototype is a creational design pattern that allows cloning objects,
 * even complex ones, without coupling to their specific classes.
 *
 * All prototype classes should have a common interface that makes it possible
 * to copy objects even if their concrete classes are unknown. Prototype objects
 * can produce full copies since objects of the same class can access each other’s
 * private fields.
 *
 * What classes does it consist of?
 * What roles do these classes play?
 * In what way the elements of the pattern are related?
 *
 */

class ComponentWithBackReference {
  constructor(public prototype: Prototype) {}
}

class Prototype {
  constructor(
    public primitive: any,
    public component: object,
    public circularReference: ComponentWithBackReference | null
  ) {}

  public clone(): this {
    const clone = Object.create(this);
    clone.component = Object.create(this.component);
    clone.circularReference = {
      ...this.circularReference, // copy everything in circular reference
      prototype: { ...this }, // copy this separately, else it will be linked to original object
    };
    return clone;
  }
}

function main() {
  const p1 = new Prototype(245, new Date(), null);
  p1.circularReference = new ComponentWithBackReference(p1);

  const p2 = p1.clone();

  if (p1.primitive === p2.primitive) {
    console.log("✓ Primitive values have been cloned!");
  } else {
    console.log("✗ Primitive values have not been cloned!");
  }

  if (p1.component === p2.component) {
    console.log("✗ Simple component has not been cloned!");
  } else {
    console.log("✓ Simple components have been cloned!");
  }

  if (p1.circularReference === p2.circularReference) {
    console.log("✗ Component with back reference has not been cloned!");
  } else {
    console.log("✓ Component with back reference has been cloned!");
  }

  if (p1.circularReference && p2.circularReference && p1.circularReference.prototype === p2.circularReference.prototype) {
    console.log("✗ Component with back reference is linked to original object!");
  } else {
    console.log("✓ Component with back reference is linked to the clone!");
  }
}

main()
