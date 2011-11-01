/**
 * Copyright (C) 2011 Shaun Johnson, LMXM LLC
 * 
 * This file is part of Universal Task Executer.
 * 
 * Universal Task Executer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * Universal Task Executer is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Universal Task Executer. If not, see <http://www.gnu.org/licenses/>.
 */
package net.lmxm.ute.gui.editors;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import net.lmxm.ute.beans.Configuration;
import net.lmxm.ute.beans.DescribableBean;
import net.lmxm.ute.beans.IdentifiableDomainBean;
import net.lmxm.ute.gui.components.GuiComponentFactory;
import net.lmxm.ute.gui.components.GuiComponentLabel;
import net.lmxm.ute.listeners.ChangeAdapter;
import net.miginfocom.swing.MigLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * The Class AbstractEditor.
 */
public abstract class AbstractEditorPanel extends JPanel {

	/** The Constant BOLD_FONT. */
	private static final Font BOLD_FONT;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractEditorPanel.class);

	/** The Constant SEPARATOR_LABEL_COLOR. */
	private static final Color SEPARATOR_LABEL_COLOR = new Color(0, 70, 213);

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2931881275263682418L;

	static {
		BOLD_FONT = new Font(Font.DIALOG, Font.BOLD, 12);
	}

	/** The action listener. */
	private final ActionListener actionListener;

	/** The content panel. */
	private final JPanel contentPanel;

	/** The description pane. */
	private JScrollPane descriptionPane = null;

	/** The description text area. */
	private JTextArea descriptionTextArea = null;

	/** The http location target combo box. */
	private JComboBox httpLocationTargetComboBox = null;

	/** The subversion repository location target combo box. */
	private JComboBox subversionRepositoryLocationTargetComboBox = null;

	/** The user object. */
	private Object userObject;

	/**
	 * Instantiates a new abstract editor panel.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @param actionListener the action listener
	 */
	public AbstractEditorPanel(final GuiComponentLabel guiComponentLabel, final ActionListener actionListener) {
		super();

		Preconditions.checkNotNull(actionListener, "Action listener may not be null");

		this.actionListener = actionListener;

		// Setup this panel
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder());

		// Setup the toolbar
		final JToolBar toolBar = getToolBar();
		if (toolBar != null) {
			add(toolBar, BorderLayout.NORTH);
		}

		// Setup the content main
		contentPanel = new JPanel(new MigLayout("wrap 2", "[right][fill]"));
		add(contentPanel, BorderLayout.CENTER);

		// Add initial separator label
		addSeparator(contentPanel, guiComponentLabel);
	}

	/**
	 * Adds the checkbox.
	 * 
	 * @param panel the panel
	 * @param checkBox the check box
	 * @param guiComponentLabel the gui component label
	 */
	protected final void addCheckbox(final JPanel panel, final JCheckBox checkBox,
			final GuiComponentLabel guiComponentLabel) {
		final JPanel subPanel = new JPanel(new MigLayout("ins 0", "[left]"));
		subPanel.add(checkBox);
		subPanel.add(createLabel(guiComponentLabel));

		panel.add(subPanel, "skip 1");
	}

	/**
	 * Adds the label.
	 * 
	 * @param panel the panel
	 * @param guiComponentLabel the gui component label
	 */
	protected final void addLabel(final JPanel panel, final GuiComponentLabel guiComponentLabel) {
		panel.add(createLabel(guiComponentLabel), "gapleft 20, top");
	}

	/**
	 * Adds the separator.
	 * 
	 * @param panel the panel
	 * @param guiComponentLabel the gui component label
	 */
	protected final void addSeparator(final JPanel panel, final GuiComponentLabel guiComponentLabel) {
		final JLabel label = createLabel(guiComponentLabel);
		label.setFont(BOLD_FONT);
		label.setForeground(SEPARATOR_LABEL_COLOR);

		panel.add(label, "gapy 10, span, split 2, aligny center");
		panel.add(new JSeparator(), "gapleft rel, gapy 14, growx");
	}

	/**
	 * Creates the default combo box model.
	 * 
	 * @param list the list
	 * @return the combo box model
	 */
	protected final ComboBoxModel createDefaultComboBoxModel(final List<? extends IdentifiableDomainBean> list) {
		if (list == null) {
			return new DefaultComboBoxModel();
		}
		else {
			return new DefaultComboBoxModel(list.toArray());
		}
	}

	/**
	 * Creates the label.
	 * 
	 * @param guiComponentLabel the gui component label
	 * @return the j label
	 */
	protected final JLabel createLabel(final GuiComponentLabel guiComponentLabel) {
		return GuiComponentFactory.createLabel(guiComponentLabel, SwingConstants.LEADING);
	}

	/**
	 * Gets the action listener.
	 * 
	 * @return the action listener
	 */
	protected final ActionListener getActionListener() {
		return actionListener;
	}

	/**
	 * Gets the content panel.
	 * 
	 * @return the content panel
	 */
	protected JPanel getContentPanel() {
		return contentPanel;
	}

	/**
	 * Gets the description pane.
	 * 
	 * @return the description pane
	 */
	protected final JScrollPane getDescriptionPane() {
		if (descriptionPane == null) {
			descriptionPane = new JScrollPane(getDescriptionTextArea());
		}

		return descriptionPane;
	}

	/**
	 * Gets the description text area.
	 * 
	 * @return the description text area
	 */
	protected final JTextArea getDescriptionTextArea() {
		if (descriptionTextArea == null) {
			descriptionTextArea = new JTextArea();
			descriptionTextArea.setColumns(40);
			descriptionTextArea.setRows(5);
			descriptionTextArea.setLineWrap(true);
			descriptionTextArea.setTabSize(4);
			descriptionTextArea.getDocument().addDocumentListener(new ChangeAdapter() {
				@Override
				public void valueChanged(final String newValue) {
					if (getUserObject() instanceof DescribableBean) {
						((DescribableBean) getUserObject()).setDescription(newValue);
					}
				}
			});
		}
		return descriptionTextArea;
	}

	/**
	 * Gets the http location source combo box.
	 * 
	 * @return the http location source combo box
	 */
	protected final JComboBox getHttpLocationSourceComboBox() {
		if (httpLocationTargetComboBox == null) {
			httpLocationTargetComboBox = new JComboBox();
		}

		return httpLocationTargetComboBox;
	}

	/**
	 * Gets the subversion repository location source combo box.
	 * 
	 * @return the subversion repository location source combo box
	 */
	protected final JComboBox getSubversionRepositoryLocationSourceComboBox() {
		if (subversionRepositoryLocationTargetComboBox == null) {
			subversionRepositoryLocationTargetComboBox = new JComboBox();
		}

		return subversionRepositoryLocationTargetComboBox;
	}

	/**
	 * Gets the tool bar.
	 * 
	 * @return the tool bar
	 */
	protected abstract JToolBar getToolBar();

	/**
	 * Gets the user object.
	 * 
	 * @return the user object
	 */
	protected final Object getUserObject() {
		return userObject;
	}

	/**
	 * Initialize.
	 * 
	 * @param configuration the configuration
	 */
	public void initialize(final Configuration configuration) {
		getHttpLocationSourceComboBox().setModel(createDefaultComboBoxModel(configuration.getHttpLocations()));
		getSubversionRepositoryLocationSourceComboBox().setModel(
				createDefaultComboBoxModel(configuration.getSubversionRepositoryLocations()));
	}

	/**
	 * Sets the focus to first input.
	 */
	public abstract void setFocusToFirstInput();

	/**
	 * Sets the selected index.
	 * 
	 * @param comboBox the combo box
	 * @param value the value
	 */
	protected final void setSelectedIndex(final JComboBox comboBox, final Object value) {
		final String prefix = "setSelectedIndex() :";

		LOGGER.debug("{} entered", prefix);

		final int itemCount = comboBox.getItemCount();
		int selectedIndex = -1;

		if (value == null) {
			LOGGER.debug("{} value to select is null, setting index to -1", prefix);
		}
		else {
			LOGGER.debug("{} value={}", prefix, value);

			for (int i = 0; i < itemCount; i++) {
				if (comboBox.getItemAt(i).toString().equals(value.toString())) {
					selectedIndex = i;

					break;
				}
			}
		}

		LOGGER.debug("{} setting index to {}", prefix, selectedIndex);

		comboBox.setSelectedIndex(selectedIndex);

		LOGGER.debug("{} leaving", prefix);
	}

	/**
	 * Sets the user object.
	 * 
	 * @param userObject the new user object
	 */
	protected final void setUserObject(final Object userObject) {
		this.userObject = userObject;
	}
}
