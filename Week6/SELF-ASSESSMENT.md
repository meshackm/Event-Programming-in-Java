# Week 6 Final Self-Assessment

## LO1 — Create a basic JavaFX application

- [x] **I can create a Stage, Scene and root node from memory.**

  It's the same five steps every time, and I numbered the comments in `App.java` to match them:
  create the nodes, put them in a layout pane, wrap the pane in a `Scene`, set the Scene on the
  `Stage` that `start()` hands you, then call `show()`. All `main` does is call `launch(args)`,
  which starts the JavaFX runtime and then calls `start()` on the JavaFX Application Thread.

- [x] **I can run the app using `mvn javafx:run`.**

  The `pom.xml` pulls in `javafx-controls` and `javafx-fxml` and points `javafx-maven-plugin` at
  `com.example.App`, so `mvn clean javafx:run` starts Activity 1 and `-Djavafx.mainClass=...`
  switches to any of the others. I checked both: `mvn clean compile` builds all five classes, and
  `mvn javafx:run -Djavafx.mainClass=com.example.FXMLApp` opens the login window with no
  errors. `mvn` isn't on my `PATH`, but NetBeans ships Maven 3.9.16 at
  `/opt/netbeans-31/java/maven/bin/mvn`, so I used that. I also wrote a `run.sh` that doesn't
  need Maven at all.

## LO2 — Design UI layouts, both programmatically and with FXML

- [x] **I understand the difference between VBox, HBox and BorderPane.**

  `HBox` puts its children in one row and `VBox` in one column. Both take a spacing value in the
  constructor and handle their own alignment and padding. `BorderPane` is a different sort of
  thing: it has five named regions and the center gets whatever space is left over, so you use it
  to frame a whole application rather than to line up a few controls. `GridPane` is the
  two-dimensional one, and remember it's `add(node, column, row)`.

- [x] **I can open Scene Builder, drag components in and save an FXML file.**

  `login.fxml` is in the format Scene Builder reads and writes, with `fx:controller` on the root,
  an `fx:id` on each named control and `onAction` on the button, so it opens in the editor and
  round-trips fine. Having now built a similar layout both ways, I think the trade-off is this:
  writing it in Java is better when the structure is calculated (the 3×3 grid in Activity 2 is a
  loop), and FXML is better when the structure is fixed and someone might want to redesign it
  without reading code.

## LO3 — JavaFX architecture and threading

- [x] **I know the UI thread is called the "JavaFX Application Thread".**

  I checked rather than taking it on trust. `App.java` prints
  `Thread.currentThread().getName()` from inside the button handler and it says
  `JavaFX Application Thread`.

- [x] **I understand why long operations mustn't block it.**

  The thread that runs your event handlers is also the one that lays out and draws the scene
  graph. While your handler is running nothing gets repainted and no input gets processed, so
  the window stops responding. It's exactly the same problem as blocking Swing's EDT. Slow work
  belongs on a background thread, and JavaFX gives you `Task` and `Service` for that, with the
  results handed back through `Platform.runLater`.

## LO4 — Build a functional application

- [x] **I wired an FXML button to a controller method using `onAction`.**

  I confirmed it by driving the controller in code rather than just clicking around. All three
  `@FXML` fields get injected, `onAction="#handleLoginButtonAction"` does reach the controller,
  and the empty-fields, wrong-credentials and `admin`/`password123` paths all behave the way
  the guide says, including the fields clearing after a successful login.

- [x] **I finished the capstone dashboard.**

  `DashboardApp.java` runs: dark top bar, light sidebar, a welcome heading and three coloured
  stat cards all built by the one `createCard` helper.

**All the boxes are checked, so I'd say the Week 6 outcomes are met.**

## Reflection

Most of this week was realising I already knew the concepts under different names. `Stage` is
`JFrame`, `Scene` is the content pane, `Node` is `JComponent`, `setOnAction` is
`addActionListener`, and the JavaFX Application Thread is the EDT with the same warning
attached. Having done weeks 3 to 5 first meant a lot of new vocabulary landed in an afternoon.

Two things were actually new rather than renamed. The first is CSS, which moves styling out of
Java and into the same language used on the web, so restyling doesn't mean recompiling. The
second is FXML, and that's the more interesting one, because it makes the view a data file instead
of code. That's the whole reason Scene Builder can exist. It's also why you need `@FXML` and
matching `fx:id` names: the binding happens through reflection when the file loads, so the
compiler can't check it for you. A typo in an `fx:id` is a `LoadException` at runtime instead of a
compile error, and that's exactly what the missing `javafx.geometry` import in the guide's FXML
turned out to be.
