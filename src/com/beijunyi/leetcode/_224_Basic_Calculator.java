package com.beijunyi.leetcode;

import java.util.Deque;
import java.util.LinkedList;

import com.beijunyi.leetcode.category.difficulty.Hard;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * The expression string may contain open "(" and closing parentheses ")", the plus "+" or minus sign "-", non-negative integers
 * and empty spaces " ".
 *
 * You may assume that the given expression is always valid.
 *
 * Some examples:
 *   "1 + 1" = 2
 *   " 2-1 + 2 " = 3
 *   "(1+(4+5+2)-3)+(6+8)" = 23
 */
public class _224_Basic_Calculator implements Hard {

  public interface Solution {
    int calculate(String s);
  }

  public static class Solution1 implements Solution {

    @Override
    public int calculate(String s) {
      char[] chars = removeWhitespaces(s).toCharArray();
      Token token;
      int offset = 0;
      Deque<Character> signs = new LinkedList<>();
      Deque<Integer> numbers = new LinkedList<>();
      while((token = Token.readNext(chars, offset)) != null) {
        switch(token.type) {
          case NUMBER:
            numbers.offer(readNumber(token, chars));
            computeLastSign(signs, numbers);
            break;
          case SIGN:
            char sign = readSign(token, chars);
            if(')' == sign) {
              char open = signs.pollLast();
              assert '(' == open;
              computeLastSign(signs, numbers);
            } else {
              signs.offer(sign);
            }
            break;
          default:
            throw new RuntimeException();
        }
        offset = token.end;
      }
      return numbers.pollLast();
    }

    // O(n)
    private static String removeWhitespaces(String s) {
      StringBuilder ret = new StringBuilder();
      for(char c : s.toCharArray()) {
        switch(c) {
          case ' ':
            continue;
          default:
            ret.append(c);
        }
      }
      return ret.toString();
    }

    private static int readNumber(Token token, char[] chars) {
      StringBuilder sb = new StringBuilder();
      for(int i = token.start; i < token.end; i++)
        sb.append(chars[i]);
      return Integer.parseInt(sb.toString());
    }

    private static char readSign(Token token, char[] chars) {
      return chars[token.start];
    }

    private static int computeSign(char sign, int a, int b) {
      switch(sign) {
        case '+':
          return a + b;
        case '-':
          return a - b;
        default:
          throw new RuntimeException();
      }
    }

    private static void computeLastSign(Deque<Character> signs, Deque<Integer> numbers) {
      if(signs.size() != 0 && ('+' == signs.peekLast() || '-' == signs.peekLast())) {
        char sign = signs.pollLast();
        int b = numbers.pollLast();
        int a = numbers.pollLast();
        numbers.offer(computeSign(sign, a, b));
      }
    }

    private enum TokenType {
      SIGN,
      NUMBER;

      public static TokenType of(char c) {
        switch(c) {
          case '(':
          case ')':
          case '+':
          case '-':
            return TokenType.SIGN;
          default:
            assert Character.isDigit(c);
            return TokenType.NUMBER;
        }
      }
    }

    private static class Token {
      public final TokenType type;
      public final int start;
      public final int end;

      public Token(TokenType type, int start, int end) {
        this.type = type;
        this.start = start;
        this.end = end;
      }

      public static Token readNext(char[] s, int start) {
        int end = start;
        TokenType type = null;
        while(end < s.length) {
          char c = s[end];
          if(type == null) type = TokenType.of(c);
          else if(type == TokenType.SIGN || TokenType.of(c) != type) break;
          end++;
        }
        if(type != null) return new Token(type, start, end);
        return null;
      }
    }

  }

  public static void main(String[] args) {
    String s = "(1+(4+5+2)-3)+(6+8)";
    System.out.println(new Solution1().calculate(s));
  }

}
