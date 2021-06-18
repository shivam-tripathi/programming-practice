package main

func defangIPaddr(address string) string {
  builder := strings.Builder{}
  for _, ch := range address {
    if ch == '.' {
      builder.WriteString("[.]")
    } else {
      builder.WriteRune(ch)
    }
  }
  return builder.String()
}
