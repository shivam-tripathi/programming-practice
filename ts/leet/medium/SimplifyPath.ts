export function simplifyPath(path: string): string {
  const canon = path.split("/");
  const stack = [];
  for (const p of canon) {
    if (p === "" || p === ".") {
      continue;
    } else if (p !== "..") {
      stack.push(p);
    } else if (stack.length > 0) {
      stack.pop();
    }
  }
  return `/${stack.join("/")}`;
}
