# Assignment 5 — Part A: Persistence Design & Pseudocode

## A1 — Bus-pass file format
**Delimited text layout:** `passId,regNo,name,routeNo,boardingStop,validityDate,amountPaid`. Escape comma and backslash in fields or use a quoted CSV parser; never split blindly if fields may contain commas.  
**Binary layout:** write each field in a documented order using `DataOutputStream`: UTF passId, UTF regNo, UTF name, int routeNo, UTF boardingStop, UTF validityDate, double amountPaid.  
**Operations:** append with `BufferedWriter`; find and list with `BufferedReader` line scans. Validate field count and numeric fields; skip/log damaged rows. Missing file should be reported distinctly. Use temp-file write plus atomic rename for safer replacement after a power failure.  
**Complexity:** text scan find O(n); fixed-record RandomAccessFile seek O(1) only when record index is known. Fixed-width padding wastes space proportional to unused field capacity.

## A2 — Buffered I/O
A 50 MiB file read byte-by-byte causes roughly 52,428,800 read calls at the Java API level; buffered reads amortize calls across a buffer (e.g. 8 KiB, about 6,400 buffer fills). Both are O(N) time; streaming buffer memory O(B). `Files.readAllLines` is O(N) time and O(N) memory, appropriate only when the file fits comfortably in memory.

## A3 — DAO pattern
Repeated steps: open connection, prepare/execute SQL, map ResultSet, close resources. Varying parts: table, SQL, key and mapper. `Dao<T,K>` exposes insert, findById, findAll, update, delete; `StudentDao` implements it with prepared statements. Student fields map to student table columns; `reg_no` is the primary key. DAO centralizes schema changes; asymptotic per-operation time/space is unchanged.

## A4 — Serialization
Mark live `Connection` and password transient; never persist credentials. The reachable serializable object graph (Hostel → Rooms → Residents) is traversed recursively. Add `serialVersionUID` and define migration/default handling for new phoneNumber; matching UID permits compatible evolution but new field defaults to null/zero unless handled. CSV/JSON are more portable and schema-visible but require explicit mapping.

## A5 — Transactions and injection
Set autoCommit(false); debit source wallet; credit destination wallet; commit only if both succeed; rollback on any exception. String concatenation permits crafted input such as `' OR '1'='1` to alter SQL logic. Use `PreparedStatement("SELECT ... WHERE id=? AND pwd=?")` and bind values. One transaction protects atomicity; two auto-commit statements can leave a debit without credit.

