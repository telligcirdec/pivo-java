package net.gillets.pivo.views.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import net.gillets.pivo.views.entity.EntityView;
import net.gillets.pivo.views.pivo.DataExplorerView;
import net.gillets.pivo.views.pivo.PivoViewableRepositoryDiscover;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/main-view.css")
public class MainView extends AppLayout {

    /**
     * Generated Serial Version UID
     */
    private static final long serialVersionUID = 5233844653852624575L;

    private final PivoViewableRepositoryDiscover pivoViewableRepositoryDiscover; 
    private final Tabs menu;
    private H1 viewTitle;

    public MainView(@Autowired PivoViewableRepositoryDiscover pivoViewableRepositoryDiscover) {
        this.pivoViewableRepositoryDiscover = pivoViewableRepositoryDiscover;
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }

    private Component createHeaderContent() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle);
        layout.add(new Image("images/user.svg", "Avatar"));
        return layout;
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/logo.png", "Pivo logo"));
        logoLayout.add(new H1("Pivo"));
        layout.add(logoLayout, menu);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {
        List<Tab> tabs = new ArrayList<>(pivoViewableRepositoryDiscover.getdataExplorerViewMap().size());
        Iterator<Map.Entry<String,DataExplorerView<?,?>>> it = pivoViewableRepositoryDiscover.getdataExplorerViewMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,DataExplorerView<?,?>> pair = it.next();


            //pair.getValue().getPivoViewableRepository().menuTitle()

            String menuTitle = StringUtils.isNotBlank(pair.getValue().getPivoViewableRepository().menuTitle())?pair.getValue().getPivoViewableRepository().menuTitle():pair.getKey();

            Tab tab = createTab(menuTitle, EntityView.class, new RouteParameters("entity",pair.getKey()));
            tabs.add(tab);
        }
        return tabs.toArray(new Tab[]{});
    }

    private static Tab createTab(String text, Class<? extends Component> navigationTarget, RouteParameters routeParameters) {
        final Tab tab = new Tab();
        if(routeParameters != null){
            tab.add(new RouterLink(text, navigationTarget, routeParameters));
        } else { 
            tab.add(new RouterLink(text, navigationTarget));
        }
        ComponentUtil.setData(tab, Class.class, navigationTarget);
        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren()
                .filter(tab -> ComponentUtil.getData(tab, Class.class)
                        .equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        String currentComponentTitle = "";
        PageTitle pageTitleByAnnotation = getContent().getClass().getAnnotation(PageTitle.class);
        if(pageTitleByAnnotation != null){
            currentComponentTitle = pageTitleByAnnotation.value();
        } else if(HasDynamicTitle.class.isAssignableFrom(getContent().getClass())){
                HasDynamicTitle currentComponentHasDynamicTitleClass = HasDynamicTitle.class.cast(getContent());
                currentComponentTitle = currentComponentHasDynamicTitleClass.getPageTitle();            
        }
        return currentComponentTitle;
    }
}
