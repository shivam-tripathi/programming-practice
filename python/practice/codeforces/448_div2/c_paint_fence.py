def solve(left, right, fence, base):
    print(fence[left:right])
    if left > right:
        return 0

    if left == right:
        return 1

    planks = right - left + 1

    min_val = min(fence[left : right + 1])

    vertical_strokes = planks

    horizontal_strokes = min_val - base
    remaining_strokes = 0

    i = left
    while i <= right:
        while i <= right and fence[i] <= min_val:
            i += 1
        a = i
        while i <= right and fence[i] > min_val:
            i += 1
        b = i - 1
        remaining_strokes += solve(a, b, fence, min_val)

    ans = min(vertical_strokes, horizontal_strokes + remaining_strokes)

    print(
        ans,
        fence[left : right + 1],
        {
            "left": left,
            "right": right,
            "min_val": min_val,
            "base": base,
            "vertical_strokes": vertical_strokes,
            "horizontal_strokes": horizontal_strokes,
            "remaining_strokes": remaining_strokes,
        },
    )

    return ans


def main():
    fence = [i for i in range(5000)]
    print(solve(0, len(fence) - 1, fence, 0))


if __name__ == "__main__":
    main()
