# Week 6 — Introduction to Modern JavaFX

Completed work for *Week 6U Self-Paced Guide: Introduction to Modern JavaFX using VS code.pdf*.
A Maven project laid out exactly as the guide specifies; every class compiles and every app has
been run and verified.

## Learning outcomes

1. Create a basic JavaFX application with a window and UI components — **done**
2. Design UI layouts using both programmatic and FXML approaches — **done**
3. Explain the JavaFX architecture and threading model — **done**
4. Build a functional JavaFX application with proper structure — **done**

## Folder map

```text
Week6/
├── pom.xml                                     # JavaFX 21 + javafx-maven-plugin
├── run.sh                                      # no-Maven fallback runner
├── .gitignore                                  # target/, build/, lib/
├── src/main/java/com/example/
│   ├── App.java                                # Activity 1: Stage / Scene / Node
│   ├── LayoutDemo.java                         # Activity 2: HBox, VBox, GridPane, BorderPane
│   ├── LoginController.java                    # Activity 4: @FXML injection + onAction
│   ├── FXMLApp.java                            # Activity 4: FXMLLoader entry point
│   └── DashboardApp.java                       # Activity 5: capstone SaaS dashboard
├── src/main/resources/com/example/
│   └── login.fxml                              # Activity 3: the view, Scene Builder format
├── SELF-CHECK.md                               # per-activity questions, answered
└── SELF-ASSESSMENT.md                           # the Final Self-Assessment
```

## How to run

JavaFX is **not** part of the JDK (it was removed in Java 11), so its jars must come from
somewhere. There are two routes.

**The guide's route — Maven.** `mvn` is not on your `PATH`, but Maven **is** on this machine:
NetBeans ships a copy at `/opt/netbeans-31/java/maven/bin/mvn` (3.9.16), and that is what your
Oracle Java VS Code extension uses to build the project in the background. Put it on the path
for a shell session with:

```bash
export PATH="/opt/netbeans-31/java/maven/bin:$PATH"
```

Then the guide's commands work verbatim (verified — see below):

```bash
mvn clean javafx:run                                          # runs com.example.App
mvn clean javafx:run -Djavafx.mainClass=com.example.LayoutDemo
mvn clean javafx:run -Djavafx.mainClass=com.example.FXMLApp
mvn clean javafx:run -Djavafx.mainClass=com.example.DashboardApp
```

**The shorter route — `run.sh`.** No Maven and no `PATH` fiddling: it downloads the same
JavaFX 21 artifacts into a gitignored `lib/` on first run, then calls `javac`/`java` directly:

```bash
./run.sh App
./run.sh LayoutDemo
./run.sh FXMLApp
./run.sh DashboardApp
```

Scene Builder (Activity 3) is a separate GUI download from
<https://gluonhq.com/products/scene-builder/>. It is only needed to *edit* `login.fxml`
visually — the file here is already in the format Scene Builder reads and writes, so it opens
straight in it, and nothing in the project requires Scene Builder to run.

## JavaFX vs. Swing — the mapping

| Concept | Swing (weeks 3–5) | JavaFX (week 6) |
|---|---|---|
| Window | `JFrame` | `Stage` |
| Window contents | the frame's content pane | `Scene` |
| Component | `JComponent` | `Node` |
| Row / column layout | `BoxLayout`, `FlowLayout` | `HBox`, `VBox` |
| Grid layout | `GridLayout`, `GridBagLayout` | `GridPane` |
| Five-region layout | `BorderLayout` | `BorderPane` |
| Button action | `addActionListener(...)` | `setOnAction(...)` |
| UI thread | Event Dispatch Thread (EDT) | JavaFX Application Thread |
| Scheduling onto the UI thread | `SwingUtilities.invokeLater` | `Platform.runLater` |
| Styling | setter calls (`setBackground`, …) | CSS (`-fx-background-color`, …) |
| Visual designer | NetBeans Matisse (generates Java) | Scene Builder (generates FXML) |

The threading rule is identical in both: all UI mutation happens on one thread, and anything
slow on that thread freezes the window.

## Correction made to the guide's code

| File | Issue | Fix |
|---|---|---|
| `login.fxml` | The listing uses `<Insets>` but imports only `javafx.scene.control.*` and `javafx.scene.layout.*`. `Insets` lives in `javafx.geometry`, so `FXMLLoader.load()` throws `LoadException ... ClassNotFoundException: Insets` at startup | Added `<?import javafx.geometry.*?>` |

Also folded in: the `onAction="#handleLoginButtonAction"` attribute on the `<Button>`. The
guide's printed FXML omits it and Step 4.2 has you add it through Scene Builder's **Code → On
Action** field; without it the login button does nothing.

## Verification performed

- `mvn clean compile` succeeds (all 5 classes), resolving JavaFX 21 from Maven Central — so the
  `pom.xml` is valid as written.
- `mvn javafx:run -Djavafx.mainClass=com.example.FXMLApp` launches the app with no errors,
  confirming the `javafx-maven-plugin` configuration works.
- All five classes also compile standalone against JavaFX 21 on the installed JDK 26.
- `App`, `LayoutDemo`, `FXMLApp` and `DashboardApp` each launch and stay running with no
  exceptions (checked on a virtual display).
- Activity 4 was driven programmatically to confirm the FXML wiring really works: all three
  `@FXML` fields are injected, `onAction` reaches the controller, and the three documented
  branches behave as the guide says —

  ```text
  INJECTED: usernameField=true passwordField=true loginButton=true
  -- empty fields --            Error: Fields cannot be empty.
  -- user / pass --             Attempting login for: user / Login Failed!
  -- admin / password123 --     Attempting login for: admin / Login Successful!
                                fields cleared after success: username='' password=''
  ```

## Summary of activities

| Activity | Time | Outcomes | Real-world connection |
|---|---|---|---|
| 1. First JavaFX window | 45 min | LO1, LO3 | The foundation of every JavaFX app |
| 2. Layouts | 45 min | LO1, LO2 | Responsive, resizable app chrome |
| 3. Scene Builder + FXML | 60 min | LO2, LO4 | Designer/developer split in enterprise teams |
| 4. FXML + Controller | 45 min | LO4 | MVC — the standard enterprise architecture |
| 5. Capstone dashboard | 90 min | LO1–LO4 | AWS, Azure, Stripe, Shopify consoles |
