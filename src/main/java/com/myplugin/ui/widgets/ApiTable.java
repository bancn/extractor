package com.myplugin.ui.widgets;

import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Set;

public class ApiTable extends JBTable {
    private DefaultTableModel model;
    private ResolvedCellRenderer cellRenderer;
    JTableHeader header;
    public ApiTable() {
        String colNames[] = {"API", "行为", "接口", "模块", "风险等级", "源码位置"};
        model = new DefaultTableModel(colNames, 0);
        setModel(model);

        header = getTableHeader();
        setupHeaderRenderer();
        setupContextMenu();
        setupRenderer();
    }
    public void init() {
        setupHeaderRenderer();
    }

    // 添加单行数据
    public void addRow(Object[] rowData) {
        DefaultTableModel model = (DefaultTableModel) this.getModel();
        model.addRow(rowData);
    }

    // 添加多行数据
    public void addRows(Object[][] rowsData) {
        for (Object[] rowData : rowsData) {
            model.addRow(rowData);
        }
    }

    // 清空表格内容
    public void clearTable() {
        model.setRowCount(0);  // 将行数设置为0，即清空表格
    }

    private void setupHeaderRenderer() {
        JTableHeader header = getTableHeader();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                return this;
            }
        };
        header.setDefaultRenderer(headerRenderer);
    }

    private void setupRenderer() {
        cellRenderer = new ResolvedCellRenderer();
        for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    private void setupContextMenu() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem getContentItem = new JMenuItem("获取单元格内容");
        JMenuItem resolveItem = new JMenuItem("已解决");
        JMenuItem unresolvedItem = new JMenuItem("未解决");

        getContentItem.addActionListener(e -> {
            int row = getSelectedRow();
            int col = getSelectedColumn();
            if (row != -1 && col != -1) {
                Object value = getModel().getValueAt(row, col);
                JOptionPane.showMessageDialog(this, "单元格内容: " + value);
            }
        });

        resolveItem.addActionListener(e -> {
            int row = getSelectedRow();
            if (row != -1) {
                cellRenderer.setResolved(row, true);
                repaint();
            }
        });

        unresolvedItem.addActionListener(e -> {
            int row = getSelectedRow();
            if (row != -1) {
                cellRenderer.setResolved(row, false);
                repaint();
            }
        });

        popupMenu.add(getContentItem);
        popupMenu.add(resolveItem);
        popupMenu.add(unresolvedItem);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }

            private void showMenu(MouseEvent e) {
                int r = rowAtPoint(e.getPoint());
                int c = columnAtPoint(e.getPoint());
                if (r >= 0 && c >= 0) {
                    setRowSelectionInterval(r, r);
                    setColumnSelectionInterval(c, c);
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
    }

    class ResolvedCellRenderer extends DefaultTableCellRenderer {
        private final Set<Integer> resolvedRows;

        public ResolvedCellRenderer() {
            resolvedRows = new HashSet<>();
        }

        public void setResolved(int row, boolean isResolved) {
            if (isResolved) {
                resolvedRows.add(row);
            } else {
                resolvedRows.remove(row);
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (resolvedRows.contains(row)) {
                c.setBackground(Color.GREEN);
            } else {
                c.setBackground(Color.WHITE);
            }
            return c;
        }
    }

}
