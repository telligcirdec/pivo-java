package net.gillets.pivo.views.helloworld;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.gillets.pivo.views.main.MainView;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "hello", layout = MainView.class)
@PageTitle("Hello World")
@CssImport("./styles/views/helloworld/hello-world-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class HelloWorldView extends Div {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = -2180708756969409285L;

    public HelloWorldView() {
        setId("hello-world-view");
        add(new Label("Content placeholder"));
    }

}
