function maskPII(s: string): string {
	if (s.indexOf('@') !== -1) {
		const [name, domain] = s.split('@');
		return name[0].toLowerCase() + "*".repeat(5) + name[name.length - 1].toLowerCase() + "@" + domain.toLowerCase();
	} else {
		const num = s.replace(/[\+\-\(\)\s]/g, '');
		const last4 = num.slice(num.length - 4);
		const ccode = num.length - 10;
		return (ccode ? '+' + '*'.repeat(ccode) + '-' : '') + '***-'.repeat(2) + last4;
	}
};