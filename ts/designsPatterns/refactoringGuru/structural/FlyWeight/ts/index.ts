class Flyweight<U, S> {
	constructor(private sharedState: S) { }

	public getState(uniqueState: U): any {
		console.log(`Flyweight: Displaying shared (${JSON.stringify(this.sharedState)}) and unique (${JSON.stringify(uniqueState)}) state.`);
		return Object.assign({}, uniqueState, this.sharedState);
	}
}

class FlyweightFactory<U, S> {
	private flyweights: { [key: string]: Flyweight<U, S> } = {};

	constructor(initialFlyweights: S[]) {
		for (const state of initialFlyweights) {
			this.flyweights[this.getKey(state)] = new Flyweight<U, S>(state);
		}
	}

	private getKey(state: S): string {
		return JSON.stringify(state);
	}


	public getFlyWeight(sharedState: S): Flyweight<U, S> {
		const key = this.getKey(sharedState);
		if (!this.flyweights[key]) {
			this.flyweights[key] = new Flyweight(sharedState);
		}
		return this.flyweights[key];
	}

	public listFlyWeights(): Flyweight<U, S>[] {
		return Object.values(this.flyweights);
	}
}

const factory = new FlyweightFactory([
	['Chevrolet', 'Camaro2018', 'pink'],
	['Mercedes Benz', 'C300', 'black'],
	['Mercedes Benz', 'C500', 'red'],
	['BMW', 'M5', 'red'],
	['BMW', 'X6', 'white'],
]);

console.log(factory.listFlyWeights());
