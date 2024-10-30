package dev.joguenco.pos.ticketsnumrefund;

import com.unicenta.basic.BasicException;
import com.unicenta.data.user.EditorRecord;
import com.unicenta.data.user.ListProviderCreator;
import com.unicenta.data.user.SaveProvider;
import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.panels.JPanelTable2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jorgeluis
 */
@Slf4j
public class TicketsNumRefundPanel extends JPanelTable2 {

    private TicketsNumRefundFilter ticketsNumRefundFilter;
    private TicketsNumRefundEditor ticketsNumRefundEditor;

    public TicketsNumRefundPanel() {
    }

    @Override
    protected void init() {
        ticketsNumRefundFilter = new TicketsNumRefundFilter();
        ticketsNumRefundFilter.init(app);

        ticketsNumRefundFilter.addActionListener(new ReloadActionListener());

        DataLogicTicketsNumRefund dataLogicTicketsNumRefund
                = (DataLogicTicketsNumRefund) app
                .getBean("dev.joguenco.pos.ticketsnumrefund.DataLogicTicketsNumRefund");

        // Load rows of TicketsNum query
        row = dataLogicTicketsNumRefund.getTicketsNumRefundRow();

        // Load data of TicketsNum query
        lpr = new ListProviderCreator(
                dataLogicTicketsNumRefund.getTicketsNumRefundList(),
                ticketsNumRefundFilter
        );

        // Enable update insert and delete in form
        spr = new SaveProvider(
                dataLogicTicketsNumRefund
                        .getTableDefinitionTicketsNumRefund().getUpdateSentence(),
                dataLogicTicketsNumRefund
                        .getTableDefinitionTicketsNumRefund().getInsertSentence(),
                dataLogicTicketsNumRefund
                        .getTableDefinitionTicketsNumRefund().getDeleteSentence()
        );
    }

    @Override
    public void activate() throws BasicException {
        ticketsNumRefundFilter.activate();
        String peopleId = (String) ticketsNumRefundFilter
                .getComboBoxValModelPeople()
                .getSelectedKey();

        ticketsNumRefundEditor = new TicketsNumRefundEditor(peopleId, dirty);

        super.activate();
    }

    @Override
    public String getTitle() {
        return AppLocal.getIntString("Menu.SeriesRefund");
    }

    @Override
    public EditorRecord getEditor() {
        return ticketsNumRefundEditor;
    }

    @Override
    public Component getFilter() {
        return ticketsNumRefundFilter.getComponent();
    }

    private class ReloadActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                TicketsNumRefundPanel.this.bd.actionLoad();
                String peopleId = (String) TicketsNumRefundPanel.this.ticketsNumRefundFilter
                        .getComboBoxValModelPeople()
                        .getSelectedKey();

                TicketsNumRefundPanel.this.ticketsNumRefundEditor.setPeopleId(peopleId);
            } catch (BasicException ex) {
                log.error(ex.getMessage());
            }
        }
    }
}
