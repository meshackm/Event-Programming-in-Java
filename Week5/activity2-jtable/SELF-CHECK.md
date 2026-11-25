# Activity 2 Self-Check -- JTable

| Class | What it's for |
|---|---|
| `DefaultTableModel` | Holds the rows. `addRow()`, `removeRow()`, `setValueAt()` |
| `JTable` | Displays whatever is in the model |
| `JScrollPane` | Gives you the column header and the scrollbars |
| `TableCellRenderer` | How a cell looks |
| `TableCellEditor` | How a cell is edited |

**Q1: What's the difference between the `TableModel` and the `JTable`?**

The model holds the data, the column names and the rule about which cells are editable. The
table just draws it. They're linked by events, so changing the model fires a `TableModelEvent`
and the table updates itself. You shouldn't try to keep data in the `JTable`.

**Q2: Why does a `JTable` have to go in a `JScrollPane`?**

The header is a separate component (`JTableHeader`) and the table puts it in the scroll pane's
column header slot. No scroll pane means there's nowhere to put it, so it just doesn't appear,
and there's no viewport to scroll in either.

**Q3: How do you make a column non-editable?**

Override `isCellEditable(int row, int column)` in the model and return `false` for that column. In
mine only columns 1 (Name) and 3 (Grade) return `true`, so ID, Age and GPA are read-only.

## One thing I did differently from the guide

Delete Selected puts the row index through `table.convertRowIndexToModel(...)` before calling
`removeRow`. It makes no difference right now because the two indexes are the same, but as soon
as you add a `RowSorter` they aren't, and deleting by the view index would remove the wrong
student.

## Deliverables

- [x] `StudentTableDemo.java`, editable Name and Grade columns, Add/Delete/Clear, and GPAs
      under 3.0 shown in red
