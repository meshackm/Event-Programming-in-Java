# Week 5 — Advanced Swing Components

Completed work for *Week 5 Self paced Activity.pdf*. Everything here compiles and runs on the
JDK with no external dependencies.

## Learning outcomes

1. Create and use advanced Swing components including lists, tables, and trees — **done**
2. Implement custom renderers for displaying complex data — **done**
3. Create and manage dialogs, menus, and toolbars — **done**
4. Build a multi-window/multi-pane application with navigation — **done**

## Folder map

```text
Week5/
├── activity1-jlist-renderer/
│   └── ContactListDemo.java      # JList + DefaultListCellRenderer, status dots
├── activity2-jtable/
│   └── StudentTableDemo.java     # JTable, editable columns, Add/Delete/Clear, GPA renderer
├── activity3-jtree-tabs/
│   └── FileExplorerDemo.java     # JTree + JTabbedPane in a JSplitPane
├── activity4-menus-dialogs/
│   └── MiniEditor.java           # JMenuBar, JToolBar, JFileChooser, JOptionPane
├── activity5-capstone-file-manager/
│   └── FileManager.java          # capstone: tree + table + menu + toolbar + status bar
├── SELF-ASSESSMENT.md            # the Final Self-Assessment, answered
└── <each activity folder also has its own SELF-CHECK.md>
```

## How to run

```bash
cd activity1-jlist-renderer
javac ContactListDemo.java
java ContactListDemo
```

Or compile everything at once from `Week5/`:

```bash
for d in activity*/; do (cd "$d" && javac *.java); done
```

## Component reference

| Component | Use it for | Model | Customise appearance with |
|---|---|---|---|
| `JList` | A flat list of items | `ListModel` / `DefaultListModel` | `ListCellRenderer` |
| `JTable` | Tabular data, optionally editable | `TableModel` / `DefaultTableModel` | `TableCellRenderer` per column |
| `JTree` | Hierarchical data | `TreeModel` / `DefaultTreeModel` | `TreeCellRenderer` |
| `JTabbedPane` | Grouping views into tabs | — | tab titles / icons |
| `JSplitPane` | Two resizable panes | — | `setDividerLocation` |
| `JMenuBar` | App-wide commands | — | — |
| `JToolBar` | Quick-access commands | — | — |
| `JFileChooser` | Picking files to open or save | — | `FileFilter` |
| `JOptionPane` | Message, confirm, input popups | — | message type constants |

Three rules worth remembering:

- A `JTable` must be wrapped in a `JScrollPane`, or it loses its column header.
- A menu bar is installed with `frame.setJMenuBar(...)`, never `frame.add(...)`.
- A renderer runs once per visible cell per repaint, so it must stay cheap and must not hold
  per-row state — one renderer instance serves every row.

## Corrections made to the guide's code

Three listings in the PDF needed fixing; each is documented in the relevant `SELF-CHECK.md`.

| File | Issue | Fix |
|---|---|---|
| `MiniEditor.java` | `saveAction` calls `saveAsAction.run()` but is declared **before** it — an illegal forward reference to a local variable, so the listing does not compile | Swapped the two declarations; no logic changed |
| `FileManager.java` | `buildFolderTree` eagerly recurses all of `$HOME`; slow, and unbounded through symlinked directories | Added a depth limit (`TREE_DEPTH = 3`) |
| `FileManager.java` | Tree rows show `File.toString()`, i.e. the full absolute path, on every node | Added a `TreeCellRenderer` that shows `file.getName()` |

Two smaller improvements: `FileExplorerDemo` puts its three theme radio buttons in a
`ButtonGroup` (the guide leaves them ungrouped, so all three can be selected at once), and
`StudentTableDemo` routes its delete through `convertRowIndexToModel`.

## Summary of activities

| Activity | Time | Outcomes | Real-world connection |
|---|---|---|---|
| 1. JList + Renderer | 60 min | LO1, LO2 | WhatsApp contacts, Spotify playlists |
| 2. JTable | 75 min | LO1, LO2 | Bank statements, inventory systems |
| 3. JTree + Tabs | 60 min | LO1 | File Explorer, VS Code panels |
| 4. Menus + Dialogs | 75 min | LO3 | Notepad, any desktop app |
| 5. Capstone | 120 min | LO1–LO4 | Windows File Explorer |
