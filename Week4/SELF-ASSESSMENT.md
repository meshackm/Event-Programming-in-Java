# Week 4 Final Self-Assessment

## LO1 — Implement `ActionListener` using multiple techniques — 5 / 5

There are four ways to supply the handler, and I used all of them:

1. **Named class** that implements `ActionListener`. Good when the listener gets reused or needs
   its own state.
2. **Anonymous inner class** written inline. This is what most pre-Java 8 code looks like.
3. **Lambda**, `e -> { ... }`. Only works because `ActionListener` has a single abstract method.
   This is my default now.
4. **Method reference**, `ClassName::handler`, if a method with the right signature already
   exists.

All four are in `activity2-listener-techniques/ListenerDemo.java`, and `EventSourceDemo.java`
has one shared listener that works out which button fired using `e.getSource()`.

## LO2 — Handle state changes in checkboxes, radio buttons and text fields — 5 / 5

Checkboxes and radio buttons both use `ItemListener`, and its `itemStateChanged` method gets an
`ItemEvent` that tells you whether the change was `SELECTED` or `DESELECTED`.

Text fields are the odd one out. They use a `DocumentListener`, and you register it on the
document rather than the field itself:
`field.getDocument().addDocumentListener(...)`. I got this wrong the first time and wondered
why nothing fired.

These are all in `activity3-item-and-document-listeners/`: a pizza total that updates live, a
character counter that turns red past 200, and a search box that filters a `JList` as you type.

## LO3 — Differentiate between listener interfaces — 5 / 5

| Component or interaction | Listener |
|---|---|
| `JButton` | `ActionListener` |
| `JCheckBox` | `ItemListener` |
| `JTextField` | `DocumentListener` |
| Mouse hover | `MouseListener` |
| Key press | `KeyListener`, or `InputMap`/`ActionMap` for app-wide shortcuts |

Two things I had to learn the hard way. A `JCheckBox` fires an `ItemEvent` *and* an
`ActionEvent`, so if you register both listeners the handler runs twice per click. And a
`KeyListener` only gets events while its component has focus, which is why `CounterApp` uses
key bindings instead.

## LO4 — Build an interactive Swing application — 5 / 5

**Did the registration form work?** Yes. `activity5-interactive-form/RegistrationForm.java`
checks the name, the email and the password length, and puts any failure in the status label in
red. If everything passes it collects the gender, interests, country and comments into a
confirmation dialog. Clear resets everything including the `ButtonGroup` selection, and Exit asks
before closing.

`activity4-counter-app/CounterApp.java` is the second complete app. State lives in instance
fields, the three handlers keep the count between 0 and 20, the number changes colour at the
limits, and there are keyboard shortcuts on the root pane.

**All four ratings are 4 or 5, so I'd say the Week 4 outcomes are met.**

## Reflection

Week 3 was about making windows look right. This week was about making them do something,
and a listener is the bit that turns a `JButton` from a picture into an actual button. The pattern
behind it doesn't really change: something fires an event, a registered listener gets it, a handler
method deals with it. The four styles from LO1 are just four different ways of writing that one
handler.

The part I hadn't thought about before is that handler code runs *later*, on the Event Dispatch
Thread, long after the method that registered it has finished. That's the reason state has to go in
instance fields instead of locals. It's also why you can't do anything slow in `actionPerformed`,
because the thread running your handler is the same one that repaints the window, so the whole
interface just freezes until you're done.
