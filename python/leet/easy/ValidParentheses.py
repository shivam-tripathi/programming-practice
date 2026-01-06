class Solution:
    def isValid(self, s: str) -> bool:
        stack = []
        for e in s:
            if e in ['(', '{', '[']:
                stack.append(e)
            else:
                if len(stack) == 0:
                    return False
                elif e == ']' and stack[-1] != '[':
                    return False
                elif e == '}' and stack[-1] != '{':
                    return False
                elif e == ')' and stack[-1] != '(':
                    return False
                stack.pop()
        return len(stack) == 0
