# Week 6 Self-Check — all activities

## Activity 1 — Your First JavaFX Window

**Q1: What's the difference between a Stage and a Scene?**

The `Stage` is the actual window, so the title bar, the minimise and maximise buttons and the
window handle the OS knows about. The `Scene` is the content inside it, the root of the node tree.
A Stage shows one Scene at a time, but you can swap the Scene while the window is open, which
is how an app with a single window moves between screens.

**Q2: What's the JavaFX main thread called?**

The **JavaFX Application Thread**. It's JavaFX's version of Swing's Event Dispatch Thread, and the
same rule applies: all the UI work happens on it, and anything slow running there locks up the
window. `App.java` prints the thread name on every click so you can see it:

```text
Button clicked on: JavaFX Application Thread
```

If you need to get work onto it from another thread you use `Platform.runLater(...)`, which is
the equivalent of `SwingUtilities.invokeLater(...)`.

## Activity 2 — Layouts

The guide doesn't give self-check questions for this one. The four panes I used:

| Pane | How it arranges children | Good for |
|---|---|---|
| `HBox` | One row | Toolbars, header bars, rows of buttons |
| `VBox` | One column | Sidebars, stacked form fields |
| `GridPane` | Rows and columns | Forms, keypads, calendar grids |
| `BorderPane` | Top, left, center, right, bottom | The overall frame of the app |

One thing that caught me out: `GridPane.add(node, column, row)` takes the column first, which is
the opposite order to what I expected.

## Activity 3 — Scene Builder and FXML

No self-check questions here either. The three attributes that actually matter:

| Attribute | What it means |
|---|---|
| `fx:controller` | The class this file binds to. Goes on the root element, once |
| `fx:id` | Names an element so the matching controller field gets it |
| `onAction="#method"` | Calls that method on the controller when the control is used |

## Activity 4 — Wiring FXML with a Controller

**Q1: What does `@FXML` do?**

It tells `FXMLLoader` that it's allowed to see this field or method. The loader then puts the
component it built from the FXML into the field, matching the field name against the element's
`fx:id`. So `@FXML private Button loginButton;` gets the element with `fx:id="loginButton"`. The
annotation is also what lets the field stay `private`, since without it only public members could
be bound.

**Q2: Why use FXML instead of just writing it all in Java?**

It keeps the layout and the logic apart. The FXML is the view and someone can redesign it in
Scene Builder without opening any Java. The controller holds the logic and never describes
layout. It also means a layout change shows up as a diff in one XML file rather than being
buried in construction code, which matters if a designer and a developer are both working on
the same screen.

## Activity 5 — Capstone Dashboard

No self-check questions. Two things the capstone does that the earlier activities didn't:

- **Styling with CSS.** `-fx-background-color`, `-fx-background-radius` and so on, instead of the
  individual setter calls Swing needs. The same string could sit in an external `.css` file applied
  with `scene.getStylesheets().add(...)`.
- **A factory method for repeated bits of UI.** `createCard(title, value, colour)` gets called
  three times. It's a bit like a Swing cell renderer in that you describe one piece of UI once and
  stamp it out, except this returns a real node that sticks around instead of being called again
  on every repaint.

## One correction to the guide's code

`login.fxml` as printed uses `<Insets>` but only imports `javafx.scene.control.*` and
`javafx.scene.layout.*`. `Insets` is in `javafx.geometry`, so `FXMLLoader.load()` throws
`LoadException ... ClassNotFoundException: Insets` the moment you start the app. I added
`<?import javafx.geometry.*?>`.

The printed FXML also leaves out `onAction="#handleLoginButtonAction"` on the `<Button>`, since
Step 4.2 has you add it through Scene Builder. Mine has it, because without it the button does
nothing at all.

## Deliverables

- [x] `App.java`, a label and a button, where clicking updates the label and prints the thread
- [x] `LayoutDemo.java`, HBox header, VBox sidebar and a 3×3 GridPane in a BorderPane
- [x] `login.fxml`, a VBox with a label, text field, password field and a wired-up button
- [x] `LoginController.java`, `@FXML` injection, validation and the three outcomes
- [x] `FXMLApp.java`, loads the FXML and shows it
- [x] `DashboardApp.java`, the dashboard with a top bar, sidebar and three stat cards
