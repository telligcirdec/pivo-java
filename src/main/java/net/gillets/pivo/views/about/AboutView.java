package net.gillets.pivo.views.about;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.gillets.pivo.views.main.MainView;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
@CssImport("./styles/views/about/about-view.css")
public class AboutView extends Div {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -9016244710319439508L;

    public AboutView() {
        setId("about-view");
        add(new Label("Content placeholder"));
    }

}
