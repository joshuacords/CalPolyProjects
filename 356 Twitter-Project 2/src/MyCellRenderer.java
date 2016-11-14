import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class MyCellRenderer extends DefaultListCellRenderer {
	/**
	 *	Bolds non-Users
	 */
	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if(value.getClass() == UserGroupProxy.class){
        	if (((UserGroupProxy)value).isUser() ){// <= put your logic here
                c.setFont(c.getFont().deriveFont(Font.PLAIN));
            } else {
                c.setFont(c.getFont().deriveFont(Font.BOLD));
            }
        }

        return c;
    }

	private static final long serialVersionUID = 1L;

}
