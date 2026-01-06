package main

import "fmt"

// FACADE - Wallet
// =========================================================================
type WalletFacade struct {
	account      *Account
	wallet       *Wallet
	securityCode *SecurityCode
	notif        *Notification
	ledger       *Ledger
}

func NewWalletFacade(accountId string, code int) *WalletFacade {
	return &WalletFacade{
		account:      NewAccount(accountId),
		wallet:       NewWallet(),
		securityCode: NewSecurityCode(code),
		notif:        &Notification{},
		ledger:       &Ledger{},
	}
}

func (walletFacade *WalletFacade) Verify(accountID string, code int) error {
	err := walletFacade.account.CheckAccount(accountID)
	if err != nil {
		return err
	}
	err = walletFacade.securityCode.CheckCode(code)
	if err != nil {
		return err
	}
	return nil
}

func (walletFacade *WalletFacade) AddMoney(accountID string, code int, amount int) error {
	err := walletFacade.Verify(accountID, code)
	if err != nil {
		return err
	}
	walletFacade.wallet.creditBalance(amount)
	walletFacade.ledger.makeEntry(accountID, "credit", amount)
	walletFacade.notif.sendCreditNotif(accountID, amount)
	return nil
}

func (walletFacade *WalletFacade) DeductMoney(accountID string, code int, amount int) error {
	err := walletFacade.Verify(accountID, code)
	if err != nil {
		return err
	}
	err = walletFacade.wallet.debitBalance(amount)
	if err != nil {
		return err
	}
	walletFacade.ledger.makeEntry(accountID, "debit", amount)
	walletFacade.notif.sendDebitNotif(accountID, amount)
	return nil
}

func (walletFacade *WalletFacade) GetBalance() int {
	return walletFacade.wallet.balance
}

// COMPLEX SUBSYSTEMS
// =========================================================================

// Account
// -------------------------------------------------------------------------
type Account struct {
	Name string
}

func NewAccount(accountName string) *Account {
	return &Account{accountName}
}

func (a *Account) CheckAccount(accountName string) error {
	if a.Name != accountName {
		return fmt.Errorf("Account name doesn't match %s", accountName)
	}
	fmt.Printf("Account verified %s\n", a.Name)
	return nil
}

// Security Code
// -------------------------------------------------------------------------
type SecurityCode struct {
	code int
}

func NewSecurityCode(code int) *SecurityCode {
	return &SecurityCode{code}
}

func (a *SecurityCode) CheckCode(incomingCode int) error {
	if a.code != incomingCode {
		return fmt.Errorf("Security code does't match %d", incomingCode)
	}
	fmt.Printf("Security code verified %d\n", incomingCode)
	return nil
}

// Wallet
// -------------------------------------------------------------------------
type Wallet struct {
	balance int
}

func NewWallet() *Wallet {
	return &Wallet{balance: 0}
}

func (w *Wallet) creditBalance(incoming int) {
	w.balance += incoming
}

func (w *Wallet) debitBalance(outgoing int) error {
	if w.balance < outgoing {
		return fmt.Errorf("Not enough balance")
	}
	fmt.Println("Debiting balance")
	w.balance -= outgoing
	return nil
}

// Ledger
// -------------------------------------------------------------------------
type LedgerEntry struct {
	accountID string
	txnType   string
	amount    int
}
type Ledger struct {
	entry []*LedgerEntry
}

func (ledger *Ledger) makeEntry(accountID, txnType string, amount int) {
	if ledger.entry == nil {
		ledger.entry = []*LedgerEntry{}
	}
	ledger.entry = append(ledger.entry, &LedgerEntry{
		accountID, txnType, amount,
	})
}

// Notification
// -------------------------------------------------------------------------
type Notification struct{}

func (n *Notification) sendCreditNotif(accountID string, amount int) {
	fmt.Println("Credit notification", accountID, amount)
}

func (n *Notification) sendDebitNotif(accountID string, amount int) {
	fmt.Println("Debit notification", accountID, amount)
}

// =========================================================================

func main() {
	accountID, code := "1234a12", 43421
	wallet := NewWalletFacade(accountID, code)
	err := wallet.AddMoney(accountID, code, 1000)
	if err != nil {
		panic(err)
	}
	err = wallet.DeductMoney(accountID, code, 100)
	if err != nil {
		panic(err)
	}
	fmt.Println("Final balance:", wallet.GetBalance())
}
