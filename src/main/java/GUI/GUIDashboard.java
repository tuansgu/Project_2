package GUI;

import BLL.ThongKeBLL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pc
 */
public class GUIDashboard extends javax.swing.JPanel {

    ThongKeBLL thongkeBLL = new ThongKeBLL();
    private DefaultTableModel tableModel;

    public GUIDashboard() {
        initComponents();
        displayDataInTable();
        displayDataInTableXuly();
        dislayDataComboboxKhoa();
        dislayDataComboboxNganh();
        displayDataComboboxThietBi();
        displayDataInThietBiDuocMuon();
        tinhTongThanhVien();
        double tongTienViPham = thongkeBLL.tongTienViPham();
        jLabel10.setText(String.valueOf(tongTienViPham));

        int dangDuocXuLy = thongkeBLL.soLuongDangDuocXuLy();
        jLabel7.setText(String.valueOf(dangDuocXuLy));
        int daDuocXuLy = thongkeBLL.soLuongDaDuocXuLy();
        jLabel8.setText(String.valueOf(daDuocXuLy));

    }

    public void dislayDataComboboxKhoa() {
        List<String> listKhoa = thongkeBLL.getKhoa();
        jComboBoxKhoa.addItem("0-chon khoa");
        if (listKhoa != null) {
            for (String khoa : listKhoa) {
                jComboBoxKhoa.addItem(khoa);
            }
        }
        jComboBoxKhoa.setSelectedIndex(0);
    }

    public void dislayDataComboboxNganh() {
        List<String> listNganh = thongkeBLL.getNganh();
        jComboBoxNganh.addItem("0-chon nganh");
        if (listNganh != null) {
            for (String nganh : listNganh) {
                jComboBoxNganh.addItem(nganh);
            }
        }
        jComboBoxNganh.setSelectedIndex(0);

    }

    public void displayDataInTable() {
        DefaultTableModel model = (DefaultTableModel) jtablejoin.getModel();
        model.setRowCount(0);
        List<Object[]> results = thongkeBLL.getAllJoin();
        for (Object[] result : results) {
            model.addRow(result);
        }
    }

    public void displayDataInThietBiDuocMuon() {
        DefaultTableModel model = (DefaultTableModel) jtableduocmuon.getModel();
        model.setRowCount(0);
        List<Object[]> results = thongkeBLL.getThietBiDuocMuon();
        for (Object[] result : results) {
            String tenThietBi = (String) result[0];
            Date thoiGianMuon = (Date) result[1];
            Object thoiGianTraObject = result[2];
            String thoiGianTra;
            if (thoiGianTraObject != null) {
                thoiGianTra = ((Date) thoiGianTraObject).toString();
            } else {
                thoiGianTra = "Chưa trả";
            }
            model.addRow(new Object[]{tenThietBi, thoiGianMuon, thoiGianTra, result[3]});
        }
        tinhThietBiMuon();
    }

    public void displayDataInTableXuly() {
        DefaultTableModel model = (DefaultTableModel) jtableXuly.getModel();
        model.setRowCount(0);
        List<Object[]> results = thongkeBLL.getAllViPham();
        for (Object[] result : results) {
            String hoTen = (String) result[0];
            String hinhThucXL = (String) result[1];
            int soTien = (int) result[2];
            int trangThaiXL = (int) result[3];
            String trangThaiLabel = (trangThaiXL == 0) ? "Đã xử lý" : "Đang được xử lý";
            model.addRow(new Object[]{hoTen, hinhThucXL, soTien, trangThaiLabel});
        }
    }

    public void displayDataComboboxThietBi() {
        List<String> listThietBi = thongkeBLL.getTenThietBi();
        jComboBoxTenThietBi.addItem("0-chon thiet bi");
        if (listThietBi != null) {
            for (String thietbi : listThietBi) {
                jComboBoxTenThietBi.addItem(thietbi);
            }
        }
        jComboBoxTenThietBi.setSelectedIndex(0);
    }

    private void tinhTongThanhVien() {
        DefaultTableModel model = (DefaultTableModel) jtablejoin.getModel();
        int rowCount = model.getRowCount();
        int tongThanhVien = 0;
        System.out.println("helllooooodfd " + rowCount);
        for (int i = 0; i < rowCount; i++) {
            int soLuong = Integer.parseInt(model.getValueAt(i, 3).toString());
            tongThanhVien += soLuong;
        }
        jLabel20.setText(String.valueOf(tongThanhVien));
    }

