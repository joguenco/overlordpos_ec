package dev.joguenco.pos.ticketsnumrefund;

import com.unicenta.basic.BasicException;
import com.unicenta.data.gui.ComboBoxValModel;
import com.unicenta.data.user.DirtyManager;
import com.unicenta.data.user.EditorRecord;
import com.unicenta.format.Formats;
import dev.joguenco.error.ErrorMessage;
import dev.joguenco.types.TransctionType;

import java.awt.Component;
import javax.swing.JPanel;

/**
 * @author jorgeluis
 */
public class TicketsNumRefundEditor extends JPanel implements EditorRecord {

    private ComboBoxValModel comboModelTypeTransaction;
    private String peopleId; // Is equal to combobox at the filter screen

    public TicketsNumRefundEditor(String peopleId, DirtyManager dirty) {
        initComponents();

        this.peopleId = peopleId;

        cbxCode.addActionListener(dirty);
        txtId.getDocument().addDocumentListener(dirty);
        txtSerie.getDocument().addDocumentListener(dirty);
        chkStatus.addActionListener(dirty);

        comboModelTypeTransaction = new ComboBoxValModel();
        comboModelTypeTransaction.add(new TransctionType("DV", "Devolución Venta o Nota de Crédito"));        
        comboModelTypeTransaction.add(new TransctionType("DE", "Devolución Entrega"));

        cbxCode.setModel(comboModelTypeTransaction);
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    @Override
    public void writeValueEOF() {
        comboModelTypeTransaction.setSelectedFirst();
        txtId.setText(null);
        txtSerie.setText(null);
        chkStatus.setText(null);

        cbxCode.setEnabled(false);
        txtId.setEnabled(false);
        txtSerie.setEnabled(false);
        chkStatus.setEnabled(false);
    }

    @Override
    public void writeValueInsert() {
        comboModelTypeTransaction.setSelectedFirst();
        txtId.setText(null);
        txtSerie.setText(null);
        chkStatus.setText(null);

        cbxCode.setEnabled(true);
        txtId.setEnabled(true);
        txtSerie.setEnabled(true);
        chkStatus.setEnabled(true);

        cbxCode.requestFocus();
    }

    @Override
    public void writeValueEdit(Object value) {
        Object[] ticketsNumRefund = (Object[]) value;

        comboModelTypeTransaction.setSelectedKey(Formats.STRING.formatValue(ticketsNumRefund[0]));
        txtSerie.setText(Formats.STRING.formatValue(ticketsNumRefund[2]));
        txtId.setText(Formats.INT.formatValue(ticketsNumRefund[3]));

        if (ticketsNumRefund[5].equals("Active")) {
            chkStatus.setSelected(true);
            chkStatus.setText("Activo");
        } else {
            chkStatus.setSelected(false);
            chkStatus.setText("Inactivo");
        }

        lblSerieId.setText(Formats.STRING.formatValue(ticketsNumRefund[2]) + " - "
                + Formats.INT.formatValue(ticketsNumRefund[3]).toString());

        cbxCode.setEnabled(false);
        txtId.setEnabled(true);
        txtSerie.setEnabled(true);
        chkStatus.setEnabled(true);
    }

    @Override
    public void writeValueDelete(Object value) {
        Object[] ticketsNumRefund = (Object[]) value;

        comboModelTypeTransaction.setSelectedKey(Formats.STRING.formatValue(ticketsNumRefund[0]));
        txtSerie.setText(Formats.STRING.formatValue(ticketsNumRefund[2]));
        txtId.setText(Formats.INT.formatValue(ticketsNumRefund[3]));

        if (ticketsNumRefund[5].equals("Active")) {
            chkStatus.setSelected(true);
            chkStatus.setText("Activo");
        } else {
            chkStatus.setSelected(false);
            chkStatus.setText("Inactivo");
        }

        lblSerieId.setText(Formats.STRING.formatValue(ticketsNumRefund[2]) + " - "
                + Formats.INT.formatValue(ticketsNumRefund[3]));

        cbxCode.setEnabled(false);
        txtId.setEnabled(false);
        txtSerie.setEnabled(false);
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

        Object[] ticketsNumRefund = new Object[6];

        ticketsNumRefund[0] = comboModelTypeTransaction.getSelectedKey();
        ticketsNumRefund[1] = peopleId;
        ticketsNumRefund[2] = txtSerie.getText();
        ticketsNumRefund[3] = Formats.INT.parseValue(txtId.getText());
        ticketsNumRefund[4] = "alternative";

        if (chkStatus.isSelected()) {
            ticketsNumRefund[5] = "Active";
        } else {
            ticketsNumRefund[5] = "Inactive";
        }

        return ticketsNumRefund;
    }

    private ErrorMessage validateData() {
        try {
            int number = Integer.parseInt(txtId.getText());

            if (number < 0) {
                return new ErrorMessage("El secuencial debe ser un número mayor a cero");
            }

            return new ErrorMessage();
        } catch (NumberFormatException ex) {
            return new ErrorMessage("El secuencial debe ser un número");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtId = new javax.swing.JTextField();
        txtSerie = new javax.swing.JTextField();
        chkStatus = new javax.swing.JCheckBox();
        lblCode = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblSerie = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        lblSerieId = new javax.swing.JLabel();
        cbxCode = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(640, 420));

        txtId.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtSerie.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        chkStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        chkStatus.setText("Activo / Inactivo");
        chkStatus.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkStatusStateChanged(evt);
            }
        });

        lblCode.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCode.setText("Código");

        lblId.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblId.setText("Secuencial");

        lblSerie.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSerie.setText("Serie");

        lblStatus.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblStatus.setText("Estado");

        lblSerieId.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblSerieId.setText("...");

        cbxCode.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(128, 128, 128)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCode)
                    .addComponent(lblStatus)
                    .addComponent(lblSerie)
                    .addComponent(lblId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkStatus)
                    .addComponent(lblSerieId, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtSerie, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(cbxCode, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(lblSerieId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCode)
                    .addComponent(cbxCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSerie))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblId))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkStatus)
                    .addComponent(lblStatus))
                .addContainerGap(224, Short.MAX_VALUE))
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
    private javax.swing.JComboBox<String> cbxCode;
    private javax.swing.JCheckBox chkStatus;
    private javax.swing.JLabel lblCode;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblSerie;
    private javax.swing.JLabel lblSerieId;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
}
