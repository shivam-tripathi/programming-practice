function findDuplicate(paths: string[]): string[][] {
  const record = paths.reduce((acc, path) => {
    const [addr, nameWithContent] = path.split(" ");
    const [name, content] = nameWithContent.split("(");
    const fullAddr = addr + "/" + name;
    if (!acc[content]) {
      acc[content] = [fullAddr];
    } else {
      acc[content].push(fullAddr);
    }
    return acc;
  }, {} as Record<string, string[]>);
  return Object.values(record).filter((dupl) => dupl.length > 1);
}
