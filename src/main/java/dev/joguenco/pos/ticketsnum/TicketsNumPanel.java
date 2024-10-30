package dev.joguenco.pos.ticketsnum;

import com.unicenta.basic.BasicException;
import com.unicenta.data.user.EditorRecord;
import com.unicenta.data.user.ListProviderCreator;
import com.unicenta.data.user.SaveProvider;
import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.panels.JPanelTable2;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author jorgeluis
 */
@Slf4j
public class TicketsNumPanel extends JPanelTable2 {

    private TicketsNumFilter ticketsNumFilter;
    private TicketsNumEditor ticketsNumEditor;

    public TicketsNumPanel() {
    }

    @Override
    protected void init() {

        ticketsNumFilter = new TicketsNumFilter();
        ticketsNumFilter.init(app);
        ticketsNumFilter.addActionListener(new ReloadActionListener());

        DataLogicTicketsNum dataLogicTicketsNum
                = (DataLogicTicketsNum) app
                        .getBean("dev.joguenco.pos.ticketsnum.DataLogicTicketsNum");

        // Load rows of TicketsNum query
        row = dataLogicTicketsNum.getTicketsNumRow();

        // Load data of TicketsNum query
        lpr = new ListProviderCreator(
                dataLogicTicketsNum.getTicketsNumList(),
                ticketsNumFilter
        );

        // Enable update insert and delete in form
        spr = new SaveProvider(
                dataLogicTicketsNum
                        .getTableDefinitionTicketsNum().getUpdateSentence(),
                dataLogicTicketsNum
                        .getTableDefinitionTicketsNum().getInsertSentence(),
                dataLogicTicketsNum
                        .getTableDefinitionTicketsNum().getDeleteSentence()
        );
    }

    @Override
    public Component getFilter() {
        return ticketsNumFilter.getComponent();
    }

    @Override
    public EditorRecord getEditor() {
        return ticketsNumEditor;
    }

    @Override
    public void activate() throws BasicException {

        ticketsNumFilter.activate();
        String peopleId = (String) ticketsNumFilter
                .getComboBoxValModelPeople()
                .getSelectedKey();

        ticketsNumEditor = new TicketsNumEditor(peopleId, dirty);

        super.activate();
    }

    @Override
    public String getTitle() {
        return AppLocal.getIntString("Menu.Series");
    }
    
    private class ReloadActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                TicketsNumPanel.this.bd.actionLoad();
                String peopleId = (String) TicketsNumPanel.this.ticketsNumFilter
                        .getComboBoxValModelPeople()
                        .getSelectedKey();

                TicketsNumPanel.this.ticketsNumEditor.setPeopleId(peopleId);
            } catch (BasicException ex) {
                log.error(ex.getMessage());                
            }
        }
    }
}
