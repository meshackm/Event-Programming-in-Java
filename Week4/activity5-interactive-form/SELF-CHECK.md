# Activity 5 Self-Check -- Interactive Registration Form

The guide builds this form by dragging components around in NetBeans. I wrote it out by hand
instead, but kept the shape NetBeans would generate: one `initComponents()` method that builds
and positions everything, the three `*ActionPerformed` handlers from Step 5.4, and the variable
names from the Step 5.3 table. So it still opens in NetBeans, and it also runs straight from the
terminal:

```bash
javac RegistrationForm.java && java RegistrationForm
```

**Q1: Why use `.trim()` on the text fields?**

To get rid of spaces around the input. Without it a field with nothing but spaces in it would pass
the `isEmpty()` check, and a name typed as `"  Alice  "` would be saved with the padding on it.

**Q2: What does `passwordField.getPassword()` return?**

A `char[]`, not a `String`. The point is that you can overwrite a `char[]` once you're done with it,
whereas a `String` hangs around in memory until it's collected.

**Q3: Why `else if` for the gender radio buttons instead of separate `if`s?**

The `ButtonGroup` means only one can ever be selected, so once I've found it there's no reason to
keep checking. Using `else if` also makes that obvious to anyone reading the code.

## Tests from Step 5.5

| What I did | What I expected | Worked |
|---|---|---|
| Submitted with no name | "Error: Please enter your name" in red | yes |
| Email with no `@` | "Error: Please enter a valid email" | yes |
| Password under 6 characters | "Error: Password must be at least 6 characters" | yes |
| Filled everything in, then Submit | Dialog listing all the values back | yes |
| Clear | Fields emptied, radio buttons cleared, country back to Kenya | yes |
| Exit | Asks Yes/No, closes on Yes | yes |

## Deliverables

- [x] `RegistrationForm.java` with Submit, Clear and Exit all validating properly