    private void tinhThietBiMuon() {
        DefaultTableModel model = (DefaultTableModel) jtableduocmuon.getModel();
        int rowCount = model.getRowCount();
        int soLuongThietBiDuocMuon = 0;
        int soLuongThietBiDangMuon = 0;
        System.out.println("helllooooodfd " + rowCount);
        for (int i = 0; i < rowCount; i++) {
            int soLuong = Integer.parseInt(model.getValueAt(i, 3).toString());
            String thoiGianTra = (String) model.getValueAt(i, 2);
            if ("Chưa trả".equals(thoiGianTra)) {
                soLuongThietBiDangMuon += soLuong;
            }
            soLuongThietBiDuocMuon += soLuong;
        }
        System.out.println("Total quantity: " + soLuongThietBiDuocMuon);
        jLabel18.setText(String.valueOf(soLuongThietBiDuocMuon));
        jLabel16.setText(String.valueOf(soLuongThietBiDangMuon));
    }

    public void getJoinWithThietBi() {

        Date startDate = jDateChooser4.getDate();
        Date endDate = jDateChooser3.getDate();
        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và ngày kết thúc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean isChecked = jCheckBox1.isSelected();
        System.out.println("ischecked" + isChecked);
        String selectedTenThietBi = (String) jComboBoxTenThietBi.getSelectedItem();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedStartDate = sdf.format(startDate);
        String formattedEndDate = sdf.format(endDate);
        DefaultTableModel model = (DefaultTableModel) jtablejoin.getModel();
        model.setRowCount(0);
        try {
            Date parsedStartDate = sdf.parse(formattedStartDate);
            Date parsedEndDate = sdf.parse(formattedEndDate);
            if ("0-chon thiet bi".equals(selectedTenThietBi)) {
                List<Object[]> results = thongkeBLL.getJoinThietBiWithAll(startDate, endDate, null, isChecked);
                displayResultsThietBi(results);
                return;
            }
            if (!"0-chon thiet bi".equals(selectedTenThietBi) && !selectedTenThietBi.isEmpty()) {
                List<Object[]> results = thongkeBLL.getJoinThietBiWithAll(startDate, endDate, selectedTenThietBi, isChecked);
                displayResultsThietBi(results);
                return;
            }
            if (isChecked) {
                List<Object[]> results = thongkeBLL.getJoinThietBiWithAll(startDate, endDate, selectedTenThietBi, true);
                displayResultsThietBi(results);
                return;
            }
        } catch (Exception e) {
        }
        tinhThietBiMuon();
    }

    public void getJoinWithAll() {
        Date startDate = jDateChooser2.getDate();
        Date endDate = jDateChooser1.getDate();
        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và ngày kết thúc.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String selectedKhoa = (String) jComboBoxKhoa.getSelectedItem();
        String selectedNganh = (String) jComboBoxNganh.getSelectedItem();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedStartDate = sdf.format(startDate);
        String formattedEndDate = sdf.format(endDate);
        DefaultTableModel model = (DefaultTableModel) jtablejoin.getModel();
        model.setRowCount(0);
        try {
            Date parsedStartDate = sdf.parse(formattedStartDate);
            Date parsedEndDate = sdf.parse(formattedEndDate);
            if ("0-chon khoa".equals(selectedKhoa) && "0-chon nganh".equals(selectedNganh)) {
                List<Object[]> results = thongkeBLL.getJoinWithAll(parsedStartDate, parsedEndDate, null, null);
                displayResultsThanhVien(results);
                System.out.println("GUI.GUIDashboard.getJoinWithAll()" + results);
                return;
            }
            if ("0-chon khoa".equals(selectedKhoa) && !selectedNganh.isEmpty()) {
                List<Object[]> results = thongkeBLL.getJoinWithAll(parsedStartDate, parsedEndDate, null, selectedNganh);
                System.out.println("result" + results);
                displayResultsThanhVien(results);
                return;
            }
            if ("0-chon nganh".equals(selectedNganh) && !selectedKhoa.isEmpty()) {
                List<Object[]> results = thongkeBLL.getJoinWithAll(parsedStartDate, parsedEndDate, selectedKhoa, null);
                System.out.println("result" + results);
                displayResultsThanhVien(results);
                return;
            }
            if (!("0-chon khoa".equals(selectedKhoa) || "0-chon nganh".equals(selectedNganh))) {
                List<Object[]> results = thongkeBLL.getJoinWithAll(parsedStartDate, parsedEndDate, selectedKhoa, selectedNganh);
                displayResultsThanhVien(results);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void displayResultsThietBi(List<Object[]> results) {
        DefaultTableModel model = (DefaultTableModel) jtableduocmuon.getModel();
        model.setRowCount(0);
        for (Object[] result : results) {
            Object thoiGianTraObject = result[2];
            String thoiGianTra;
            if (thoiGianTraObject != null) {
                thoiGianTra = thoiGianTraObject.toString();
            } else {
                thoiGianTra = "Chưa trả";
            }

            model.addRow(new Object[]{result[0], result[1], thoiGianTra, result[3]});

        }
    }
//model.addRow(result);

    private void displayResultsThanhVien(List<Object[]> results) {
        DefaultTableModel model = (DefaultTableModel) jtablejoin.getModel();
        model.setRowCount(0);

        for (Object[] result : results) {
            model.addRow(result);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jthanhvien = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtablejoin = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jComboBoxNganh = new javax.swing.JComboBox<>();
        jComboBoxKhoa = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jthietbi = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtableduocmuon = new javax.swing.JTable();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxTenThietBi = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jvipham = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtableXuly = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1067, 772));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jthanhvien.setBackground(new java.awt.Color(255, 255, 255));
        jthanhvien.setLayout(new java.awt.BorderLayout());

        jtablejoin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Thời Gian", "Khoa", "Ngành ", "Số Lượng"
            }
        ));
        jScrollPane1.setViewportView(jtablejoin);

