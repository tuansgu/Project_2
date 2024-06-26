package GUI;

import java.awt.Frame;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class UpdateViolationDlg extends java.awt.Dialog {

    private BLL.BLLXuLy xulyBLL;
    private int maXL;
    private int maTV;
    private String hinhthuc;
    private int soTien;

    public UpdateViolationDlg(Frame parent, boolean modal, int maXL, int maTV, String hinhthuc, int soTien) {
        super(parent, modal);
        xulyBLL = new BLL.BLLXuLy();
        initComponents();
        this.maXL = maXL;
        this.maTV = maTV;
        this.soTien = soTien;
        this.hinhthuc = hinhthuc;
        loadHinhThuc(hinhthuc);
        loadMaTV(maTV);
        loadSoTien(soTien);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbMaTV = new javax.swing.JComboBox<>();
        cmbHinhThuc = new javax.swing.JComboBox<>();
        txtSoTien = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cập Nhật Vi Phạm");
        jLabel1.setToolTipText("");

        jLabel2.setText("Mã Thành Viên");

        jLabel3.setText("Hình Thức Xử Lý");

        jLabel4.setText("Số Tiền(Nếu có)");

        cmbMaTV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbHinhThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khóa thé 1 tháng", "Khóa thẻ 2 tháng", "Khóa thẻ vĩnh viễn", "Bồi thường", "Bồi thường và khóa thẻ 1 tháng" }));

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnUpdate.setText("Cập Nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbHinhThuc, 0, 191, Short.MAX_VALUE)
                            .addComponent(cmbMaTV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSoTien)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(cmbMaTV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addComponent(jLabel3))
                    .addComponent(cmbHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtSoTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addComponent(btnUpdate)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    public void loadMaTV(int maTV) {
        String name = "";
        String nameSelectedItem = xulyBLL.getThanhVienById(maTV);
        cmbMaTV.removeAllItems();
        cmbMaTV.addItem(String.valueOf(maTV) + " - " + nameSelectedItem);

        int[] allMaTV = xulyBLL.getAllThanhVienById();
        for (int maTVs : allMaTV) {
            name = xulyBLL.getThanhVienById(maTVs);
            cmbMaTV.addItem(String.valueOf(maTVs) + " - " + name);
        }
        cmbMaTV.setSelectedIndex(0);
    }
    
    public void loadHinhThuc(String hinhthuc) {
        cmbHinhThuc.removeAllItems();

        cmbHinhThuc.addItem(hinhthuc);

        String[] otherOptions = {"Khóa thẻ 1 tháng", "Khóa thẻ 2 tháng", "Khóa thẻ vĩnh viễn", "Bồi thường", "Bồi thường và khóa thẻ 1 tháng"};
        for (String option : otherOptions) {
            // Kiểm tra xem option đã tồn tại trong JComboBox chưa trước khi thêm
            if (!option.equals(hinhthuc)) {
                cmbHinhThuc.addItem(option);
            }
        }
    }

    public void loadSoTien(int soTien) {
        txtSoTien.setText(String.valueOf(soTien));
    }


    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateData(maXL);
    }//GEN-LAST:event_btnUpdateActionPerformed
public void updateData(int maXL) {
        String selectedMaTV = (String) cmbMaTV.getSelectedItem();
        if (selectedMaTV == null || selectedMaTV.equals("Chọn mã thành viên")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã thành viên", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StringTokenizer tokenizer = new StringTokenizer(selectedMaTV, "-");
        // get maTV
        int maTV = Integer.parseInt(tokenizer.nextToken().trim());

        // Get Hinhthucxuly
        String selectedHinhThuc = (String) cmbHinhThuc.getSelectedItem();
        // get soTien
        int soTien;
        int trangthai; // Đặt trạng thái ở đây
        String txtSoTienStr = txtSoTien.getText().trim();
        if (selectedHinhThuc.equals("Bồi thường")) {
            trangthai = 0;
            if (txtSoTienStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền bồi thường", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                soTien = Integer.parseInt(txtSoTienStr);
            }
        } else if (selectedHinhThuc.equals("Bồi thường và khóa thẻ 1 tháng")) {
            trangthai = 1;
            if (txtSoTienStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền bồi thường và khóa thẻ 1 tháng", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                soTien = Integer.parseInt(txtSoTienStr);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hình thức xử lý", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Loại bỏ dòng sau đây vì trangthai đã được gán trong các điều kiện trên
        // trangthai = selectedHinhThuc.equals("Bồi thường") ? 0 : 1;
        if (xulyBLL.updateXuLy(maXL, maTV, selectedHinhThuc, soTien, trangthai)) {
            JOptionPane.showMessageDialog(null, "Sửa vi phạm thành công");
            setVisible(false);
            PanelViolation pn = new PanelViolation();
            pn.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sửa vi phạm thất bại", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    
public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateViolationDlg dialog = new UpdateViolationDlg(new java.awt.Frame(), true, 0, 0, "", 0);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbHinhThuc;
    private javax.swing.JComboBox<String> cmbMaTV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtSoTien;
    // End of variables declaration//GEN-END:variables
}
