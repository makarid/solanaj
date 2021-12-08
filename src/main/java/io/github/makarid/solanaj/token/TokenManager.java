package io.github.makarid.solanaj.token;

import io.github.makarid.solanaj.programs.MemoProgram;
import io.github.makarid.solanaj.programs.TokenProgram;
import io.github.makarid.solanaj.core.Account;
import io.github.makarid.solanaj.core.PublicKey;
import io.github.makarid.solanaj.core.Transaction;
import io.github.makarid.solanaj.rpc.RpcClient;
import io.github.makarid.solanaj.rpc.RpcException;

/** Manager class for calling {@link TokenProgram}-related APIs */
public class TokenManager {

  private final RpcClient client;

  public TokenManager(final RpcClient client) {
    this.client = client;
  }

  public String transfer(
      final Account owner,
      final PublicKey source,
      final PublicKey destination,
      final PublicKey tokenMint,
      long amount) {
    final Transaction transaction = new Transaction();

    // SPL token instruction
    transaction.addInstruction(
        TokenProgram.transfer(source, destination, amount, owner.getPublicKey()));

    // Memo
    transaction.addInstruction(MemoProgram.writeUtf8(owner.getPublicKey(), "Hello from SolanaJ"));

    // Call sendTransaction
    String result = null;
    try {
      result = client.getApi().sendTransaction(transaction, owner);
    } catch (RpcException e) {
      e.printStackTrace();
    }

    return result;
  }

  public String transferCheckedToSolAddress(
      final Account owner,
      final PublicKey source,
      final PublicKey destination,
      final PublicKey tokenMint,
      long amount,
      byte decimals) {
    // getTokenAccountsByOwner
    PublicKey tokenAccount = null;

    try {
      tokenAccount = client.getApi().getTokenAccountsByOwner(destination, tokenMint);
    } catch (RpcException e) {
      e.printStackTrace();
    }

    final Transaction transaction = new Transaction();
    // SPL token instruction
    transaction.addInstruction(
        TokenProgram.transferChecked(
            source, tokenAccount, amount, decimals, owner.getPublicKey(), tokenMint));

    // Memo
    transaction.addInstruction(MemoProgram.writeUtf8(owner.getPublicKey(), "Hello from SolanaJ"));

    // Call sendTransaction
    String result = null;
    try {
      result = client.getApi().sendTransaction(transaction, owner);
    } catch (RpcException e) {
      e.printStackTrace();
    }

    return result;
  }

  public String initializeAccount(Account newAccount, PublicKey usdcTokenMint, Account owner) {
    final Transaction transaction = new Transaction();

    // SPL token instruction
    transaction.addInstruction(
        TokenProgram.initializeAccount(
            newAccount.getPublicKey(), usdcTokenMint, owner.getPublicKey()));

    // Call sendTransaction
    String result = null;
    try {
      result = client.getApi().sendTransaction(transaction, owner);
    } catch (RpcException e) {
      e.printStackTrace();
    }

    return result;
  }
}
