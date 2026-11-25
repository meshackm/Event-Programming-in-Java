# Activity 6 Self-Check -- Mouse and Key Listeners

(The guide calls this Activity 6 but tells you to put the files in `activity7-mouse-and-key/`, so
that's the folder name I used.)

**Q1: What's the difference between `MouseListener` and `MouseMotionListener`?**

`MouseListener` deals with one-off things: pressed, released, clicked, entered, exited.
`MouseMotionListener` deals with movement, so `mouseMoved` when no button is down and
`mouseDragged` when one is. A drawing app needs both, one to start a stroke and one to keep it
going.

**Q2: Why use `MouseAdapter` instead of implementing `MouseListener`?**

`MouseListener` has five methods, so implementing it directly means writing three or four empty
ones just to satisfy the compiler. `MouseAdapter` already has empty versions of all of them, so I
only override the ones I actually care about.

**Q3: Why might a `KeyListener` not fire?**

The component it's on needs keyboard focus, and a `JPanel` doesn't have it by default. You need
`setFocusable(true)` and then `requestFocusInWindow()`. For application-wide shortcuts it's
easier to use `InputMap`/`ActionMap` key bindings like I did in Activity 4, since those don't care
about focus at all.

**Q4: What does `e.getKeyCode()` return?**

An `int` for the physical key, which you compare against the `KeyEvent.VK_*` constants.

## Deliverables

- [x] `DrawingApp.java`, click and drag to draw
- [x] `KeyboardMover.java`, arrow keys move the ball and it stops at the edges
