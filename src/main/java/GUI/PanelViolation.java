package GUI;

import BLL.BLLXuLy;
import DAL.xuly;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import static jdk.javadoc.internal.doclets.toolkit.util.DocPath.parent;

public class PanelViolation extends javax.swing.JPanel {

    private BLLXuLy xulyBLL;
    private DefaultTableModel tableModel;
    JPopupMenu popupMenu = new JPopupMenu();
    JMenuItem deleteMenuItem;
    JMenuItem updateMenuItem;

    /**
     * Creates new form GUIViolation
     */
    public PanelViolation() {
        xulyBLL = new BLLXuLy();
        initComponents();
        deleteMenuItem = new JMenuItem("Delete");
        updateMenuItem = new JMenuItem("Update");
        popupMenu.add(deleteMenuItem);
        popupMenu.add(updateMenuItem);

        displayDataInTable();
        deleteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableXuLy.getSelectedRow();
                if (selectedRow != -1) {
                    int maTV = (int) tableXuLy.getValueAt(selectedRow, 1);
                    if (xulyBLL.deleteXuLy(maTV)) {
                        loadDataInTable();
                        JOptionPane.showMessageDialog(null, "Xóa vi phạm thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        updateMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { // Sửa "actionPerfomed" thành "actionPerformed"
                int selectedRow = tableXuLy.getSelectedRow();
                if (selectedRow != -1) {
                    int maXL = (int) tableXuLy.getValueAt(selectedRow, 0);
                    int maTV = (int) tableXuLy.getValueAt(selectedRow, 1);
                    String hinhthuc = (String) tableXuLy.getValueAt(selectedRow, 2); 
                    int soTien = (int) tableXuLy.getValueAt(selectedRow, 3); 
                    UpdateViolationDlg updateDlg = new UpdateViolationDlg(new javax.swing.JFrame(), true, maXL, maTV, hinhthuc, soTien);
                    updateDlg.setVisible(true);
                }
                loadDataInTable();
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableXuLy = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnInsert = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnImport = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        popupMenu1.setLabel("popupMenu1");

        tableXuLy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Xử Lý", "Mã Thành Viên", "Hình Thức Xử Lý", "Số Tiền(Nếu có)", "Ngày Xử Lý", "Trạng Thái"
            }
        ));
        tableXuLy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableXuLyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableXuLy);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("QUẢN LÝ XỬ LÝ VI PHẠM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        btnInsert.setText("Thêm Mới");
        btnInsert.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertMouseClicked(evt);
            }
        });
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnSearch.setText("Tìm Kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnImport.setText("Import");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        jButton1.setText("Reload");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(btnInsert)
                .addGap(59, 59, 59)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSearch)
                .addGap(61, 61, 61)
                .addComponent(btnImport)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImport, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(923, 923, 923))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(314, 314, 314))))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(62, 62, 62)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        InsertViolationDlg insertDlg = new InsertViolationDlg(new javax.swing.JFrame(), true);
        insertDlg.setVisible(true);
        loadDataInTable();
    }//GEN-LAST:event_btnInsertActionPerformed


    private void btnInsertMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertMouseClicked

    }//GEN-LAST:event_btnInsertMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loadDataInTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        searchData();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // Gọi phương thức import từ tệp Excel ở đây
            if (xulyBLL.importFromExcel(selectedFile)) {
                JOptionPane.showMessageDialog(null, "Import thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Import thất bại", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        }
    }//GEN-LAST:event_btnImportActionPerformed

    private void tableXuLyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableXuLyMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tableXuLy.rowAtPoint(evt.getPoint());
            tableXuLy.getSelectionModel().setSelectionInterval(row, row);
            popupMenu.show(tableXuLy, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tableXuLyMouseClicked
    public void displayDataInTable() {
        List<xuly> listXuLy = xulyBLL.getAllXuLy();

        DefaultTableModel model = (DefaultTableModel) tableXuLy.getModel();
        model.setRowCount(0); // Clear the table before adding new data

        for (xuly xl : listXuLy) {
            Object[] row = {
                xl.getMaXL(),
                xl.getMaTV(),
                xl.getHinhThucXL(),
                xl.getSoTien(),
                xl.getNgayXL(),
                xl.getTrangThaiXL(),};
            model.addRow(row);
        }
    }

    public void loadDataInTable() {
        List<xuly> listXuLy = xulyBLL.getAllXuLy();

        DefaultTableModel model = (DefaultTableModel) tableXuLy.getModel();
        model.setRowCount(0); // Clear the table before adding new data

        for (xuly xl : listXuLy) {
            Object[] row = {
                xl.getMaXL(),
                xl.getMaTV(),
                xl.getHinhThucXL(),
                xl.getSoTien(),
                xl.getNgayXL(),
                xl.getTrangThaiXL(),};
            model.addRow(row);
        }
    }

    public void searchData() {
        String inputSearch = txtSearch.getText();
        if (inputSearch.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị cần tìm kiếm", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<xuly> searchResult = xulyBLL.searchData(inputSearch);

        DefaultTableModel model = (DefaultTableModel) tableXuLy.getModel();
        model.setRowCount(0); // Clear the table before adding new data

        for (xuly xl : searchResult) {
            Object[] row = {
                xl.getMaXL(),
                xl.getMaTV(),
                xl.getHinhThucXL(),
                xl.getSoTien(),
                xl.getNgayXL(),
                xl.getTrangThaiXL()
            };
            model.addRow(row);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTable tableXuLy;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
