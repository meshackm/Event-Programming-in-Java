# Activity 3 Self-Check -- ItemListener and DocumentListener

| Component | Listener | Where you'd see it |
|---|---|---|
| `JCheckBox` | `ItemListener` | A "remember me" tickbox |
| `JRadioButton` | `ItemListener` | Picking a payment method |
| `JTextField` | `DocumentListener` | Search suggestions as you type |
| `JComboBox` | `ActionListener` | Choosing a country, which fills in the cities |

**Q1: What event does a `JCheckBox` fire when you toggle it?**

An `ItemEvent`. You handle it with an `ItemListener` and its `itemStateChanged()` method.

**Q2: What's the difference between `ItemListener` and `ActionListener`?**

`ItemListener` fires when the selected state changes. `ActionListener` fires when the user does
something, like a click or pressing Enter. Checkboxes fire both, which caught me out at first:
if you register both listeners on the pizza app, the total gets recalculated twice per click.

**Q3: Why do you have to implement all three `DocumentListener` methods?**

It has three abstract methods, so it isn't a functional interface and you can't use a lambda for it.

**Q4: When does `insertUpdate` fire?**

Any time text goes into the document. Typing, pasting, or calling `setText(...)` from code.

## Deliverables

- [x] `ItemListenerDemo.java`, pizza order with a live total
- [x] `DocumentListenerDemo.java`, character count that goes red past 200
- [x] `LiveSearchDemo.java`, filters a `JList` as you type
