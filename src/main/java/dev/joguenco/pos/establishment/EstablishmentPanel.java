package dev.joguenco.pos.establishment;

import com.unicenta.data.gui.ListCellRendererBasic;
import com.unicenta.data.loader.TableDefinition;
import com.unicenta.data.user.EditorRecord;
import com.unicenta.data.user.ListProvider;
import com.unicenta.data.user.ListProviderCreator;
import com.unicenta.data.user.SaveProvider;
import com.unicenta.pos.forms.AppLocal;
import com.unicenta.pos.panels.JPanelTable;
import javax.swing.ListCellRenderer;

/**
 *
 * @author jorgeluis
 */
public class EstablishmentPanel extends JPanelTable {

    private TableDefinition tdEstablishment;
    private EstablishmentEditor establishmentEditor;

    @Override
    protected void init() {
        DataLogicEstablishment dlEstablishment = (DataLogicEstablishment) app.getBean("dev.joguenco.pos.establishment.DataLogicEstablishment");
        tdEstablishment = dlEstablishment.getTableEstablishment();
        establishmentEditor = new EstablishmentEditor(app, dirty);
    }

    @Override
    public EditorRecord getEditor() {
        return establishmentEditor;
    }

    @Override
    public ListProvider getListProvider() {
        return new ListProviderCreator(tdEstablishment);
    }

    @Override
    public SaveProvider getSaveProvider() {
        return new SaveProvider(tdEstablishment);
    }

    @Override
    public String getTitle() {
        return AppLocal.getIntString("Menu.Establishment");
    }

    @Override
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(tdEstablishment.getRenderStringBasic(new int[]{0, 3}));
    }        
}
