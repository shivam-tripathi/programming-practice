#include <bits/stdc++.h>
using namespace std;

// We are trying to find all factors for the number here
// We already know that 1 and the number itself are a factor
// We have to iterate over 2 to sqrt(num) - we know every num%mod = 0 gives two factors
// But looking deeply - we know prime number have 2 distinct factors: 1 and they themselves
// Adding a new factor will alway have it's comtemporary take it's place as well (i*j = num)
// So now after 1 and the number it self, we can only have sqrt of the number as other factor.
// This other factor should not have factors other than 1 and itself - which is basically a prime.

// Which numbers can have only 3 factors?
// Squares of prime numbers


int main()
{
	long long int n;
	cin >> n;

	vector<bool> primes(1e6, true);
	set<long long int> ans;
	for (long long int i = 2; i * i < primes.size(); i++)
	{
		if (primes[i])
		{
			for (int j = i * i; j < primes.size(); j += i)
			{
				primes[j] = false;
			}
		}
	}

	for (long long int i = 2; i < primes.size(); i++)
	{
		if (primes[i])
		{
			ans.insert(i * i);
		}
	}

	long long int num;

	for (int i = 0; i < n; i++)
	{
		cin >> num;
		if (ans.find(num) != ans.end())
		{
			cout << "YES" << endl;
		}
		else
		{
			cout << "NO" << endl;
		}
	}
}
