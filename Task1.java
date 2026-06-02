import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public List<LoanAccount> getOverdueLoans(List<LoanAccount> accounts) {

    // FIX: initialize result list to avoid NullPointerException
    List<LoanAccount> result = new ArrayList<>();

    // FIX: handle null accounts list safely
    if (accounts == null) {
        return result;
    }

    for (LoanAccount account : accounts) {

        // FIX: dueDate may be null for restructured accounts
        if (account.getDueDate() != null
                && account.getDueDate().before(new Date())) {

            if (account.getOutstandingBalance() > 0) {
                result.add(account);
            }
        }
    }

    return result;
}
