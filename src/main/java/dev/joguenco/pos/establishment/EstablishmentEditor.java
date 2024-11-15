package dev.joguenco.pos.establishment;

import com.unicenta.basic.BasicException;
import com.unicenta.data.user.DirtyManager;
import com.unicenta.data.user.EditorRecord;
import com.unicenta.format.Formats;
import com.unicenta.pos.forms.AppView;
import dev.joguenco.error.ErrorMessage;
import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author jorgeluis
 */
public class EstablishmentEditor extends JPanel implements EditorRecord {

    public EstablishmentEditor(AppView app, DirtyManager dirty) {
        initComponents();

        txtId.getDocument().addDocumentListener(dirty);
        txtComercialName.getDocument().addDocumentListener(dirty);
        txtCity.getDocument().addDocumentListener(dirty);
        txtAddress.getDocument().addDocumentListener(dirty);
        txtPhone.getDocument().addDocumentListener(dirty);
        txtEmail.getDocument().addDocumentListener(dirty);
        radMain.addActionListener(dirty);
        radBranchOffice.addActionListener(dirty);
        chkStatus.addActionListener(dirty);
    }

    @Override
    public void writeValueEOF() {
        txtId.setText(null);
        txtComercialName.setText(null);
        txtCity.setText(null);
        txtAddress.setText(null);
        txtPhone.setText(null);
        txtEmail.setText(null);
        chkStatus.setText(null);

        txtId.setEnabled(false);
        txtComercialName.setEnabled(false);
        txtCity.setEnabled(false);
        txtAddress.setEnabled(false);
        txtPhone.setEnabled(false);
        txtEmail.setEnabled(false);
        radMain.setEnabled(false);
        radBranchOffice.setEnabled(false);
        chkStatus.setEnabled(false);
    }

    @Override
    public void writeValueInsert() {
        txtId.setText(null);
        txtComercialName.setText(null);
        txtCity.setText(null);
        txtAddress.setText(null);
        txtPhone.setText(null);
        txtEmail.setText(null);
        chkStatus.setSelected(true);

        txtId.setEnabled(true);
        txtComercialName.setEnabled(true);
        txtCity.setEnabled(true);
        txtAddress.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        radMain.setEnabled(true);
        radBranchOffice.setEnabled(true);
        chkStatus.setEnabled(true);

        txtId.requestFocus();
    }

    @Override
    public void writeValueEdit(Object value) {
        Object[] establishment = (Object[]) value;

        txtId.setText(Formats.STRING.formatValue(establishment[0]));
        txtComercialName.setText(Formats.STRING.formatValue(establishment[1]));
        txtCity.setText(Formats.STRING.formatValue(establishment[2]));
        txtAddress.setText(Formats.STRING.formatValue(establishment[3]));
        txtPhone.setText(Formats.STRING.formatValue(establishment[4]));
        txtEmail.setText(Formats.STRING.formatValue(establishment[5]));

        if (establishment[6].equals("Principal")) {
            radMain.setSelected(true);
        } else {
            radBranchOffice.setSelected(true);
        }

        var status = Boolean.valueOf(Formats.BOOLEAN.formatValue(establishment[7]));
        if (status) {
            chkStatus.setSelected(true);
            chkStatus.setText("Activo");
        } else {
            chkStatus.setSelected(false);
            chkStatus.setText("Inactivo");
        }

        txtId.setEnabled(false);
        txtComercialName.setEnabled(true);
        txtCity.setEnabled(true);
        txtAddress.setEnabled(true);
        txtPhone.setEnabled(true);
        txtEmail.setEnabled(true);
        radMain.setEnabled(true);
        radBranchOffice.setEnabled(true);
        chkStatus.setEnabled(true);
    }

    @Override
    public void writeValueDelete(Object value) {
        Object[] establishment = (Object[]) value;

        txtId.setText(Formats.STRING.formatValue(establishment[0]));
        txtComercialName.setText(Formats.STRING.formatValue(establishment[1]));
        txtCity.setText(Formats.STRING.formatValue(establishment[2]));
        txtAddress.setText(Formats.STRING.formatValue(establishment[3]));
        txtPhone.setText(Formats.STRING.formatValue(establishment[4]));
        txtEmail.setText(Formats.STRING.formatValue(establishment[5]));

        if (establishment[6].equals("Principal")) {
            radMain.setSelected(true);
        } else {
            radBranchOffice.setSelected(true);
        }

        if (establishment[7].equals("Active")) {
            chkStatus.setSelected(true);
        } else {
            chkStatus.setSelected(false);
        }

        txtId.setEnabled(false);
        txtComercialName.setEnabled(false);
        txtCity.setEnabled(false);
        txtAddress.setEnabled(false);
        txtPhone.setEnabled(false);
        txtEmail.setEnabled(false);
        radMain.setEnabled(false);
        radBranchOffice.setEnabled(false);
        chkStatus.setEnabled(false);
    }

