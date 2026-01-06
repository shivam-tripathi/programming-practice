package com.lint.oops;

import java.util.*;

enum TransactionType {
  DEPOSIT,
  WITHDRAW;
}

class Transation {
  TransactionType type;
  int amount;
  long timestamp;
  int balance;
  public Transation(TransactionType type, int amount, long timestamp, int balance) {
    this.type = type;
    this.amount = amount;
    this.timestamp = timestamp;
    this.balance = balance;
  }
}

class RegularAccount implements Account {
  List<Transation> transations = new ArrayList<>();
  volatile int balance;
  long lastUpdated;

  public void handleTransaction(TransactionType transactionType, int amount, long timestamp) {
    int balanceAfter = balance;
    synchronized (this) {
      if (transactionType == TransactionType.DEPOSIT) {
        balance += amount;
        lastUpdated = timestamp;
      } else if (transactionType == TransactionType.WITHDRAW) {
        if (amount > this.balance) {
          throw new RuntimeException("Cannot withdraw more than balance. Retry.");
        }
        balance -= amount;
        lastUpdated = timestamp;
      }

      balanceAfter = balance;
    }
    transations.add(new Transation(transactionType, amount, timestamp, balanceAfter));
  }

  public int getBalance() {
    return this.balance;
  }

  public int getBalance(long timestamp) {
    int prev = 0;
    for (int i = 0; i < transations.size(); i++) {
      if (transations.get(i).timestamp > timestamp) {
        break;
      }
      prev = transations.get(i).balance;
    }
    return prev;
  }
}

interface Account {
  void handleTransaction(TransactionType transactionType, int amount, long timestamp);
  int getBalance();
  int getBalance(long timestamp);
}

public class BankSystem {
  Map<Integer, Account> accounts = new HashMap<>();

  public BankSystem() {
  }

  /**
   * @param id:        user account id
   * @param amount:    the number of bank deposits
   * @param timestamp: the data of bank transaction
   * @return: nothing
   */
  public void deposit(int id, int amount, long timestamp) {
    if (!accounts.containsKey(id)) {
      accounts.put(id, new RegularAccount());
    }
    accounts.get(id).handleTransaction(TransactionType.DEPOSIT, amount, timestamp);
  }

  /**
   * @param id:        user account id
   * @param amount     : the number of bank withdraw
   * @param timestamp: the data of bank transaction
   * @return: if user account can not withdraw the number of amount,return false. else return true
   */
  public boolean withdraw(int id, int amount, long timestamp) {
    if (accounts.containsKey(id)) {
      Account account = accounts.get(id);
      try {
        account.handleTransaction(TransactionType.WITHDRAW, amount, timestamp);
        return true;
      } catch (Exception e) {
        return false;
      }
    }
    return true;
  }

  /**
   * @param id:        user account id
   * @param startTime: start time
   * @param endTime:   end time
   * @return: need return two numbers,the first one is start time account balance,the second is end time account balance
   */
  public int[] check(int id, long e, long endTime) {
//    return new int[]{accounts.get(id).getBalance(startTime), accounts.get(id).getBalance(endTime)};
    return new int[]{};
  }
}
