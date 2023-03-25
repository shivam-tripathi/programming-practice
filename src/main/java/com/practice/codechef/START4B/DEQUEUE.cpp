#include <bits/stdc++.h>
using namespace std;

void solve() {
    int n, m;
    cin >> n >> m;
    vector<bool> visited(n); // array to mark if number has been visited
    vector<int> arr(m); // array to store the elements
    vector<int> freq(n+1);

    int prev = -1;
    for (int i = 0; i < m; i++) {
        cin >> arr[i];
        freq[arr[i]]++;
    }

    if (m == n) {
        cout << n << endl;
    }

    for(size_t _i = 0; _i < arr.size(); _i++) { cout << arr[_i] << ' '; } cout << endl;
    for(size_t _i = 0; _i < freq.size(); _i++) { cout << freq[_i] << ' '; } cout << endl;

    int a = 0, b = 0,

    int nextL = 0, nextR = 0, count = 0;
    int l = 0, r = m-1;
    while (l <= r && count < n) {
        cout << "arr[" << l << "]=" << arr[l] << " arr[" << r << "]=" << arr[r] <<  " count=" << count << " m: " << m << " n: " << n << endl;
        if (!visited[arr[l]] && !visited[arr[r]]) {
            if (arr[l] != arr[r]) {
                l++; r--; count += 2; visited[arr[l]] = visited[arr[r]] = true;
            } else {
                l++; count += 1; visited[arr[l]] = true;
            }
        } else if (visited[arr[l]] && visited[arr[r]]) {
            if (freqL[l] > freqR[r]) {
                l = l + freqL[l];
            } else {
                r = r - freqR[r];
            }
        } else if (visited[arr[l]]) {
            r--; count += 1; visited[arr[r]] = true;
        } else {
            l++; count += 1; visited[arr[l]] = true;
        }
    }

    cout << "l: " << l << " r: " << r << " m: " << m << " n: " << n << endl;
    cout << l + m - r << endl;
}

int main() {
    int t;
    cin >> t;
    for (int i = 0; i < t; i++) {
        solve();
    }
    return 0;
}