# Task 2 – ConcurrentModificationException Analysis

### 1. What is the exact cause of ConcurrentModificationException in Java?

ConcurrentModificationException occurs when a collection is structurally modified while it is being iterated using an Iterator, enhanced for-loop, or similar iteration mechanism, except through the Iterator's own remove() method.

### 2. What code pattern at line 142 most likely triggered this error?

Most likely the code was removing elements from an ArrayList while iterating over it, for example:

for (Transaction txn : transactions) {
    if (shouldRemove(txn)) {
        transactions.remove(txn);
    }
}

This modifies the collection directly during iteration and triggers ConcurrentModificationException.

### 3. Provide the minimal code change (one or two lines) that resolves this safely.

Use the Iterator's remove() method instead of removing from the collection directly:

Iterator<Transaction> itr = transactions.iterator();

while (itr.hasNext()) {
    Transaction txn = itr.next();
    if (shouldRemove(txn)) {
        itr.remove();
    }
}

This safely removes elements during iteration without causing ConcurrentModificationException.
