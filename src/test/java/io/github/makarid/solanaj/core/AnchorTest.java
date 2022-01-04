package io.github.makarid.solanaj.core;

import org.junit.Ignore;
import org.junit.Test;
import io.github.makarid.solanaj.programs.MemoProgram;
import io.github.makarid.solanaj.programs.anchor.AnchorBasicTutorialProgram;
import io.github.makarid.solanaj.rpc.Cluster;
import io.github.makarid.solanaj.rpc.RpcClient;
import io.github.makarid.solanaj.rpc.RpcException;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class AnchorTest extends AccountBasedTest {

  private final RpcClient client = new RpcClient(Cluster.TESTNET);

  /**
   * Calls a testnet Anchor program: (tutorials/basic-0)'s 'initialize" call. Also attaches a memo.
   */
  @Test
  @Ignore
  public void basicInitializeTest() {
    final Account feePayer = testAccount;

    final Transaction transaction = new Transaction();
    transaction.addInstruction(AnchorBasicTutorialProgram.initialize(feePayer));

    transaction.addInstruction(
        MemoProgram.writeUtf8(
            feePayer.getPublicKey(), "I just called an Anchor program from SolanaJ."));

    final List<Account> signers = List.of(feePayer);
    String result = null;
    try {
      result = client.getApi().sendTransaction(transaction, signers, null);
    } catch (RpcException e) {
      e.printStackTrace();
    }

    assertNotNull(result);
  }
}