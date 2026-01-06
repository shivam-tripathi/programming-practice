#include <bits/stdc++.h>
using namespace std;

int main() {
	int t;
	cin >> t;
	while (t--) {
		int n;
		cin >> n;
		n = n*2;
		int odd = 0, even = 0;
		int num;
		while (n--) {
			cin >> num;
			if (num & 1) {
				odd++;
			} else {
				even++;
			}
		}
		cout << (odd == even ? "YES" : "NO") << endl;
	}
}