    @Override
    public void refresh() {
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public Object createValue() throws BasicException {
        ErrorMessage validate = validateData();

        if (validate.getIsError()) {
            throw new BasicException(validate.getMessage());
        }

        Object[] establishment = new Object[8];

        establishment[0] = txtId.getText().trim();
        establishment[1] = txtComercialName.getText();
        establishment[2] = txtCity.getText();
        establishment[3] = txtAddress.getText();
        establishment[4] = txtPhone.getText();
        establishment[5] = txtEmail.getText();

        if (radMain.isSelected()) {
            establishment[6] = "Principal";
        } else {
            establishment[6] = "BranchOffice";
        }

        if (chkStatus.isSelected()) {
            establishment[7] = true;
        } else {
            establishment[7] = false;
        }

        return establishment;
    }

    private ErrorMessage validateData() {

        if (txtId.getText().trim().isEmpty()) {
            return new ErrorMessage("El código de establecimiento no puede ser vacío");
        }

        if (txtCity.getText().trim().isEmpty()) {
            return new ErrorMessage("La ciudad no puede ser vacío");
        }

        if (txtAddress.getText().trim().isEmpty()) {
            return new ErrorMessage("La dirección no puede ser vacío");
        }

        return new ErrorMessage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        groRadio = new javax.swing.ButtonGroup();
        txtId = new javax.swing.JTextField();
        txtComercialName = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        radMain = new javax.swing.JRadioButton();
        radBranchOffice = new javax.swing.JRadioButton();
        chkStatus = new javax.swing.JCheckBox();
        lblEstablishment = new javax.swing.JLabel();
        lblComercialName = new javax.swing.JLabel();
        lblCity = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();

        txtId.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtComercialName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtCity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtPhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        groRadio.add(radMain);
        radMain.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        radMain.setText("Principal");

        groRadio.add(radBranchOffice);
        radBranchOffice.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        radBranchOffice.setSelected(true);
        radBranchOffice.setText("Sucursal");

        chkStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chkStatus.setSelected(true);
        chkStatus.setText("Activo / Inactivo");
        chkStatus.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkStatusStateChanged(evt);
            }
        });

        lblEstablishment.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblEstablishment.setLabelFor(txtId);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("pos_messages"); // NOI18N
        lblEstablishment.setText(bundle.getString("label.establishment")); // NOI18N
        lblEstablishment.setToolTipText("");

        lblComercialName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblComercialName.setLabelFor(txtComercialName);
        lblComercialName.setText(bundle.getString("label.locationname")); // NOI18N

        lblCity.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCity.setLabelFor(txtCity);
        lblCity.setText(bundle.getString("label.city")); // NOI18N

        lblAddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblAddress.setLabelFor(txtAddress);
        lblAddress.setText("Dirección");

        lblPhone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblPhone.setLabelFor(txtPhone);
        lblPhone.setText(bundle.getString("label.phone")); // NOI18N

        lblEmail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblEmail.setLabelFor(txtEmail);
        lblEmail.setText(bundle.getString("label.email")); // NOI18N

        lblTipo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTipo.setText(bundle.getString("label.type")); // NOI18N

        lblStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblStatus.setLabelFor(chkStatus);
        lblStatus.setText(bundle.getString("label.Status")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblComercialName)
                    .addComponent(lblEstablishment)
                    .addComponent(lblCity)
                    .addComponent(lblAddress)
                    .addComponent(lblTipo)
                    .addComponent(lblStatus)
                    .addComponent(lblPhone)
                    .addComponent(lblEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkStatus)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtComercialName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radMain)
                        .addGap(18, 18, 18)
                        .addComponent(radBranchOffice)))
                .addContainerGap(208, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstablishment))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtComercialName, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblComercialName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(radMain)
                    .addComponent(radBranchOffice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkStatus)
                    .addComponent(lblStatus))
                .addGap(70, 70, 70))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void chkStatusStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkStatusStateChanged
        if (chkStatus.isSelected()) {
            chkStatus.setText("Activo");
        } else {
            chkStatus.setText("Inactivo");
        }
    }//GEN-LAST:event_chkStatusStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkStatus;
    private javax.swing.ButtonGroup groRadio;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblComercialName;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstablishment;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JRadioButton radBranchOffice;
    private javax.swing.JRadioButton radMain;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtComercialName;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
