# Week 7 Self-Check — all activities

## Activity 1 — Event Filters vs. Event Handlers

**Q1: Which phase of propagation does an event filter belong to?**

The **capturing phase**. The event starts at the root of the scene graph and travels down to the
target, running every filter it passes on the way, before any handler gets a look at it.

**Q2: What does `e.consume()` do?**

It marks the event as dealt with and stops it travelling any further. If you call it in a filter the
target never sees the event at all. If you call it in a handler it stops going up to the parents.
It doesn't end your method though, so the rest of your handler still runs.

**Q3: How do you register an event handler for the bubbling phase?**

With `addEventHandler(eventType, handler)`, or with one of the shortcut setters like
`setOnMouseClicked`, `setOnAction` or `setOnKeyPressed`. There is a difference worth knowing:
`addEventHandler` adds to a list so you can have several, while `setOnX` only holds one and
quietly throws away whatever was there before.

### The guide's expected output is wrong

The guide says that clicking the button prints all three lines, 1, 2 and 3. It doesn't. Step 3 never
happens, because **a JavaFX `Button` consumes the `MOUSE_CLICKED` event** once its own
handler has run, so it never carries on up to the root's handler.

I didn't want to just assume I'd made a mistake, so I checked it three ways: firing a synthesised
`MOUSE_CLICKED` at the button, firing the same event at a node that isn't a `Button`, and driving
a real OS-level click with `javafx.scene.robot.Robot` on a virtual display. All three agreed.

So instead of quietly changing the output to match, I left the guide's button exactly as written
and added a second target next to it, a styled `StackPane`, which doesn't consume. Clicking each
one shows both behaviours:

```text
>>> REAL CLICK on the BUTTON:
1. [FILTER] Root intercepted the click!
2. [HANDLER] Button was clicked!          <- stops here, the Button consumed it

>>> REAL CLICK on the BOX:
1. [FILTER] Root intercepted the click!
2. [HANDLER] Box was clicked!
3. [HANDLER] Root saw the click bubbling up!   <- the full round trip
```

I think this is a better thing to learn than what the guide intended. Propagation isn't
guaranteed. Whether an event makes it to a parent depends on whether anything along the way
consumed it, and the built-in controls consume a lot of input as part of doing their job.

## Activity 2 — Mouse and Key Events

**Q1: Which method tracks the mouse moving without a click?**

`setOnMouseMoved()`. The other half of the pair is `setOnMouseDragged()`, which fires for the
same movement but with a button held down. That's how a drawing tool tells the difference
between hovering and painting.

**Q2: Why might a `KeyEvent` not fire when you press a key?**

Because the node doesn't have **focus**. Key events only go to whatever currently has focus, and
a plain `Pane` isn't focusable by default. You have to make it focus-traversable and then actually
give it focus:

```java
root.setFocusTraversable(true);
primaryStage.setOnShown(e -> root.requestFocus());
```

The `setOnShown` part matters. Asking for focus before the window is on screen does nothing,
which took me a few minutes to work out.

**Q3: How do you tell which key was pressed?**

`e.getCode()`, compared against the `KeyCode` enum, so `e.getCode() == KeyCode.UP`. Use
`e.getText()` or `e.getCharacter()` only if you want the character that was typed rather than the
physical key.

### `setFocusable` doesn't exist in JavaFX

The guide's listing calls `root.setFocusable(true)`. That's the AWT and Swing name. A JavaFX
`Node` has no such method, so the file as printed doesn't compile:

```text
cannot find symbol: method setFocusable(boolean)
```

The JavaFX method is `setFocusTraversable(boolean)`, which is what mine uses.

## Activity 3 — Drag and Drop

**Q1: Which handler on the source starts a drag?**

`setOnDragDetected()`. Inside it you call `node.startDragAndDrop(TransferMode...)` to get a
`Dragboard`, put a `ClipboardContent` on it, and then `consume()` the event.

**Q2: What's `event.acceptTransferModes(TransferMode.MOVE)` for in `setOnDragOver`?**

It's the target saying it's willing to take this drag, and on what terms (`MOVE`, `COPY` or
`LINK`). If you don't call it the drop gets rejected: the cursor shows the "no" symbol and
`setOnDragDropped` never runs. You have to decide it per event, which is why the handler
checks `getGestureSource()` and `getDragboard().hasString()` first.

**Q3: How do you get the data out when it's dropped?**

Call `event.getDragboard()` inside `setOnDragDropped`, check what's on it (`db.hasString()`,
`hasFiles()`, `hasImage()` and so on), then read it with `db.getString()`. Finish with
`event.setDropCompleted(success)`, which is what tells the source the drop worked. It's also
what makes a `MOVE` actually finish instead of snapping back.

Which side owns each handler:

| Handler | Side | Job |
|---|---|---|
| `setOnDragDetected` | source | Start the drag, fill the `Dragboard` |
| `setOnDragOver` | target | Accept or refuse the data |
| `setOnDragEntered` | target | Visual feedback, so turning the zone green |
| `setOnDragExited` | target | Undo that feedback |
| `setOnDragDropped` | target | Read the data and report success |

Because the data travels on a `Dragboard`, which is basically the system clipboard for the
duration of the gesture, the same mechanism works between two windows and even for files
dragged in from outside the application.

## Activity 4 — Custom Events

**Q1: Why use a custom event instead of just calling a method?**

To keep things loosely coupled. Whatever fires the event doesn't know who's listening, how
many listeners there are, or what any of them do. In `CustomEventDemo` the button's entire job is
`root.fireEvent(new LoginSuccessEvent(username))`, and the code that updates the label and
clears the field is in a completely separate handler. The real-world version is a checkout button
firing one `OrderPlacedEvent` and the email service, the inventory service and the receipt
printer all reacting on their own, where adding a fourth listener later means not touching the
button.

It's the Observer pattern from Week 2 again, except JavaFX's event system does the registering
and dispatching for you.

**Q2: Which class does a custom event have to extend?**

`javafx.event.Event`.

**Q3: How do you define the type of your event?**

With a `static final EventType<T>` constant:

```java
public static final EventType<LoginSuccessEvent> LOGIN_SUCCESS =
        new EventType<>(Event.ANY, "LOGIN_SUCCESS");
```

The first argument is the parent type, which puts it in the event type hierarchy and is what
makes `addEventHandler(LoginSuccessEvent.LOGIN_SUCCESS, ...)` match it. The class itself then
carries whatever the listeners need, which here is the username behind a getter.

## Deliverables

- [x] `EventPropagationDemo.java`, with a filter, a target handler, a bubbling handler, and the
      extra non-consuming target that shows the full round trip
- [x] `MouseKeyDemo.java`, live pointer readout and arrow-key movement that stops at the edges
- [x] `DragDropDemo.java`, drag source, drop target, green hover feedback, data transferred
- [x] `LoginSuccessEvent.java`, a custom `Event` with its `EventType` and a payload
- [x] `CustomEventDemo.java`, fires the event and handles it in separate code
