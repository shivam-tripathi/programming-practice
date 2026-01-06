#include <bits/stdc++.h>
using namespace std;

struct Node {
    Node* nodes[26];
    bool holds;
};

Node *root = new Node();
map<string, Node*> word_end_pos;

void insert_word(string word, int char_index, Node* node) {
    if (char_index < word.size()) {
        int node_index = word[char_index] - 'a';
        if (node->nodes[node_index] == nullptr) {
            node->nodes[node_index] = new Node();
        }
        if (char_index == word.size() - 1) {
            node->nodes[node_index]->holds = true;
            word_end_pos[word] = node->nodes[node_index];
        } else {
            insert_word(word, char_index + 1, node->nodes[node_index]);
        }
    }
}

int lookup(string word, Node* node) {
    if (node == nullptr) {
        return 0;
    }

    int count = 0;
    for (int i = 0; i < 26; i++) {
        if (node->nodes[i] != nullptr) {
            string next_word = word + (char)(i + 'a');
            if (node->nodes[i]->holds) {
                count++;
                printf("%s\n", next_word.c_str());
            }
            count += lookup(next_word, node->nodes[i]);
        }
    }

    return count;
}


Node* find_position(string word, int char_index, Node* node) {
    if (node != nullptr && char_index < word.size()) {
        int node_index = word[char_index] - 'a';
        Node* cur_node = node->nodes[node_index];
        if (cur_node == nullptr) {
            return nullptr;
        }
        if (char_index == word.size() - 1) {
            return cur_node;
        }
        return find_position(word, ++char_index, node->nodes[node_index]);
    }
    return nullptr;
}

int main() {
    int number_of_words;
    string word;
    char *in = new char();
    scanf("%d", &number_of_words);
    int word_index = 0;
    for (int i = 0; i < number_of_words; i++) {
        scanf("%s", in);
        word = string(in);
        if (word_end_pos.find(word) == word_end_pos.end()) {
            insert_word(word, 0, root);
        }
    }

    int number_of_lookups;
    scanf("%d", &number_of_lookups);
    for (int i = 0; i < number_of_lookups; i++) {
        scanf("%s", in);
        printf("Case #%d:\n", i+1);
        word = string(in);
        Node* pos = NULL;
        map<string, Node*>::iterator iter = word_end_pos.find(word);
        int char_index = word.size() - 1;
        if (iter == word_end_pos.end()) {
            pos = find_position(word, 0, root);
        } else {
            pos = iter->second;
        }

        if (pos == NULL) {
            printf("No match.\n");
        } else {
            int count = lookup(word, pos);
            if (count == 0) {
                printf("No match.\n");
            }
        }
    }
    return 0;
}
