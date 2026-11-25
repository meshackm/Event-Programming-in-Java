# Week 7 — Event Handling in JavaFX

Completed work for *Week 7U Self-Paced Guide: Event Handling in JavaFX.pdf*. A Maven project
in the same shape as Week 6; every class compiles and every behaviour below was verified by
firing real events, not just by launching the windows.

## Learning outcomes

1. Handle various event types in JavaFX including action, key, and mouse events — **done**
2. Differentiate between event filters and event handlers — **done**
3. Implement drag-and-drop functionality in a JavaFX application — **done**
4. Create and fire custom events in JavaFX — **done**

## Folder map

```text
Week7/
├── pom.xml                                     # JavaFX 21 + javafx-maven-plugin
├── run.sh                                      # no-Maven fallback runner
├── .gitignore                                  # target/, build/, lib/
├── src/main/java/com/example/
│   ├── EventPropagationDemo.java               # Activity 1: filters vs handlers
│   ├── MouseKeyDemo.java                       # Activity 2: mouse + key events
│   ├── DragDropDemo.java                       # Activity 3: drag and drop
│   ├── LoginSuccessEvent.java                  # Activity 4: the custom Event class
│   └── CustomEventDemo.java                     # Activity 4: firing and handling it
├── SELF-CHECK.md                               # per-activity questions, answered
└── SELF-ASSESSMENT.md                           # the Final Self-Assessment
```

## How to run

```bash
./run.sh EventPropagationDemo        # no Maven needed; fetches JavaFX into ./lib once
./run.sh MouseKeyDemo
./run.sh DragDropDemo
./run.sh CustomEventDemo
```

Or the guide's route. `mvn` is not on your `PATH`, but NetBeans ships Maven 3.9.16 at
`/opt/netbeans-31/java/maven/bin/mvn` — put it on the path and the guide's commands work
verbatim (`mvn clean compile` on this project is verified passing):

```bash
export PATH="/opt/netbeans-31/java/maven/bin:$PATH"
mvn clean javafx:run                                                  # EventPropagationDemo
mvn clean javafx:run -Djavafx.mainClass=com.example.MouseKeyDemo
```

## The JavaFX event model in one table

| | Filter | Handler |
|---|---|---|
| Phase | Capturing — root **down** to target | Bubbling — target **up** to root |
| Registered with | `addEventFilter(type, h)` | `addEventHandler(type, h)`, or `setOnMouseClicked` / `setOnAction` / … |
| Runs | before the target sees the event | after the target has handled it |
| Typical use | veto, audit, or rewrite an event before it lands | respond to an event |
| Stop propagation | `e.consume()` | `e.consume()` |

Swing has no capturing phase at all — a Swing listener is only ever the handler half. The filter
is what lets a parent intercept input destined for a child, which is the ATM "ignore Cancel once
cash is dispensed" case from the guide.

## Corrections made to the guide's code

| File | Issue | Fix |
|---|---|---|
| `MouseKeyDemo.java` | Calls `root.setFocusable(true)`. **No such method exists on a JavaFX `Node`** — `setFocusable` is the AWT/Swing name, so the listing does not compile (`cannot find symbol: method setFocusable(boolean)`) | Changed to `root.setFocusTraversable(true)`, the JavaFX equivalent |
| `EventPropagationDemo.java` | The guide's stated expected output is `1, 2, 3`. It is actually `1, 2` — a JavaFX `Button` consumes `MOUSE_CLICKED`, so the event never bubbles to the root handler | Kept the guide's button untouched and added a second, non-consuming target (a styled `StackPane`) so a real `1 → 2 → 3` round trip is observable. Documented in `SELF-CHECK.md` |

That second one is a documentation error rather than a code error, and it was worth running
down rather than papering over. It was confirmed three ways: a synthesised `MOUSE_CLICKED`
fired at the button, the same fired at a non-`Button` node, and a genuine OS-level click driven
through `javafx.scene.robot.Robot` on a virtual display. All three agree — the parent's bubbling
handler never runs when the target is a `Button`:

```text
>>> REAL CLICK on the BUTTON:
1. [FILTER] Root intercepted the click!
2. [HANDLER] Button was clicked!
>>> REAL CLICK on the BOX:
1. [FILTER] Root intercepted the click!
2. [HANDLER] Box was clicked!
3. [HANDLER] Root saw the click bubbling up!
```

One cosmetic change: `MouseKeyDemo` formats the pointer position with `%.0f` instead of
concatenating the raw `double`s, so the readout says `X=137, Y=242` rather than
`X=137.0, Y=242.0`.

## Verification performed

Beyond "it launches", each activity's behaviour was exercised programmatically:

| Activity | What was checked | Result |
|---|---|---|
| 1 | Propagation order for a `Button` target and a non-consuming target, via synthetic events **and** a real `Robot` click | `1, 2` for the button; `1, 2, 3` for the box |
| 2 | `MOUSE_MOVED` at (137, 242) updates the readout; `UP, UP, RIGHT` moves the circle by `STEP`; 40× `UP` clamps at the edge | readout `X=137, Y=242`; centre `250,200 → 265,170`; clamped to `y=20` |
| 3 | All five drag handlers registered on the right nodes; drop-zone resting fill | all five present; fill `0xd3d3d3ff` (light grey) |
| 4 | Empty username rejected; `Alice` fires the custom event, which updates the status label and clears the field | `Username cannot be empty.` → `Welcome back, Alice!`, field cleared |

A real drag gesture cannot be synthesised (`startDragAndDrop` needs a live OS gesture), so
Activity 3 was verified by asserting the handler wiring and then exercising the drag by hand.

## Summary of activities

| Activity | Time | Outcomes | Real-world connection |
|---|---|---|---|
| 1. Filters vs handlers | 45 min | LO2 | ATM security interlocks, input vetoing |
| 2. Mouse and key events | 60 min | LO1 | Photo editors, games, CAD |
| 3. Drag and drop | 75 min | LO3 | Trello/Jira cards, file managers |
| 4. Custom events | 60 min | LO4 | E-commerce checkout fan-out |
