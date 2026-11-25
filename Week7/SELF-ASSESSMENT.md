# Week 7 Final Self-Assessment

## LO1 — Handle action, key and mouse events

- [x] **I can use `setOnMouseMoved` to track X and Y coordinates.**

  `MouseKeyDemo` updates a `Text` node from `e.getX()` and `e.getY()` every time the pointer
  moves. I checked it by firing a `MOUSE_MOVED` at (137, 242) and reading back
  `Mouse Coordinates: X=137, Y=242`.

- [x] **I can use `setOnKeyPressed` and `KeyCode` to move something.**

  The same app moves a `Circle` by `STEP` for each arrow key and keeps it inside the window. I
  checked that too: `UP, UP, RIGHT` moves the centre from `250,200` to `265,170`, and holding
  `UP` for 40 more presses stops it at `y=20` rather than letting it leave the window.

  The part that actually gives you trouble here isn't the key handling, it's focus. You need
  `setFocusTraversable(true)` and then a `requestFocus()` once the stage is showing. (The
  guide's `setFocusable(true)` doesn't even compile, since that's a Swing method name.)

## LO2 — Tell the difference between filters and handlers

- [x] **I understand that filters capture top-down and handlers bubble bottom-up.**

  A filter added with `addEventFilter` runs in the capturing phase, root down to target. A handler
  added with `addEventHandler`, or with `setOnMouseClicked` and friends, runs in the bubbling
  phase, target back up to root. Swing doesn't have a capturing phase at all, so this is one of the
  few things this term that wasn't just a Swing idea with a new name on it.

- [x] **I know how to use `e.consume()` to stop propagation.**

  Consuming in a filter means the target never sees the event, which is the guide's ATM example
  where Cancel has to stop working once the cash is out. Consuming in a handler stops it
  reaching the parents.

  Working on this outcome is where I found the mistake in the guide. It says clicking the button
  prints steps 1, 2 and 3, but it only prints 1 and 2, because a JavaFX `Button` consumes
  `MOUSE_CLICKED` itself. I confirmed it with a synthesised event, with the same event fired at a
  node that isn't a `Button`, and with a real OS-level click through `javafx.scene.robot.Robot`.
  `EventPropagationDemo` now keeps the guide's button and puts a non-consuming box next to it
  so you can see both in one run. The lesson I took from it is more useful than the one the guide
  was going for: propagation is never guaranteed, because built-in controls consume input as part
  of their normal behaviour.

## LO3 — Implement drag and drop

- [x] **I can make a drag source with `setOnDragDetected` and a `Dragboard`.**

  The source in `DragDropDemo` calls `startDragAndDrop(TransferMode.MOVE)`, puts a string into
  a `ClipboardContent`, sets that on the `Dragboard` and consumes the event.

- [x] **I can make a drop target with `setOnDragOver` and `setOnDragDropped`.**

  The target accepts the transfer mode in `setOnDragOver`, which it has to do or the drop is
  refused outright. It turns green and back again in `setOnDragEntered` and `setOnDragExited`,
  reads `db.getString()` in `setOnDragDropped`, and then calls `setDropCompleted(success)`.

  I confirmed all five handlers were registered on the right nodes, then tested the actual drag by
  hand, because `startDragAndDrop` needs a real OS drag gesture and can't be faked in code.

## LO4 — Create and fire custom events

- [x] **I can write a custom `Event` class with its own `EventType`.**

  `LoginSuccessEvent extends javafx.event.Event`, with
  `static final EventType<LoginSuccessEvent> LOGIN_SUCCESS = new EventType<>(Event.ANY, "LOGIN_SUCCESS")`
  and a `username` payload behind a getter.

- [x] **I can fire it with `.fireEvent()` and listen with `.addEventHandler()`.**

  Checked: with the field empty the button says `Username cannot be empty.`, and with `Alice`
  typed in it fires the event, the separate handler prints
  `Custom Event Received! User logged in: Alice`, the label changes to `Welcome back, Alice!` and
  the field clears.

**All the boxes are checked, so I'd say the Week 7 outcomes are met.**

## Reflection

Week 4 taught me that something fires an event and a registered listener responds to it. This
week adds that in JavaFX the event also travels, and the journey turns out to be the interesting
bit. It goes down from the root to the target past every filter, then climbs back up past every
handler, and anything on that route can stop it with `consume()`. Swing only ever showed me the
second half of that trip.

That mechanism is what the rest of the week rests on. Drag-and-drop isn't some special feature
bolted onto the framework, it's five ordinary handlers split between a source and a target with a
`Dragboard` carrying the data, which is why it works between windows and between separate
applications without any extra effort. Custom events are the same idea turned around: if the
framework will route any `Event` subclass for me, then my own events can use the same system,
and a button can announce that a login succeeded without knowing anything about who cares.

The most useful hour of the week was one I hadn't planned on. The guide promises that a click on
a button bubbles all the way back up to the root, and it doesn't, because `Button` eats the event.
Checking that with a real click instead of just believing the handout is what turned "events go
down and then up" from a diagram I'd memorised into something where I actually know the
limits.
