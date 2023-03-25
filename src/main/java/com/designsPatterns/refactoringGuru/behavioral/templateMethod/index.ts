abstract class AbstractClass {
	public templateMethod(): void {

	}
	protected baseOperation1(): void {
		console.log('AbstractClass: I am doing maximum work');
	}
	protected baseOperation2(): void {
		console.log('AbstractClass: I let some subclass to override');
	}
	protected baseOperation3(): void {
		console.log('AbstractClass: I am doing minimum work');
	}

	protected abstract requiredOperations1(): void;
	protected abstract requiredOperations2(): void;

	protected hook1(): void { }
	protected hook2(): void { }
}