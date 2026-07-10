# Exercise: File I/O

Practice core Java file input/output using the `java.io` package. You will
create a text file, write records to it, append more records, read them back,
compare absolute and relative paths, and finally delete the file.

## Goal

Implement the six stubbed methods in
`src/main/java/com/rti/fileio/FileIoExercise.java`. Each has a `// TODO:`
comment describing what it should do. When all six are complete, running the
program end-to-end should:

1. **Create** a file named `student_data.txt` (report created vs. already-exists).
2. **Write** these rows (overwrite mode):
   - `Alice, A`
   - `Bob, B`
   - `Charlie, A+`
3. **Append** these rows (append mode, do not overwrite):
   - `David, B+`
   - `Eva, A`
4. **Read** the file line by line and print each line.
5. **Compare paths** — print the file's absolute path and its relative path.
6. **Delete** the file and report whether the deletion succeeded.

## Hints

- Use the `File` class for create/exists/delete.
- Use `BufferedWriter` wrapping a `FileWriter` for writing; pass `true` as the
  second `FileWriter` argument to open in **append** mode.
- Use `BufferedReader` wrapping a `FileReader` to read line by line.
- Wrap every stream in a **try-with-resources** block so it closes
  automatically, even if an error occurs.
- Handle `IOException` — file operations can fail (missing file, permissions).

## Build & Run

```bash
# Compile
mvn compile

# Run the program
mvn exec:java

# (Optional) run the tests
mvn test
```

The `student_data.txt` file is created in the directory you run Maven from
(the project root). It is deleted again by the final step, so a clean run
leaves no file behind.

## Reflection

After you have it working, think about:

1. Why prefer a relative path over an absolute path in a Java program?
2. What can go wrong if you skip try-with-resources for file I/O?
3. When would you append to a file instead of overwriting it?
4. What real-world scenarios rely on file I/O?
