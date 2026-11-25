# Activity 3 Self-Check -- JTree and JTabbedPane

| Piece | What it does |
|---|---|
| `DefaultMutableTreeNode` | One node. "Mutable" means you can add and remove children later |
| `DefaultTreeModel` | Holds the structure and fires events when it changes |
| `getLastSelectedPathComponent()` | The selected node, as an `Object`, so cast it |
| `TreeSelectionListener` | Fires when the selection changes |
| `addTab(title, component)` | Adds a tab holding that component |
| `ChangeListener` | Fires when you switch tabs |

**Q1: What's the difference between `JTree` and `JList`?**

A `JList` is a flat list and you get at items by index. A `JTree` is for data nested inside other
data, like folders, and it has the expand and collapse arrows. You get at its items with a
`TreePath`, which is the chain of nodes from the root down to the one you want.

**Q2: What does `addTab()` do?**

Adds a tab with a title and a component inside it. Only one component is visible at a time, and
clicking a tab swaps which one that is.

**Q3: What is `DefaultMutableTreeNode`?**

The standard implementation of `TreeNode`. It holds a user object, which can be anything (a
`String` here, a `File` in the capstone) plus a list of children.

## Two things I added to the guide's code

- A `ButtonGroup` around the three theme radio buttons. The guide creates them loose, which
  means you can select all three at once.
- A `ChangeListener` on the tabbed pane that writes tab switches into the info area, so the
  `JTabbedPane` half of the activity has something visible happening too.

## Deliverables

- [x] `FileExplorerDemo.java`, tree on the left, three tabs on the right, a divider you can drag,
      and the selection shown below
