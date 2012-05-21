package org.jboss.bpm.console.client.navigation.modules;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.IsTreeItem;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.Widget;
import org.drools.guvnor.client.messages.ConstantsCore;
import org.drools.guvnor.client.resources.ImagesCore;
import org.drools.guvnor.client.util.Util;

public abstract class ModulesTreeItemBaseViewImpl
    implements
    ModulesTreeItemBaseView {

    protected static final ConstantsCore constants = GWT.create( ConstantsCore.class );
    protected static final ImagesCore    images    = GWT.create( ImagesCore.class );

    protected final Tree                 tree      = new Tree();
    protected Presenter                  presenter;

    public ModulesTreeItemBaseViewImpl() {
        tree.setStyleName( "guvnor-Tree" );
        tree.setAnimationEnabled( true );

        addSelectionHandler();
    }

    private void addSelectionHandler() {
        tree.addSelectionHandler( new SelectionHandler<TreeItem>() {
            public void onSelection(SelectionEvent<TreeItem> treeItemSelectionEvent) {
                presenter.onModuleSelected(
                        treeItemSelectionEvent.getSelectedItem().getUserObject() );
            }
        } );
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public IsTreeItem addModulesTreeItem() {
        return tree.addItem(
                getTreeHeader() );
    }

    protected abstract SafeHtml getTreeHeader();

    public IsTreeItem addModuleTreeItem(IsTreeItem parentTreeItem,
                                        String moduleName) {
        return parentTreeItem.asTreeItem().addItem( Util.getHeader( images.emptyPackage(),
                                                                    moduleName ) );
    }

    public IsTreeItem addModuleTreeSelectableItem(IsTreeItem parentTreeItem,
                                                  String moduleName) {
        return parentTreeItem.asTreeItem().addItem( Util.getHeader( images.packages(),
                                                                    moduleName ) );
    }

    public Widget asWidget() {
        return tree;
    }
}