# Week 4 — Making Swing Applications Interactive (Event Handling)

Completed work for *Week 4 Self-Paced Activity.pdf*. Everything here compiles and runs on the
JDK with no external dependencies.

## Learning outcomes

1. Implement `ActionListener` for buttons and menu items using multiple techniques — **done**
2. Handle state changes in checkboxes, radio buttons, and text fields — **done**
3. Differentiate between and use appropriate listener interfaces — **done**
4. Build an interactive Swing application with proper event handling — **done**

## Folder map

```text
Week4/
├── activity1-actionlistener-basics/
│   ├── FirstButton.java          # first interactive button (lambda listener)
│   └── ExperimentButton.java     # the Step 1.3 experiments
├── activity2-listener-techniques/
│   ├── ListenerDemo.java         # named class / anonymous / lambda / method reference
│   └── EventSourceDemo.java      # one shared listener + getSource()/getActionCommand()
├── activity3-item-and-document-listeners/
│   ├── ItemListenerDemo.java     # pizza order, live total
│   ├── DocumentListenerDemo.java # live character count, red past 200
│   └── LiveSearchDemo.java       # type-to-filter a JList
├── activity4-counter-app/
│   └── CounterApp.java           # counter + UP/DOWN/R key bindings
├── activity5-interactive-form/
│   └── RegistrationForm.java     # validated registration form (NetBeans-style)
├── activity7-mouse-and-key/
│   ├── DrawingApp.java           # MouseListener + MouseMotionListener
│   └── KeyboardMover.java        # KeyListener
├── SELF-ASSESSMENT.md            # the Final Self-Assessment, answered
└── <each activity folder also has its own SELF-CHECK.md>
```

The setup section of the guide also lists `activity6-complete-app/`, but no Activity 6 content is
specified for it — the guide's "Activity 6" is the mouse/key work and it instructs you to put
those files in `activity7-mouse-and-key/`. That folder is therefore left out rather than filled with
invented work.

## How to run

Each activity folder is self-contained:

```bash
cd activity1-actionlistener-basics
javac FirstButton.java
java FirstButton
```

Or compile everything at once from `Week4/`:

```bash
for d in activity*/; do (cd "$d" && javac *.java); done
```

## Listener reference (Learning Outcome 3)

| Component / interaction | Listener | Handler method(s) |
|---|---|---|
| `JButton`, `JMenuItem` | `ActionListener` | `actionPerformed` |
| `JCheckBox`, `JRadioButton` | `ItemListener` | `itemStateChanged` |
| `JComboBox` | `ActionListener` (or `ItemListener`) | `actionPerformed` |
| `JTextField`, `JTextArea` | `DocumentListener` | `insertUpdate`, `removeUpdate`, `changedUpdate` |
| Mouse click / press / hover | `MouseListener` | `mouseClicked`, `mousePressed`, `mouseEntered`, … |
| Mouse drag / move | `MouseMotionListener` | `mouseDragged`, `mouseMoved` |
| Key press | `KeyListener` | `keyPressed`, `keyTyped`, `keyReleased` |
| Application shortcut | `InputMap` + `ActionMap` | an `Action` |

## Summary of activities

| Activity | Time | Outcomes | Real-world connection |
|---|---|---|---|
| 1. First Button | 45 min | LO1 | Every button in every app |
| 2. Three Techniques | 60 min | LO1 | Converting between legacy and modern code |
| 3. Item + Document | 75 min | LO2, LO3 | Order forms, live search / autocomplete |
| 4. Counter App | 60 min | LO1, LO4 | Inventory counters, clicker games, scoreboards |
| 5. Interactive Form | 90 min | LO1, LO2, LO4 | Enterprise registration and onboarding apps |
| 6. Mouse + Keyboard | 60 min | LO3 | Drawing apps, games |
