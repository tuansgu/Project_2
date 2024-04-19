/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BLL.BLLThongTinSD;
import DAL.thietbi;
import DAL.thongtinsd;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author phanq
 */
public class GUIMuonThietBi extends javax.swing.JDialog {

    public int maTV;
    public String hoTen;
    public Date tgVao;
    public Date tgMuon;
    public thongtinsd ttsd = new thongtinsd();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public GUIMuonThietBi(java.awt.Frame parent, boolean modal, thongtinsd ttsd) {
        super(parent, modal);
        initComponents();

        this.ttsd = ttsd;
        this.maTV = ttsd.getMaTV();
        this.hoTen = BLLThongTinSD.layTenThanhVienTheoID(this.ttsd.getMaTV());
        this.tgVao = ttsd.getTgVao();
        jTextField2.setText(maTV + "");
        jTextField3.setText(hoTen);
        jTextField4.setText(dateFormat.format(tgVao));

        jComboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    int maTB = Integer.parseInt(getStringAfterDash(jComboBox1.getSelectedItem().toString()));
                    String moTa = BLLThongTinSD.layMoTaThietBi(maTB);
                    jTextArea1.setText(moTa);
                    boolean isExisting = BLLThongTinSD.checkThietBiDaMuon(maTB);
                    if (isExisting) {
                        jTextField5.setText("Thiết bị đã được mượn");
                    } else {
                        jTextField5.setText("Thiết bị hiện có sẵn");
                    }
                }
            }
        });

        loadDataToCombobox();
        customTable();
    }

    public GUIMuonThietBi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    public void customTable() {
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void onDelete(int row) {
                if (jTable1.isEditing()) {
                    jTable1.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.removeRow(row);
            }

            @Override
            public void onView(int row) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

        };
        jTable1.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender1());
        jTable1.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor1(event));
    }

    public void loadDataToCombobox() {
        try {
            List<thietbi> list = BLLThongTinSD.layDanhSachThietBi();
            for (int i = 0; i < list.size(); i++) {

                jComboBox1.addItem(list.get(i).getMaTB() + " - " + list.get(i).getTenTB());
            }
            jComboBox1.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        int maTB = Integer.parseInt(getStringAfterDash(jComboBox1.getSelectedItem().toString()));
                        String moTa = BLLThongTinSD.layMoTaThietBi(maTB);
                        jTextArea1.setText(moTa);
                        boolean isExisting = BLLThongTinSD.checkThietBiDaMuon(maTB);
                        if (isExisting) {
                            jTextField5.setText("Thiết bị đã được mượn");
                        } else {
                            jTextField5.setText("Thiết bị hiện có sẵn");
                        }
                    }
                }
            });

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public String getStringAfterDash(String input) {
        int index = input.indexOf("- ");
        if (index != -1) {
            return input.substring(0, index).trim();
        }
        return input;
    }

    public Object[] getObjectAfterDash(String input) {
        int index = input.indexOf("- ");
        if (index != -1) {
            return (Object[]) (Object) input.substring(0, index).trim();
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnGuiDi = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jCheckBox1 = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(221, 238, 220));

        jLabel2.setBackground(new java.awt.Color(102, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(131, 95, 95));
        jLabel2.setText("Mượn thiết bị mới");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã thành viên", "Họ và tên", "Mã thiết bị", "Thiết bị", "Thời gian vào", "Thời gian đặt chỗ", "Hành động"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(50);
        jScrollPane1.setViewportView(jTable1);

        btnThem.setBackground(new java.awt.Color(51, 102, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnGuiDi.setBackground(new java.awt.Color(51, 204, 0));
        btnGuiDi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuiDi.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiDi.setText("Gửi đi");
        btnGuiDi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiDiActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(255, 0, 0));
        btnHuy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mã thành viên");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Họ và tên");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Chọn thiết bị");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Thời gian vào");

        jTextField2.setEditable(false);

        jTextField3.setEditable(false);

        jTextField4.setEditable(false);

        jDateChooser1.setDateFormatString("yyyy-MM-dd");

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setText("Đặt chỗ");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Mô ta thiết bị");

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText("Thiết bị hiện đang có sẵn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnThem)
                            .addGap(67, 67, 67)
                            .addComponent(btnGuiDi)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnHuy))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel8))
                            .addGap(36, 36, 36)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addGap(31, 31, 31)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addComponent(jCheckBox1))
                            .addGap(38, 38, 38)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 687, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnGuiDi)
                            .addComponent(btnHuy)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addDataToTable();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnGuiDiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiDiActionPerformed
        addDataToDatabase();
    }//GEN-LAST:event_btnGuiDiActionPerformed

    public void addDataToDatabase() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int rowCount = model.getRowCount();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp ngayXL = new Timestamp(System.currentTimeMillis());
        boolean isSuccess = true;
        try {
            if (jTable1.getRowCount() == 0) {
                return;
            }
            for (int row = 0; row < rowCount; row++) {
                Object check = model.getValueAt(row, 5);
                try {
                    if (check == null) {

                        int maTV = Integer.parseInt(model.getValueAt(row, 0).toString());
                        int maTB = Integer.parseInt(model.getValueAt(row, 2).toString());
                        Date tgVao = dateFormat.parse(model.getValueAt(row, 4).toString());

                        Date tgMuon = ngayXL;
                        Date tgTra = null;
                        Date tgDatCho = null;

                        thongtinsd ttsd = new thongtinsd();
                        String tenTV = BLLThongTinSD.layTenThanhVien(maTV).getHoTen();
                        String tenTB = BLLThongTinSD.layTenThietBi(maTB).getTenTB();

                        boolean isExisting = BLLThongTinSD.checkExistingData(maTV, maTB);
                        if (isExisting) {
                            JOptionPane.showMessageDialog(this, tenTV + " đã mượn " + tenTB + " mã " + maTB, "Error", JOptionPane.INFORMATION_MESSAGE);
                            model.setRowCount(0);
                            return;
                        }
                        ttsd.setMaTV(maTV);
                        ttsd.setMaTB(maTB);
                        ttsd.setTgVao(tgVao);
                        ttsd.setTgMuon(tgMuon);
                        ttsd.setTgTra(tgTra);
                        ttsd.setTgDatCho(tgDatCho);

                        if (BLLThongTinSD.themThongTinSD(ttsd)) {
                            isSuccess = true;
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm thất bại", "Error", JOptionPane.ERROR_MESSAGE);
                            isSuccess = false;
                        }

                    } else {
                        // có đặt chỗ
                        int maTV = Integer.parseInt(model.getValueAt(row, 0).toString());
                        int maTB = Integer.parseInt(model.getValueAt(row, 2).toString());
                        Date tgVao = dateFormat.parse(model.getValueAt(row, 4).toString());
                        Date tgDatCho = dateFormat.parse(model.getValueAt(row, 5).toString());

                        Date tgMuon = null;
                        Date tgTra = null;

                        thongtinsd ttsd = new thongtinsd();
                        String tenTV = BLLThongTinSD.layTenThanhVien(maTV).getHoTen();
                        String tenTB = BLLThongTinSD.layTenThietBi(maTB).getTenTB();

                        boolean isExisting = BLLThongTinSD.checkExistingData(maTV, maTB);
                        if (isExisting) {
                            JOptionPane.showMessageDialog(this, tenTV + " đã mượn " + tenTB + " mã " + maTB, "Error", JOptionPane.INFORMATION_MESSAGE);
                            model.setRowCount(0);
                            return;
                        }
                        ttsd.setMaTV(maTV);
                        ttsd.setMaTB(maTB);
                        ttsd.setTgVao(tgVao);
                        ttsd.setTgMuon(tgMuon);
                        ttsd.setTgTra(tgTra);
                        ttsd.setTgDatCho(tgDatCho);

                        System.out.println("Thoi gian vao " + tgVao);
                        System.out.println("Thoi gian dat cho " + tgDatCho);

                        if (BLLThongTinSD.themThongTinSD(ttsd)) {
                            isSuccess = true;
                        } else {
                            JOptionPane.showMessageDialog(this, "Thêm thất bại", "Error", JOptionPane.ERROR_MESSAGE);
                            isSuccess = false;
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    System.err.println("Lỗi thêm mới");
                }

            }
            if (isSuccess) {
                JOptionPane.showMessageDialog(rootPane, "Thêm thành công");
                model.setRowCount(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addDataToTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            String maTV = jTextField2.getText();
            String hoTen = jTextField3.getText();
            String thietBi = BLLThongTinSD.layTenThietBi(Integer.parseInt(getStringAfterDash(jComboBox1.getSelectedItem().toString()))).getTenTB();
            String maTB = getStringAfterDash(jComboBox1.getSelectedItem().toString());
            String tgVao = jTextField4.getText();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Object[] columnMaTV = getColumnMaThanhVien(jTable1);
            Object[] columnMaTB = getColumnMaThietBi(jTable1);

            boolean isMaTV = false;
            boolean isMaTB = false;
            for (Object data : columnMaTV) {
                if (data != null && data.toString().equals(maTV)) {
                    isMaTV = true;
                    break;
                }
            }
            for (Object data : columnMaTB) {
                if (data != null && data.toString().equals(maTB)) {
                    isMaTB = true;
                    break;
                }
            }

            if (isMaTV && isMaTB) {
                JOptionPane.showMessageDialog(null, "Đã có trong danh sách", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean isExisting = BLLThongTinSD.checkThietBiDaMuon(Integer.parseInt(maTB));
            if (isExisting) {
                JOptionPane.showMessageDialog(this, "Thiết bị đã được mượn", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // Kiểm tra đã check vào ô đặt chỗ thiết bị hay chưa
            boolean isChecked = jCheckBox1.isSelected();

            if (isChecked) {
                if (jDateChooser1.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày đặt chỗ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String tgDatCho = dateFormat.format(jDateChooser1.getDate());
                Date selectedDate = jDateChooser1.getDate();
                Date currentDate = new Date();
                Timestamp ngayXL = new Timestamp(System.currentTimeMillis());
                if (selectedDate.before(ngayXL)) {
                    JOptionPane.showMessageDialog(null, "Thời gian không hợp lệ", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                tableModel.addRow(new Object[]{maTV, hoTen, maTB, thietBi, tgVao, tgDatCho});
            } else {
                DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                tableModel.addRow(new Object[]{maTV, hoTen, maTB, thietBi, tgVao});
            }

        } catch (Exception e) {
            System.err.println(e);
        }

    }

    public static Object[] getColumnMaThanhVien(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        Object[] columnData = new Object[rowCount];

        for (int i = 0; i < rowCount; i++) {
            columnData[i] = model.getValueAt(i, 0);
        }

        return columnData;
    }

    public static Object[] getColumnMaThietBi(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        Object[] columnData = new Object[rowCount];

        for (int i = 0; i < rowCount; i++) {
            columnData[i] = model.getValueAt(i, 2);
        }

        return columnData;
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void layTenThanhVien(int id) {
        String hoTen = BLLThongTinSD.layTenThanhVien(id).getHoTen();
        System.out.println(hoTen);
        jTextField3.setText(hoTen);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUIMuonThietBi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIMuonThietBi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIMuonThietBi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIMuonThietBi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new GUIMuonThietBi().setVisible(true);
                GUIMuonThietBi dialog = new GUIMuonThietBi(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuiDi;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThem;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