        jthanhvien.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jthanhvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 14, 1007, 560));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("Lọc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Từ");

        jLabel2.setText("Đến");

        jLabel3.setText("Khoa");

        jLabel4.setText("Ngành");

        jButton4.setText("Làm Mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel19.setText("Số Lượng Thành Viên");

        jLabel20.setText("jLabel20");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel1)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxKhoa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxNganh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(77, 77, 77)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(90, 90, 90)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel20)
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jLabel19)
                        .addComponent(jLabel20)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jComboBoxNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 596, -1, 110));

        jTabbedPane2.addTab("Thành Viên", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jthietbi.setBackground(new java.awt.Color(255, 255, 255));

        jtableduocmuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên Thiết Bị", "Thời Gian Mượn", "Thời Gian Trả", "Số Lượng"
            }
        ));
        jScrollPane3.setViewportView(jtableduocmuon);

        jLabel12.setText("Từ:");

        jLabel13.setText("Đến:");

        jLabel14.setText("Thiết Bị");

        jButton2.setText("Lọc");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("làm mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Thiết Bị Đang Mượn");

        jLabel15.setText("Thiết Bị Đang Mượn: ");

        jLabel16.setText("jLabel16");

        jLabel17.setText("Thiết Bị Được Mượn:");

        jLabel18.setText("jLabel16");

        javax.swing.GroupLayout jthietbiLayout = new javax.swing.GroupLayout(jthietbi);
        jthietbi.setLayout(jthietbiLayout);
        jthietbiLayout.setHorizontalGroup(
            jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jthietbiLayout.createSequentialGroup()
                .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jthietbiLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jthietbiLayout.createSequentialGroup()
                                .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(jthietbiLayout.createSequentialGroup()
                                        .addComponent(jComboBoxTenThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)
                                        .addComponent(jCheckBox1)))
                                .addGap(64, 64, 64)
                                .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton3)))
                            .addGroup(jthietbiLayout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18))
                            .addGroup(jthietbiLayout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16))))
                    .addGroup(jthietbiLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jthietbiLayout.setVerticalGroup(
            jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jthietbiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addGap(26, 26, 26)
                .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(48, 48, 48)
                .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jthietbiLayout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jthietbiLayout.createSequentialGroup()
                        .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12))
                        .addGap(10, 10, 10)))
                .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jthietbiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxTenThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        jPanel2.add(jthietbi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 1120, 660));

        jLabel11.setText("Thiết Bị Được Mượn");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jTabbedPane2.addTab("Thiết Bị", jPanel2);

        jvipham.setLayout(new java.awt.BorderLayout());

        jtableXuly.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Họ Tên", "Hình Thức Xử Lý", "Số Tiền", "Trạng Thái"
            }
        ));
        jScrollPane2.setViewportView(jtableXuly);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Đang Được Xử Lý:");

        jLabel6.setText("Đã Xử Lý:");

        jLabel7.setText("jLabel7");

        jLabel8.setText("jLabel8");

        jLabel9.setText("Tổng Tiền Bồi Thường Đã Được Xử Lý:");

        jLabel10.setText("jLabel10");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel7)))
                .addGap(91, 91, 91)
                .addComponent(jLabel9)
                .addGap(54, 54, 54)
                .addComponent(jLabel10)
                .addContainerGap(447, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jvipham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1006, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jvipham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Vi Phạm", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        getJoinWithAll();
        tinhTongThanhVien();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        displayDataInTable();
        tinhTongThanhVien();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        getJoinWithThietBi();
        tinhThietBiMuon();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        displayDataInThietBiDuocMuon();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBoxKhoa;
    private javax.swing.JComboBox<String> jComboBoxNganh;
    private javax.swing.JComboBox<String> jComboBoxTenThietBi;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jtableXuly;
    private javax.swing.JTable jtableduocmuon;
    private javax.swing.JTable jtablejoin;
    private javax.swing.JPanel jthanhvien;
    private javax.swing.JPanel jthietbi;
    private javax.swing.JPanel jvipham;
    // End of variables declaration//GEN-END:variables
}
