# GoF Definition
Define an interface for creating an object, but let the classes which implement the interface decide which class to instantiate. The Factory method lets a class defer instantiation to subclasses


# Factory Method versus Constructor

> Note: This conversation is more around static factory pattern and not the GoF factory pattern

**Bill Venners: When would you use a factory method versus a constructor?**

**Josh Bloch**: I like factory methods, as you probably know from the book. Factory methods give you a lot of flexibility in terms of what type you can return: you can return **any subclass of a declared type**. They give you the **ability not to create an object each time they are invoked**. For **immutable** types, they are just great, because they can save you from producing a whole bunch of functionally identical objects.

For example, the Boolean type, which is a boxed primitive boolean, simply should not have had public constructors. It is basically a type-safe enum, and it should have been one. There should only be two Boolean objects in a VM at any time, one true and one false. There's really no great advantage to allow multiple trues or multiple falses, and I've seen programs that produce millions of trues and millions of falses, creating needless work for the garbage collector.

So, in the case of immutables, I think factory methods are great. In the case of **classes that reasonably admit multiple implementations, for which you want the ability to change implementations over time, or change implementations at run time based on usage characteristics**, I think they are good.

I also think factory methods are useful if you find yourself with a bunch of constructors that have a bunch or arguments, and it's hard to keep them straight. When you read a constructor invocation and you see a bunch of different arguments, you don't know what they are there for. It's nice if you can replace the name, say new BigInteger() with BigInteger.probablePrime. Now you know what that invocation is doing--it's producing a probable prime.

In fact, if you look at BigInteger, you'll see that we did exactly that. We needed to change the semantics of the prime generation stuff a little bit. At the same time as we did that, we changed it from a constructor to a _static factory_. Once again, this represents an evolution in our thinking over the years.

The only time you really need accessible constructors is when you want to allow for subclassing, as subclass constructors must invoke superclass constructors. If you want to be subclassable, then you pretty much can't use static factories.

**Bill Venners: Those constructors can be protected and not public.**

**Josh Bloch**: They can. That's another approach, to have public static factories and protected constructors. And I must confess, I haven't actually written to that style much, I think because I tend not to write classes that encourage subclassing.

When you are writing a class, you can run down the my book's list of the advantages of static factories over public constructors. If you find that a significant number of those advantages actually apply in your case, then you should go with the static factories. Otherwise you should go with the constructors.

Some people were disappointed to find that advice in my book. They read it and said, "You've argued so strongly for public static factories that we should just use them by default." I think the only real disadvantage in doing so is that it's a bit disconcerting to people who are used to using constructors to create their objects. And I suppose it provides a little less of a visual cue in the program. (You don't see the new keyword.) Also it's a little more difficult to find static factories in the documentation, because Javadoc groups all the constructors together. But I would say that everyone should consider static factories all the time, and use them when they are appropriate.
