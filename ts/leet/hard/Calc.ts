function evaluate(exp: (string | number)[]): number {
	if (exp.length == 0) return 0;

	const stack: (number | string)[] = [];

	// First we remove all +/- signs (by default everything is +)
	const sanitized = exp.reduceRight((acc, e) => {
		if (e === '-') {
			acc[0] = (acc[0] as number) * -1;
		} else if (e !== '+') {
			acc = [e, ...acc];
		}
		return acc;
	}, [] as (number | string)[]);

	// Next we multiple or divide (L to R)
	for (let i = 0; i < sanitized.length; i++) {
		const token = sanitized[i];
		if (token === '*') {
			stack[stack.length - 1] = (stack[stack.length - 1] as number) * (sanitized[i + 1] as number);
			i++;
		} else if (token == '/') {
			stack[stack.length - 1] = (stack[stack.length - 1] as number) / (sanitized[i + 1] as number);
			i++;
		} else {
			stack.push(sanitized[i]);
		}
	}

	// Finally we sum all the results
	const ans = stack.reduce((acc: number, e: (string | number)) => acc + (e as number), 0);

	console.log(exp.join(''), '=', ans);
	return ans;
}

function solve(tokens: (string | number)[], begin: number): { end: number, ans: number } {
	let pos = begin, exp = [];
	if (pos >= tokens.length) return { end: pos, ans: 0 };
	while (pos < tokens.length) {
		const token = tokens[pos];
		// Stop the current execution (push it into the stack) and process the expression in brackets
		if (token === '(') {
			const { end, ans } = solve(tokens, pos + 1);
			pos = end;
			exp.push(ans);
		} else if (token === ')') {
			break;
		} else {
			exp.push(token);
			pos++;
		}
	}
	return { end: pos + 1, ans: evaluate(exp) };
}

function main(input: string) {
	const tokens = input
		.split(/([-\(\)\*\/\+])/g)
		.map(e => e.trim())
		.filter(e => e !== '')
		.map(e => /\d+/.test(e) ? parseInt(e, 10) : e);

	console.log(input);
	return solve(tokens, 0);
}

const inputs = ['2-3', '1 + 1', ' 6-4 / 2 ', '2*(5+5*2)/3+(6/2+8)', '(2+6* 3+5- (3*14/7+2)*5)+3'];

inputs.forEach(inp => console.log('ans = ', main(inp).ans, '\n---------\n'));