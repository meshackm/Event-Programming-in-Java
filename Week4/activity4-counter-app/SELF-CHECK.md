# Activity 4 Self-Check -- Counter Application

**Q1: Why is `count` an instance variable and not a local one?**

Because the handlers don't run until later, long after `createAndShow()` has returned. A local
variable would be gone by then. On top of that, a lambda can only capture a local that's
effectively final, so I wouldn't be able to increment it anyway. A field sticks around as long as
the object does and any handler can change it.

**Q2: What does `doClick()` do?**

It fakes a click on the button, which fires that button's `ActionListener`. That's how the keyboard
shortcuts reuse the button code instead of me writing it out twice.

**Q3: Why check `if (count < MAX_VALUE)` before incrementing?**

So the counter never goes out of range in the first place, rather than going over and being
corrected afterwards.

## Behaviour I checked

- Starts at 0, and `+`, `-` and Reset all update the number and the status line
- At 20, `+` says "Maximum reached!" and the number turns red
- At 0, `-` says "Minimum reached!" and the number turns orange
- `UP`, `DOWN` and `R` do the same thing as the three buttons

## Deliverables

- [x] `CounterApp.java` with the keyboard shortcuts
