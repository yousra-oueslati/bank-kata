package adapter;

import com.financial.bank.acount.management.domain.model.Account;
import com.financial.bank.acount.management.domain.model.Statement;
import com.financial.bank.acount.management.server.adapter.SpringDataAccountRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class SpringDataAccountRepositoryTest {

    Account account;

    @InjectMocks
    @Spy
    SpringDataAccountRepository accountRepository ;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void save() throws Exception {
        exceptionRule.expect(Exception.class);
        accountRepository.save(null);
    }

    @Test
    public void findAccountByNumber() throws Exception {
        exceptionRule.expect(Exception.class);
        account = accountRepository.findAccountByNumber("1123234344444EEEEE");
    }

    @Test
    public void findTransactionsByAccountNumber() throws Exception {
        exceptionRule.expect(Exception.class);
        List<Statement> transactions = accountRepository.findTransactionByAccountNumber(null);

    }

    @Test
    public void saveTransaction() throws Exception {
        exceptionRule.expect(Exception.class);
        accountRepository.saveTransaction(null);
    }
}