# Activity 2 Self-Check -- Listener Techniques

| Style | When to use it |
|---|---|
| Named class | Several components share the listener, or it needs its own state |
| Anonymous inner class | Older code, or when you need the enclosing `this` |
| Lambda | Short handlers. This is what I'd use by default |
| Method reference | The handler already exists as a method |

**Q1: Best style for a button doing a simple action?**

A lambda. It's the shortest and easiest to read.

**Q2: When would you use a named class instead?**

If more than one component needs the same listener, or if the listener has to remember
something between calls.

**Q3: What does `e.getSource()` return?**

Whichever component fired the event. It comes back as `Object`, so you have to cast it before you
can call `JButton` methods on it.

**Q4: What does `e.getActionCommand()` return?**

A `String`. For a button it's the button's text unless you've called `setActionCommand(...)`.

## Deliverables

- [x] `ListenerDemo.java`, all four styles side by side
- [x] `EventSourceDemo.java`, one listener working out which button was clicked
